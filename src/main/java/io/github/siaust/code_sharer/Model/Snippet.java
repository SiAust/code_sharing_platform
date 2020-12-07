package io.github.siaust.code_sharer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Snippet implements Comparable<Snippet> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String code;

    private final String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // for Jackson
    public Snippet() {}

    public Snippet(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "***\ncode: " + code + "\ndate: " + date;
    }

    @Override
    public int compareTo(Snippet o) {
        return this.getDate().compareTo(o.getDate());
    }
}
