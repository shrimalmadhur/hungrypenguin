package com.example.archanaiyer.hungrypenguin.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.data.DishData;
import com.example.archanaiyer.hungrypenguin.util.DetailEventHandler;

import com.example.archanaiyer.hungrypenguin.model.Dish;
import com.example.archanaiyer.hungrypenguin.adapter.DishesAdapter;
import com.example.archanaiyer.hungrypenguin.util.PrefsEventHandler;
import com.example.archanaiyer.hungrypenguin.util.SharedPrefsHelper;

import java.util.List;


public class DishListFragment extends Fragment implements PrefsEventHandler, DetailEventHandler {
    private static final String ARG_PARAM1 = "page";
    private List<Dish> dishes;
    private RecyclerView dishesRV;
    private DishesAdapter dishesAdapter;
    private SharedPrefsHelper prefsHelper;

    private int mpage;

    public static DishListFragment newInstance(int page) {
        DishListFragment fragment = new DishListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page);
        fragment.setArguments(args);
        return fragment;
    }

    public DishListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prefsHelper = new SharedPrefsHelper(getContext());
            mpage = getArguments().getInt(ARG_PARAM1);
            dishes = DishData.getDishesData().get(mpage);
            dishesAdapter = new DishesAdapter(dishes, prefsHelper, this, this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dish_list, container, false);
        dishesRV = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        dishesRV.setLayoutManager(llm);
        dishesRV.setAdapter(dishesAdapter);
        return view;
    }

    @Override
    public void showState() {
        Toast.makeText(getContext(), prefsHelper.getState(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotoDetail(int id) {
//        Intent i = new Intent(getActivity(), FoodDetailActivity.class);
//        i.putExtra("id", id);
//        startActivity(i);
    }
}
