package in.softment.armworkout.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalDB {



        public static SharedPreferences getSharedPreferences(Context context) {
            return context.getSharedPreferences("arm_workout",0);
        }

        public static void setLastWorkoutDayId(Context context, String workoutID, String dayId) {
            getSharedPreferences(context).edit().putString(workoutID, dayId).apply();
        }


      public static String getLastWorkoutDayId(Context context, String workoutID, String dayId) {
           return getSharedPreferences(context).getString(workoutID,"");
      }

    public static void setLastWorkoutDayId(Context context, boolean isFirstTime) {
        getSharedPreferences(context).edit().putBoolean("LAST_COMPLETE_DAY_ID", isFirstTime).apply();
    }


    public static boolean getLastWorkoutDayId(Context context) {
        return getSharedPreferences(context).getBoolean("LAST_COMPLETE_DAY_ID",true);
    }

        public static void setSoundMute(Context context, boolean isMute) {
            getSharedPreferences(context).edit().putBoolean("MUTE",isMute).apply();
        }

        public static boolean getSoundMute(Context context) {
            return getSharedPreferences(context).getBoolean("MUTE",false);
        }

        public static void setVoiceGuide(Context context,boolean isMute) {
            getSharedPreferences(context).edit().putBoolean("VOICE_GUIDE",isMute).apply();
        }
    public static boolean getVoiceGuide(Context context) {
       return getSharedPreferences(context).getBoolean("VOICE_GUIDE",false);
    }

    public static void setCoachTips(Context context,boolean isMute) {
        getSharedPreferences(context).edit().putBoolean("COACH_TIPS",isMute).apply();
    }
    public static boolean getCoachTips(Context context) {
        return getSharedPreferences(context).getBoolean("COACH_TIPS",false);
    }


    public static void setCountDownTime(Context context, int weight) {
        getSharedPreferences(context).edit().putInt("COUNTDOWN_TIME", weight).apply();
    }

    public static int getCountDownTime(Context context) {
        return  getSharedPreferences(context).getInt("COUNTDOWN_TIME",15);
    }

    public static void setRestTime(Context context, int weight) {
        getSharedPreferences(context).edit().putInt("REST_TIME", weight).apply();
    }

    public static int getRestTime(Context context) {
        return  getSharedPreferences(context).getInt("REST_TIME",30);
    }


    public static void setKeppScreen(Context context, boolean isSet) {
        getSharedPreferences(context).edit().putBoolean("KEEP_SCREEN", isSet).apply();
    }

    public static boolean getKeepScreen(Context context) {
        return getSharedPreferences(context).getBoolean("KEEP_SCREEN",false);
    }





}
