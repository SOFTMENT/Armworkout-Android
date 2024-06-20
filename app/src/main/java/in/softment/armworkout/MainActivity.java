package in.softment.armworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import in.softment.armworkout.Fragments.HomeFragment;
import in.softment.armworkout.Fragments.ProfileFragment;

import in.softment.armworkout.Fragments.ReportFragment;
import in.softment.armworkout.Utils.TextToSpeechService;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

       TextToSpeechService.speechTextToProfile(this,"");

        viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        //BottomLayout
        ImageView homeImage = findViewById(R.id.homeImage);
        ImageView reportImage = findViewById(R.id.reportImage);
        ImageView profileImage = findViewById(R.id.profileImage);

        ImageView homeDot = findViewById(R.id.homeDot);
        ImageView reportDot = findViewById(R.id.reportDot);
        ImageView profileDot = findViewById(R.id.profileDot);


        findViewById(R.id.homeView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(0);
                homeImage.setImageResource(R.drawable.blue_home);
                homeDot.setVisibility(View.VISIBLE);

                reportImage.setImageResource(R.drawable.gray_report);
                reportDot.setVisibility(View.GONE);

                profileImage.setImageResource(R.drawable.gray_profile);
                profileDot.setVisibility(View.GONE);
            }
        });

        findViewById(R.id.reportView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(1);
                homeImage.setImageResource(R.drawable.gray_home);
                homeDot.setVisibility(View.GONE);

                reportImage.setImageResource(R.drawable.blue_report);
                reportDot.setVisibility(View.VISIBLE);

                profileImage.setImageResource(R.drawable.gray_profile);
                profileDot.setVisibility(View.GONE);

            }
        });

        findViewById(R.id.profileView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(2);
                homeImage.setImageResource(R.drawable.gray_home);
                homeDot.setVisibility(View.GONE);

                reportImage.setImageResource(R.drawable.gray_report);
                reportDot.setVisibility(View.GONE);

                profileImage.setImageResource(R.drawable.blue_profile);
                profileDot.setVisibility(View.VISIBLE);
            }
        });
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(this));
        adapter.addFrag(new ReportFragment(this));
        adapter.addFrag(new ProfileFragment(this));


        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}