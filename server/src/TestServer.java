import com.google.gson.Gson;
import entity.DeviceModel;
import entity.FloorModel;
import entity.MainListModel;

import java.io.*;
import java.net.Socket;

/**
 * Created by cylee on 17/2/14.
 */
public class TestServer {
    public static final Gson gson = new Gson();
    public static void main(String[] args) throws Exception{
        String read = null;
//        while (Boolean.TRUE) {
//            Socket clientSocket2 = new Socket("123.125.127.186", 8989);
////        Socket clientSocket2 = new Socket("192.168.0.105", 8989);
//            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket2.getOutputStream()));
//            bw.write("INIT01\n");
//            bw.flush();
//
//            read = inFromServer.readLine();
//            System.out.println("read : "+read);
//            Thread.sleep(1000);
//        }

        Socket clientSocket = new Socket("123.125.127.186", 8989);
//        Socket clientSocket = new Socket("192.168.0.105", 8989);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        bw.write("INIT01\n");
        bw.flush();

        boolean pushTest = false;
        while (true) {
            read = inFromServer.readLine();
            System.out.println("read : "+read);
            if (read == null) break;

//            if (read.contains("OK") && !pushTest) {
//                pushTest = true;
//                bw.write("PUSH02test_token\n");
//                bw.flush();
//            }

            if (read.startsWith("#") && read.length() > 3) {
                String id = read.substring(1,3);
                String data = read.substring(3);
                Command command = Command.fromJson(data);
                if (command != null) {
                    String cd = command.command;
                    System.out.println("exec command "+cd);
                    System.out.println("exec params "+command._params);
                    if (cd.equals("login")) {
                        Login login = new Login();
                        login.token = "test_token";
                        String result = "#"+id+gson.toJson(login)+"\n";
                        System.out.println(result);
                        bw.write(result);
                        bw.flush();

                    } else if (cd.equals("mainlist")) {
                        MainListModel model = new MainListModel();
                        MainListModel.MainItem item0 = new MainListModel.MainItem();
                        item0.cid = 0; // 烟雾传感器
                        item0.state = 1; // 状态正常为0
                        model.mainStates.add(item0);
                        MainListModel.MainItem item1 = new MainListModel.MainItem();
                        item1.cid = 1; // 水流传感器
                        item1.state = 0; // 状态正常为0
                        model.mainStates.add(item1);
                        MainListModel.MainItem item2 = new MainListModel.MainItem();
                        item2.cid = 2; // 可燃气传感器
                        item2.state = 0; // 状态正常为0
                        model.mainStates.add(item2);
                        MainListModel.MainItem item3 = new MainListModel.MainItem();
                        item3.cid = 3; // 电机传感器
                        item3.state = 0; // 状态正常为0
                        model.mainStates.add(item3);
                        MainListModel.MainItem item4 = new MainListModel.MainItem();
                        item4.cid = 4; // 温度传感器
                        item4.state = 0; // 状态正常为0
                        model.mainStates.add(item4);
                        String result = "#"+id+gson.toJson(model)+"\n";
                        System.out.println(result);
                        bw.write(result);
                        bw.flush();
                    } else if (cd.equals("floor")) {  // 楼层列表
                        FloorModel model = new FloorModel();
                        FloorModel.FloorItem floorItem0 = new FloorModel.FloorItem();
                        floorItem0.fid = 0; // 楼层id
                        floorItem0.name = "一层"; // 楼层名称
                        floorItem0.state = 0; // 楼层状态 0 代表此楼层内所有设备都正常
                        model.floors.add(floorItem0);

                        FloorModel.FloorItem floorItem1 = new FloorModel.FloorItem();
                        floorItem1.fid = 1;
                        floorItem1.name = "二层"; // 楼层名称
                        floorItem1.state = 0; // 楼层状态 0 代表此楼层内所有设备都正常
                        model.floors.add(floorItem1);

                        FloorModel.FloorItem floorItem2 = new FloorModel.FloorItem();
                        floorItem2.fid = 2;
                        floorItem2.name = "三层"; // 楼层名称
                        floorItem2.state = 1; // 楼层状态 0 代表此楼层内所有设备都正常
                        model.floors.add(floorItem2);

                        String result = "#"+id+gson.toJson(model)+"\n";
                        System.out.println(result);
                        bw.write(result);
                        bw.flush();
                    } else if (cd.equals("device")) { // 设备列表
                        DeviceModel model = new DeviceModel();
                        DeviceModel.DeviceItem deviceItem0 = new DeviceModel.DeviceItem();
                        deviceItem0.astate = 0; // 设备的操作状态 1可关闭,设备异常时判断是否可关闭
                        deviceItem0.state = 0; //设备状态
                        deviceItem0.ostate = 0;
                        deviceItem0.name = "光电发射器";
                        model.devices.add(deviceItem0);

                        DeviceModel.DeviceItem deviceItem1 = new DeviceModel.DeviceItem();
                        deviceItem1.astate = 0; // 设备的操作状态 1可关闭
                        deviceItem1.state = 0; //设备状态
                        deviceItem1.ostate = 1;
                        deviceItem1.name = "物理发射器";
                        model.devices.add(deviceItem1);

                        DeviceModel.DeviceItem deviceItem2 = new DeviceModel.DeviceItem();
                        deviceItem2.astate = 0; // 设备的操作状态 1可关闭
                        deviceItem2.state = 0; //设备状态
                        deviceItem2.ostate = 0;
                        deviceItem2.name = "生化发射器";
                        model.devices.add(deviceItem2);

                        DeviceModel.DeviceItem deviceItem3 = new DeviceModel.DeviceItem();
                        deviceItem3.astate = 1; // 设备的操作状态 1可关闭
                        deviceItem3.state = 1; //设备状态 0 正常 1 异常
                        deviceItem3.ostate = 0;
                        deviceItem3.name = "雷电接收装置";
                        model.devices.add(deviceItem3);

                        String result = "#"+id+gson.toJson(model)+"\n";
                        System.out.println(result);
                        bw.write(result);
                        bw.flush();
                    } else if (cd.equals("close")) {
                        String result = "#"+id+"success"+"\n";
                        System.out.println(result);
                        bw.write(result);
                        bw.flush();
                    }
                }
            } else {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("write ping");
                bw.write("Ping\n");
                bw.flush();
            }
        }
    }

    public static class Login {
        public String token;
    }

    public static class Command {
        private static Gson gson = new Gson();
        public String command;
        public String _params;
        public static Command fromJson(String json) {
            return gson.fromJson(json, Command.class);
        }
    }
}
