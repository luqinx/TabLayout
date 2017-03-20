package chao.widget.sample;


/**
 * chao.qin 2016/6/1.
 */
public class AnnotationsUtil {

    public static int getLayoutIdFromAnnotation(Class c) {
        int layoutId = 0;
        LayoutID annoId = c.getClass().getAnnotation(LayoutID.class);
        if (annoId != null) {
            layoutId = annoId.value();
        }
        return layoutId;
    }
}
