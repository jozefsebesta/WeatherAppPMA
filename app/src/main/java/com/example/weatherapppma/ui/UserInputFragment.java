package com.example.weatherapppma.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapppma.MainActivity;
import com.example.weatherapppma.R;
import com.example.weatherapppma.WeatherAPIRequest;


public class UserInputFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_userinput, container, false);
        final Button button = view.findViewById(R.id.button2);
        final TextView textbox = view.findViewById(R.id.textbox);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                if(!textbox.getText().toString().equals("")) {
                    String a = WeatherAPIRequest.GetRequest(textbox.getText().toString());
                    String[] b = a.split(",");
                    MainActivity.mainActivity.homeFragment.AddItem(a);
                }
                else
                    Toast.makeText(view.getContext(),"Nezadal si input",Toast.LENGTH_LONG).show();
            }
        });
        return view;




    }
}