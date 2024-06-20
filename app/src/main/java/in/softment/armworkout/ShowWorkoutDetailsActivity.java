package in.softment.armworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import in.softment.armworkout.Model.WorkoutModel;
import in.softment.armworkout.Utils.Services;

public class ShowWorkoutDetailsActivity extends AppCompatActivity {

    int count;
    int finalCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_workout_details);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WorkoutModel workoutModel = (WorkoutModel) getIntent().getSerializableExtra("workout");

        if (workoutModel == null) {
            return;
        }

        LottieAnimationView lottieAnimationView = findViewById(R.id.animationView);
        TextView heading = findViewById(R.id.heading);
        TextView subheading = findViewById(R.id.subheading);
        TextView countText = findViewById(R.id.count);

        lottieAnimationView.setAnimation(workoutModel.workout_animation);
        lottieAnimationView.setScaleY(2f);
        lottieAnimationView.setScaleX(2f);


        heading.setText(workoutModel.name.toUpperCase());
        subheading.setText(workoutModel.description);
        countText.setText(workoutModel.durations_repetations+"");
        count = workoutModel.durations_repetations;
        finalCount = workoutModel.durations_repetations;
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                countText.setText(count+"");
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                countText.setText(count+"");
            }
        });


        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = finalCount;
                countText.setText(finalCount+"");
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Services.showCenterToast(ShowWorkoutDetailsActivity.this,"Saved");
            }
        });
    }
}