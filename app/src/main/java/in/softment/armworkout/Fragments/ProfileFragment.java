package in.softment.armworkout.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import in.softment.armworkout.Model.UserModel;
import in.softment.armworkout.R;
import in.softment.armworkout.SettingsActivity;


public class ProfileFragment extends Fragment {


    private Context context;
    public ProfileFragment(Context context) {
        // Required empty public constructor
        this.context = context;


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        view.findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SettingsActivity.class));
            }
        });

        ImageView profileImage = view.findViewById(R.id.profileImage);
        Glide.with(context).load(UserModel.data.profileImage).placeholder(R.drawable.man1).into(profileImage);

        TextView name = view.findViewById(R.id.name);
        name.setText(UserModel.data.getFullName());

        TextView weightTV = view.findViewById(R.id.weightTV);
        weightTV.setText(UserModel.data.getWeight() + " kg");

        TextView heightTV = view.findViewById(R.id.heightTV);
        heightTV.setText(UserModel.data.getHeight() + " feet");

        double weight = Double.parseDouble(UserModel.data.getWeight());
        double height = Double.parseDouble(UserModel.data.getHeight());
        Log.d("HEIGHT",height+"");
        double meter = height / 3.281;
        Log.d("METER",meter+"");
        double dBmi =(weight / ((meter) * (meter)));
        String sBMI = String.format("%.1f", dBmi);

        TextView bmiTV = view.findViewById(R.id.bmiTV);
        bmiTV.setText(sBMI+" %");

        return view;
    }
}