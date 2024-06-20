package in.softment.armworkout.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.softment.armworkout.MainActivity;
import in.softment.armworkout.R;
import in.softment.armworkout.ShowWorkoutActivity;

public class BicepsFragment extends Fragment {


    private Context context;

    public BicepsFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_biceps, container, false);

       view.findViewById(R.id.bicep_beginner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowWorkoutActivity.class);
                intent.putExtra("workoutType","beginner_biceps");
                startActivity(intent);
            }
     });
        view.findViewById(R.id.bicep_inter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowWorkoutActivity.class);
                intent.putExtra("workoutType","beginner_inter");
                startActivity(intent);
            }
        });
        view.findViewById(R.id.bicep_advance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowWorkoutActivity.class);
                intent.putExtra("workoutType","beginner_advance");
                startActivity(intent);
            }
        });
        return view;
    }
}