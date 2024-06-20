package in.softment.armworkout.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.softment.armworkout.Model.UserModel;
import in.softment.armworkout.R;
import in.softment.armworkout.Utils.NonSwipeAbleViewPager;
import in.softment.armworkout.Utils.Services;


public class HomeFragment extends Fragment {
    private RelativeLayout bicepsRR, tricepsRR, dumbellRR;
    public NonSwipeAbleViewPager viewPager;
    private Context context;
    private ViewPagerAdapter adapter;

    public HomeFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    public HomeFragment() {

    }



    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new TricepsFragment(context));
        adapter.addFrag(new BicepsFragment(context));
        adapter.addFrag(new DumbellFragment(context));
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private int mCurrentPosition = -1;

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


        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);

            if (position != mCurrentPosition && container instanceof NonSwipeAbleViewPager) {
                Fragment fragment = (Fragment) object;
                NonSwipeAbleViewPager pager = (NonSwipeAbleViewPager) container;

                if (fragment.getView() != null) {
                    mCurrentPosition = position;
                    pager.measureCurrentView(fragment.getView());
                }
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        CircleImageView profileImage = view.findViewById(R.id.profileImage);
        Glide.with(context).load(UserModel.data.getProfileImage()).placeholder(R.drawable.placeholder_profile).into(profileImage);

        HorizontalScrollView horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        tricepsRR = view.findViewById(R.id.tricepsRR);
        bicepsRR = view.findViewById(R.id.bicepsRR);
        dumbellRR = view.findViewById(R.id.dumbellRR);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        ImageView back1 = view.findViewById(R.id.back1);
        ImageView back2 = view.findViewById(R.id.back2);
        ImageView back3 = view.findViewById(R.id.back3);

        TextView title1 = view.findViewById(R.id.tricepsTX);
        TextView title2 = view.findViewById(R.id.bicepsTX);
        TextView title3 = view.findViewById(R.id.dumbellTX);

        TextView workout1 = view.findViewById(R.id.workTX1);
        TextView workout2 = view.findViewById(R.id.workTX2);
        TextView workout3 = view.findViewById(R.id.workTX3);

        ImageView triceps = view.findViewById(R.id.triceps);
        ImageView biceps = view.findViewById(R.id.biceps);
        ImageView dumbell = view.findViewById(R.id.dumbell);


        final float scale = getResources().getDisplayMetrics().density;
        int px200 = (int) (200 * scale + 0.5f);  // replace 100 with your dimensions
        int px130 = (int) (130 * scale + 0.5f);  // replace 100 with your dimensions
        int px110 = (int) (110 * scale + 0.5f);  // replace 100 with your dimensions
        int px172 = (int) (172 * scale + 0.5f);  // replace 100 with your dimensions

        tricepsRR.getLayoutParams().height = px200;
        tricepsRR.getLayoutParams().width = px130;

        bicepsRR.getLayoutParams().height = px172;
        bicepsRR.getLayoutParams().width = px110;

        dumbellRR.getLayoutParams().height = px172;
        dumbellRR.getLayoutParams().width = px110;


        tricepsRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Services.setAlphaAnimation(view);

                horizontalScrollView.fullScroll(ScrollView.SCROLLBAR_POSITION_LEFT);

                tricepsRR.getLayoutParams().height = px200;
                tricepsRR.getLayoutParams().width = px130;

                bicepsRR.getLayoutParams().height = px172;
                bicepsRR.getLayoutParams().width = px110;

                dumbellRR.getLayoutParams().height = px172;
                dumbellRR.getLayoutParams().width = px110;

                bicepsRR.requestLayout();
                tricepsRR.requestLayout();
                dumbellRR.requestLayout();

                back1.setVisibility(View.GONE);
                back2.setVisibility(View.VISIBLE);
                back3.setVisibility(View.VISIBLE);


                triceps.setVisibility(View.VISIBLE);
                biceps.setVisibility(View.GONE);
                dumbell.setVisibility(View.GONE);


                title1.setVisibility(View.VISIBLE);
                workout1.setVisibility(View.VISIBLE);

                title2.setVisibility(View.GONE);
                workout2.setVisibility(View.GONE);

                title3.setVisibility(View.GONE);
                workout3.setVisibility(View.GONE);


                viewPager.setCurrentItem(0);
            }
        });

        bicepsRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Services.setAlphaAnimation(view);
                tricepsRR.getLayoutParams().height = px172;
                tricepsRR.getLayoutParams().width = px110;

                bicepsRR.getLayoutParams().height = px200;
                bicepsRR.getLayoutParams().width = px130;

                dumbellRR.getLayoutParams().height = px172;
                dumbellRR.getLayoutParams().width = px110;


                bicepsRR.requestLayout();
                tricepsRR.requestLayout();
                dumbellRR.requestLayout();

                back1.setVisibility(View.VISIBLE);
                back2.setVisibility(View.GONE);
                back3.setVisibility(View.VISIBLE);


                triceps.setVisibility(View.GONE);
                biceps.setVisibility(View.VISIBLE);
                dumbell.setVisibility(View.GONE);

                title1.setVisibility(View.GONE);
                workout1.setVisibility(View.GONE);

                title2.setVisibility(View.VISIBLE);
                workout2.setVisibility(View.VISIBLE);

                title3.setVisibility(View.GONE);
                workout3.setVisibility(View.GONE);



                viewPager.setCurrentItem(1);
            }
        });



        dumbellRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Services.setAlphaAnimation(view);
                horizontalScrollView.fullScroll(View.FOCUS_RIGHT);

                tricepsRR.getLayoutParams().height = px172;
                tricepsRR.getLayoutParams().width = px110;

                bicepsRR.getLayoutParams().height = px172;
                bicepsRR.getLayoutParams().width = px110;

                dumbellRR.getLayoutParams().height = px200;
                dumbellRR.getLayoutParams().width = px130;

                bicepsRR.requestLayout();
                tricepsRR.requestLayout();
                dumbellRR.requestLayout();

                back1.setVisibility(View.VISIBLE);
                back2.setVisibility(View.VISIBLE);
                back3.setVisibility(View.GONE);


                triceps.setVisibility(View.GONE);
                biceps.setVisibility(View.GONE);
                dumbell.setVisibility(View.VISIBLE);

                title1.setVisibility(View.GONE);
                workout1.setVisibility(View.GONE);

                title2.setVisibility(View.GONE);
                workout2.setVisibility(View.GONE);

                title3.setVisibility(View.VISIBLE);
                workout3.setVisibility(View.VISIBLE);


                viewPager.setCurrentItem(2);
            }
        });

        return view;
    }
}