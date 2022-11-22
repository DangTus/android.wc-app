package com.reddog.worldcup2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.reddog.worldcup2022.fragment.GroupStateFragment;
import com.reddog.worldcup2022.fragment.HomeFragment;
import com.reddog.worldcup2022.fragment.KnockoutFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_GROUP_STATE = 1;
    private static final int FRAGMENT_KNOCKOUT_STATE = 2;

    private int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ImageButton settingButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        sharedPreferences = getSharedPreferences("dataApp", MODE_PRIVATE);

        mToolbar.setTitle(R.string.nav_home);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        //set default
        replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        //on click setting
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSetting();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                if(mCurrentFragment != FRAGMENT_HOME) {
                    replaceFragment(new HomeFragment());
                    mCurrentFragment = FRAGMENT_HOME;
                    mToolbar.setTitle(getString(R.string.nav_home));
                }
                break;

            case R.id.nav_group_state:
                if(mCurrentFragment != FRAGMENT_GROUP_STATE) {
                    replaceFragment(new GroupStateFragment());
                    mCurrentFragment = FRAGMENT_GROUP_STATE;
                    mToolbar.setTitle(getString(R.string.nav_group_state));
                }
                break;

            case R.id.nav_knockout_state:
                if(mCurrentFragment != FRAGMENT_KNOCKOUT_STATE) {
                    replaceFragment(new KnockoutFragment());
                    mCurrentFragment = FRAGMENT_KNOCKOUT_STATE;
                    mToolbar.setTitle(getString(R.string.nav_knockout_state));
                }
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    private void showSetting() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_setting);
        dialog.setCanceledOnTouchOutside(false);

        //anh xa
        final ImageButton btnHuy = dialog.findViewById(R.id.huy_button);
        final Button btnLuu = dialog.findViewById(R.id.luu_button);
        final Switch swAmThanh = dialog.findViewById(R.id.amthanh_switch);
        
        //set switch
        swAmThanh.setChecked(sharedPreferences.getBoolean("setting_amthanh", true));

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("setting_amthanh", swAmThanh.isChecked());
                editor.commit();

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            this.finishAffinity();
        }
    }

    private void anhXa() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.navigation_view);
        settingButton = findViewById(R.id.setting_button);
    }
}