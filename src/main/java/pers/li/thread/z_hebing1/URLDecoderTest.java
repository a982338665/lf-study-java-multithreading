package pers.li.thread.z_hebing1;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 普通字符串和MIME字符串之间的转换
 * create by lishengbo 2018/5/17
 */
public class URLDecoderTest {
    public static void main(String[] args) throws UnsupportedEncodingException {

        //将普通字符串转成...格式
        String url= URLEncoder.encode("讲义","GBK");
        System.out.println(url);
        //将application/x-www-form-urlencoded字符串转成普通字符串
        String decode = URLDecoder.decode("%BD%B2%D2%E5", "GBK");
        System.out.println(decode);


    }
}
