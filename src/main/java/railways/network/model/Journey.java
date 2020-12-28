package railways.network.model;

import java.io.Serializable;
import java.util.Date;

public class Journey implements Serializable {

    private Long id;
    private String from;
    private String to;
    private Long places;
    private Long price;
    private Date date;

    public Journey(Long id, String from, String to, Long places, Long price, Date date) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.places = places;
        this.price = price;
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getPlaces() {
        return places;
    }

    public void setPlaces(Long places) {
        this.places = places;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
