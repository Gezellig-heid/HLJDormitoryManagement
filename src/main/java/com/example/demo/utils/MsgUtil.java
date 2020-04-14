package com.example.demo.utils;
import java.util.HashMap;
import java.util.Map;

public class MsgUtil {
    //消息辅助类
    private int code;
    private String msg;
    private Map<String, Object> map = new HashMap<String, Object>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public static MsgUtil success(){
        MsgUtil msg = new MsgUtil();
        msg.setCode(100);
        msg.setMsg("成功");
        return msg;
    }

    public static MsgUtil error(){
        MsgUtil msg = new MsgUtil();
        msg.setCode(200);
        msg.setMsg("失败");
        return msg;
    }

    public MsgUtil add(String key, Object value) {
        map.put(key, value);
        return this;
    }

}