package in.softment.armworkout.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import in.softment.armworkout.MainActivity;
import in.softment.armworkout.Model.UserModel;
import in.softment.armworkout.ProfileSetup.GenderAcitivity;
import in.softment.armworkout.R;

public class Services {


    public static  String convertFullDateToDate(String strDate){
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = originalFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }

    public static String convertDate(Long dayInMileSecond, String dateFormat){
        return DateFormat.format(dateFormat, dayInMileSecond).toString();
    }
    public static Date getStringToDate(String strDt) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            return formatter.parse(strDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }
    public static String convertLongToDay(String strDate) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd", Locale.getDefault());
        try {
            Date date = originalFormat.parse(strDate);
            return  targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return  "";
    }

    public static ArrayList<String> getCurrentWeekByFirstDay(Context context) {

        ArrayList<String> currentWeekArrayList = new ArrayList<String>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            for(int i=0 ; i < 7; i++) {
                try {
                    String data =  format.format(calendar.getTime());
                    currentWeekArrayList.add(data);
                    calendar.add(Calendar.DAY_OF_MONTH,1);
                }
                catch (Exception ignored) {

                }

            }

        }
        catch (Exception exception) {
            Services.showDialog(context, "Error", exception.getMessage());
        }
        return currentWeekArrayList;

    }





    public String getFirstWeekDayNameByDayNo(int dayNo) {
        String dayNumber = "";
        switch (dayNo) {
            case 1 : dayNumber = "Sunday";
                break;
            case 2 : dayNumber = "Monday";
                break;
            case 3: dayNumber = "Saturday";
                break;
        }

        return dayNumber;

    }



    public static void setAlphaAnimation(View v) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v, "alpha", 0.1f, 1.0f);
        fadeOut.setDuration(1000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", 0.3f, 1.0f);
        fadeIn.setDuration(800);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeOut);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                 //mAnimationSet.start();
            }
        });
        mAnimationSet.start();
    }

    public static void showToast(Context context,String message){
      Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER,0,0);
      toast.show();
    }

    public static void DownloadTTSEngine(Context context) {
        try {

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=text to speech&c=apps")));
        } catch (Exception e) {
            context.startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/search?q=text to speech&c=apps")
                    )
            );
        }
    }

    public static void showDialog(Context context,String title,String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        Activity activity = (Activity) context;
        View view = activity.getLayoutInflater().inflate(R.layout.error_message_layout, null);
        TextView titleView = view.findViewById(R.id.title);
        TextView msg = view.findViewById(R.id.message);
        titleView.setText(title);
        msg.setText(message);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        if(!((Activity) context).isFinishing())
        {
            alertDialog.show();

        }

    }

    public static void getCurrentUserData(Context context,String uid, Boolean showProgress) {

        if (showProgress) {
            ProgressHud.show(context,"");
        }

        FirebaseFirestore.getInstance().collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (showProgress) {
                    ProgressHud.dialog.dismiss();
                }

                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        documentSnapshot.toObject(UserModel.class);

                        if (UserModel.data != null) {
                            if (UserModel.data.gender != "") {
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(context, GenderAcitivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intent);
                            }


                        }
                        else  {
                            showCenterToast(context,"Something Went Wrong. Code - 101");
                        }
                    }
                    else {

                        String imageURL =  "https://firebasestorage.googleapis.com/v0/b/armworkout-2b1df.appspot.com/o/man1.jpeg?alt=media&token=f0cc5f65-2fca-411f-bc98-c8b1379f7c8f";
                        if (FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl() != null) {
                            imageURL = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
                        }

                        String sEmail = FirebaseAuth.getInstance().getCurrentUser().getDisplayName()+"@armworkout.com";
                        sEmail = sEmail.replaceAll(" ","");
                        for (UserInfo user: Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getProviderData()) {

                            if (user.getEmail() != null) {
                                sEmail = user.getEmail();

                            }
                        }
                        addUserDataOnServer(context,FirebaseAuth.getInstance().getCurrentUser().getUid(),FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), sEmail,imageURL);

                    }
                }
                else {
                    showCenterToast(context,"Something Went Wrong. Code - 102");
                }

            }
        });
    }

    public static void showCenterToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0,0);
        toast.show();
    }



    public static void addUserDataOnServer(Context context,String uid,String fullName, String emailAddress, String imageUrl){
        Map<String,Object> user = new HashMap<>();
        user.put("uid",uid);
        user.put("fullName",fullName);
        user.put("emailAddress",emailAddress);
        user.put("profileImage",imageUrl);
        user.put("date", FieldValue.serverTimestamp());

        ProgressHud.show(context,"");
        FirebaseFirestore.getInstance().collection("Users").document(uid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                ProgressHud.dialog.dismiss();
                if (task.isSuccessful()) {
                    Services.getCurrentUserData(context,FirebaseAuth.getInstance().getCurrentUser().getUid(),true);
                }
            }
        });
    }

}
