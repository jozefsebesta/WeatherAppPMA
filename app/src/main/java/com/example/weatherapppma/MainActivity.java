package com.example.weatherapppma;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.weatherapppma.ui.AboutFragment;
import com.example.weatherapppma.ui.FileWriter;
import com.example.weatherapppma.ui.HomeFragment;
import com.example.weatherapppma.ui.UserInputFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    public static MainActivity mainActivity;
    ListView listView;

    public HomeFragment homeFragment = new HomeFragment();
    UserInputFragment userInputFragment = new UserInputFragment();
    AboutFragment aboutFragment = new AboutFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileWriter.Init(this);

        String content = FileWriter.Read();
        String[] lines = content.split("\n");
        for(int i = 0; i< lines.length; i++)
        {
            if(!lines[i].equals(""))
                homeFragment.AddItem(lines[i],false);
        }

        setContentView(R.layout.activity_main);
        mainActivity = this;

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(homeFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle(R.string.title_home);
                    fragment = homeFragment;
                    break;

                case R.id.navigation_userinput:
                    setTitle(R.string.title_userinput);
                    fragment = userInputFragment;
                    break;

                case R.id.navigation_about:
                    setTitle(R.string.title_about);
                    fragment = aboutFragment;
                    break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }

        //Fragment f = this.findViewById(R.id.fragment);
        return false;
    }

}
