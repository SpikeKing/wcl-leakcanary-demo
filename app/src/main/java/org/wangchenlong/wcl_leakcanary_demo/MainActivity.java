package org.wangchenlong.wcl_leakcanary_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_tv_text) TextView mTvText;  // 首页的文本控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /**
         * org.wangchenlong.wcl_leakcanary_demo.MainActivity has leaked:
         * GC ROOT static org.wangchenlong.wcl_leakcanary_demo.LeakSingle.sInstance
         * references org.wangchenlong.wcl_leakcanary_demo.LeakSingle.mContext
         * leaks org.wangchenlong.wcl_leakcanary_demo.MainActivity instance
         */
//        LeakSingle.getInstance(this).setRetainedTextView(mTvText);

        /**
         * org.wangchenlong.wcl_leakcanary_demo.MainActivity has leaked:
         * GC ROOT static org.wangchenlong.wcl_leakcanary_demo.LeakSingle.sInstance
         * references org.wangchenlong.wcl_leakcanary_demo.LeakSingle.mTextView
         * references android.support.v7.widget.AppCompatTextView.mContext
         * leaks org.wangchenlong.wcl_leakcanary_demo.MainActivity instance
         */
        LeakSingle.getInstance(this.getApplication()).setRetainedTextView(mTvText);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        // 防止内泄露
        LeakSingle.getInstance(this.getApplication()).removeRetainedTextView();
    }
}
