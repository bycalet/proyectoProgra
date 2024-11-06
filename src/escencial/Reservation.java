package escencial;

import java.util.Date;


public class Reservation {

    private Date date;

    private SportSpace space;

    private String state;

    private Regular regular;

    public Reservation(Date date, SportSpace space, String state, Regular regular) {
        this.date = date;
        this.space = space;
        this.state = state;
        this.regular = regular;
    }
    
    
    

    public void cancel() {
    }

    public void confirm() {
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the space
     */
    public SportSpace getSpace() {
        return space;
    }

    /**
     * @param space the space to set
     */
    public void setSpace(SportSpace space) {
        this.space = space;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the regular
     */
    public Regular getRegular() {
        return regular;
    }

    /**
     * @param regular the regular to set
     */
    public void setRegular(Regular regular) {
        this.regular = regular;
    }
}
