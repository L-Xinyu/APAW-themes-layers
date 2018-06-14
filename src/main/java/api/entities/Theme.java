package api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Theme {

    private String id;

    private String reference;

    private LocalDateTime date;

    private Category category;

    private User user;

    private List<Vote> votes;

    public Theme(String reference, Category category, User user) {
        this.reference = reference;
        this.date = LocalDateTime.now();
        this.category = category;
        this.user = user;
        this.votes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id='" + id + '\'' +
                ", reference='" + reference + '\'' +
                ", date=" + date +
                ", category=" + category +
                ", user=" + user +
                ", votes=" + votes +
                '}';
    }
}
