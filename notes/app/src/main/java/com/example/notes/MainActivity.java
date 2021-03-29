package com.example.notes;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{

    private static int indexNode = 0;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                initFragmentPort();
            }else {
                initFragmentLand(indexNode);
            }
        }if (savedInstanceState != null) {
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                initFragmentPort();
            }else {
                indexNode = savedInstanceState.getInt(NotesListFragment.KEY_BUNDL, 0);
                initFragmentLand(indexNode);
            }
        }

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_about) {
                Snackbar.make(findViewById(R.id.drawer_layout), "About", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(findViewById(R.id.drawer_layout), "Settings", Snackbar.LENGTH_SHORT).show();
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
        toggle.syncState();


    }

    private void initFragmentPort() {
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragment.beginTransaction();
        NotesListFragment notesListFragment = new NotesListFragment();
        fragmentTransaction.replace(R.id.fragment_notes, notesListFragment);
        fragmentTransaction.commit();
    }

    private void initFragmentLand(int indexNode) {
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragment.beginTransaction();
        NotesListFragment notesListFragment = new NotesListFragment();
        TextNodeFragment textNodeFragment = TextNodeFragment.newInstance(indexNode);
        fragmentTransaction.replace(R.id.fragment_notes, notesListFragment);
        fragmentTransaction.replace(R.id.text_node_fragment_land, textNodeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem search = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) search.getActionView();
        MenuItem sort = menu.findItem(R.id.menu_sort);
        MenuItem addPhoto = menu.findItem(R.id.menu_add_photo);
        MenuItem send = menu.findItem(R.id.menu_send);
        send.setOnMenuItemClickListener(this);
        addPhoto.setOnMenuItemClickListener(this);
        sort.setOnMenuItemClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(findViewById(R.id.drawer_layout), query, Snackbar.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Snackbar.make(findViewById(R.id.drawer_layout), item.getTitle().toString(), Snackbar.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}