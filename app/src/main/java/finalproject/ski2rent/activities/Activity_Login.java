package finalproject.ski2rent.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;


import finalproject.ski2rent.R;

public class Activity_Login extends Activity_Base {

    private final int RC_SIGN_IN = 1234;

    // https://firebase.google.com/docs/auth/android/firebaseui?authuser=0
    // https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("loginLifeCycle", "onCreate: Activity_Login");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//
//        if (user != null) {
//            openApp();
//        } else {
//            startLoginMethod();
//        }

        startLoginMethod();

    } // onCreate

    private void startLoginMethod() {
        Log.d("pttt", "startLoginMethod");

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.PhoneBuilder().build()
                        ))

                        .build(),
                RC_SIGN_IN);
    }

    private void openApp() {
        Log.d("pttt", "openApp");
        Intent myIntent = new Intent(this, Activity_MainMenu.class);
        startActivity(myIntent);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                openApp();
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    showMessage(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showMessage(R.string.no_internet_connection);
                    return;
                }

                showMessage(R.string.unknown_error);
                Log.e("pttt", "Sign-in error: ", response.getError());
            }
        }
    }

    private void showMessage(int id) {
        Toast.makeText(this, getText(id), Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onStart() {
        Log.d("loginLifeCycle", "onStart: Activity_Login");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("loginLifeCycle", "onResume: Activity_Login");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("loginLifeCycle", "onPause: Activity_Login");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("loginLifeCycle", "onStop: Activity_Login");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("loginLifeCycle", "onDestroy: Activity_Login");
        super.onDestroy();
    }


} // Activity_Login