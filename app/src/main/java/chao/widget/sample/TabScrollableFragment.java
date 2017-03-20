package chao.widget.sample;

import android.os.Bundle;
import android.view.View;

import chao.widget.tablayout.TabLayout;

/**
 * @author chao.qin
 * @since 2017/3/20.
 */
public class TabScrollableFragment extends TabFragment {
    @Override
    protected void setupView(View layout, Bundle savedInstanceState) {
        super.setupView(layout, savedInstanceState);
        setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected Class[] getFragments() {
        return new Class[]{FragmentA.class,FragmentB.class,FragmentC.class,FragmentD.class,FragmentE.class,FragmentF.class,FragmentG.class};
    }

    @Override
    protected int[] getTitleIds() {
        return new int[]{R.string.title_fragment_a,R.string.title_fragment_b,R.string.title_fragment_c,R.string.title_fragment_d,R.string.title_fragment_e,R.string.fragment_f,R.string.fragment_g};
    }
}
