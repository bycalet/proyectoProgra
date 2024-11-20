package escencial;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileSaves {

    
    /**
     * This method allows you to save the users in a file.
     * @param users
     * @param userCount 
     */
    public static void saveUsersToFile(Person[] users, int userCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter
        ("listaUsuario.txt"))) {
            for (int i = 0; i < userCount; i++) {
                Person user = users[i];
                String role = (user instanceof Admin) ? "admin" : "regular";
                writer.write(user.getId() + "," + user.getName() + "," + 
                        user.getEmail() + "," + role);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    
    /**
     * This method allows you to load users from a file. When reading them, 
     * it creates an arrangement that divides the information into parts. 
     * @param users
     * @return 
     */
    
    public static int loadUsersFromFile(Person[] users) {
        int count = 0;
        File file = new File("listaUsuario.txt");
        if (!file.exists()) {
            System.out.println("El archivo listaUsuario.txt no existe. "
                    + "Se creará uno nuevo.");
            return count;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                String role = parts[3];

                if (role.equalsIgnoreCase("admin")) {
                    users[count++] = new Admin(name, email, id);
                } else if (role.equalsIgnoreCase("regular")) {
                    users[count++] = new Regular(name, email, id);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
        return count;
    }

   /**
    * This method allows writing the information to the file.
    * @param reservations
    * @param reservationCount 
    */
    public static void saveReservationsToFile(Reservation[] reservations, int 
            reservationCount) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("listaReserva.txt"))) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < reservationCount; i++) {
            Reservation r = reservations[i];
            writer.write(r.getUser().getId() + "," +
                         r.getSpace().getName() + "," +
                         r.getDateTime().format(formatter));
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error al guardar reservas: " + e.getMessage());
    }
}

    /**
     * Added functionality to load reservations from a file.
     * Reads `listaReserva.txt` to load reservations into the `reservations` array.
     * Links reservations to users (`Person[]`) and spaces (`SportSpace[]`) using their IDs and names.
     * Validates file existence, parses data (user ID, space name, and date-time), and creates reservations.
     * Handles errors for missing files, malformed data, and invalid formatting.
     * Returns the total count of successfully loaded reservations.
     * @param reservations
     * @param users
     * @param spaces
     * @param userCount
     * @param spaceCount
     * @return 
     */
 public static int loadReservationsFromFile(Reservation[] reservations, 
         Person[] users, SportSpace[] spaces, int userCount, int spaceCount) {
    int count = 0;
    File file = new File("listaReserva.txt");
    if (!file.exists()) {
        System.out.println("El archivo listaReserva.txt no existe. "
                + "Se creará uno nuevo.");
        return count;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int userId = Integer.parseInt(parts[0]);
            String spaceName = parts[1];
            String dateTimeString = parts[2]; 

            
            Person user = findUserById(users, userCount, userId);
            SportSpace space = findSpaceByName(spaces, spaceCount, spaceName);

         
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

            if (user != null && space != null) {
                reservations[count++] = new Reservation(user, space, dateTime);
            }
        }
    } catch (IOException | NumberFormatException | 
            java.time.format.DateTimeParseException e) {
        System.out.println("Error al cargar reservas: " + e.getMessage());
    }
    return count;
}


 /**
  * This method allows you to search for a user by their id. It returns the 
  * user in the position where it is found. 
  * @param users
  * @param userCount
  * @param id
  * @return 
  */
private static Person findUserById(Person[] users, int userCount, int id) {
    for (int i = 0; i < userCount; i++) {
        if (users[i].getId() == id) {
            return users[i];
        }
    }
    return null;
}

/**
 * This method allows you to find a sports space by name.
 * @param spaces
 * @param spaceCount
 * @param name
 * @return 
 */
private static SportSpace findSpaceByName(SportSpace[] spaces, int spaceCount, 
        String name) {
    for (int i = 0; i < spaceCount; i++) {
        if (spaces[i].getName().equalsIgnoreCase(name)) {
            return spaces[i];
        }
    }
    return null; 
}
}