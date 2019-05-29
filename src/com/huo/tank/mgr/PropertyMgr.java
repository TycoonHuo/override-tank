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

    /**
     * 读配置文件的，禁止用户来new
     */
    private PropertyMgr(){}

    public static String get(String str){
        return prop.getProperty(str);
    }
}
