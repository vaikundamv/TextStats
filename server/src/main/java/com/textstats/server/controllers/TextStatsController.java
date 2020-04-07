package com.textstats.server.controllers;

import com.textstats.server.model.TextStats;
import com.textstats.server.service.TextStatsService;
import com.textstats.server.storage.StorageService;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class TextStatsController {

    StorageService storageService;
    TextStatsService textStatsService;

    @Autowired
    public TextStatsController(StorageService storageService,
        TextStatsService textStatsService) {
        this.storageService = storageService;
        this.textStatsService = textStatsService;
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


    /**
     * API to get statistics of the uploaded file
     * @return text stats
     */
    @GetMapping("/stats")
    public TextStats getStats() {
        List<Path> paths = storageService.fetchUploadedFiles();
        assert paths.size() > 0;
        Path path = paths.get(0);
        return textStatsService.getWordCountStats(path);
    }

}
