package mfq.com.refooddelivery2.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.atomic.AtomicReference;

import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.utils.RequestStatus;

/**
 * Use Case: Login Use Case
 * Link: https://tinyurl.com/taf6ca4
 */
public class LoginActivity extends AppCompatActivity {

    private UserLoginTask mAuthTask = null;

    private EditText mLoginView;
    private EditText mPasswordView;
    private Button loginButton;
    private Button signUpButton;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        mLoginView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.signup_button);
        mProgressBar = findViewById(R.id.login_progress);
        signUpButton.setOnClickListener(view -> signUp());
        loginButton.setOnClickListener(view -> attemptLogin());


    }

    private void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mLoginView.setError(null);
        mPasswordView.setError(null);


        String email = mLoginView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }else if(!isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mLoginView.setError(getString(R.string.error_field_required));
            focusView = mLoginView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mLoginView.setError(getString(R.string.error_invalid_email));
            focusView = mLoginView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgressBar(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.matches(".+@\\w+\\.[a-zA-Z]+");
    }

    private boolean isPasswordValid(String password) {
        return password.matches("\\w\\S+");
    }

    private void showProgressBar(boolean show){
        mProgressBar.setVisibility(show ? View.VISIBLE: View.GONE);
        loginButton.setVisibility(show ? View.GONE: View.VISIBLE);
        signUpButton.setVisibility(show ? View.GONE: View.VISIBLE);
    }

    //stub

    /**
     * Asynchronous login task
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mLogin;
        private final String mPassword;
        private RequestStatus status;

        UserLoginTask(String email, String password) {
            mLogin = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            AtomicReference<Boolean> result = new AtomicReference<>(false);
            status = RequestStatus.WAIT;


            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(mLogin, mPassword)
                .addOnCompleteListener(LoginActivity.this,  new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("INFO", "signInWithEmail:success");
                            status = RequestStatus.SUCCESS;
                            result.set(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ERROR", "signInWithEmail:failure", task.getException());
                            status = RequestStatus.FAIL;
                        }
                    }
                });

            do {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (status == RequestStatus.WAIT);

            return result.get();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                finish();
            } else {
                showProgressBar(false);
                mPasswordView.setError("Invalid password");
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.finish();
    }
}
