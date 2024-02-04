package entity;

public class Pansion {

    private int id;
    private int hotelId;
    private String pansionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getPansionType() {
        return pansionType;
    }

    public void setPansionType(String pansionType) {
        this.pansionType = pansionType;
    }
}
