package in.softment.armworkout.ProfileSetup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import in.softment.armworkout.MainActivity;
import in.softment.armworkout.Model.UserModel;
import in.softment.armworkout.R;
import in.softment.armworkout.SignInActivity;
import in.softment.armworkout.Utils.ProgressHud;

public class MoreRelaxedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_more_relaxed);



        findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserData();
            }
        });

        findViewById(R.id.noBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserData();
            }
        });
        //back
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void updateUserData(){
        ProgressHud.show(this,"Wait...");
        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(UserModel.data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                ProgressHud.dialog.dismiss();
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MoreRelaxedActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}