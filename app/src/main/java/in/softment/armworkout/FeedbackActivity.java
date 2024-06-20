package in.softment.armworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import in.softment.armworkout.Utils.ProgressHud;
import in.softment.armworkout.Utils.Services;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        EditText suggestionET = findViewById(R.id.suggestionET);


        findViewById(R.id.sendBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String suggestion = suggestionET.getText().toString().trim();
                if (suggestion.isEmpty()) {
                    Services.showToast(FeedbackActivity.this,"Enter Feedback");
                }
                else {

                    ProgressHud.show(FeedbackActivity.this,"Sending...");
                    Map<String , String> map = new HashMap<>();
                    map.put("suggestion",suggestion);
                    FirebaseFirestore.getInstance().collection("Feedback").document().set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            ProgressHud.dialog.dismiss();
                            if (task.isSuccessful()) {
                                suggestionET.setText("");
                                Services.showCenterToast(FeedbackActivity.this, "Thank you!, We have received your feedback & suggestion");
                            }
                            else {
                                Services.showDialog(FeedbackActivity.this,"ERROR",task.getException().getLocalizedMessage());
                            }
                        }
                    });
                }
            }
        });
    }
}