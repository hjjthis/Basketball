package clock;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/14.
 */

public class MyClock extends Chronometer {
    SimpleDateFormat mTimeFormat;

    public MyClock(Context context, AttributeSet attrs) {
        super( context, attrs );
        //指定样式、设置Chronometer Tick监听器
        mTimeFormat = new SimpleDateFormat( "ss" );
        this.setOnChronometerTickListener( listener );
    }

    public static long mNextTime;
    private MyClock.OnTimeCompleteListener mListener;

    public MyClock(Context context) {
        super( context );
    }


     //OnChronometerTickListener listener

    private OnChronometerTickListener listener = new OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            //当一节走完之后即mNextTime为0时，暂停clock，并触发OnTimeCompleteListener监听器的回调函数；
            //此时mNextTime复位，直接开始
            //当时间走到9的时候设置颜色为红色
            if (mNextTime == 9) {
                MyClock.this.setTextColor( Color.rgb( 190, 3, 38 ) );
            }
            if (mNextTime == 0) {
                MyClock.this.start();
                MyClock.this.setTextColor( Color.rgb( 19, 164, 159 ) );
                if (null != mListener) {
                    mListener.onTimeComplete1();
                }
                updateTimeText();
            } else {
                mNextTime--;
                updateTimeText();
            }
        }
    };

    public void setOnTimeCompleteListener(MyClock.OnTimeCompleteListener l) {
        mListener = l;
    }

   //初始化时间
    public void initTime(long _time_s) {
        mNextTime = _time_s;
        updateTimeText();
    }

    //更新时间
    private void updateTimeText() {
        this.setText( mTimeFormat.format( new Date( mNextTime * 1000 ) ) );
    }


    //定义接口
    public interface OnTimeCompleteListener {
        void onTimeComplete1();
    }

}
