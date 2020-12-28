package railways.database.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_journeys")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user")),
        @AssociationOverride(name = "pk.journey", joinColumns = @JoinColumn(name = "journey"))
})
public class UserJourney implements Serializable {
    private UserJourneyId pk = new UserJourneyId();

    public UserJourney() {
    }

    @EmbeddedId
    public UserJourneyId getPk() {
        return pk;
    }

    public void setPk(UserJourneyId pk) {
        this.pk = pk;
    }

    @Transient
    public Journey getJourney() {
        return getPk().getJourney();
    }

    public void setJourney(Journey journey) {
        getPk().setJourney(journey);
    }

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public Long getPlace() {
        return getPk().getPlace();
    }

    public void setPlace(Long place) {
        getPk().setPlace(place);
    }


}
