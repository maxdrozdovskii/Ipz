package railways.network.model;

import java.io.Serializable;

public class UserJourney implements Serializable {

    private long place;
    private User user;
    private Journey journey;

    public UserJourney(long place, User user, Journey journey) {
        this.place = place;
        this.user = user;
        this.journey = journey;
    }

    public long getPlace() {
        return place;
    }

    public void setPlace(long place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }
}
