package com.textstats.server.service;

import com.textstats.server.model.TextStats;
import java.nio.file.Path;

public interface TextStatsService {
    public TextStats getStats(Path path, Integer limit);
}
