package in.softment.armworkout.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.softment.armworkout.R;
import in.softment.armworkout.ShowWorkoutActivity;

public class HorizontalDayAdapter extends RecyclerView.Adapter<HorizontalDayAdapter.ViewHolder> {
    private Context context;
    private int selectedIndex = 0;
    public HorizontalDayAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.horizontal_day_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.day1.setText((position+1)+"");
        holder.day.setText((position+1)+"");

        if (position == selectedIndex) {
            holder.selectedLL.setVisibility(View.VISIBLE);
            holder.deselectLL.setVisibility(View.GONE);
        }
        else {
            holder.selectedLL.setVisibility(View.GONE);
            holder.deselectLL.setVisibility(View.VISIBLE);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedIndex = holder.getAdapterPosition();
                ((ShowWorkoutActivity)context).changeWorkoutDay(holder.getAdapterPosition() + 1);
                notifyDataSetChanged();

                
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout selectedLL, deselectLL;
        private TextView day, day1;
        private View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            selectedLL = itemView.findViewById(R.id.selected_ll);
            deselectLL = itemView.findViewById(R.id.deselect_ll);
            day = itemView.findViewById(R.id.day);
            day1 = itemView.findViewById(R.id.day1);
            view = itemView;



        }
    }
}
