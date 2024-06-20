package in.softment.armworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.Objects;

import in.softment.armworkout.ProfileSetup.GenderAcitivity;
import in.softment.armworkout.Utils.ProgressHud;
import in.softment.armworkout.Utils.Services;

public class SignInActivity extends AppCompatActivity {
    private LottieAnimationView animationView;
    private CallbackManager mCallbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();


        animationView = findViewById(R.id.animationView);
        animationView.playAnimation();
        mCallbackManager = CallbackManager.Factory.create();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.google).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 909);

            }
        });


        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
                        firebaseAuth(credential);

                    }

                    @Override
                    public void onCancel()
                    {

                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception)
                    {


                        Services.showDialog(SignInActivity.this,"ERROR",exception.getLocalizedMessage());
                    }


                });


        //facebook
        findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this, Arrays.asList( "email", "public_profile"));

            }
        });




    }

    private void firebaseAuth(AuthCredential credential) {

        ProgressHud.show(this,"Loading...");
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ProgressHud.dialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if (mAuth.getCurrentUser() != null){

                                Services.getCurrentUserData(SignInActivity.this,mAuth.getCurrentUser().getUid(),true);

                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Services.showDialog(SignInActivity.this,"ERROR", Objects.requireNonNull(task.getException()).getLocalizedMessage());
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 909) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                firebaseAuth(credential);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Services.showDialog(SignInActivity.this,"ERROR",e.getLocalizedMessage());
            }
        }
        else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }
}