package in.softment.armworkout.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.softment.armworkout.R;
import in.softment.armworkout.ShowWorkoutActivity;


public class TricepsFragment extends Fragment {

    private Context context;
    public TricepsFragment(Context context) {
       this.context = context;
    }
    public TricepsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_triceps, container, false);

        view.findViewById(R.id.triceps_beginner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowWorkoutActivity.class);
                intent.putExtra("workoutType","triceps_beginner");
                startActivity(intent);
            }
        });
        view.findViewById(R.id.triceps_inter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowWorkoutActivity.class);
                intent.putExtra("workoutType","triceps_inter");
                startActivity(intent);
            }
        });
        view.findViewById(R.id.triceps_advance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowWorkoutActivity.class);
                intent.putExtra("workoutType","triceps_advance");
                startActivity(intent);
            }
        });
        return view;
    }
}