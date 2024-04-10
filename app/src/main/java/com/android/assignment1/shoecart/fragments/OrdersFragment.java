package com.android.assignment1.shoecart.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.adapters.OrdersAdapter;
import com.android.assignment1.shoecart.databinding.FragmentOrdersBinding;
import com.android.assignment1.shoecart.interfaces.AdapterInterface;
import com.android.assignment1.shoecart.models.Orders;
import com.android.assignment1.shoecart.models.Product;

import java.util.ArrayList;


public class OrdersFragment extends Fragment implements AdapterInterface<Orders> {


    FragmentOrdersBinding binding;
    ArrayList<Orders> ordersList = new ArrayList<>();
    ArrayList<Product> productArrayList = new ArrayList<>();

    OrdersAdapter adapter;
    LinearLayoutManager layoutManager;

    public OrdersFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater,container,false);
        productArrayList = new ArrayList<>();
        ordersList = new ArrayList<>();
        productArrayList.add(new Product("3",new ArrayList<>(),"Nike 01", 20.0,"Men shoe","Xl",3));
        productArrayList.add(new Product("1",new ArrayList<>(),"Nike 03", 20.0,"women shoe","Xl",2));
        productArrayList.add(new Product("2",new ArrayList<>(),"Nike 02", 10.0,"kids shoe","Xl",7));

        ordersList.add(new Orders(productArrayList,"Arrived","23/01/2024","3","139 jackson","card"));
        ordersList.add(new Orders(productArrayList,"Arrived","23/01/2024","3","139 jackson","cash"));
        ordersList.add(new Orders(productArrayList,"Arrived","23/01/2024","3","139 jackson","card"));
        ordersList.add(new Orders(productArrayList,"Arrived","23/01/2024","3","139 jackson","cash"));
        ordersList.add(new Orders(productArrayList,"Arrived","23/01/2024","3","139 jackson","card"));

        setData();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void setData(){
        adapter = new OrdersAdapter(ordersList,this);
        layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        binding.rvOrders.setLayoutManager(layoutManager);
        binding.rvOrders.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(Orders data, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", data);
//        moving to add fragment
        Fragment fragment = new OrderDetailsFragment();
        //passing arguments
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frames, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}