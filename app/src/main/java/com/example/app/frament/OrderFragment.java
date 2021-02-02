package com.example.app.frament;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.OderAdapter;
import com.example.app.Adapter.RecomentAdapter;
import com.example.app.LoginFrom;
import com.example.app.Object.cart;
import com.example.app.R;
import com.example.app.Regist;
import com.example.app.model.Popular;
import com.example.app.model.Recommended;
import com.example.app.tabnavi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    View v;
    RecyclerView recyclerViewOder;
    private List<cart> cartList = tabnavi.sql.getCart();
    private List<Recommended> recommendedList=new ArrayList<>();
    TextView Search;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    public OrderFragment(List<cart> cartList) {
        // Required empty public constructor
        this.cartList=cartList;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String textSearch;
        v=inflater.inflate(R.layout.fragment_oder, container, false);
        recyclerViewOder=v.findViewById(R.id.reSearch);
        Search=v.findViewById(R.id.IpSearch);
        textSearch=Search.getText().toString();
        Button btdk =v.findViewById(R.id.btnSearch);
        Log.d("hirnthij ", textSearch);
        btdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recommendedList=tabnavi.sql.getSearchRecoment(textSearch);
                RecomentAdapter recomentAdapter=new RecomentAdapter(getContext(),recommendedList);
                recyclerViewOder.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerViewOder.setAdapter(recomentAdapter);
            }
        });

        return v;
    }
}