package com.foresight.smartleadcollector;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foresight.smartleadcollector.data.Constant;
import com.foresight.smartleadcollector.navigationdrawer.NavMenuAdapter;
import com.foresight.smartleadcollector.navigationdrawer.NavMenuModel;
import com.foresight.smartleadcollector.navigationdrawer.SubTitle;
import com.foresight.smartleadcollector.navigationdrawer.TitleMenu;
import com.foresight.smartleadcollector.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavMenuAdapter.MenuItemClickListener {

    String current = "";
    Toolbar toolbar;
    DrawerLayout drawer;
    ArrayList<NavMenuModel> menu;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current = getIntent().getStringExtra("userName");

        setToolbar();

        drawer = findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setNavigationDrawerMenu();

        tv = findViewById(R.id.position);
        tv.setText("Web and android dev.");

    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setNavigationDrawerMenu() {
        NavMenuAdapter adapter = new NavMenuAdapter(this, getMenuList(), this);
        RecyclerView navMenuDrawer = findViewById(R.id.main_nav_menu_recyclerview);
        navMenuDrawer.setAdapter(adapter);
        navMenuDrawer.setLayoutManager(new LinearLayoutManager(this));
        navMenuDrawer.setAdapter(adapter);

//        INITIATE SELECT MENU
        adapter.selectedItemParent = menu.get(0).menuTitle;
        onMenuItemClick(adapter.selectedItemParent);
        adapter.notifyDataSetChanged();
    }

    private List<TitleMenu> getMenuList() {
        List<TitleMenu> list = new ArrayList<>();

        menu = Constant.getMenuNavigasi();
        for (int i = 0; i < menu.size(); i++) {
            ArrayList<SubTitle> subMenu = new ArrayList<>();
            if (menu.get(i).subMenu.size() > 0) {
                for (int j = 0; j < menu.get(i).subMenu.size(); j++) {
                    subMenu.add(new SubTitle(menu.get(i).subMenu.get(j).subMenuTitle));
                }
            }

            list.add(new TitleMenu(menu.get(i).menuTitle, subMenu));
        }

        return list;
    }

  /*  @Override
    protected void onStart() {
        super.onStart();

        Log.i("this is ", "onStart: " + current);
        if (current == null) {
            mainToWelcome();

        } else {
            Toast.makeText(this, "Welcome to Dashboard", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void mainToWelcome() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuItemClick(String itemString) {
        for (int i = 0; i < menu.size(); i++) {
            if (itemString.equals("LogOut")) {
                Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(this).clear();
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                break;
            }
            if (itemString.equals(menu.get(i).menuTitle)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, menu.get(i).fragment)
                        .commit();
                break;
            } else {
                for (int j = 0; j < menu.get(i).subMenu.size(); j++) {
                    if (itemString.equals(menu.get(i).subMenu.get(j).subMenuTitle)) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_content, menu.get(i).subMenu.get(j).fragment)
                                .commit();
                        break;
                    }
                }
            }
        }

        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.main_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


}
