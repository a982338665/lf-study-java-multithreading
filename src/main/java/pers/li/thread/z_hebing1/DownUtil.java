package pers.li.thread.z_hebing1;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * create by lishengbo 2018/5/17
 * 多线程下载工具类___未实现断点续传
 * 实现：
 * 断点续传-下载文件时生成两个文件
 * 1.与网络资源一样大的空文件
 * 2.配置文件：分别记录每个线程已下载到那个字节，
 * 当网络断开后再次下载时，根据配置文件记录的位置可继续向下下载
 */
public class DownUtil {
    //定义被下载资源的路径
    private String path;
    //指定下载文件的保存位置
    private String targetFile;
    //定义使用多少个线程下载资源
    private int threadNum;
    //定义下载的线程对象
    private DownThread[] threads;
    //定义文件总大小
    private int fileSize;

    public DownUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
    }


    public void download() throws  Exception{
        URL url = new URL(path);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty(
                "Accept",
                "image/gif,image/jpeg,image/pjpeg,image/jpg,"
                        +"application/x-shockwave-flash,application/xaml+xml,"
                        +"application/vnd.ms-application,application/x-ms-xbap,"
                        +"application/x-ms-application,application/vnd.ms-excel,"
                        +"application/vnd.ms-powerpoint,application/msword,*/*"
        );
        conn.setRequestProperty("Accept-Language","zh-CN");
        conn.setRequestProperty("Charset","UTF-8");
        conn.setRequestProperty("Connection","Keep-Alive");

        //得到文件大小
        fileSize= conn.getContentLength();
        conn.disconnect();
        int  currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
        //设置本地文件大小
        file.setLength(fileSize);
        file.close();
        for (int i = 0; i <threadNum ; i++) {
            //计算每个线程下载的开始位置
            int startPos = i * currentPartSize;
            //每个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
            //定位该线程的下载位置
            currentPart.seek(startPos);
            //创建下载线程
            threads[i]=new DownThread(startPos,currentPartSize,currentPart);
            //启动下载线程
            threads[i].start();


        }



    }


//获取下载完成的百分比
public double getCompleteRate(){
        //统计多个线程已下载的总大小
        int sumsize=0;
    for (int i = 0; i <threadNum ; i++) {
        sumsize += threads[i].length;
    }
    //返回已完成的百分比
    return sumsize*1.0/fileSize;
}


    /**
     * create by lishengbo 2018/5/17
     */
    private class DownThread extends Thread {
        //当前线程的下载位置
        private  int startPos;
        //定义当前线程负责下载的文件大小
        private int currentPartSize;
        //当前线程需要下载的文件块
        private RandomAccessFile currentPart;
        //定义该线程已下载的字节数
        public int length;

        public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        @Override
        public void run() {
            try{
                URL url = new URL(path);
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5*1000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty(
                        "Accept",
                        "image/gif,image/jpeg,image/pjpeg,image/jpg,"
                                +"application/x-shockwave-flash,application/xaml+xml,"
                                +"application/vnd.ms-application,application/x-ms-xbap,"
                                +"application/x-ms-application,application/vnd.ms-excel,"
                                +"application/vnd.ms-powerpoint,application/msword,*/*"
                );
                conn.setRequestProperty("Accept-Language","zh-CN");
                conn.setRequestProperty("Charset","UTF-8");

                InputStream inputStream = conn.getInputStream();
                //跳过startPos个字节，表明该线程只下载自己负责的那部分文件
                inputStream.skip(this.startPos);
                byte[] bytes = new byte[1024];
                int has=0;
                //读取网络数据，写入本地文件
                while (length<currentPartSize&&(has=inputStream.read(bytes))!=-1){
                    currentPart.write(bytes,0,has);
                    //累计该线程下载的总大小
                    length+=has;
                }

                currentPart.close();
                inputStream.close();


            }catch(Exception e){

            }finally{

            }
        }
    }



}
