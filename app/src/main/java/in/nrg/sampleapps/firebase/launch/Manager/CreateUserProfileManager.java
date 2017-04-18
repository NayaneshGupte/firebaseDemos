package in.nrg.sampleapps.firebase.launch.Manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by nayaneshg on 04/02/17.
 */

public class CreateUserProfileManager implements OnCompleteListener<Void> {

    private FirebaseUser user;

    private OnUserProfileChangedListener onUserProfileChangedListener;

    public interface OnUserProfileChangedListener {

        void onUserProfileChanged(String username);

        void onUserProfileChangeFialed();
    }

    public CreateUserProfileManager(OnUserProfileChangedListener onUserProfileChangedListener) {
        this.onUserProfileChangedListener = onUserProfileChangedListener;
    }

    public void createFirebaseUserProfile(final FirebaseUser user, String username) {
        this.user = user;
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();
        user.updateProfile(addProfileName).addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            onUserProfileChangedListener.onUserProfileChanged(user.getDisplayName());
            Log.d("CreateUserProfile", "user.getDisplayName(): " + user.getDisplayName());

        } else {
            onUserProfileChangedListener.onUserProfileChangeFialed();
        }
    }
}
