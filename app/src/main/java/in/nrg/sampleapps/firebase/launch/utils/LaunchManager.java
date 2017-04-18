package in.nrg.sampleapps.firebase.launch.utils;

import android.app.Activity;
import android.content.Intent;

import in.nrg.sampleapps.firebase.home.activity.HomeActivity;
import in.nrg.sampleapps.firebase.launch.activity.SignInActivity;
import in.nrg.sampleapps.firebase.launch.activity.SignUpActivity;

/**
 * @author Nayanesh Gupte
 */

public class LaunchManager {

    public static void launchHome(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void showSignUpScreen(Activity activity) {
        Intent intent = new Intent(activity, SignUpActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void showSignInScreen(Activity activity) {
        Intent intent = new Intent(activity, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
}
