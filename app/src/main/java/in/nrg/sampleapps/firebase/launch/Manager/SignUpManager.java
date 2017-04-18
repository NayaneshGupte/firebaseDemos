package in.nrg.sampleapps.firebase.launch.Manager;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Perform Sign up using Firebase
 */
public class SignUpManager implements OnCompleteListener<AuthResult>,
        CreateUserProfileManager.OnUserProfileChangedListener {

    private FirebaseAuth firebaseAuth;

    private FirebaseSignUpCompleteListener signUpCompleteListener;

    private String username;

    public interface FirebaseSignUpCompleteListener {
        void onSignUpSuccess(FirebaseUser user);

        void onSignUpFailure();
    }

    public SignUpManager(FirebaseSignUpCompleteListener signUpCompleteListener) {
        //Get Firebase firebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();
        this.signUpCompleteListener = signUpCompleteListener;
    }

    public void signUpUser(String username, String userEmail, String password) {
        this.username = username;
        //create user
        firebaseAuth.createUserWithEmailAndPassword(userEmail, password).addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        FirebaseUser user = task.getResult().getUser();
        CreateUserProfileManager createUserProfileManager = new CreateUserProfileManager(this);
        createUserProfileManager.createFirebaseUserProfile(user, username);
    }

    @Override
    public void onUserProfileChanged(String username) {
        signUpCompleteListener.onSignUpSuccess(firebaseAuth.getCurrentUser());
    }

    @Override
    public void onUserProfileChangeFialed() {
        signUpCompleteListener.onSignUpFailure();
    }

}
