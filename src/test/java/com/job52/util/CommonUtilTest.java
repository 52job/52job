package com.job52.util;

import com.job52.model.Job;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CommonUtilTest {

    @Test
    public void getJson() throws Exception {
        CommonUtil commonUtil = new CommonUtil();
        System.out.println(commonUtil.getJson(new Job(),new HashMap<Object,Object>()));

    }

}