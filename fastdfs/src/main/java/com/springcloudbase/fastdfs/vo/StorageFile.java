package com.springcloudbase.fastdfs.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by Mirko on 2020/2/24.
 */
public class StorageFile implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(StorageFile.class);
    private static final long serialVersionUID = 7029184543916815359L;
    private long flowId;
    private String fileName;
    private String fileType;
    private long fileSize;
    private String storePath;
    private String fullPath;
    private String remark;
    private String fullPathUrl;

    public long getFlowId() {
        return flowId;
    }

    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFullPathUrl() {
        return fullPathUrl;
    }

    public void setFullPathUrl(String fullPathUrl) {
        this.fullPathUrl = fullPathUrl;
    }
}
