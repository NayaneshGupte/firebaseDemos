package in.nrg.sampleapps.firebase.launch.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.home.utils.ValidationUtils;
import in.nrg.sampleapps.firebase.launch.Manager.SignUpManager;
import in.nrg.sampleapps.firebase.launch.utils.LaunchManager;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,
        SignUpManager.FirebaseSignUpCompleteListener {

    private EditText edtTxtUserName;
    private EditText edtTxtEmail;
    private EditText edtTxtPassword;

    private TextView txtvSignIn;
    private Button btnSignUp;

    private ProgressBar progressBar;
    private SignUpManager signUpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        txtvSignIn = (TextView) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        edtTxtUserName = (EditText) findViewById(R.id.EdtTxtUserName);
        edtTxtEmail = (EditText) findViewById(R.id.email);
        edtTxtPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        txtvSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                LaunchManager.showSignInScreen(this);
                break;

            case R.id.sign_up_button:
                String username = edtTxtUserName.getText().toString().trim();
                String email = edtTxtEmail.getText().toString().trim();
                String password = edtTxtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    edtTxtUserName.setError(getString(R.string.error_messgae_valid_username));
                    edtTxtUserName.requestFocus();
                    return;
                }

                if (!ValidationUtils.isEmailValid(email)) {
                    edtTxtEmail.setError("Enter email address!");
                    edtTxtEmail.requestFocus();
                    return;
                }

                if (!ValidationUtils.isPasswordValid(password)) {
                    edtTxtPassword.setError("Enter valid password!");
                    edtTxtPassword.requestFocus();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                //create user
                signUpManager = new SignUpManager(this);
                signUpManager.signUpUser(username, email, password);
                break;
        }
    }

    @Override
    public void onSignUpSuccess(FirebaseUser user) {
        progressBar.setVisibility(View.GONE);
        LaunchManager.launchHome(this);
    }

    @Override
    public void onSignUpFailure() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
    }
}