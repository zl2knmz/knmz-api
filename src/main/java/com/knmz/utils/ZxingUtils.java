package com.knmz.utils;

import com.google.common.base.Charsets;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by reese on 2019-10-15.
 */
public class ZxingUtils {

    /**
     * 解析二维码
     */
    public static String readQRCode(byte[] data) {

        BufferedImage image = null;
        BinaryBitmap bitmap = null;
        Result result = null;

        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            image = ImageIO.read(in);
            int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
            RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
            bitmap = new BinaryBitmap(new HybridBinarizer(source));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (bitmap == null)
            return null;

        QRCodeReader reader = new QRCodeReader();
        Map<DecodeHintType,Object> hints = new LinkedHashMap<DecodeHintType,Object>();
        //码设置编码方式为：utf-8，
        hints.put(DecodeHintType.CHARACTER_SET, Charsets.UTF_8);
        //优化精度
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        //复杂模式，开启PURE_BARCODE模式
        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
//
        try {
            result = reader.decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ChecksumException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String [] args) {
        try {



            Path currentRelativePath = Paths.get(".");
            String root = currentRelativePath.toAbsolutePath().toString();
            String path = root + File.separator + "images" + File.separator + "img-base64.txt";


            String imgBase64=new String (Files.readAllBytes(Paths.get(path)));

            if(imgBase64.indexOf("data:image/png;base64,")>-1){
                imgBase64=imgBase64.replace("data:image/png;base64,","");
            }else if(imgBase64.indexOf("data:image/jpg;base64,")>-1){
                imgBase64=imgBase64.replace("data:image/jpg;base64,","");
            }else if(imgBase64.indexOf("data:image/jpeg;base64,")>-1){
                imgBase64=imgBase64.replace("data:image/jpeg;base64,","");
            }else if(imgBase64.indexOf("data:image/gif;base64,")>-1){
                imgBase64=imgBase64.replace("data:image/gif;base64,","");
            }else if(imgBase64.indexOf("data:image/tiff;base64,")>-1){
                imgBase64=imgBase64.replace("data:image/tiff;base64,","");
            }
            BASE64Decoder d = new BASE64Decoder();
            byte[] data = d.decodeBuffer(imgBase64);
            String  content = ZxingUtils.readQRCode(data);
            System.out.println(content);
            Map<String,Object> result=new HashMap<>();

            // root = currentRelativePath.toAbsolutePath().toString();
            // path = root + File.separator + "images" + File.separator + "qr.jpg";

           // String readQRCode = readQRCode(Files.readAllBytes(Paths.get(path)));
            //System.out.println(readQRCode);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
