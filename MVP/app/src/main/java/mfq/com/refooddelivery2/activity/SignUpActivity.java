package mfq.com.refooddelivery2.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mfq.com.refooddelivery2.R;
import mfq.com.refooddelivery2.utils.InMemoryStorage;

/**
 * Source: Sign Up Use Case
 * Link: https://tinyurl.com/ulslf4v
 */
public class SignUpActivity extends AppCompatActivity {

    private UserSignUpTask mAuthTask = null;

    private EditText mNameView;
    private EditText mLoginView;
    private EditText mPasswordView;
    private EditText mPhoneView;
    private EditText mAddressView;
    private FirebaseAuth mAuth;

    private Button signUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        mLoginView = findViewById(R.id.up_email);
        mPasswordView = findViewById(R.id.up_password);
        mNameView = findViewById(R.id.name);
        mAddressView = findViewById(R.id.address);
        mPhoneView = findViewById(R.id.phone);
        mAddressView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                signUp();
                return true;
            }
            return false;
        });

        signUpButton = findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(view -> signUp());
    }

    private boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
    }

    private boolean isPasswordValid(String password) {
        return password.matches("\\w\\S+");
    }

    private boolean isNameValid(String name){
        return name.matches("[A-Za-z]+");
    }

    private boolean isPhoneValid(String phone){
        return phone.matches("\\d+") && phone.length() == 11;
    }

    private boolean isAddressValid(String address){
        return address.length() > 0;
    }

    private void signUp(){

        if (mAuthTask != null) {
            return;
        }

        mLoginView.setError(null);
        mPasswordView.setError(null);
        mNameView.setError(null);
        mPhoneView.setError(null);
        mAddressView.setError(null);


        String email = mLoginView.getText().toString();
        String password = mPasswordView.getText().toString();
        String name = mNameView.getText().toString();
        String phone = mPhoneView.getText().toString();
        String address = mAddressView.getText().toString();


        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) ) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        } else if(!isPasswordValid(password)){
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

        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        } else if (!isNameValid(name)) {
            mNameView.setError(getString(R.string.error_invalid_name));
            focusView = mNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError(getString(R.string.error_field_required));
            focusView = mPhoneView;
            cancel = true;
        } else if (!isPhoneValid(phone)) {
            mPhoneView.setError(getString(R.string.error_invalid_phone));
            focusView = mPhoneView;
            cancel = true;
        }

        if (TextUtils.isEmpty(address)) {
            mAddressView.setError(getString(R.string.error_field_required));
            focusView = mAddressView;
            cancel = true;
        } else if (!isAddressValid(address)) {
            mAddressView.setError(getString(R.string.error_invalid_address));
            focusView = mAddressView;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            mAuthTask = new UserSignUpTask(email, password, name, phone,address );
            mAuthTask.execute((Void) null);
        }
    }

    //stub
    public class UserSignUpTask extends AsyncTask<Void, Void, Boolean> {

        private final String mLogin;
        private final String mPassword;
        private final String mName;
        private final String mPhone;
        private final String mAddress;

        UserSignUpTask(String email, String password, String name, String phone, String address) {
            mLogin = email;
            mPassword = password;
            mName = name;
            mPhone = phone;
            mAddress = address;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            AtomicReference<Boolean> result = new AtomicReference<>(false);
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(mLogin, mPassword)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("INFO", "createUserWithEmail:success");
                        result.set(true);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("ERROR", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpActivity.this, "Sign Up failed.", Toast.LENGTH_SHORT).show();
                    }
                });

            String newCredential = mLogin + ":" + mPassword;
            InMemoryStorage.getCredentials().add(newCredential);
            return result.get();
        }

        //server response stub
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            finish();
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.finish();
    }


}
