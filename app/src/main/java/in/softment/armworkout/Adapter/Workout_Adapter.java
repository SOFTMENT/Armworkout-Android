package in.softment.armworkout.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

import in.softment.armworkout.Model.WorkoutModel;
import in.softment.armworkout.R;
import in.softment.armworkout.ShowWorkoutDetailsActivity;

public class Workout_Adapter extends RecyclerView.Adapter<Workout_Adapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<WorkoutModel> workoutModels;
    public Workout_Adapter(Context context,ArrayList<WorkoutModel> workoutModels){
        this.context = context;
        this.workoutModels = workoutModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.wokout_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            WorkoutModel workoutModel = workoutModels.get(position);
            
            holder.lottieAnimationView.setScaleY(2f);
            holder.lottieAnimationView.setScaleX(2f);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ShowWorkoutDetailsActivity.class);
                    intent.putExtra("workout",workoutModel);
                    context.startActivity(intent);
                }
            });
            holder.title.setText(workoutModel.name);
            if (workoutModel.hasSeconds) {
                holder.durations_repetations.setText(workoutModel.durations_repetations + " s");

            }
            else {
                holder.durations_repetations.setText("x"+workoutModel.durations_repetations);
            }
            holder.lottieAnimationView.setAnimation(workoutModel.workout_animation);

    }

    @Override
    public int getItemCount() {
        return workoutModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final LottieAnimationView lottieAnimationView;
        private final TextView title;
        private final TextView durations_repetations;
        private View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            lottieAnimationView = itemView.findViewById(R.id.lottie_workout);
            durations_repetations = itemView.findViewById(R.id.durations_repetations);
            title = itemView.findViewById(R.id.title);

        }
    }
}
