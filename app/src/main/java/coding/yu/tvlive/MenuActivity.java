package coding.yu.tvlive;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yu on 10/29/2020.
 */
public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";

    public static final String CONFIG_MENU_FILE = "menu-chinamobile-hls.json";

    private TextView text_version;
    private MenuLayout mMenuLayout;
    private IndexLayout mIndexLayout;
    private List<MenuItem> mMenuList = new ArrayList<>();
    private List<IndexItem> mIndexList = new ArrayList<>();
    private int mIndexSelectPos = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        text_version = findViewById(R.id.text_version);
        text_version.setText("Version " + BuildConfig.VERSION_NAME);

        mMenuLayout = findViewById(R.id.layout_menu);
        mMenuLayout.setMenuClickListener(new MenuLayout.OnMenuClickListener() {
            @Override
            public void onMenuClick(View view, int position) {
                MenuItem item = mMenuList.get(position);
                PlayerActivity.LaunchPlayerActivity(MenuActivity.this, item.url);
            }
        });

        mIndexLayout = findViewById(R.id.layout_index);
        mIndexLayout.setIndexClickListener(new IndexLayout.OnIndexClickListener() {
            @Override
            public void onIndexClick(View view, int position) {
                if (position == mIndexSelectPos) {
                    return;
                }
                IndexItem item = mIndexList.get(position);
                setupMenuData(item.path);
                mIndexSelectPos = position;
                mIndexLayout.select(mIndexSelectPos);
                mMenuLayout.requestFirstFocus();
            }
        });

        setupIndexData();

        IndexItem item = mIndexList.get(mIndexSelectPos);
        setupMenuData(item.path);
        mIndexLayout.select(mIndexSelectPos);
        mMenuLayout.requestFirstFocus();
    }

    private void setupIndexData() {
        mIndexList.clear();
        mIndexList.add(new IndexItem("menu-gitv-hls.json", "Gitv·HLS"));
        mIndexList.add(new IndexItem("menu-chinamobile-hls.json", "移动·HLS"));
        mIndexList.add(new IndexItem("menu-bupt-hls.json", "北邮·HLS"));
        mIndexList.add(new IndexItem("menu-bupt-rtmp.json", "北邮·RTMP"));
        mIndexLayout.setIndexList(mIndexList);
    }

    private void setupMenuData(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = getResources().getAssets().open(path);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String jsonLine;
            while ((jsonLine = reader.readLine()) != null) {
                stringBuilder.append(jsonLine);
            }
            reader.close();
            isr.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = stringBuilder.toString();
        Gson gson = new Gson();
        List<MenuItem> list = gson.fromJson(result, new TypeToken<List<MenuItem>>(){}.getType());
        mMenuList.clear();
        mMenuList.addAll(list);
        mMenuLayout.removeAllViewsInLayout();
        mMenuLayout.setMenuList(mMenuList);
    }
}
