package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.app.Adapter.OderAdapter;
import com.example.app.Object.cart;
import com.example.app.SQL.SQL;
import com.example.app.model.Recommended;

import java.util.List;

public class CartProduct extends AppCompatActivity {

    RecyclerView recyclerViewOder;

    TextView itemPrice, itemQuantity;
    SQL sql= new SQL(CartProduct.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_cart_product);


        List<cart> cartListsqlCart=sql.getCart();
        List<Recommended> recommendedListsqlCart=sql.getRecoment();
        int totalPrice=0;
        int total=0;
        for (int i=0;i<cartListsqlCart.size();i++) {
            int quantity = cartListsqlCart.get(i).getQuanlity();
            int price = Integer.parseInt(recommendedListsqlCart.get(cartListsqlCart.get(i).getIdPro() - 1).getPrice());
            total+=quantity;
            totalPrice += quantity * price;
            Log.d("i:", String.valueOf(totalPrice));
        }

        itemQuantity=findViewById((R.id.txtTotalCart));
        itemPrice = findViewById(R.id.txtTotalPrice);
        itemPrice.setText(String.valueOf(totalPrice)+ " $");
        itemQuantity.setText(String.valueOf(total));
        recyclerViewOder=findViewById(R.id.recyCartProduct);

        OderAdapter oderAdapter=new OderAdapter(this,cartListsqlCart,recommendedListsqlCart);
        recyclerViewOder.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOder.setAdapter(oderAdapter);

//        Button btnBackCart=findViewById(R.id.btnBackCart);
//        btnBackCart.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                SQL sql1= new SQL(CartProduct.this);
//                sql1.delete(position);
//            }
//        });


    }
}