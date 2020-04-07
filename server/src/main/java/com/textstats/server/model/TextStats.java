package com.textstats.server.model;

import java.util.List;

public class TextStats {
    private Integer wordCount;

    public TextStats(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    @Override
    public String toString() {
        return "TextStats{" +
            "wordCount=" + wordCount +
            '}';
    }
}
