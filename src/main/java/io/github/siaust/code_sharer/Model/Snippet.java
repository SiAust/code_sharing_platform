package io.github.siaust.code_sharer.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Snippet implements Comparable<Snippet> {

    // for Jackson
    public Snippet() {}

    private String code;
    private final LocalDateTime date = LocalDateTime.now();
// .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
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
        return LocalDateTime.from(date).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "***\ncode: " + code + "\ndate: " + date;
    }

    @Override
    public int compareTo(Snippet o) {
        return this.getDate().compareTo(o.getDate());
    }
}
