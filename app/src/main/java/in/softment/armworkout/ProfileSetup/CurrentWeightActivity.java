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

public class CurrentWeightActivity extends AppCompatActivity {
    String weight;
    int kgIndex = 1;
    int gramIndex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_current_weight);
        NumberPicker numberPicker = findViewById(R.id.number_picker);



// Using string values
// IMPORTANT! setMinValue to 1 and call setDisplayedValues after setMinValue and setMaxValue
        String[] data = {"30", "31", "32", "33", "34", "35", "36", "37", "38","39","40","41","42","43","44","45",
                "46", "47", "48", "49", "50", "51", "52", "53", "54","55","56","57","58","59","60","61",
                "62", "63", "64", "65", "66", "67", "68", "69", "70","71","72","73","74","75","76","77",
                "78", "79", "80", "81", "82", "83", "84", "85", "86","87","88","89","90","91","92","93",
                "94", "95", "96", "96", "98", "99", "100", "101", "102","103","104","105","106","107","108","109",
                "110", "111", "112", "113", "114", "115", "116", "117", "118","119","120","121","122","123","124","125",
                "126", "127", "128", "129", "130", "131", "132", "132", "133","134","135","136","137","138","139","140",
        };
        String[] data1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8","9"};

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(data.length);
        numberPicker.setDisplayedValues(data);
        numberPicker.setValue(1);

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

        weight = data[kgIndex]+"."+data1[gramIndex];

// OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                kgIndex = newVal;
                weight = data[kgIndex - 1]+"."+data1[gramIndex - 1];
            }
        });

// OnScrollListener
        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker picker, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {

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
                gramIndex = newVal;
                weight = data[kgIndex - 1]+"."+data1[gramIndex - 1];
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
                UserModel.data.weight = weight;
                startActivity(new Intent(CurrentWeightActivity.this, AgeActivity.class));
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