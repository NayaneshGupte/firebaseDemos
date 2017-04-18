package in.nrg.sampleapps.firebase.launch.Manager;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Perform Sign In using Firebase
 */
public class SignInManager implements OnCompleteListener<AuthResult> {

    private FirebaseAuth firebaseAuth;

    private FirebaseSignInCompleteListener signInCompleteListener;


    public interface FirebaseSignInCompleteListener {
        void onSignInSuccess(FirebaseUser user);

        void onSignInFailure();
    }

    public SignInManager(FirebaseSignInCompleteListener signInCompleteListener) {
        //Get Firebase firebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();
        this.signInCompleteListener = signInCompleteListener;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            signInCompleteListener.onSignInSuccess(user);
        } else {
            signInCompleteListener.onSignInFailure();
        }
    }


    public void signInUser(String userEmail, String password) {
        //create user
        firebaseAuth.signInWithEmailAndPassword(userEmail, password).addOnCompleteListener(this);
    }
}
