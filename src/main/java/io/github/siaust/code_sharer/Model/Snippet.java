package io.github.siaust.code_sharer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Snippet implements Comparable<Snippet> {

    @Id
    @JsonIgnore
    private final UUID id = UUID.randomUUID(); // Universally Unique ID for each Snippet

    private String code; // The actual code

    private final LocalDateTime date = LocalDateTime.now();
    @JsonIgnore
    private LocalDateTime lastTimeCheck; // helps in decrementing the time field

    private long time; // time the code snippet is available to view. Deleted when expired.

    private long views; // number of views allowed. Deleted when < 1.

    @JsonIgnore
    private boolean secret;

    // for Jackson
    public Snippet() {}

    public Snippet(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @JsonIgnore
    public LocalDateTime getLocalDateTime() {
        return date;
    }

    public LocalDateTime getLastTimeCheck() {
        return lastTimeCheck;
    }

    public void setLastTimeCheck(LocalDateTime lastCheck) {
        this.lastTimeCheck = lastCheck;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "\n***\ncode: " + code
                + "\ndate: " + getDate()
                + "\nuuid: " + id
                + "\nviews: " + views
                + "\ntime: " + time
                + "\n***";
    }

    @Override
    public int compareTo(Snippet o) {
        return this.getDate().compareTo(o.getDate());
    }
}
