package in.softment.armworkout.Utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class TextToSpeechService {


    static TextToSpeech textToSpeech = null;

    public static void speechText(Context context, String strSpeechText) {

        if (textToSpeech == null) {
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i == TextToSpeech.ERROR) {

                        textToSpeech.setLanguage(Locale.ENGLISH);

                    }
                }

            });
        }

        if (checkVoiceOnorOff(context)) {
            if (textToSpeech == null) {
                textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if (i == TextToSpeech.ERROR) {

                            textToSpeech.setLanguage(Locale.ENGLISH);

                        }
                    }

                });
            }
                textToSpeech.setSpeechRate(0.9f);
                textToSpeech.speak(strSpeechText,TextToSpeech.QUEUE_FLUSH,null,null);

        }
    }

    public static boolean checkVoiceOnorOff(Context context) {
        return !LocalDB.getSoundMute(context) && LocalDB.getVoiceGuide(context);
    }

    public static void  speechTextToProfile(Context context, String strSpeechText) {
        if (textToSpeech == null) {
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i == TextToSpeech.ERROR) {

                        textToSpeech.setLanguage(Locale.ENGLISH);

                    }
                }

            });
        }
        textToSpeech.setSpeechRate(0.9f);
        textToSpeech.speak(strSpeechText, TextToSpeech.QUEUE_FLUSH, null, null);



    }


}
