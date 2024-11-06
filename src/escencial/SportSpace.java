package escencial;

public abstract class SportSpace implements Space {

    private String namePlace;

    private boolean available;

    private int capacity;

    public SportSpace(String namePlace, boolean available, int capacity) {
        this.namePlace = "";
        this.available = true;
        this.capacity = 0;
    }
    
    public boolean checkAvailability() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the namePlace
     */
    public String getNamePlace() {
        return namePlace;
    }

    /**
     * @param namePlace the namePlace to set
     */
    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
