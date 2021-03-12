package com.example.readingassistant.screens.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.readingassistant.R;
import com.example.readingassistant.screens.allBook.AddBookFragment;
import com.example.readingassistant.screens.allBook.AllBookFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private FloatingActionButton addBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        mToolbar = findViewById(R.id.main_toolbar);

        setSupportActionBar(mToolbar);

        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.miAll:
                            selectedFragment = new AllBookFragment();
                            break;
                        case R.id.miPresence:
                            break;
                        case R.id.miReading:

                            break;
                        case R.id.miEnd:

                            break;
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.allBookFragment, selectedFragment).addToBackStack(null).commit();
                    return true;
                }
            });

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.miAll)
                .setDrawerLayout(drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        addBookButton = findViewById(R.id.addBookButton);

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addBookFragment = new AddBookFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.allBookFragment, addBookFragment, addBookFragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem saveItem = menu.findItem(R.id.app_bar_save);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        saveItem.setVisible(false);
        searchItem.setVisible(true);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}