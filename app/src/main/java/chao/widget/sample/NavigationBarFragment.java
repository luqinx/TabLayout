package chao.widget.sample;

import android.os.Bundle;
import android.view.View;

import chao.widget.tablayout.TabLayout;

@LayoutID(R.layout.fragment_navigation_bar)
public class NavigationBarFragment extends TabFragment {

    @Override
    protected Class[] getFragments() {
        return new Class[]{
                TabFixedFragment.class,
                TabFixedCommonFragment.class,
                TabScrollableFragment.class,
                ForumFragment.class,
                MineFragment.class
        };
    }

    @Override
    protected int[] getTitleIds() {
        return new int[]{
                R.string.title_fragment_fixed,
                R.string.title_fragment_common,
                R.string.title_fragment_scrollable,
                R.string.title_message,
                R.string.title_mine
        };
    }

    protected int[] getIconResource() {
        return new int[]{
                R.drawable.selector_main_fulljob_tab_image,
                R.drawable.selector_main_internship_tab_image,
                R.drawable.selector_main_report_tab_image,
                R.drawable.selector_main_forum_tab_image,
                R.drawable.selector_main_mine_tab_image
        };
    }

    @Override
    protected void setupView(View layout, Bundle savedInstanceState) {
        super.setupView(layout, savedInstanceState);

        setTabMode(TabLayout.MODE_FIXED);

        setScrollEnable(false);
    }
}
