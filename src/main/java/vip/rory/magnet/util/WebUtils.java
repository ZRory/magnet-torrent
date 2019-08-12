package vip.rory.magnet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

/**
 * @author zhanghangtian
 * @date 2019年8月12日 下午3:17:50
 */
public class WebUtils {

    public static void writeFileToResponse(File file, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String userAgent = request.getHeader("User-Agent");
        //针对IE或者以IE为内核的浏览器：
        String fileName = file.getName();
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);

        //获得该文件的输入流
        InputStream in = new FileInputStream(file);

        //获得输出流---通过response获得的输出流 用于向客户端写内容
        ServletOutputStream out = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[4096];
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
    }

}
