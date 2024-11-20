package escencial;

import java.time.LocalDateTime;
/**
 * Class for a reservation. Initialise and use objects from other classes.
 * @author calet
 */
public class Reservation {
    private Person user;
    private SportSpace space;
    private LocalDateTime dateTime;

    public Reservation(Person user, SportSpace space, LocalDateTime dateTime) {
        this.user = user;
        this.space = space;
        this.dateTime = dateTime;
    }
    /**
     * Gets the user of Person class
     * @return 
     */
    public Person getUser() {
        return user;
    }
    /**
     * Gets the space of SportSpace class
     * @return 
     */
    public SportSpace getSpace() {
        return space;
    }
    /**
     * Gets the date and hour in the established format
     * @return 
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
