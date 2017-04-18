package in.nrg.sampleapps.firebase.home.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.dishdetails.activities.DishDetailsActivity;
import in.nrg.sampleapps.firebase.dishdetails.fragments.DishUploadFragment;
import in.nrg.sampleapps.firebase.home.fragment.HomePageFragment;


/**
 * Manager to handle home page related operations
 */
public class HomeFragmentManager {

    private Activity activity;

    public HomeFragmentManager(Activity activity) {
        this.activity = activity;
    }

    /**
     * Load home fragment in HomeActivity
     */
    public void showHomePage() {
        HomePageFragment homePageFragment = new HomePageFragment();
        activity.getFragmentManager().
                beginTransaction().
                add(R.id.content_home, homePageFragment).
                commit();
    }

    /**
     * Load DetailsPage fragment in HomeActivity
     *
     * @param bundle
     */
    public void showDishDetails(Bundle bundle) {
        Intent intent = new Intent(activity, DishDetailsActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    /**
     * Load DishUpload fragment in HomeActivity
     */
    public void dishUpload() {
        DishUploadFragment dishUploadFragment = new DishUploadFragment();
        activity.getFragmentManager().
                beginTransaction().
                add(R.id.content_home, dishUploadFragment).
                addToBackStack("").
                commit();
    }
}
