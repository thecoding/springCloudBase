package com.springcloudbase.webserver.fastdfs.controller;

import com.springcloudbase.webserver.fastdfs.wrapper.FastDFSClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/fastDfs")
public class FastDfsController {

    private static transient Logger logger = LoggerFactory.getLogger(FastDfsController.class);

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @RequestMapping(value="/upload")
    public String uploadFile(@RequestPart("file") MultipartFile[] files,@RequestParam("userName") String userName, @RequestParam("remark") String remark) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (MultipartFile file : files) {
            sb.append(fastDFSClientWrapper.uploadFile(file, null));
        }
        return sb.toString();
    }

    @RequestMapping("/download")
    public void downloadFile(@RequestParam("fileUrl") String fileUrl, HttpServletResponse response) throws IOException {
        byte[] bytes = fastDFSClientWrapper.downloadFile(fileUrl);
        // 这里只是为了整合fastdfs，所以写死了文件格式。需要在上传的时候保存文件名。下载的时候使用对应的格式
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("sb.xlsx", "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}