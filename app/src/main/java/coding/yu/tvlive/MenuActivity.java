package coding.yu.tvlive;

import android.content.Intent;
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

    private MenuLayout mMenuLayout;
    private TextView mVersionText;
    private List<MenuItem> mMenuList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mMenuLayout = findViewById(R.id.layout_menu);
        mMenuLayout.setMenuClickListener(new MenuLayout.OnMenuClickListener() {
            @Override
            public void onMenuClick(View view, int position) {
                MenuItem item = mMenuList.get(position);
                PlayerActivity.LaunchPlayerActivity(MenuActivity.this, item.url);
            }
        });
        setupData();
    }

    private void setupData() {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = getResources().getAssets().open(CONFIG_MENU_FILE);
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
        mMenuLayout.setMenuList(mMenuList);
    }
}
