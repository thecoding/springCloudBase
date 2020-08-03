package com.springcloudbase.fastdfs.wrapper;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Component
public class FastDFSClientWrapper {

    private final Logger logger = LoggerFactory.getLogger(FastDFSClientWrapper.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    /**
     * 上传文件
     * @param file
     * @param metaDataMap  metaData信息
     * @throws IOException
     */
    public String uploadFile(MultipartFile file, @NotNull Map<String,String> metaDataMap) throws IOException {
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        long fileSize = file.getSize();
        logger.info(originalFileName + "------" + fileName + "------" + fileSize + "------" + extension + "------" + bytes.length);

        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataMap.forEach((k,v) -> {
            MetaData metaData = new MetaData();
            metaData.setName(k);
            metaData.setValue(v);
            metaDataSet.add(metaData);
        });
        return uploadFile(bytes, fileSize, extension, metaDataSet);
    }



    /**
     * 文件上传
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDfs路径
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        return uploadFile(bytes, fileSize, extension, null);
    }


    /**
     * 文件上传
     * @param bytes
     * @param fileSize
     * @param extension
     * @param set  metaData信息
     * @return
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension, Set<MetaData> set){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(byteArrayInputStream, fileSize, extension, set == null || set.size() ==0 ? null : set);
        logger.info(storePath.getGroup() + "===" + storePath.getPath() + "======" + storePath.getFullPath());
        return storePath.getFullPath();
    }


    /**
     * 下载文件
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     * @throws IOException
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }
}