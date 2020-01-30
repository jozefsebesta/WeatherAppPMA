package com.example.weatherapppma.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weatherapppma.MainActivity;
import com.example.weatherapppma.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class HomeFragment extends Fragment {
    public ListView listView;
    ArrayAdapter arrayAdapter = null;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView=(ListView)view.findViewById(R.id.listview);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub

                RemoveItem(pos, true);

                return true;
            }
        });
        try {
            arrayAdapter = new ArrayAdapter(MainActivity.mainActivity, android.R.layout.select_dialog_item, arrayList);
        }
        catch(Exception e)
        {
            Log.println(Log.ERROR,"tag",e.getMessage());
        }
        listView.setAdapter(arrayAdapter);
        return view;
    }

    public void AddItem(String text, Boolean writeToFile)
    {
        if(arrayAdapter == null)
            arrayList.add(text);
        else
            arrayAdapter.add(text);

        if(writeToFile)
            FileWriter.Write(text);
    }

    public void RemoveItem(int pos, Boolean removeFromFile)
    {
        if(arrayAdapter == null)
            arrayList.remove(pos);
        else {

            arrayAdapter.remove(arrayAdapter.getItem(pos));
            listView.invalidate();
            Toast.makeText(listView.getContext(),"Odstranil si prvok zo Zoznamu",Toast.LENGTH_LONG).show();
        }

        if(removeFromFile)
            FileWriter.RemoveLineFromFile(pos);

    }

}
