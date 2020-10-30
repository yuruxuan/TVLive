package coding.yu.tvlive;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by yu on 10/30/2020.
 */
public class IndexLayout extends LinearLayout {

    private OnIndexClickListener mIndexClickListener;

    public IndexLayout(Context context) {
        super(context);
        init();
    }

    public IndexLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    public void setIndexClickListener(OnIndexClickListener listener) {
        this.mIndexClickListener = listener;
    }

    public void setIndexList(List<IndexItem> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            IndexItem item = list.get(i);
            TextView textView = new TextView(getContext());
            textView.setText(item.name);
            textView.setTextSize(12);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setPadding(0, 10, 0, 10);

            FrameLayout.LayoutParams p1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

            FrameLayout wrapper = new FrameLayout(getContext());
            wrapper.setBackgroundResource(R.drawable.index_item_selector);
            wrapper.setFocusable(true);
            wrapper.setFocusableInTouchMode(true);
            wrapper.setForeground(new ColorDrawable(0x00000000));
            wrapper.addView(textView, p1);

            final int finalI = i;
            wrapper.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIndexClickListener != null) {
                        mIndexClickListener.onIndexClick(v, finalI);
                    }
                }
            });

            LayoutParams p2 = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            if (i != (list.size() - 1)) {
                p2.bottomMargin = 30;
            }

            addView(wrapper, p2);
        }
    }

    public void select(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            FrameLayout wrapper = (FrameLayout) getChildAt(i);
            TextView textView = (TextView) wrapper.getChildAt(0);
            if (i == position) {
                textView.setTextColor(Color.WHITE);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                wrapper.setSelected(true);
                wrapper.setFocusable(false);
                wrapper.setFocusableInTouchMode(false);
            } else {
                textView.setTextColor(Color.LTGRAY);
                textView.setTypeface(Typeface.DEFAULT);
                wrapper.setSelected(false);
                wrapper.setFocusable(true);
                wrapper.setFocusableInTouchMode(true);
            }
        }
    }

    public interface OnIndexClickListener {
        void onIndexClick(View view, int position);
    }
}
