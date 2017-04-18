package in.nrg.sampleapps.firebase.home.presenter;

import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.nrg.sampleapps.firebase.R;

/**
 * Presenter class to handle header in Navigation page
 *
 * @author Nayanesh Gupte
 */

public class HeaderViewPresenter implements View.OnClickListener {

    private Activity activity;
    private View view;
    private DrawerLayout drawer;
    private ImageView imgevProfile;
    private TextView textvName;
    private TextView textvEmail;

    public HeaderViewPresenter(Activity activity, View view, DrawerLayout drawer) {
        this.activity = activity;
        this.view = view;
        this.drawer = drawer;
    }

    public void initViews() {
        imgevProfile = (ImageView) view.findViewById(R.id.imageViewProfile);
        textvName = (TextView) view.findViewById(R.id.textvName);
        textvEmail = (TextView) view.findViewById(R.id.textvEmail);
        view.setOnClickListener(this);
        setData();
    }

    private void setData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("HeaderViewPresenter", "user.getDisplayName(): " + user.getDisplayName());
        textvName.setText(user.getDisplayName());
        textvEmail.setText(user.getEmail());
    }

    @Override
    public void onClick(View v) {
        if (v == view) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}
