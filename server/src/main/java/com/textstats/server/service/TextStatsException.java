package com.textstats.server.service;

public class TextStatsException extends RuntimeException {

    public TextStatsException(String message) {
        super(message);
    }

    public TextStatsException(String message, Throwable cause) {
        super(message, cause);
    }
}
