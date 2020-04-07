package com.textstats.server.service;

import com.textstats.server.model.TextStats;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class TextStatsServiceImpl implements TextStatsService {

    @Override
    public TextStats getWordCountStats(Path path) {
        Map<String,Long> wordCountMap = getWordCountMap(path);
        return new TextStats(getCount(wordCountMap));
    }

    /**
     * Method returns a map with the words and their total count
     * Method is case insensitive - converts all the words to lower case before perforing the mapping
     * @param path
     * @returns a Map of the words and their count
     */
    private Map<String,Long> getWordCountMap(Path path) {
        try {
            Stream<String> data = Files.lines(path);
            return data
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        }
        catch(IOException ioe) {
            throw new TextStatsException("Unable to process file " + path.getFileName().toString());
        }
    }

    /**
     * returns the sum of all the values from the given map
     * @param countMap
     * @return the total count
     */
    private Integer getCount(Map<String,Long> countMap) {
        return countMap.values()
                        .stream()
                        .mapToInt(Long::intValue)
                        .sum();
    }
}
