package escencial;

public class Soccer extends SportSpace {

    private String type;

    public Soccer(String namePlace, boolean available, int capacity, String type) {
        super(namePlace, available, capacity);
    }

    

    @Override
    public void reserveSpace() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
