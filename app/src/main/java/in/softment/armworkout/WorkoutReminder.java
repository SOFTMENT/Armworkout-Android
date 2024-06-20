package in.softment.armworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Service;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.softment.armworkout.Adapter.ReminderAdapter;
import in.softment.armworkout.Model.ReminderModel;
import in.softment.armworkout.Model.WorkoutModel;
import in.softment.armworkout.Utils.Services;

public class WorkoutReminder extends AppCompatActivity {

    private ReminderAdapter reminderAdapter;
    private ArrayList<ReminderModel> reminderModels;
    private TextView please_set_reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_reminder);

        please_set_reminder = findViewById(R.id.please_set_your_reminder);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MyObject", "");
        Type type = new TypeToken<ArrayList<ReminderModel>>(){}.getType();
        reminderModels = gson.fromJson(json, type);
        if (reminderModels == null) {
            reminderModels = new ArrayList<>();
        }
        reminderAdapter = new ReminderAdapter(this, reminderModels);
        recyclerView.setAdapter(reminderAdapter);
        if (reminderModels.size() > 0) {
            hideReminderMessage();
        }
        else {
            showReminderMessage();
        }


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(WorkoutReminder.this,R.style.MyTimePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        ReminderModel reminderModel = new ReminderModel();
                        reminderModel.hour = selectedHour;
                        reminderModel.minute = selectedMinute;

                        reminderModel.isActivate = true;
                        reminderModels.add(reminderModel);
                        reminderAdapter.notifyDataSetChanged();
                        if (reminderModels.size() > 0) {
                            hideReminderMessage();
                        }
                        else {
                            showReminderMessage();
                        }


                        SharedPreferences appSharedPrefs = PreferenceManager
                                .getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(reminderModels);
                        prefsEditor.putString("MyObject", json);
                        prefsEditor.apply();
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });
    }

    public void deleteReminder(int position){
        Services.showToast(this,"Deleted");
        reminderModels.remove(position);
        reminderAdapter.notifyDataSetChanged();
        if (reminderModels.size() > 0) {
            hideReminderMessage();
        }
        else {
            showReminderMessage();
        }
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(reminderModels);
        prefsEditor.putString("MyObject", json);
        prefsEditor.apply();
    }

    public void updateReminder(int position, boolean isActivate){

        ReminderModel reminderModel = reminderModels.remove(position);
        reminderModel.isActivate = isActivate;
        reminderModels.add(position, reminderModel);
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(reminderModels);
        prefsEditor.putString("MyObject", json);
        prefsEditor.apply();


    }

    public void hideReminderMessage(){
        please_set_reminder.setVisibility(View.GONE);
    }

    public void showReminderMessage(){
        please_set_reminder.setVisibility(View.VISIBLE);
    }
}

class ObjectSerializer {

    public static String serialize(Serializable obj) throws IOException {
        if (obj == null) return "";
        ByteArrayOutputStream serialObj = new ByteArrayOutputStream();
        ObjectOutputStream objStream = new ObjectOutputStream(serialObj);
        objStream.writeObject(obj);
        objStream.close();
        return encodeBytes(serialObj.toByteArray());
    }

    public static Object deserialize(String str) throws IOException, ClassNotFoundException {
        if (str == null || str.length() == 0) return null;
        ByteArrayInputStream serialObj = new ByteArrayInputStream(decodeBytes(str));
        ObjectInputStream objStream = new ObjectInputStream(serialObj);
        return objStream.readObject();
    }

    public static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }

    public static byte[] decodeBytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i+=2) {
            char c = str.charAt(i);
            bytes[i/2] = (byte) ((c - 'a') << 4);
            c = str.charAt(i+1);
            bytes[i/2] += (c - 'a');
        }
        return bytes;
    }
}