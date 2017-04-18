package in.nrg.sampleapps.firebase.launch.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.home.utils.ValidationUtils;
import in.nrg.sampleapps.firebase.launch.Manager.SignInManager;
import in.nrg.sampleapps.firebase.launch.utils.LaunchManager;

/**
 * Sign in screen to for app
 *
 * @author Nayanesh Gupte
 */
public class SignInActivity extends AppCompatActivity implements
        SignInManager.FirebaseSignInCompleteListener,
        View.OnClickListener {

    private SignInManager signInManager;

    private EditText edtTxtEmail;
    private EditText edtTxtPassword;
    private TextView txtvSignup;
    private Button btnLogin;

    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtTxtEmail = (EditText) findViewById(R.id.edtTxtEmail);
        edtTxtPassword = (EditText) findViewById(R.id.edtTxtPassword);
        txtvSignup = (TextView) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);

        txtvSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login: {
                String email = edtTxtEmail.getText().toString().trim();
                password = edtTxtPassword.getText().toString().trim();

                if (!ValidationUtils.isEmailValid(email)) {
                    edtTxtEmail.setError(getString(R.string.email_error_message));
                    edtTxtEmail.requestFocus();
                    return;
                }

                if (!ValidationUtils.isPasswordValid(password)) {
                    edtTxtPassword.setError(getString(R.string.password_error_message));
                    edtTxtPassword.requestFocus();
                    return;
                }

                //authenticate user
                signInManager = new SignInManager(this);
                signInManager.signInUser(email, password);
            }
            break;

            case R.id.btn_signup: {
                LaunchManager.showSignUpScreen(this);
            }
            break;

        }

    }

    @Override
    public void onSignInSuccess(FirebaseUser user) {
        LaunchManager.launchHome(this);
    }

    @Override
    public void onSignInFailure() {
        Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
    }
}