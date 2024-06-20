package in.softment.armworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;
import in.softment.armworkout.Adapter.HorizontalDayAdapter;
import in.softment.armworkout.Adapter.Workout_Adapter;
import in.softment.armworkout.Model.WorkoutModel;
import in.softment.armworkout.Utils.Constants;

public class ShowWorkoutActivity extends AppCompatActivity {

    private HorizontalDayAdapter horizontalDayAdapter;
    private Workout_Adapter workout_adapter;
    private CircularProgressIndicator progressIndicator;
    private ArrayList<WorkoutModel> workoutModels;
    private  String workoutType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.gray_back_color));
        setContentView(R.layout.activity_show_workout);

        RecyclerView workout_recyclerview = findViewById(R.id.recyclerview_workout);
        workout_recyclerview.setHasFixedSize(true);
        workout_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        workoutModels = new ArrayList<>();
        workoutType = getIntent().getStringExtra("workoutType");
        if (workoutType.equalsIgnoreCase("beginner_biceps")) {
            workoutModels = Constants.getBeginnerBicepsDay1Workout();
        }
        else if (workoutType.equalsIgnoreCase("beginner_inter")) {
            workoutModels = Constants.getIntermediateBicepsDay1Workout();
        }
        else if (workoutType.equalsIgnoreCase("beginner_advance")) {
            workoutModels = Constants.getAdvancedBicepsDay1Workout();
        }

        else if (workoutType.equalsIgnoreCase("triceps_beginner")) {
            workoutModels = Constants.getIntermediateBicepsDay1Workout();
        }
        else if (workoutType.equalsIgnoreCase("triceps_inter")) {
            workoutModels = Constants.getAdvancedBicepsDay1Workout();
        }
        else if (workoutType.equalsIgnoreCase("triceps_advance")) {
            workoutModels = Constants.getIntermediateBicepsDay1Workout();
        }
        else if (workoutType.equalsIgnoreCase("dumbell_beginner")) {
            workoutModels = Constants.getAdvancedBicepsDay1Workout();
        }
        else if (workoutType.equalsIgnoreCase("dumbell_inter")) {
            workoutModels = Constants.getIntermediateBicepsDay1Workout();
        }
        else if (workoutType.equalsIgnoreCase("dumbell_advance")) {
            workoutModels = Constants.getAdvancedBicepsDay1Workout();
        }


        workout_adapter = new Workout_Adapter(this,workoutModels);
        workout_recyclerview.setAdapter(workout_adapter);
        progressIndicator = findViewById(R.id.circular_progress);
        progressIndicator.setMaxProgress(100);
        progressIndicator.setProgressTextAdapter(new CircularProgressIndicator.ProgressTextAdapter() {
            @NonNull
            @Override
            public String formatText(double currentProgress) {
                return "10%";
            }
        });
        progressIndicator.setDirection(CircularProgressIndicator.DIRECTION_CLOCKWISE);
        progressIndicator.setCurrentProgress(20);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        horizontalDayAdapter = new HorizontalDayAdapter(this);
        recyclerView.setAdapter(horizontalDayAdapter);

    }

    public void changeWorkoutDay(int position) {

        if (position % 4 == 0) {
            return;
        }
        workoutModels.clear();


        if (workoutType.equalsIgnoreCase("beginner_biceps")) {
            switch (position) {
                case 1 :
                   workoutModels.addAll(Constants.getBeginnerBicepsDay1Workout());
                    break;
                case 2 :

                    workoutModels.addAll(Constants.getBeginnerBicepsDay2Workout());

                    break;
                case 3 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay3Workout());

                    break;
                case 5 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay5Workout());

                    break;
                case 6 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getBeginnerBicepsDay30Workout());
                    break;
                default:
                    workoutModels = Constants.getBeginnerBicepsDay1Workout();
            }

            workout_adapter.notifyDataSetChanged();
        }
        else if (workoutType.equalsIgnoreCase("beginner_inter")) {
            switch (position) {
                case 1 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay1Workout());
                    break;
                case 2 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay2Workout());
                    break;
                case 3 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay3Workout());
                    break;
                case 5 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay5Workout());
                    break;
                case 6 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getIntermediateBicepsDay30Workout());
                    break;
            }
            workout_adapter.notifyDataSetChanged();
        }
        else if (workoutType.equalsIgnoreCase("beginner_advance")) {
            switch (position) {
                case 1 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay1Workout());
                    break;
                case 2 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay2Workout());
                    break;
                case 3 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay3Workout());
                    break;
                case 5 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay5Workout());
                    break;
                case 6 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getAdvancedBicepsDay30Workout());
                    break;


            }

            workout_adapter.notifyDataSetChanged();
        }
        else if (workoutType.equalsIgnoreCase("triceps_beginner")) {
            switch (position) {
                case 1 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay1Workout());
                    break;
                case 2 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay2Workout());
                    break;
                case 3 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay3Workout());
                    break;
                case 5 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay5Workout());
                    break;
                case 6 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay30Workout());
                    break;


            }

            workout_adapter.notifyDataSetChanged();
        }

        else if (workoutType.equalsIgnoreCase("triceps_inter")) {
            switch (position) {
                case 1 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay1Workout());
                    break;
                case 2 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay2Workout());
                    break;
                case 3 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay3Workout());
                    break;
                case 5 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay5Workout());
                    break;
                case 6 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getIntermediateTricepsDay30Workout());
                    break;


            }

            workout_adapter.notifyDataSetChanged();
        }


        else if (workoutType.equalsIgnoreCase("triceps_advance")) {
            switch (position) {
                case 1 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay1Workout());
                    break;
                case 2 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay2Workout());
                    break;
                case 3 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay3Workout());
                    break;
                case 5 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay5Workout());
                    break;
                case 6 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getBeginnerTricepsDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getAdvancedTricepsDay30Workout());
                    break;


            }

            workout_adapter.notifyDataSetChanged();
        }


        else if (workoutType.equalsIgnoreCase("dumbell_beginner")) {
            switch (position) {
                case 1 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay1Workout());
                    break;
                case 2 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay2Workout());
                    break;
                case 3 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay3Workout());
                    break;
                case 5 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay5Workout());
                    break;
                case 6 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getBeginnerDumbbellDay30Workout());
                    break;


            }

            workout_adapter.notifyDataSetChanged();
        }

        else if (workoutType.equalsIgnoreCase("dumbell_inter")) {
            switch (position) {
                case 1 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay1Workout());
                    break;
                case 2 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay2Workout());
                    break;
                case 3 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay3Workout());
                    break;
                case 5 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay5Workout());
                    break;
                case 6 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getIntermediateDumbbellDay30Workout());
                    break;


            }

            workout_adapter.notifyDataSetChanged();
        }

        else if (workoutType.equalsIgnoreCase("dumbell_advance")) {
            switch (position) {
                case 1 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay1Workout());
                    break;
                case 2 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay2Workout());
                    break;
                case 3 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay3Workout());
                    break;
                case 5 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay5Workout());
                    break;
                case 6 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay6Workout());
                    break;
                case 7 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay7Workout());
                    break;
                case 9 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay9Workout());
                    break;
                case 10 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay10Workout());
                    break;
                case 11 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay11Workout());
                    break;
                case 13 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay13Workout());
                    break;
                case 14 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay14Workout());
                    break;
                case 15 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay15Workout());
                    break;
                case 17 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay17Workout());
                    break;
                case 18 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay18Workout());
                    break;
                case 19 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay19Workout());
                    break;
                case 21 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay21Workout());
                    break;
                case 22 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay22Workout());
                    break;
                case 23 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay23Workout());
                    break;
                case 25 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay25Workout());
                    break;
                case 26 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay26Workout());
                    break;
                case 27 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay27Workout());
                    break;
                case 29 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay29Workout());
                    break;
                case 30 :
                    workoutModels.addAll(Constants.getAdvancedDumbbellDay30Workout());
                    break;


            }

            workout_adapter.notifyDataSetChanged();
        }


    }
}