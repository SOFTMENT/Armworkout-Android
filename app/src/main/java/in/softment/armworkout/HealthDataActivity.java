package in.softment.armworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import in.softment.armworkout.Model.UserModel;

public class HealthDataActivity extends AppCompatActivity {

    TextView gender;
    TextView height;
    TextView weight;
    TextView age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_data);

        gender = findViewById(R.id.genderTV);
        height = findViewById(R.id.heightTV);
        weight = findViewById(R.id.weightTV);
        age = findViewById(R.id.ageTV);


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        findViewById(R.id.gender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthDataActivity.this, EditGenderActivity.class));
            }
        });

        findViewById(R.id.weight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthDataActivity.this, EditWeightActivity.class));
            }
        });

        findViewById(R.id.height).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthDataActivity.this, EditHeightActivity.class));
            }
        });

        findViewById(R.id.age).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthDataActivity.this, EditAgeActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        gender.setText(UserModel.data.getGender());
        String []HeightList = UserModel.data.getHeight().split("\\.");
        height.setText(HeightList[0]+"'"+HeightList[1]+"\" feet");
        weight.setText(UserModel.data.getWeight()+" kg");
        age.setText(UserModel.data.getAge());
    }
}