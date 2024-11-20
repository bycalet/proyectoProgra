package escencial;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Main class. Allows you to choose a language and perform actions according
 * to the type of user. 
 * @author calet
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Selección de idioma
        System.out.println("Select Language / Seleccione Idioma / Selecione Idioma:");
        System.out.println("1. Español");
        System.out.println("2. English");
        System.out.println("3. Português");
        System.out.print("Choice / Elección / Escolha: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                LanguageManager.setLanguage("es");
                break;
            case 2:
                LanguageManager.setLanguage("en");
                break;
            case 3:
                LanguageManager.setLanguage("pt");
                break;
            default:
                System.out.println("Invalid choice. Defaulting to English.");
                LanguageManager.setLanguage("en");
        }

        ReservationSystem system = new ReservationSystem();
        Scanner sc = new Scanner(System.in);

        // Cargar datos iniciales
        System.out.println(LanguageManager.get("loading_users"));
        system.loadUsersFromFile();
        System.out.println(LanguageManager.get("users_loaded"));

        System.out.println(LanguageManager.get("loading_reservations"));
        system.loadReservationsFromFile();
        System.out.println(LanguageManager.get("reservations_loaded"));

        // Inicio de sesión
        System.out.println("=== " + LanguageManager.get("system_title") + " ===");
        System.out.print(LanguageManager.get("enter_id"));
        int userId = sc.nextInt();
        sc.nextLine();

        Person user = system.findUserById(userId);

        if (user == null) {
            System.out.println(LanguageManager.get("id_not_found"));
            return;
        }

        // Diferenciar entre Admin y Regular
        if (user instanceof Admin) {
            adminMenu(system, sc, (Admin) user);
        } else if (user instanceof Regular) {
            regularMenu(system, sc, (Regular) user);
        }
    }

    // Menú para Administradores
    
    /**
     * Method to show a menu to the administrators. Call methods according to 
     * the option you choose.
     * @param system
     * @param scanner
     * @param admin 
     */
    private static void adminMenu(ReservationSystem system, Scanner scanner, 
            Admin admin) {
        boolean running = true;

        while (running) {
            System.out.println("\n" + LanguageManager.get("menu_admin"));
            System.out.println(LanguageManager.get("option_1"));
            System.out.println(LanguageManager.get("option_2"));
            System.out.println(LanguageManager.get("option_3"));
            System.out.println(LanguageManager.get("option_4"));
            System.out.println(LanguageManager.get("option_5"));
            System.out.print(LanguageManager.get("select_option"));
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllReservations(system);
                    break;
                case 2:
                    addUser(system, scanner);
                    break;
                case 3:
                    deleteUser(system, scanner);
                    break;
                case 4:
                    addSportSpace(system, scanner);
                    break;
                case 5:
                    System.out.println(LanguageManager.get("exiting_system"));
                    running = false;
                    break;
                default:
                    System.out.println(LanguageManager.get("invalid_option"));
                    break;
            }
        }
    }

    // Menú para Usuarios Regulares
    
    /**
     * Method to show a menu to the regulars users. Call methods according to
     * the option you choose.
     * @param system
     * @param scanner
     * @param user 
     */
    private static void regularMenu(ReservationSystem system, Scanner scanner, 
            Regular user) {
        boolean running = true;

        while (running) {
            System.out.println("\n" + LanguageManager.get("menu_user"));
            System.out.println(LanguageManager.get("option_6"));
            System.out.println(LanguageManager.get("option_7")); 
            System.out.println(LanguageManager.get("option_8")); 
            System.out.println(LanguageManager.get("option_5")); 
            System.out.print(LanguageManager.get("select_option"));
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makeReservation(system, scanner, user);
                    break;
                case 2:
                    system.listUserReservations(user);
                    break;
                case 3:
                    listAvailableSpaces(system);
                    break;
                case 5:
                    System.out.println(LanguageManager.get("exiting_system"));
                    running = false;
                    break;
                default:
                    System.out.println(LanguageManager.get("invalid_option"));
                    break;
            }
        }
    }

    // Métodos del menú Administrador
    
    /**
     * Method to see all reservations. Print the information of facts that
     * meet the size of the arrangement. 
     * @param system 
     */
    private static void listAllReservations(ReservationSystem system) {
        System.out.println("\n" + LanguageManager.get("all_reservations"));
        
        if (system.getReservationCount() == 0) {
            System.out.println(LanguageManager.get("no_reservations"));
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < system.getReservationCount(); i++) {
            Reservation r = system.getReservations()[i];
            System.out.println(LanguageManager.get("reservation_info")
                    .replace("{user}", r.getUser().getName())
                    .replace("{space}", r.getSpace().getName())
                    .replace("{datetime}", r.getDateTime().format(formatter)));
        }
    }
    
    /**
     * Method to add a new user. Increases the user counter according to the
     * role choose and saves them in a file. 
     * @param system
     * @param scanner 
     */
    private static void addUser(ReservationSystem system, Scanner scanner) {
        scanner.nextLine(); 
        System.out.print(LanguageManager.get("enter_user_name"));
        String name = scanner.nextLine();
        System.out.print(LanguageManager.get("enter_user_email"));
        String email = scanner.nextLine();
        System.out.print(LanguageManager.get("enter_user_id"));
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print(LanguageManager.get("enter_user_role"));
        String role = scanner.nextLine();

        if (role.equalsIgnoreCase("admin")) {
            system.getUsers()[system.getUserCount()] = new Admin(name, email, 
                    id);
            system.incrementUserCount();
        } else if (role.equalsIgnoreCase("regular")) {
            system.getUsers()[system.getUserCount()] = new Regular(name, email, 
                    id);
            system.incrementUserCount();
        } else {
            System.out.println(LanguageManager.get("invalid_role"));
            return;
        }

        system.saveUsersToFile();
        System.out.println(LanguageManager.get("user_added_successfully"));
    }
    /**
     * Method to remove a user. Decreases the user counter and updates the 
     * file.
     * @param system
     * @param scanner 
     */
    private static void deleteUser(ReservationSystem system, Scanner scanner) {
        System.out.print(LanguageManager.get("enter_user_id_delete"));
        int id = scanner.nextInt();
        boolean deleted = false;

        for (int i = 0; i < system.getUserCount(); i++) {
            if (system.getUsers()[i].getId() == id) {
                System.arraycopy(system.getUsers(), i + 1, system.getUsers(), i,
                        system.getUserCount() - i - 1);
                system.decrementUserCount();
                deleted = true;
                break;
            }
        }

        if (deleted) {
            system.saveUsersToFile();
            System.out.println(LanguageManager.get("user_deleted_successfully"));
        } else {
            System.out.println(LanguageManager.get("user_not_found"));
        }
    }
    /**
     * Method to add a new sport space. 
     * @param system
     * @param scanner 
     */
    private static void addSportSpace(ReservationSystem system, Scanner scanner) {
        scanner.nextLine(); 
        System.out.print(LanguageManager.get("enter_space_name"));
        String name = scanner.nextLine();
        system.getSpaces()[system.getSpaceCount()] = new SportSpace(name);
        system.incrementSpaceCount();
        System.out.println(LanguageManager.get("space_added_successfully"));
    }

    // Métodos del menú Usuario Regular
    
    /**
     * Method for a regular user to make a reservation. Shows available spaces
     * and asks you to enter one to make the reservation. Request the date if 
     * the option is valid. 
     * @param system
     * @param scanner
     * @param user 
     */
    private static void makeReservation(ReservationSystem system, Scanner scanner, Regular user) {
        System.out.println("\n" + LanguageManager.get("available_spaces"));
        boolean hasSpaces = false;

        // Mostrar los espacios deportivos disponibles
        for (int i = 0; i < system.getSpaceCount(); i++) {
            SportSpace space = system.getSpaces()[i];
            // Asumiendo que cada espacio tiene un atributo "available"
            if (space.isAvailable()) {
                System.out.println((i + 1) + ". " + space.getName());
                hasSpaces = true;
            }
        }

        if (!hasSpaces) {
            System.out.println(LanguageManager.get("no_spaces_available"));
            return;
        }

        System.out.print(LanguageManager.get("select_space_number"));
        int spaceChoice = scanner.nextInt() - 1;

        if (spaceChoice < 0 || spaceChoice >= system.getSpaceCount() ||
                !system.getSpaces()[spaceChoice].isAvailable()) {
            System.out.println(LanguageManager.get("invalid_option"));
            return;
        }

        SportSpace selectedSpace = system.getSpaces()[spaceChoice];
        scanner.nextLine(); // Consumir línea

        System.out.print(LanguageManager.get("enter_reservation_datetime"));
        String dateTimeInput = scanner.nextLine();

        try {
            // Parsear la fecha y hora utilizando LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeInput, formatter);

            if (system.isReservationConflict(selectedSpace, dateTime)) {
                System.out.println(LanguageManager.get("reservation_conflict"));
            } else {
                system.makeReservation(user, selectedSpace, dateTime);
                System.out.println(LanguageManager.get("reservation_successful"));
            }
        } catch (DateTimeParseException e) {
            System.out.println(LanguageManager.get("invalid_datetime_format"));
        }
    }
    
    /**
     * Method to show if a space has availability or not.
     * @param system 
     */
    private static void listAvailableSpaces(ReservationSystem system) {
        System.out.println("\n" + LanguageManager.get("available_spaces"));
        boolean hasSpaces = false;

        for (int i = 0; i < system.getSpaceCount(); i++) {
            SportSpace space = system.getSpaces()[i];
            if (space.isAvailable()) {
                System.out.println("- " + space.getName());
                hasSpaces = true;
            }
        }

        if (!hasSpaces) {
            System.out.println(LanguageManager.get("no_spaces_available"));
        }
    }
}

