package com.textstats.server.controllers;

import com.textstats.server.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class TextStatsController {

    StorageService storageService;

    @Autowired
    public TextStatsController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * API to store the uploaded file
     * File is stored in a directory initialized at application startup
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file) {
        return storageService.storeFile(file);
    }

}
