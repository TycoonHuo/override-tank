package com.huo.tank.mgr;

import java.io.IOException;
import java.util.Properties;

/**
 * @author huoyun
 * @date 2019/5/26-19:33
 */
public class PropertyMgr {
    private static Properties prop = new Properties();

    static{
        try {
            prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String str){
        return (String) prop.get(str);
    }

    public static void main(String[] args){
        String enemies = PropertyMgr.get("enemies");
        System.out.println(enemies);
    }
}
