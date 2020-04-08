package com.textstats.server.controllers;

import com.textstats.server.model.TextStats;
import com.textstats.server.service.TextStatsService;
import com.textstats.server.storage.StorageService;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Text;

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
     * API to get text statistics like wordcount, frequently used words etc...
     * @param limit - frequency limit to return n most recently used statistics
     * @return
     */
    @GetMapping("/stats")
    public Map<String,TextStats> getStats(@RequestParam Optional<Integer> limit) {
        List<Path> paths = storageService.fetchUploadedFiles();
        assert paths.size() > 0;

        Map<String, TextStats> fileStatsMap = new HashMap<>();
        for(Path path: paths) {
            TextStats stats = textStatsService.getStats(path,limit.orElse(3));
            fileStatsMap.put(path.getFileName().toString(),stats);
        }
        return fileStatsMap;
    }

}
