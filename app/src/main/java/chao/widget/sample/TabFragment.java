package chao.widget.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import chao.widget.tablayout.TabLayout;


/**
 * @author chao.qin
 *
 * @since 2017/3/1.
 */

@LayoutID(R.layout.tab_fragment)
public abstract class TabFragment extends BasicFragment {

    private TabLayout mTab;
    private FixedViewPager mPager;

    @CallSuper
    @Override
    protected void setupView(View layout, Bundle savedInstanceState) {
        mTab = findViewById(R.id.tab_layout);
        mPager = findViewById(R.id.viewpager);

        MyPageAdapter adapter = new MyPageAdapter(getChildFragmentManager());


        mTab.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式

        mPager.setAdapter(adapter);

        mTab.setupWithViewPager(mPager);

        setTabCustomView();

        checkTabHost();
    }

    /**
     *
     * 设置是否可以滑动
     * 默认值为true，可以滑动
     * */
    public void setScrollEnable(boolean scrollEnable){
        mPager.setScrollEnable(scrollEnable);
    }
    /**
     *
     * 设置tab的显示模式
     *
     * MODE_FIXED：tabs固定全显示
     * MODE_SCROLLABLE：tabs可以滑动，显示部分
     * */
    public void setTabMode(int tabMode){
        mTab.setTabMode(tabMode);
    }

    /**
     * 设置指示器是否可见
     * @param indicatorEnable 指示器是否可见， true可见，false不可见，默认可见
     * */
    public void setIndicatorEnable(boolean indicatorEnable){
        if (!indicatorEnable){
            mTab.setSelectedTabIndicatorHeight(0);
        }
    }

    protected abstract Class[] getFragments();

    protected abstract int[] getTitleIds();

    protected int[] getIconResource(){
        return new int[getFragments().length];
    }


    /**
     * 基础功能区
     * */
    /**
     * 为tab设置添加自定义View视图
     * */
    private void setTabCustomView(){

        for (int i =0;  i < getFragments().length; i++){
            TabLayout.Tab tab = mTab.getTabAt(i);
            if (tab == null) {
                continue;
            }
            int iconId = getIconResource()[i];
            Drawable drawable = null;
            if (iconId != 0) {
                drawable = getResources().getDrawable(iconId);
            }
            tab.setIcon(drawable);
            tab.setText(getTitleIds()[i]);
        }
    }

    private void checkTabHost() {
        if (getFragments() == null) {
            throw new IllegalStateException("getFragments() cannot return null.");
        }

        if (getTitleIds() == null) {
            throw new IllegalStateException("getTitleIds() cannot return null.");
        }

        int length = getFragments().length;

        if (getTitleIds().length != length) {
            throw new IllegalStateException("fragment and titleId's must be same length.");
        }

        for (Class fragment: getFragments()) {
            if (!BasicFragment.class.isAssignableFrom(fragment)) {
                throw new IllegalStateException("getFragments() should return an array of BasicFragment instance.");
            }
        }
    }

    private class MyPageAdapter extends FragmentPagerAdapter {


        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return getFragments().length;
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return (Fragment) getFragments()[position].newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(getTitleIds()[position]);
        }

    }


    /**
     *  某些场合不需要ViewPager左右滑动
     */
    public static class FixedViewPager extends ViewPager {

        private boolean mScrollEnable = true;//设置ViewPager是否可以滑动,默认为true，可以滑动

        /**
         * 构造区
         * */
        public FixedViewPager(Context context){
            super(context);
        }
        public FixedViewPager(Context context, AttributeSet attrs){
            super(context, attrs);
        }

        /**
         * 对外接口区
         * */
        /**
         * 获取是否可以滑动
         * */
        public boolean isScrollEnable(){
            return mScrollEnable;
        }
        /**
         * 设置是否可以滑动
         * 设置为true可以滑动，设置为false不可以滑动
         * 默认值为true，可以滑动
         * */
        public void setScrollEnable(boolean enable){
            mScrollEnable = enable;
        }

        /**
         * 方法覆盖区
         * */
        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return mScrollEnable && super.onInterceptTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            return mScrollEnable && super.onTouchEvent(ev);
        }

    }

}
