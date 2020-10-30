package coding.yu.tvlive;

import android.text.TextUtils;

/**
 * Created by yu on 10/29/2020.
 */
public class MenuLogoHolder {

    public static int getMenuLogoDrawableId(MenuItem item) {
        if (TextUtils.equals(item.id, "cctv1")) {
            return R.drawable.logo_cctv1;
        }

        if (TextUtils.equals(item.id, "cctv2")) {
            return R.drawable.logo_cctv2;
        }

        if (TextUtils.equals(item.id, "cctv3")) {
            return R.drawable.logo_cctv3;
        }

        if (TextUtils.equals(item.id, "cctv4")) {
            return R.drawable.logo_cctv4;
        }

        if (TextUtils.equals(item.id, "cctv5")) {
            return R.drawable.logo_cctv5;
        }

        if (TextUtils.equals(item.id, "cctv5+")) {
            return R.drawable.logo_cctv5p;
        }

        if (TextUtils.equals(item.id, "cctv6")) {
            return R.drawable.logo_cctv6;
        }

        if (TextUtils.equals(item.id, "cctv7")) {
            return R.drawable.logo_cctv7;
        }

        if (TextUtils.equals(item.id, "cctv8")) {
            return R.drawable.logo_cctv8;
        }

        if (TextUtils.equals(item.id, "cctv9")) {
            return R.drawable.logo_cctv9;
        }

        if (TextUtils.equals(item.id, "cctv10")) {
            return R.drawable.logo_cctv10;
        }

        if (TextUtils.equals(item.id, "cctv11")) {
            return R.drawable.logo_cctv11;
        }

        if (TextUtils.equals(item.id, "cctv12")) {
            return R.drawable.logo_cctv12;
        }

        if (TextUtils.equals(item.id, "cctv13")) {
            return R.drawable.logo_cctv13;
        }

        if (TextUtils.equals(item.id, "cctv14")) {
            return R.drawable.logo_cctv14;
        }

        if (TextUtils.equals(item.id, "cctv17")) {
            return R.drawable.logo_cctv17;
        }

        return 0;
    }
}
