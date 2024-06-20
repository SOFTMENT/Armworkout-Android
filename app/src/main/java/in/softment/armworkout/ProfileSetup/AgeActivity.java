package in.softment.armworkout.ProfileSetup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.Locale;

import in.softment.armworkout.Model.UserModel;
import in.softment.armworkout.R;

public class AgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_age);

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

        findViewById(R.id.continueBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AgeActivity.this, HeightActivity.class));
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