package io.github.siaust.code_sharer.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Snippet {

    // for Jackson
    public Snippet() {}

    private String code;
    private final String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public Snippet(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }
}
