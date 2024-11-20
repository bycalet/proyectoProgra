package escencial;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class that works as systems to handle reservations. This class allows you 
 * to handle information in files.
 * @author calet
 */
public class ReservationSystem {

    private Person[] users;
    private Reservation[] reservations;
    private SportSpace[] spaces;
    private int userCount;
    private int reservationCount;
    private int spaceCount;
    
    
    public ReservationSystem() {
        this.users = new Person[10]; 
        this.reservations = new Reservation[10];
        this.spaces = new SportSpace[4];
        this.userCount = 0;
        this.reservationCount = 0;
        this.spaceCount = 0;

        spaces[spaceCount++] = new SportSpace("Cancha de Futbol");
        spaces[spaceCount++] = new SportSpace("Gimnasio");
        spaces[spaceCount++] = new SportSpace("Piscina");
    }

    
    
    /**
     * Gets the user counter
     * @return 
     */
    public int getUserCount() {
        return userCount;
    }

    /**
     * Gets the user array
     * @return 
     */
    public Person[] getUsers() {
        return users;
    }

    
    /**
     * Increases the user count
     */
    public void incrementUserCount() {
        userCount++;
    }

    /**
     * Decreases the user counter 
     */
    public void decrementUserCount() {
        userCount--;
    }

    /**
     * Gets the reservation counter
     * @return 
     */
    public int getReservationCount() {
        return reservationCount;
    }

    /**
     * Gets the reservation array
     * @return 
     */
    public Reservation[] getReservations() {
        return reservations;
    }

    /**
     * Gets the space counter
     * @return 
     */
    public int getSpaceCount() {
        return spaceCount;
    }

    /**
     * Gets the sports spaces array
     * @return 
     */
    public SportSpace[] getSpaces() {
        return spaces;
    }

    /**
     * Increases the space counter
     */
    public void incrementSpaceCount() {
        spaceCount++;
    }

    /**
     * Search for the user by their ID
     * @param id
     * @return 
     */
    public Person findUserById(int id) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        return null;
    }

    /**
     * Search for the sports space by name 
     * @param name
     * @return 
     */
    public SportSpace findSpaceByName(String name) {
        for (int i = 0; i < spaceCount; i++) {
            if (spaces[i].getName().equalsIgnoreCase(name)) {
                return spaces[i];
            }
        }
        return null; 
    }

    /**
     * Check if there is a schedule conflict for the reservation
     * @param space
     * @param dateTime
     * @return 
     */
    public boolean isReservationConflict(SportSpace space, LocalDateTime dateTime) {
        for (int i = 0; i < reservationCount; i++) {
            if (reservations[i].getSpace().equals(space)
                    && reservations[i].getDateTime().equals(dateTime)) {
                return true; 
            }
        }
        return false;
    }

    /**
     *Method to make a reservation. Display a message if no more reservations 
     * can be made.
     * @param user
     * @param space
     * @param dateTime 
     */
    public void makeReservation(Person user, SportSpace space, 
            LocalDateTime dateTime) {
        if (reservationCount >= reservations.length) {
            System.out.println(LanguageManager.get("no_more_reservations"));
            return;
        }
        reservations[reservationCount++] = new Reservation(user, space, dateTime);
        System.out.println(LanguageManager.get("reservation_successful"));
        saveReservationsToFile(); 
    }
    /**
     * Show a list of reservations of reservations
     * @param user 
     */
    public void listUserReservations(Person user) {
        System.out.println("\n--- " + LanguageManager.get("user_reservations") 
                + " ---");
        boolean hasReservations = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < reservationCount; i++) {
            Reservation r = reservations[i];
            if (r.getUser().getId() == user.getId()) {
                System.out.println(LanguageManager.get("reservation_info")
                        .replace("{space}", r.getSpace().getName())
                        .replace("{datetime}", r.getDateTime().format(formatter)));
                hasReservations = true;
            }
        }

        if (!hasReservations) {
            System.out.println(LanguageManager.get("no_reservations_for_user"));
        }
    }

    /**
     * Method to load users from a file.
     */
    public void loadUsersFromFile() {
        userCount = FileSaves.loadUsersFromFile(users);
    }

    /**
     * Method to save users in a file
     */
    public void saveUsersToFile() {
        FileSaves.saveUsersToFile(users, userCount);
    }

    /**
     *Method to load reservations from a file 
     */
    public void loadReservationsFromFile() {
        reservationCount = FileSaves.loadReservationsFromFile(reservations, 
                users, spaces, userCount, spaceCount);
    }

    /**
     * Method to save reservations in a file 
     */
    public void saveReservationsToFile() {
        FileSaves.saveReservationsToFile(reservations, reservationCount);
    }
}
