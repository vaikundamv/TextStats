package com.textstats.server.storage;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service that handles storing and retrieving uploaded files
 */
public interface StorageService {

    public void createDir();
    public void deleteAll();
    public String storeFile(MultipartFile file);
    public List<Path> fetchUploadedFiles();
}
