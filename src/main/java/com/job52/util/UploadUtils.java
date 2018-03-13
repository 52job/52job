package com.job52.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author  mr.zhang
 @version:2017-9-27 1:42:52
 */
public class UploadUtils {

    private UploadUtils(){}

    /**
     * 将输入流中的内容写入到输出流
     * @param in 输入流
     * @param out 输出流
     */
    public static void In2Out(InputStream in ,OutputStream out){
        byte[] bs = new byte[1024];
        int i = 0;
        try {
            while((i=in.read(bs))!=-1){
                out.write(bs, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭输入流和输出流
     * @param in 输入流
     * @param out 输出流
     */
    public static void close(InputStream in ,OutputStream out){
        if(in!=null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                in=null;
            }
        }

        if(out!=null){
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                out=null;
            }
        }
    }
}
