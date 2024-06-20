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

public class HeightActivity extends AppCompatActivity {
    String height;
    int feetIndex = 1;
    int inchIndex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_height);

        NumberPicker numberPicker = findViewById(R.id.number_picker);



// Using string values
// IMPORTANT! setMinValue to 1 and call setDisplayedValues after setMinValue and setMaxValue
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8","9"
        };

        String[] data1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8","9"};

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

        height = data[feetIndex]+"."+data1[inchIndex];

// OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                feetIndex = newVal;
                height = data[feetIndex - 1]+"."+data1[inchIndex - 1];
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



        NumberPicker numberPicker1 = findViewById(R.id.number_picker2);



// Using string values
// IMPORTANT! setMinValue to 1 and call setDisplayedValues after setMinValue and setMaxValue

        numberPicker1.setMinValue(1);
        numberPicker1.setMaxValue(data1.length);
        numberPicker1.setDisplayedValues(data1);


// Set fading edge enabled
        numberPicker1.setFadingEdgeEnabled(true);

// Set scroller enabled
        numberPicker1.setScrollerEnabled(true);

// Set wrap selector wheel
        numberPicker1.setWrapSelectorWheel(true);

// Set accessibility description enabled
        numberPicker1.setAccessibilityDescriptionEnabled(true);

// OnClickListener
        numberPicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "Click on current value");
            }
        });

// OnValueChangeListener
        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                inchIndex = newVal;
                height = data[feetIndex - 1]+"."+data1[inchIndex - 1];
            }
        });

// OnScrollListener
        numberPicker1.setOnScrollListener(new NumberPicker.OnScrollListener() {
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
                UserModel.data.height = height;
                startActivity(new Intent(HeightActivity.this,MoreRelaxedActivity.class));
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