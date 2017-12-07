package com.dmitrii.semeniuc.contactstatus.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmitrii.semeniuc.contactstatus.App;
import com.dmitrii.semeniuc.contactstatus.R;
import com.dmitrii.semeniuc.contactstatus.adapters.MainViewPagerAdapter;
import com.dmitrii.semeniuc.contactstatus.auth.AuthActivity;
import com.dmitrii.semeniuc.contactstatus.base.Authentication;
import com.dmitrii.semeniuc.contactstatus.base.BaseActivity;
import com.dmitrii.semeniuc.contactstatus.main.contacts.ContactsFragment;
import com.dmitrii.semeniuc.contactstatus.main.invites.InvitesFragment;
import com.dmitrii.semeniuc.contactstatus.main.status.StatusFragment;
import com.dmitrii.semeniuc.contactstatus.models.User;
import com.dmitrii.semeniuc.contactstatus.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int PERMISSION_READ_STATE = 1;
    @Inject User user;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.toolbar_main) Toolbar toolbar;
    private MainPresenter presenter;
    private ContactsFragment contactsFragment;
    private StatusFragment statusFragment;
    private InvitesFragment notificationsFragment;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private MenuItem prevMenuItem;
    private Intent statusServiceIntent;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_contacts:
                    //Toast.makeText(getApplicationContext(), "Contacts", Toast.LENGTH_SHORT).show();
                    Toasty.info(getApplicationContext(), "Contacts", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_status:
                    //Toast.makeText(getApplicationContext(), "Status", Toast.LENGTH_SHORT).show();
                    Toasty.info(getApplicationContext(), "Status", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    //Toast.makeText(getApplicationContext(), "Notifications", Toast.LENGTH_SHORT).show();
                    Toasty.info(getApplicationContext(), "Notifications", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override protected void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setStatusBarColor(ContextCompat.getColor(this, R.color.blue_800));
        setPresenter(new MainPresenterImpl(this, this));
        if (Authentication.isGoogleUser()) presenter.reconnectGoogleApiClient(this);
        initSupportActionBar();
        initNavigationDrawer();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = (ViewPager) findViewById(R.id.bottom_viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }

    @Override protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
    }

    /*@Override protected void onResume() {
        super.onResume();
        startStatusService();
    }*/

    private void setupViewPager(ViewPager viewPager) {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        contactsFragment = new ContactsFragment();
        statusFragment = new StatusFragment();
        notificationsFragment = new InvitesFragment();
        adapter.addFragment(contactsFragment);
        adapter.addFragment(statusFragment);
        adapter.addFragment(notificationsFragment);
        viewPager.setAdapter(adapter);
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    @Override public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override public void startAuthActivity() {
        startActivity(new Intent(MainActivity.this, AuthActivity.class));
        finish();
    }

    @Override public void showProgress() {
        showProgressDialog();
    }

    @Override public void hideProgress() {
        hideProgressDialog();
    }

    @Override public void startStatusService() {
        statusServiceIntent = new Intent(this, StatusService.class);
        startService(statusServiceIntent);
    }

    @Override public void stopStatusService() {
        try {
            if (statusServiceIntent != null) {
                stopService(statusServiceIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initSupportActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    private void initNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView = navigationView.getHeaderView(0);
        ImageView navHeaderPhotoIV = (ImageView) navHeaderView.findViewById(R.id.nav_header_photo_iv);
        TextView navHeaderNameTV = (TextView) navHeaderView.findViewById(R.id.nav_header_name_tv);
        TextView navHeaderEmailTV = (TextView) navHeaderView.findViewById(R.id.nav_header_email_tv);

        Picasso.with(this).load(user.getPhotoUrl()).transform(new CircleTransform()).into(navHeaderPhotoIV);
        navHeaderNameTV.setText(user.getName());
        navHeaderEmailTV.setText(user.getEmail());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_share) {
            showToast("Share the app");
        } else if (id == R.id.nav_sign_out) {
            presenter.signOut();
        } else if (id == R.id.nav_disconnect) {
            presenter.disconnect();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        //stopService(statusServiceIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted!
                    // you may now do the action that requires this permission
                } else {
                    // permission denied
                }
                return;
            }

        }
    }
}
