package org.example.db;

import javax.persistence.*;
import java.sql.Date;
import java.util.Map;

@Entity
@Table(name = "statistic")
public class Statistic {

    private long id;
    private String nameSite;
    private Date dateViewed;
    private Map<String, Integer> words;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public String getNameSite() {
        return nameSite;
    }

    public Date getDateViewed() {
        return dateViewed;
    }

    @ElementCollection
    public Map<String, Integer> getWords() {
        return words;
    }

    public void setWords(Map<String, Integer> words) {
        this.words = words;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
    }

    public void setDateViewed(Date date) {
        this.dateViewed = date;
    }
}
