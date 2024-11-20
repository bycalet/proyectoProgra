package escencial;
/**
 * Super class of person. It initialises the attributes and contains methods
 * that allow information to be obtained.
 * @author calet
 */
public class Person {
    private String name;
    private String email;
    private int id;

    public Person(String name, String email, int id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }
    /**
     * Gets the name of the person
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * Allows change the person¨s name 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the email of the person
     * @return 
     */
    public String getEmail() {
        return email;
    }
    /**
     * Allows change the person¨s email
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets the ID of the person
     * @return 
     */
    public int getId() {
        return id;
    }
}
