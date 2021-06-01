package com.example.edemappactivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    public SwitchCompat darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        toggleDrawer();
        initializeDefaultFragment(savedInstanceState,0);
        setDarkModeSwitchListener();
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        frameLayout = findViewById(R.id.framelayout_id);
        navigationView = findViewById(R.id.navigationview_id);
        navigationView.setNavigationItemSelectedListener(this);
        darkModeSwitch = (SwitchCompat)navigationView.getMenu().findItem(R.id.nav_darkmode_id).getActionView();
    }

    private void toggleDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initializeDefaultFragment(Bundle savedInstanceState, int itemIndex) {
        if (savedInstanceState == null){
            MenuItem menuItem = navigationView.getMenu().getItem(itemIndex).setChecked(true);
            onNavigationItemSelected(menuItem);
        }
    }

    private void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new home())
                        .commit();
                closeDrawer();
                break;
            case R.id.prof:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new profile())
                        .commit();
                closeDrawer();
                break;
            case R.id.btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new buttonFrag())
                        .commit();
                closeDrawer();
                break;
            case R.id.chip:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new chipfrag())
                        .commit();
                closeDrawer();
                break;
            case R.id.check:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,new checkboxfrag())
                        .commit();
                closeDrawer();
                break;
            case R.id.radios:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,new radiofrag())
                        .commit();
                closeDrawer();
                break;
            case R.id.toggles:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,new togglefrag())
                        .commit();
                closeDrawer();
                break;
            case R.id.CRUD:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,new crudFrag())
                        .commit();
                closeDrawer();
                break;
        }
        return true;
    }
    private void setDarkModeSwitchListener(){
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });
    }
}