package chao.widget.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类
 *
 * @author chao.qin
 * @since 51job 6.0 2016/6/1
 */
@LayoutID(R.layout.empty_layout)
public abstract class BasicFragment extends Fragment {

    private static final String TAG = "BasicFragment";
    protected static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    protected static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;


    public static final String KEY_FRAGMENT = "fragment";
    protected static final String KEY_RESULT_EXTRA = "result_extra";

    private Activity mActivity;

    private View mLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        debugPrint("onCreateView()");
        if (mLayout != null) {
            ViewGroup parentView = (ViewGroup) mLayout.getParent();
            if (parentView != null) {
                parentView.removeView(mLayout);
            }
            return mLayout;
        }
        View layout = inflater.inflate(getLayoutID(), container, false);
        mLayout = initContentView(layout, savedInstanceState);
        if (mLayout == null) {
            mLayout = layout;
        }
        setupView(mLayout, savedInstanceState);
        return mLayout;
    }

    /**
     * 初始化Fragment的布局
     *
     * 实现setupView来定义布局
     *
     * @param layout 通过{@link #getLayoutID()}或注解{@link LayoutID}获得的布局View
     * @param savedInstanceState 同onCreateView的savedInstanceState
     */
    protected abstract void setupView(View layout, Bundle savedInstanceState);

    /**
     * 初始化Fragment布局
     *
     * 可以通过复写initContentView对布局做进一步初始化，如添加ScrollerView等
     *
     * @param layout 通过{@link #getLayoutID()}或注解{@link LayoutID}获得的布局View
     * @param savedInstanceState 同onCreateView的savedInstanceState
     * @return 返回修改后的新的布局
     */
    protected View initContentView(View layout, Bundle savedInstanceState) {
        return null;
    }



    /**
     * 通过resID查找layout的子View（同{@link View#findViewById(int)}功能一致）
     *
     * @param id 待查找的resID
     * @return 如果找到对应id，则返回view
     */
    public <T extends View> T findViewById(int id) {
        return (T) mLayout.findViewById(id);
    }


    /**
     * 指定布局ID，一般不推荐子类复写此方法
     *
     * @return 返回布局文件ID，默认使用注解指定LayoutID
     */
    public int getLayoutID() {
        return getLayoutIdFromAnnotation();
    }

    /**
     * 推荐使用注解指定Fragment的LayoutID
     */
    private int getLayoutIdFromAnnotation() {
        int layoutId = 0;
        LayoutID annoId = getClass().getAnnotation(LayoutID.class);
        if (annoId != null) {
            layoutId = annoId.value();
        }
        return layoutId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        debugPrint("onCreate()");
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        debugPrint("onAttach()");

        mActivity = (Activity) context;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        debugPrint("onDestroy()");
    }

    @Override
    public void onStart() {
        super.onStart();
        debugPrint("onStart()");
    }

    @Override
    public void onStop() {
        super.onStop();
        debugPrint("onStop()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        debugPrint("onDetach()");
    }

    @Override
    public void onPause() {
        super.onPause();
        debugPrint("onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        debugPrint("onResume()");
    }

    private void debugPrint(String msg) {
        Log.e(TAG,msg);
    }

}
