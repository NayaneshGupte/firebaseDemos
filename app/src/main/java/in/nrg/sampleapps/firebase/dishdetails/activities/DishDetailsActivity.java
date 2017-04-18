package in.nrg.sampleapps.firebase.dishdetails.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.dishdetails.manager.DetailsFragmentManager;

/**
 * Activity holding details page
 *
 * @author Nayanesh Gupte
 */
public class DishDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DetailsFragmentManager detailsFragmentManager = new DetailsFragmentManager(this);
        detailsFragmentManager.showDishDetails(getIntent().getExtras());
    }
}
