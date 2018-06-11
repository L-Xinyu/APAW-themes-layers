package api.entities;

import java.time.LocalDateTime;

public class Vote {

    private String id;

    private int value;

    private LocalDateTime date;

    public Vote(String id, int value, Theme theme) {
        this.id = id;
        this.value = value;
        this.date = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
