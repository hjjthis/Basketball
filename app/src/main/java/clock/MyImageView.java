package clock;

import android.content.Context;
import android.util.AttributeSet;

public class MyImageView extends android.support.v7.widget.AppCompatImageView {

    //带有pos信息的ImageView
    private int index = -1;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public MyImageView(Context context) {
        super( context );
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super( context, attrs );
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super( context, attrs, defStyle );
    }


}	
