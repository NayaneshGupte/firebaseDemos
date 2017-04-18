package in.nrg.sampleapps.firebase.launch.Manager;

import com.google.firebase.auth.FirebaseUser;

/**
 * Listener to listen firebase auth
 *
 * @author Nayanesh Gupte
 */
public interface FirebaseAuthCompleteListener {
    void onAuthSuccess(FirebaseUser user);

    void onAuthFailure();
}