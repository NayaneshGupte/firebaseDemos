package in.nrg.sampleapps.firebase;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Application class
 */
public class RestaurantApp extends Application {

    private static RestaurantApp mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        if (!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }

    public static RestaurantApp getInstance() {
        return mApplication;
    }
}
