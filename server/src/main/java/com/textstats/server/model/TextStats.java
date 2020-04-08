package com.textstats.server.model;

import java.util.List;

public class TextStats {
    private Integer wordCount;
    private Integer characterCount;
    private Integer symbolCount;
    private List<String> frequentlyUsedWords;
    private List<String> frequentlyUsedCharacters;
    private List<String> frequentlyUsedSymbols;

    public TextStats(Integer wordCount, Integer characterCount, Integer symbolCount,
        List<String> frequentlyUsedWords, List<String> frequentlyUsedCharacters,
        List<String> frequentlyUsedSymbols) {
        this.wordCount = wordCount;
        this.characterCount = characterCount;
        this.symbolCount = symbolCount;
        this.frequentlyUsedWords = frequentlyUsedWords;
        this.frequentlyUsedCharacters = frequentlyUsedCharacters;
        this.frequentlyUsedSymbols = frequentlyUsedSymbols;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getCharacterCount() {
        return characterCount;
    }

    public void setCharacterCount(Integer characterCount) {
        this.characterCount = characterCount;
    }

    public Integer getSymbolCount() {
        return symbolCount;
    }

    public void setSymbolCount(Integer symbolCount) {
        this.symbolCount = symbolCount;
    }

    public List<String> getFrequentlyUsedWords() {
        return frequentlyUsedWords;
    }

    public void setFrequentlyUsedWords(List<String> frequentlyUsedWords) {
        this.frequentlyUsedWords = frequentlyUsedWords;
    }

    public List<String> getFrequentlyUsedCharacters() {
        return frequentlyUsedCharacters;
    }

    public void setFrequentlyUsedCharacters(List<String> frequentlyUsedCharacters) {
        this.frequentlyUsedCharacters = frequentlyUsedCharacters;
    }

    public List<String> getFrequentlyUsedSymbols() {
        return frequentlyUsedSymbols;
    }

    public void setFrequentlyUsedSymbols(List<String> frequentlyUsedSymbols) {
        this.frequentlyUsedSymbols = frequentlyUsedSymbols;
    }

    @Override
    public String toString() {
        return "TextStats{" +
            "wordCount=" + wordCount +
            ", characterCount=" + characterCount +
            ", symbolCount=" + symbolCount +
            ", frequentlyUsedWords=" + frequentlyUsedWords +
            ", frequentlyUsedCharacters=" + frequentlyUsedCharacters +
            ", frequentlyUsedSymbols=" + frequentlyUsedSymbols +
            '}';
    }
}
