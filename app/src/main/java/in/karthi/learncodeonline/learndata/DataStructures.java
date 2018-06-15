package in.karthi.learncodeonline.learndata;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class DataStructures extends FragmentActivity implements ReturnTypeFragment.OnButtonClickListener {

    ViewPager mViewPager;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_structures);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
/** set the adapter for ViewPager */
        mViewPager.setAdapter(new SamplePagerAdapter(
                getSupportFragmentManager()));

        MobileAds.initialize(this, "ca-app-pub-4272027379659463~3033094350");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onButtonClicked(View view) {
        int currPos=mViewPager.getCurrentItem();

        mViewPager.setCurrentItem(currPos+1);
    }

    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (position == 0) {
                return new ReturnTypeFragment();
            } else if (position == 1) {
                return new StackFragment();
            } else if(position == 2) {
                return new QueueFragment();
            }else
                return new BinaryFragment();
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 4;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
