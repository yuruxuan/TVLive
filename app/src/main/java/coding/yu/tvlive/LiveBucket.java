package coding.yu.tvlive;

import java.util.List;

public class LiveBucket {
    public int version;
    public List<IndexItem> groups;

    public class IndexItem {
        public String name;
        public List<MenuItem> items;
    }

    public class MenuItem {
        public String id;
        public String url;
    }

    @Override
    public String toString() {
        return "LiveBucket{" +
                "version=" + version +
                ", groups=" + groups +
                '}';
    }
}
