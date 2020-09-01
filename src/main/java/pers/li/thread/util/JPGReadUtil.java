package pers.li.thread.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 图片与二进制数的相互转换
 * @author lishengbo
 * @Time 2017年12月8日下午3:06:16
 */
public class JPGReadUtil {
	   
    public static void main(String[] args) {   
    	String string64=JPGReadUtil.ImageConvertBinary("C:\\Users\\cjh\\Desktop\\QQ图片20171127182030.jpg");
        System.out.println(string64);   
        System.out.println(JPGReadUtil.BinaryStringToImage(string64, "C:\\Users\\cjh\\Desktop\\QQ图片2017112718203110.jpg"));   
    }   
       
    /**
     * 全路径
     * @param path:"C:\\Users\\cjh\\Desktop\\QQ图片20171127182030.jpg"
     * @return
     */
    public static String ImageConvertBinary(String path){   
    	File f =null;
    	try { 
    	 f = new File(path);    
    	} catch (Exception e) {   
    		return e.getMessage();
    	}  
    	BufferedImage bi;   
    	try {   
    		bi = ImageIO.read(f);   
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();   
    		ImageIO.write(bi, "jpg", baos);   
    		byte[] bytes = baos.toByteArray();   
    		
    		return new sun.misc.BASE64Encoder().encodeBuffer(bytes).trim();   
    	} catch (IOException e) {   
    		e.printStackTrace(); 
    		return "解析错误";
    	}   
    }   
   /**
    * 二进制转换为图片
    * @param base64String
    * @param path------生成图片的路径及名称：C:\\Users\\cjh\\Desktop\\QQ.jpg
    * @return
    */
   public  static boolean BinaryStringToImage(String base64String,String path){   
        try {   
            byte[] bytes1 = new sun.misc.BASE64Decoder().decodeBuffer(base64String);   
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);   
            BufferedImage bi1 =ImageIO.read(bais);   
            File w2 = new File(path);//可以是jpg,png,gif格式   
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动   
            return true;
        } catch (IOException e) {   
            e.printStackTrace();   
            return false;
        }   
    }   
}
