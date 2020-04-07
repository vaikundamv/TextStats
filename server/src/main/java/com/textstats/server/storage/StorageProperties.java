package com.textstats.server.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Config file to specify file upload directory
 *  defaults to "upload-dir"
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private static final String UPLOAD_DIR  = "upload-dir";

    private String location = UPLOAD_DIR;

    public static String getUploadDir() {
        return UPLOAD_DIR;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
