package coding.yu.tvlive;

import android.content.Context;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;

public class LiveRepo {

    private static final String LIVE_CONFIG_FILE = "live_config.json";

    private volatile static LiveRepo sInstance;

    private LiveRepo() {
    }

    public static LiveRepo get() {
        if (sInstance == null) {
            synchronized (LiveRepo.class) {
                if (sInstance == null) {
                    sInstance = new LiveRepo();
                }
            }
        }
        return sInstance;
    }

    public boolean checkConfigFileExist() {
        File filesDir = Utils.getApp().getFilesDir();
        File configFile = new File(filesDir, LIVE_CONFIG_FILE);
        return configFile.exists() && configFile.isFile();
    }

    public String loadFromLocal() {
        File filesDir = Utils.getApp().getFilesDir();
        File configFile = new File(filesDir, LIVE_CONFIG_FILE);
        return FileIOUtils.readFile2String(configFile);
    }

    public String loadFromAssets() {
        return ResourceUtils.readAssets2String("live-source.json");
    }

    public String loadFromNetwork() {
        return null;
    }
}
