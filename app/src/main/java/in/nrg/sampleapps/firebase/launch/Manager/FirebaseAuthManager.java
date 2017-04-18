package in.nrg.sampleapps.firebase.launch.Manager;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Auth manager to check if user is still valid user
 *
 * @author Nayanesh Gupte
 */
public class FirebaseAuthManager implements FirebaseAuth.AuthStateListener {

    private FirebaseAuth firebaseAuth;

    private FirebaseAuthCompleteListener firebaseAuthCompleteListener;

    public FirebaseAuthManager(FirebaseAuthCompleteListener firebaseAuthCompleteListener) {
        //Get Firebase firebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseAuthCompleteListener = firebaseAuthCompleteListener;
    }

    public void checkUserAuth() {
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            firebaseAuthCompleteListener.onAuthSuccess(user);
        } else {
            firebaseAuthCompleteListener.onAuthFailure();
        }
    }
}
