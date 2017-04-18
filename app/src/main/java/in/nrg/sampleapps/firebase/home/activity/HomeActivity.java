package in.nrg.sampleapps.firebase.home.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.dishdetails.fragments.DishUploadFragment;
import in.nrg.sampleapps.firebase.home.fragment.HomePageFragment;
import in.nrg.sampleapps.firebase.home.manager.HomeFragmentManager;
import in.nrg.sampleapps.firebase.home.presenter.HeaderViewPresenter;
import in.nrg.sampleapps.firebase.launch.utils.LaunchManager;

/**
 * HomeActivity to display list of all dishes
 *
 * @author Nayanesh Gupte
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , FragmentManager.OnBackStackChangedListener {

    private Toolbar toolbar;

    private NavigationView navigationView;
    private DrawerLayout drawer;

    private HomeFragmentManager homeFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);


        getFragmentManager().addOnBackStackChangedListener(this);
        homeFragmentManager = new HomeFragmentManager(this);

        setNavigation();
        homeFragmentManager.showHomePage();
    }


    private void setNavigation() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HeaderViewPresenter headerviewPresenter = new HeaderViewPresenter(this, navigationView.getHeaderView(0), drawer);
        headerviewPresenter.initViews();

        navigationView.setCheckedItem(R.id.nav_home);

    }

    @Override
    public void setTitle(final CharSequence title) {
        toolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.super.setTitle(title);
            }
        }, 200);
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home: {
                homeFragmentManager.showHomePage();
            }
            break;

            case R.id.nav_upload_dish: {
                homeFragmentManager.dishUpload();
            }
            break;

            case R.id.nav_share: {


            }
            break;

            case R.id.nav_logout: {
                FirebaseAuth.getInstance().signOut();
                LaunchManager.showSignInScreen(this);
            }
            break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackStackChanged() {
        if (getCurrentFragment() instanceof DishUploadFragment) {
            setTitle(getString(R.string.upload_dish));
            navigationView.setCheckedItem(R.id.nav_upload_dish);
        } else if (getCurrentFragment() instanceof HomePageFragment) {
            setTitle(getString(R.string.home));
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    private Fragment getCurrentFragment() {
        return getFragmentManager().findFragmentById(R.id.content_home);
    }
}
