package pers.li.thread.z_hebing1;

/**
 * create by lishengbo 2018/5/17
 */
public class MuitthreadDown {
    public static void main(String[] args)  {
        final DownUtil downUtil = new DownUtil("http://www.crazyit.org/" + "attachments/month_1403/1403202355ff6cc9a4fbf6f14a.png"
        ,"ios.png",4
        );
        try {
            downUtil.download();
            new Thread(new Runnable() {
                public void run() {
                    while (downUtil.getCompleteRate()<1){
                        //每隔0.1秒查询任务完成进度
                        //GUI程序中可根据进度来获取进度条
                        System.out.println("已完成："+downUtil.getCompleteRate());
                        try{
                            Thread.sleep(1000);
                        }catch(Exception e){

                        }finally{

                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
