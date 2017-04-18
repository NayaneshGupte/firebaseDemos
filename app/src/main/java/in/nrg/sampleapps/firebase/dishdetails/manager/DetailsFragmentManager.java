package in.nrg.sampleapps.firebase.dishdetails.manager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.dishdetails.activities.DishDetailsActivity;
import in.nrg.sampleapps.firebase.dishdetails.fragments.DishDetailFragment;


/**
 * Manager to handle details page related operations
 */
public class DetailsFragmentManager implements FragmentManager.OnBackStackChangedListener {

    private Activity activity;

    public DetailsFragmentManager(Activity activity) {
        this.activity = activity;
        this.activity.getFragmentManager().addOnBackStackChangedListener(this);
    }


    /**
     * Load DetailsPage fragment in {@link DishDetailsActivity}
     *
     * @param bundle
     */
    public void showDishDetails(Bundle bundle) {
        DishDetailFragment dishDetailFragment = new DishDetailFragment();
        dishDetailFragment.setArguments(bundle);
        activity.getFragmentManager().
                beginTransaction().
                add(R.id.content_details, dishDetailFragment).
                commit();
    }


    @Override
    public void onBackStackChanged() {
        if (getCurrentFragment() instanceof DishDetailFragment) {
            activity.setTitle(activity.getString(R.string.dish_details));
        }
    }

    private Fragment getCurrentFragment() {
        return activity.getFragmentManager().findFragmentById(R.id.content_details);
    }
}
