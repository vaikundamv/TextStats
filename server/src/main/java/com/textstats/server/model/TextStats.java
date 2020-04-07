package com.textstats.server.model;

import java.util.List;

public class TextStats {
    private Integer wordCount;
    private List<String> frequentlyUsedWords;

    public TextStats(Integer wordCount, List<String> frequentlyUsedWords) {
        this.wordCount = wordCount;
        this.frequentlyUsedWords = frequentlyUsedWords;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public List<String> getFrequentlyUsedWords() {
        return frequentlyUsedWords;
    }

    public void setFrequentlyUsedWords(List<String> frequentlyUsedWords) {
        this.frequentlyUsedWords = frequentlyUsedWords;
    }

    @Override
    public String toString() {
        return "TextStats{" +
            "wordCount=" + wordCount +
            ", frequentlyUsedWords=" + frequentlyUsedWords +
            '}';
    }
}
