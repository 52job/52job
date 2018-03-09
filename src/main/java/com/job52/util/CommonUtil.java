package com.job52.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CommonUtil {

    private static String getId() {
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String id = df.format(new Date());
        id += (new Random().nextInt(99));
        return String.valueOf(id);
    }

    public static String getJobId() {
        return "J" + getId();
    }

    public static String getPersonId() {
        return "P" + getId();
    }

    public static String getEnterpriseId() {
        return "E" + getId();
    }

    public static String getResumeId() {
        return "R" + getId();
    }

    /**
     * 生成json在同一层
     * @param o
     */
//    private static String getJsonWithBracket(Object o) {
//        StringBuffer json = new StringBuffer("");
//        //StringBuffer pack = new StringBuffer(o.getClass().getName());
//        for (Field field : o.getClass().getDeclaredFields()) {
//            try {
//                Method methodGet = o.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
//
//                String name = field.getName();
//                String value = String.valueOf(methodGet.invoke(o));
//                if (value != "null") {
//                    if (String.valueOf(methodGet.invoke(o).getClass().getPackage()).contains("java")) {
//                        //System.out.println(methodGet.invoke(o).getClass().getPackage());
//                        System.out.println(field.getName() + ":" + methodGet.invoke(o));
//                        json.append("\"" + name +"\":");
//                        json.append("\"" + value +"\",");
//                    } else {
//                        json.append(getJsonWithBracket(methodGet.invoke(o)));
//                    }
//                } else {
//                    json.append("\"" + name +"\":\"\",");
//
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        json.append("");
//        //System.out.println(json);
//        return json.toString();
//    }

//    public static String getJson(Object o) {
//        String json = getJsonWithBracket(o);
//        return "{" + json.substring(0, json.length() - 1) + "}";
//    }

    public static Map getJson(Object o, Map map) {

        //StringBuffer pack = new StringBuffer(o.getClass().getName());
        for (Field field : o.getClass().getDeclaredFields()) {
            try {
                Method methodGet = o.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));

                String name = field.getName();
                String value = String.valueOf(methodGet.invoke(o));
                if (value != "null") {
                    if (String.valueOf(methodGet.invoke(o).getClass().getPackage()).contains("java")) {
                        //System.out.println(methodGet.invoke(o).getClass().getPackage());
                        System.out.println(field.getName() + ":" + methodGet.invoke(o));
                        map.put( name, value);
                    } else {
                        map = getJson(methodGet.invoke(o), map);
                    }
                } else {
                    map.put(name,"");

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //System.out.println(json);
        return map;
    }
}
