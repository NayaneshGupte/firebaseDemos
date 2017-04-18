package in.nrg.sampleapps.firebase.launch.Manager;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaseAuthCompleteListener {
    void onAuthSuccess(FirebaseUser user);

    void onAuthFailure();
}