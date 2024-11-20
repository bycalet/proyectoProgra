
package escencial;
/**
 * The SportSpace class represents a sports space that can be booked.
 * It contains the name of the space and its availability status.
 * 
 * This class provides methods to retrieve and modify the availability status,
 * as well as to obtain the name of the sports space.
 * 
 * @author calet
 */
public class SportSpace {
    private String name;
    private boolean available;
    
    public SportSpace(String name) {
        this.name = name;
        this.available = true;
    }
    /**
     * This method allows you to obtain the name of the sports space
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * This method returns the availability
     * @return 
     */
    public boolean isAvailable() {
        return available;
    }
    /**
     * This method allows you to change the status
     * @param available 
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
