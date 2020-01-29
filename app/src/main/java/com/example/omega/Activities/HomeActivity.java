package com.example.omega.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omega.Adapters.productAdapter;
import com.example.omega.Model.customer;
import com.example.omega.Model.product;
import com.example.omega.R;
import com.example.omega.prevalent;
import com.example.omega.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recyclerView;
    private productAdapter adapters;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<product> productList=new ArrayList<product>();
    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
       // product prod=new product("Saree","WOMEN","FASHIONABLE CLASSIC COLLECTION",20000,"https://5.imimg.com/data5/DY/DA/MY-13113718/georgette-net-designer-saree-500x500.jpg");
       // prod.save();
        //product prod1=new product("SHIRT","MEN","100% cotton and fabric",2500,"data:image/jpeg;base64,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");
       // prod1.save();
       // product prod2=new product("DENIM TROUSER","MEN","BRANDED DENIMS",5000,"data:image/jpeg;base64,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");
      //  prod2.save();
         productList= (ArrayList<product>) product.listAll(product.class);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        userNameTextView.setText(prevalent.currentOnlineCustomer.getUsername());
        productList=(ArrayList<product>) product.listAll(product.class);

        recyclerView=(RecyclerView)findViewById(R.id.recycle_menu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapters=new productAdapter(productList,this);
        recyclerView.setAdapter(adapters);





        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        Fragment fragment=new HomeFragment();
        int id = item.getItemId();

        if (id == R.id.nav_cart)
        {

        }
        else if (id == R.id.nav_order)
        {

        }
        else if (id == R.id.nav_categories)
        {

        }
        else if (id == R.id.nav_setting)
        {
           // Intent intent = new Intent(HomeActivity.this, SettinsActivity.class);
            //startActivity(intent);
        }
        else if (id == R.id.nav_logout)
        {
            prevalent.currentOnlineCustomer=new customer();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if(fragment !=null){
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.)
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
