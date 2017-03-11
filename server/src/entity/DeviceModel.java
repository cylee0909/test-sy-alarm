package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cylee on 2017/3/11.
 */

public class DeviceModel {
    public List<DeviceItem> devices = new ArrayList<DeviceItem>();
    public static class DeviceItem {
        public String name;
        public int state;
        public int did;
        /**
         * 操作的状态
         */
        public int astate;
        /**
         * 运行的状态,0正在运行,其他已关闭
         */
        public int ostate;
    }
}
