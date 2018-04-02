package clock;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.VideoView;

/**
 * Created by adminuser on 2017/12/18.
 */

public class MyVedioView extends VideoView {
    public MyVedioView(Context context) {
        super( context );
    }

    public MyVedioView(Context context, AttributeSet attrs) {
        super( context, attrs );
    }

    public MyVedioView(Context context, AttributeSet attrs, int defStyle) {
        super( context, attrs, defStyle );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重新计算高度
        int width = getDefaultSize( 0, widthMeasureSpec );
        int height = getDefaultSize( 0, heightMeasureSpec );
        setMeasuredDimension( width, height );
    }

    @Override
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        super.setOnPreparedListener( l );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown( keyCode, event );
    }
}


