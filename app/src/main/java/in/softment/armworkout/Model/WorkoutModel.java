package in.softment.armworkout.Model;

import java.io.Serializable;

public class WorkoutModel implements Serializable {
    public String name;
    public int workout_animation, durations_repetations;
    public boolean hasSeconds;
    public String description;
    public int exId;
    public WorkoutModel(int exId,String description,int workout_animation, String name, int durations_repetations, boolean hasSeconds){
        this.exId = exId;
        this.workout_animation = workout_animation;
        this.name = name;
        this.durations_repetations = durations_repetations;
        this.hasSeconds = hasSeconds;
        this.description = description;
    }
}
