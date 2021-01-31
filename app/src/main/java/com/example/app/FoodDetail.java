package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.Adapter.PopularAdapter;
import com.example.app.Adapter.RelatedAdapter;
import com.example.app.Object.cart;
import com.example.app.SQL.SQL;
import com.example.app.model.Popular;
import com.example.app.model.Recommended;

import java.util.ArrayList;
import java.util.List;

public class FoodDetail extends AppCompatActivity {

    ImageView imageView;
    TextView itemName, itemPrice,itemRating;
    RecyclerView recyclerView;
    private List<Recommended> recommendedList=new ArrayList<>();
    private List<Popular> populars=new ArrayList<>();

    int id;
    String name, price, rating, imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.pet_details);
        Intent intent = getIntent();

        Log.d("btn", intent.getStringExtra("id"));
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        rating = intent.getStringExtra("rating");
        imageUrl = intent.getStringExtra("image");


        imageView = findViewById(R.id.imgDetais);
        itemName = findViewById(R.id.txtNamedetals);
        itemPrice = findViewById(R.id.txtPriceDe);
        itemRating = findViewById(R.id.txtRating);
//
        int id = this.getResources().getIdentifier(imageUrl, "drawable", this.getPackageName());
        imageView.setImageDrawable(this.getResources().getDrawable(id));
        SQL sql= new SQL(FoodDetail.this);
        populars=sql.getAllPopular();
        recommendedList=sql.getRecoment();
        itemName.setText(name);
        itemPrice.setText(price+"$");
        itemRating.setText(rating);
        recyclerView= findViewById(R.id.relative);
        RelatedAdapter relatedAdapter=new RelatedAdapter(this,populars);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(relatedAdapter);
//        itemRating.setText(rating);
//        ratingBar.setRating(Float.parseFloat(rating));

        Button btn=findViewById(R.id.btnOder);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cart cart=new cart(Integer.valueOf(intent.getStringExtra("id")),1);
                sql.addCart(cart);
                Log.d("Dat hang","thanh cong");
            }
        });

        Button btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                findNavController().navigate(R.id.action_fragmentC_to_fragmentB, null, null);
            }
        });
    }
}
