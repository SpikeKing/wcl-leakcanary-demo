package org.wangchenlong.wcl_leakcanary_demo;

import android.content.Context;
import android.widget.TextView;


/**
 * 泄露单例, 设计混乱, 单例只应该做事务性的工作, 页面操作应该使用回调.
 * 本示例仅做为反面示例, 切勿学习.
 * <p/>
 * Created by wangchenlong on 16/1/25.
 */
public class LeakSingle {
    private static LeakSingle sInstance;  // 单例

    private Context mContext;  // 单例持有Context
    private TextView mTextView;  // 单例持有视图控件

    private LeakSingle(Context context) {
        mContext = context;  // 容易导致内存泄漏
//        mContext = context.getApplicationContext();  // 正确写法
    }

    public static LeakSingle getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LeakSingle(context);
        }
        return sInstance;
    }

    // 持有视图控件，容易产生内存泄露
    public void setRetainedTextView(TextView tv) {
        mTextView = tv;
        mTextView.setText(mContext.getString(R.string.app_name));
    }

    // 删除引用，防止泄露
    public void removeRetainedTextView() {
        mTextView = null;
    }
}
