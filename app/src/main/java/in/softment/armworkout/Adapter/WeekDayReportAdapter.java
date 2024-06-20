package in.softment.armworkout.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import in.softment.armworkout.R;
import in.softment.armworkout.Utils.Services;

public class WeekDayReportAdapter extends RecyclerView.Adapter<WeekDayReportAdapter.MyViewHolder> {

   ArrayList<String> arrCurrentWeek;
   private Context context;
   public WeekDayReportAdapter(Context context) {
       this.context = context;
       arrCurrentWeek = Services.getCurrentWeekByFirstDay(context);
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.row_week_day,parent,false);
       view.getLayoutParams().width = parent.getMeasuredWidth() / 7;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtDayDate.setText(Services.convertLongToDay(arrCurrentWeek.get(position)));
        Date dtWeek = Services.getStringToDate(arrCurrentWeek.get(position));

        Calendar calToday = Calendar.getInstance();

        calToday.setTime(dtWeek);
        holder.txtDayName.setText(Services.convertDate(calToday.getTimeInMillis(),"E").substring(0,1));
        if (Services.convertDate(Calendar.getInstance().getTimeInMillis(), "dd").equals(Services.convertLongToDay(arrCurrentWeek.get(position)))) {
            holder.txtDayDate.setTextColor(ContextCompat.getColor(context,R.color.red));
        }

        if (Calendar.getInstance().getTime().after(dtWeek)) {
            holder.imgIsWorkoutDay.setImageResource(R.drawable.ic_cal_round_fill);
        } else {
            holder.imgIsWorkoutDay.setImageResource(R.drawable.ic_cal_round);
        }
    }

    @Override
    public int getItemCount() {
        return arrCurrentWeek.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDayName;
        TextView txtDayDate;
        ImageView imgIsWorkoutDay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDayName = itemView.findViewById(R.id.txtDayName);
            txtDayDate = itemView.findViewById(R.id.txtDayDate);
            imgIsWorkoutDay = itemView.findViewById(R.id.imgIsWorkoutDay);

        }
    }
}
