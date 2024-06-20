package in.softment.armworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import in.softment.armworkout.Model.UserModel;
import in.softment.armworkout.ProfileSetup.HeightActivity;
import in.softment.armworkout.Utils.ProgressHud;
import in.softment.armworkout.Utils.Services;

public class EditAgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_age);

        NumberPicker numberPicker = findViewById(R.id.number_picker);



// Using string values
// IMPORTANT! setMinValue to 1 and call setDisplayedValues after setMinValue and setMaxValue
        String[] data = {"10","11","12","13", "14", "15", "16", "17", "18", "19", "20", "21","22","23","24","25","26","27","28",
                "29", "30", "31", "32","33","34", "35", "36", "37", "38", "39", "40", "41", "42","43","44","45","46","47","48","49",
                "50", "51", "52", "53","54","55", "56", "57", "58", "59", "60", "61", "62", "63","64","65","66","67","68","69","70",
                "71", "72", "73", "74","75", "76", "77", "78","79","80","81","82","83","84","85",
                "86", "87", "88", "89","90","91","92","93","94","95",
                "96", "97", "99",
        };
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(data.length);
        numberPicker.setDisplayedValues(data);
        numberPicker.setValue(7);

// Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(true);

// Set scroller enabled
        numberPicker.setScrollerEnabled(true);

// Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);

// Set accessibility description enabled
        numberPicker.setAccessibilityDescriptionEnabled(true);

// OnClickListener
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "Click on current value");
            }
        });

// OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                UserModel.data.age = data[newVal - 1];
            }
        });

// OnScrollListener
        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker picker, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    Log.d("TAG", String.format(Locale.US, "newVal: %d", picker.getValue()));
                }
            }
        });


        findViewById(R.id.updateBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressHud.show(EditAgeActivity.this,"Updating...");
                Map<String, String> map = new HashMap<>();
                map.put("age", UserModel.data.getAge());
                FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        ProgressHud.dialog.dismiss();
                        if (task.isSuccessful()) {
                            Services.showCenterToast(EditAgeActivity.this, "Updated");
                        }
                    }
                });

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
}