package business;

import core.Helper;
import dao.HotelDao;
import dao.UserDao;
import entity.Hotel;
import entity.User;

import java.util.ArrayList;

public class HotelManager {
    private HotelDao hotelDao;
    public HotelManager(){
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotelList){
        ArrayList<Object[]> hotelObjList = new ArrayList<>();
        for (Hotel hotel : hotelList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = hotel.getMail();
            rowObject[i++] = hotel.getPhone();
            rowObject[i++] = hotel.getStar();
            rowObject[i++] = hotel.isCar_park();
            rowObject[i++] = hotel.isWifi();
            rowObject[i++] = hotel.isPool();
            rowObject[i++] = hotel.isFitness();
            rowObject[i++] = hotel.isConcierge();
            rowObject[i++] = hotel.isSpa();
            rowObject[i++] = hotel.isRoom_service();
            hotelObjList.add(rowObject);
        }
        return hotelObjList;
    }

    public boolean save(Hotel newHotel){
        if (newHotel.getId() != 0){
            Helper.showMsg("error");
            return false;
        }
        return this.hotelDao.save(newHotel);
    }


    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id+" ID kayıtlı marka bulunamadı");
            return false;
        }
        return this.hotelDao.delete(id);
    }

    public Hotel getById(int id){
        return this.hotelDao.getById(id);
    }


}
