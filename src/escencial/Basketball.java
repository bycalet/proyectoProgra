package escencial;

public class Basketball extends SportSpace {

    public Basketball(String namePlace, boolean available, int capacity) {
        super(namePlace, available, capacity);
    }

    @Override
    public void reserveSpace() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
