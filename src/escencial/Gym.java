package escencial;

public class Gym extends SportSpace {

    public Gym(String namePlace, boolean available, int capacity) {
        super(namePlace, available, capacity);
    }

    @Override
    public void reserveSpace() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
