package entity;

import core.ComboItem;

public class Pansion {

    private int id;
    private int hotelId;
    private String pansionType;
    private Hotel hotel;

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

    public Object getComboItem() {
        return new ComboItem(this.getId(),this.getPansionType());
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
