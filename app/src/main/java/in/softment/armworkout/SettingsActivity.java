package in.softment.armworkout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.BuildConfig;

import in.softment.armworkout.Utils.AppControl;
import in.softment.armworkout.Utils.LocalDB;
import in.softment.armworkout.Utils.Services;
import in.softment.armworkout.Utils.TextToSpeechService;

public class SettingsActivity extends AppCompatActivity {
    boolean boolIsSelectEngine = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Back
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //SyncToGoogleFit
        SwitchCompat syncToGoogleFit = findViewById(R.id.googleFitSwitch);
        syncToGoogleFit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Services.showToast(SettingsActivity.this,"Connetced with Google Fit Successfully");
                }
                else {
                    Services.showToast(SettingsActivity.this,"Disconnetced from Google Fit Successfully");
                }
            }
        });

        //SoundOption
        findViewById(R.id.soundOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundOptionDialog();
            }
        });

        //TestVoice
        findViewById(R.id.testVoice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    boolIsSelectEngine = false;
                    TextToSpeechService.speechTextToProfile(SettingsActivity.this, "Did you hear test voice?");
                     try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                confirmVoiceTest(SettingsActivity.this, "", "Did you hear test voice?", "CONFIRM_TEST_VOICE");

            }
        });

        //Select TTS Engine
        findViewById(R.id.select_tts_engine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolIsSelectEngine = true;
                selectTTSEngine();
            }
        });

        //Download TTS Engine
        findViewById(R.id.downloadttsengine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Services.DownloadTTSEngine(SettingsActivity.this);
            }
        });

        //Device TTS Settings
        findViewById(R.id.device_tts_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.android.settings.TTS_SETTINGS");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        //Health Data
        findViewById(R.id.healthData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this,HealthDataActivity.class));
            }
        });

      //Reminder
        findViewById(R.id.remind_to_workout_every_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, WorkoutReminder.class));
            }
        });



        //Language
        findViewById(R.id.language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //DeleteAllData
        findViewById(R.id.deleteAllData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //RateUs
        findViewById(R.id.rateUs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Arm Workout");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });

        //Feedback
        findViewById(R.id.feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(SettingsActivity.this, FeedbackActivity.class));
            }
        });

        //PrivacyPolicy
        findViewById(R.id.privacyPolicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.softment.in/privacy-policy"));
                             startActivity(browserIntent);
            }
        });




    }

    public void soundOptionDialog() {

        Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dl_sound_option);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        SwitchCompat mute = dialog.findViewById(R.id.swtMute);
        SwitchCompat voiceGuide = dialog.findViewById(R.id.swtVoiceGuide);
        SwitchCompat coachTips = dialog.findViewById(R.id.swtCoachTips);
        TextView btnOk = dialog.findViewById(R.id.btnOk);

        voiceGuide.setChecked(LocalDB.getVoiceGuide(this));
        coachTips.setChecked(LocalDB.getCoachTips(this));

        if (LocalDB.getSoundMute(this)) {
            mute.setChecked(true);
            voiceGuide.setChecked(false);
            coachTips.setChecked(false);
        }


        voiceGuide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {


                        if (b) {

                            mute.setChecked(false);
                            voiceGuide.setChecked(true);
                            LocalDB.setSoundMute(SettingsActivity.this,false);
                            LocalDB.setVoiceGuide(SettingsActivity.this, true);

                        }
                        else {
                            LocalDB.setVoiceGuide(SettingsActivity.this, false);
                            voiceGuide.setChecked(LocalDB.getVoiceGuide(SettingsActivity.this));
                            coachTips.setChecked(LocalDB.getCoachTips(SettingsActivity.this));

                        }



                }
                catch (Exception e) {
                    Log.d("ERROR",e.getLocalizedMessage());

                }
            }
        });

        mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {


                        if (b) {
                            LocalDB.setSoundMute(SettingsActivity.this, true);
                            voiceGuide.setChecked(false);
                            coachTips.setChecked(false);
                            LocalDB.setVoiceGuide(SettingsActivity.this,false);
                            LocalDB.setCoachTips(SettingsActivity.this, false);

                        }
                        else {
                            LocalDB.setSoundMute(SettingsActivity.this, false);
                            voiceGuide.setChecked(LocalDB.getVoiceGuide(SettingsActivity.this));
                            coachTips.setChecked(LocalDB.getCoachTips(SettingsActivity.this));

                        }



                }
                catch (Exception e) {
                    Log.d("ERROR",e.getLocalizedMessage());

                }
            }
        });

        coachTips.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try{


                        if (b) {
                            mute.setChecked(false);
                            coachTips.setChecked(true);
                            LocalDB.setSoundMute(SettingsActivity.this, false);
                            LocalDB.setCoachTips(SettingsActivity.this, true);
                        }
                        else {
                            LocalDB.setCoachTips(SettingsActivity.this, false);

                        }

                }
                catch (Exception e) {
                    Log.d("ERROR",e.getLocalizedMessage());
                }
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialog.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        dialog.show();

    }

    public void confirmVoiceTest(Context context, String strTitle, String strMsg, String strType) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this,R.style.AlertDialogTheme);
        builder.setMessage(strMsg);
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                if ("CONFIRM_TEST_VOICE".equals(strType)) {
                    testVoiceFailDialog();
                }

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private void testVoiceFailDialog() {

        Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dl_test_voice_fail);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        AppCompatButton btnDownloadTTSEngine = dialog.findViewById(R.id.btnDownloadTTSEngine);
        AppCompatButton btnSelectTTSEngine = dialog.findViewById(R.id.btnSelectTTSEngine);


        btnDownloadTTSEngine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Services.DownloadTTSEngine(SettingsActivity.this);
                dialog.cancel();
            }
        });

        btnSelectTTSEngine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.cancel();
            }
        });

        btnSelectTTSEngine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTTSEngine();
                dialog.cancel();
            }
        });



        dialog.show();
    }


    private void selectTTSEngine() {
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dl_select_tts_engine);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RadioButton radioButton = dialog.findViewById(R.id.rdoGoogleEngine);

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                dialog.cancel();
                if (b) {
                    TextToSpeechService.speechTextToProfile(SettingsActivity.this,"Did you hear test voice");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    confirmVoiceTest(SettingsActivity.this, "", "Did you hear test voice", "CONFIRM_TEST_VOICE");
                    boolIsSelectEngine = false;
                }
            }
        });



        dialog.show();
    }

}