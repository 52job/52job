package com.job52.util;

import java.text.SimpleDateFormat;
import java.util.Date;
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
}
