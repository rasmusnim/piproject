package coolcats.se.homecontrol;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nim on 2017-03-26.
 */

public class MyAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();

    public MyAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new LedFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
