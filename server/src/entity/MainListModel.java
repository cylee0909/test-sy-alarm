package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cylee on 2017/3/11.
 */

public class MainListModel {
    public List<MainItem> mainStates = new ArrayList<MainItem>();
    public static class MainItem {
        public int cid;
        public int state;
    }
}
