package in.softment.armworkout.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.softment.armworkout.Model.ReminderModel;
import in.softment.armworkout.R;
import in.softment.armworkout.WorkoutReminder;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ReminderModel> reminderModels;

    public ReminderAdapter(Context context, ArrayList<ReminderModel> reminderModels){
        this.context = context;
        this.reminderModels = reminderModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        ReminderModel reminderModel = reminderModels.get(position);
        holder.isActivate.setChecked(reminderModel.isActivate);
        String hours = "";
        String minutes = "";

        if (reminderModel.hour < 10) {
            hours = String.format("%02d", reminderModel.hour);
        }
        else {
            hours = String.valueOf(reminderModel.hour);
        }

        if (reminderModel.hour < 10) {
            minutes = String.format("%02d", reminderModel.minute);
        }
        else {
            minutes = String.valueOf(reminderModel.minute);
        }
        holder.time.setText(hours+":"+minutes);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((WorkoutReminder)context).deleteReminder(holder.getAdapterPosition());
            }
        });

        holder.isActivate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ((WorkoutReminder)context).updateReminder(holder.getAdapterPosition(), b);
            }
        });
    }

    @Override
    public int getItemCount() {

        return reminderModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private SwitchCompat isActivate;
        private ImageView deleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            isActivate = itemView.findViewById(R.id.reminderToggle);
            deleteBtn = itemView.findViewById(R.id.delete);

        }
    }
}
