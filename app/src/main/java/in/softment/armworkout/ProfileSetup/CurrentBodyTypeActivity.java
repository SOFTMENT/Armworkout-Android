package in.softment.armworkout.ProfileSetup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.softment.armworkout.R;

public class CurrentBodyTypeActivity extends AppCompatActivity {

    RelativeLayout rr1, rr2, rr3;
    TextView text1, text2, text3;
    ImageView icon1, icon2, icon3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_current_body_type);

        rr1 = findViewById(R.id.rr1);
        rr2 = findViewById(R.id.rr2);
        rr3 = findViewById(R.id.rr3);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);

        icon1 = findViewById(R.id.icon1);
        icon2 = findViewById(R.id.icon2);
        icon3 = findViewById(R.id.icon3);

        rr1.setSelected(true);
        rr2.setSelected(false);
        rr3.setSelected(false);

        text1.setSelected(true);
        text2.setSelected(false);
        text3.setSelected(false);

        icon1.setSelected(true);
        icon2.setSelected(false);
        icon3.setSelected(false);

        rr1.setOnClickListener(view -> {
            rr1.setSelected(true);
            text1.setSelected(true);
            icon1.setSelected(true);

            rr2.setSelected(false);
            text2.setSelected(false);
            icon2.setSelected(false);

            rr3.setSelected(false);
            text3.setSelected(false);
            icon3.setSelected(false);


        });

        rr2.setOnClickListener(view -> {
            rr2.setSelected(true);
            text2.setSelected(true);
            icon2.setSelected(true);

            rr1.setSelected(false);
            text1.setSelected(false);
            icon1.setSelected(false);

            rr3.setSelected(false);
            text3.setSelected(false);
            icon3.setSelected(false);


        });

        rr3.setOnClickListener(view -> {
            rr3.setSelected(true);
            text3.setSelected(true);
            icon3.setSelected(true);

            rr2.setSelected(false);
            text2.setSelected(false);
            icon2.setSelected(false);

            rr1.setSelected(false);
            text1.setSelected(false);
            icon1.setSelected(false);


        });

        findViewById(R.id.continueBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CurrentBodyTypeActivity.this, CurrentWeightActivity.class));
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