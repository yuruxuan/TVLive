package coding.yu.tvlive;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by yu on 10/29/2020.
 */
public class MenuLayout extends FrameLayout {

    public static final int ROW_COUNT = 4;
    public static final int COLUMN_COUNT = 4;

    private int mSpacingPx = 8;

    private OnMenuClickListener mMenuClickListener;

    public MenuLayout(Context context) {
        super(context);
        init();
    }

    public MenuLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MenuLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    public void setMenuClickListener(OnMenuClickListener listener) {
        this.mMenuClickListener = listener;
    }

    public void setSpacingPx(int spacingPx) {
        this.mSpacingPx = spacingPx;
    }


    public void setMenuList(List<MenuItem> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        for (int i = 0; i < list.size() && i < (COLUMN_COUNT * ROW_COUNT); i++) {
            MenuItem item = list.get(i);
            int drawableId = MenuLogoHolder.getMenuLogoDrawableId(item);
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(drawableId);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            p.gravity = Gravity.CENTER;

            FrameLayout wrapper = new FrameLayout(getContext());
            wrapper.setBackground(new ColorDrawable(Color.WHITE));
            wrapper.setFocusable(true);
            wrapper.setFocusableInTouchMode(true);
            wrapper.setForeground(new ColorDrawable(0x00000000));
            wrapper.addView(imageView, p);
            wrapper.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    FrameLayout layout = (FrameLayout) v;
                    if (hasFocus) {
                        layout.setForeground(new ColorDrawable(0x66000000));
                    } else {
                        layout.setForeground(new ColorDrawable(0x00000000));
                    }
                }
            });

            final int finalI = i;
            wrapper.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMenuClickListener != null) {
                        mMenuClickListener.onMenuClick(v, finalI);
                    }
                }
            });

            addView(wrapper);
        }

        if (getChildCount() > 0) {
            getChildAt(0).requestFocusFromTouch();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        float width = r - l - getPaddingLeft() - getPaddingRight();
        float height = b - t - getPaddingTop() - getPaddingBottom();

        float itemW = (width - mSpacingPx * (COLUMN_COUNT - 1)) / COLUMN_COUNT;
        float itemH = (height - mSpacingPx * (ROW_COUNT - 1)) / ROW_COUNT;

        for (int i = 0; i < getChildCount(); i++) {
            int x = (int) (getPaddingLeft() + (itemW + mSpacingPx) * (i % COLUMN_COUNT));
            int y = (int) (getPaddingTop() + (itemH + mSpacingPx) * (i / COLUMN_COUNT));
            getChildAt(i).layout(x, y, (int) (x + itemW), (int) (y + itemH));
        }
    }

    public interface OnMenuClickListener {
        void onMenuClick(View view, int position);
    }
}
