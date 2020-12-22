package pers.li.thread.z_hebing1;

import java.net.InetAddress;

/**
 * create by lishengbo 2018/5/17
 */
public class InetAddressTest {

    public static void main(String[] args) {
        try {
            //根据主机名获取对应的实例
            InetAddress ip = InetAddress.getByName("www.crazyit.org");
            //判断是否可达
            System.out.println("crazyit是否可达："+ip.isReachable(2000));
            //获取实例的ip字符串
            System.out.println(ip.getAddress());
            //全限定域名
            System.out.println(ip.getCanonicalHostName());
            System.out.println(ip.getHostAddress());
            System.out.println(ip.getHostName());
            //

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
