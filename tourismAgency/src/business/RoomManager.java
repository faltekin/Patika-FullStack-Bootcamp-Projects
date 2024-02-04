package business;

import core.Helper;
import dao.HotelDao;
import dao.RoomDao;
import entity.Pansion;
import entity.Room;
import entity.Season;
import entity.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class RoomManager {

    private RoomDao roomDao;
    public RoomManager(){
        this.roomDao = new RoomDao();
    }

    public boolean save(Room newRoom){
        if (newRoom.getId() != 0){
            Helper.showMsg("error");
            return false;
        }
        return this.roomDao.save(newRoom);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms){
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room obj : rooms){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getPansionId();
            rowObject[i++] = obj.getSeasonId();
            rowObject[i++] = obj.getRoomType();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdultPrice();
            rowObject[i++] = obj.getChildPrice();
            rowObject[i++] = obj.getBedCapacity();
            rowObject[i++] = obj.getSquareMeter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGameConsole();
            rowObject[i++] = obj.isCashBox();
            rowObject[i++] = obj.isProjection();
            roomList.add(rowObject);
        }
        return roomList;
    }

    public ArrayList<Room> searchForRoomList(String hotelName, String hotelAddress, String checkIn, String checkOut) {

        String query = "SELECT * FROM room WHERE stock > 0";

        //ArrayList<String> queryList = new ArrayList<>();

        if (hotelName != null) {
            query += " AND hotel_id IN (SELECT id FROM hotel WHERE name LIKE '%" + hotelName + "%')";
        }
        if (hotelAddress != null) {
            query += " AND hotel_id IN (SELECT id FROM hotel WHERE address LIKE '%" + hotelAddress + "%')";
        }
        if (checkIn != null && checkOut != null){

            SimpleDateFormat changeFormat = new SimpleDateFormat("dd/MM/yyyy");
            try{
                Date userStart = changeFormat.parse(checkIn);
                Date userEnd = changeFormat.parse(checkOut);

                String formattedCheckIn = changeFormat.format(userStart);
                String formattedCheckOut = changeFormat.format(userEnd);
                query += " AND season_id IN (SELECT id FROM hotel_season WHERE " + "start_date <= '" + formattedCheckIn + "' " + "AND finish_date >= '" + formattedCheckOut + "')";
            } catch (ParseException e){
                e.printStackTrace();
            }

        }

        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);
        return searchedRoomList;


    }
    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }

}
