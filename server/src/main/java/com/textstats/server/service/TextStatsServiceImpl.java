package com.textstats.server.service;

import com.textstats.server.model.TextStats;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class TextStatsServiceImpl implements TextStatsService {


    @Override
    public TextStats getStats(Path path, Integer limit) {
        // word statistics
        Map<String,Long> wordCountMap = getWordCountMap(path);
        Integer wordCount = getCount(wordCountMap);
        List<String> mostFrequentWords = getMostFrequent(wordCountMap,limit);

        // characters or letters statistics
        Map<String,Long> characterCountMap = getCharacterCountMap(path);
        Integer characterCount = getCount(characterCountMap);
        List<String> mostFrequentCharacters = getMostFrequent(characterCountMap,limit);

        // symbols statistics
        Map<String,Long> symbolsCountMap = getSymbolCountMap(path);
        Integer symbolcount = getCount(symbolsCountMap);
        List<String> mostFrequentlyUsedSymbols  = getMostFrequent(symbolsCountMap,limit);

        return new TextStats(wordCount,characterCount,symbolcount, mostFrequentWords, mostFrequentCharacters, mostFrequentlyUsedSymbols);
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
                .filter(s -> !s.equals(""))
                .map(String::trim)
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

    /**
     * returns the most frequently used values
     * @param inputMap
     * @param limitValue to limit the number of n most recently used words
     * @return the n most recently used values
     */
    private static List<String> getMostFrequent(Map<String, Long> inputMap, int limitValue) {
        return inputMap.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(limitValue)
            .map(Entry::getKey)
            .collect(Collectors.toList());
    }

    /**
     * Method to get a map of characters and their count for the given file
     * @param path
     * @return
     */
    private Map<String,Long> getCharacterCountMap(Path path)  {
        try {
            return Files.lines(path)
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                .map(Object::toString)
                .map(String::trim)
                .collect(
                    Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
        catch(IOException ioe){
            throw new TextStatsException("Unable to process file " + path.getFileName().toString());
        }
    }

    /**
     * Method to get a map of symbols and their count for the given file
     * @param path
     * @return
     */
    private Map<String,Long> getSymbolCountMap(Path path)  {
        try {
            return Files.lines(path)
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .map(s -> s.replaceAll("[a-zA-Z0-9]+" , ""))
                .map(String::trim)
                .filter(s -> !s.equals(""))
                .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                .map(Object::toString)
                .collect(
                    Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
        catch(IOException ioe){
            throw new TextStatsException("Unable to process file " + path.getFileName().toString());
        }
    }

}
