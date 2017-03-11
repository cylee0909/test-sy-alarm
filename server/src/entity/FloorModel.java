package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cylee on 2017/3/11.
 */

public class FloorModel {
    public List<FloorItem> floors = new ArrayList<FloorItem>();
    public static class FloorItem {
        public String name;
        public int state;
        public int fid;
    }
}
