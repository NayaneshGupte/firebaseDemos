package in.nrg.sampleapps.firebase.launch.Manager;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Create User profile on Firebase
 *
 * @author Nayanesh Gupte
 */

public class CreateUserProfileManager implements OnCompleteListener<Void> {

    private FirebaseUser user;

    private OnUserProfileChangedListener onUserProfileChangedListener;

    public interface OnUserProfileChangedListener {

        void onUserProfileChanged(String username);

        void onUserProfileChangeFailed();
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
        } else {
            onUserProfileChangedListener.onUserProfileChangeFailed();
        }
    }
}
