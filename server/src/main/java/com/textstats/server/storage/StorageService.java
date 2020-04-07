package com.textstats.server.storage;

/**
 * Service that handles storing and retrieving uploaded files
 */
public interface StorageService {

    public void createDir();
    public void deleteAll();
}
