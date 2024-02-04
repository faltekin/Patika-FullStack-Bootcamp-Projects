package dao;

import core.Db;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RoomDao {
    private Connection con;
    public RoomDao(){
        this.con = Db.getInstance();
    }

    public boolean save(Room newRoom){
        String query = "INSERT INTO public.room (hotel_id, pansion_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,newRoom.getHotelId());
            pr.setInt(2,newRoom.getPansionId());
            pr.setInt(3,newRoom.getSeasonId());
            pr.setString(4,newRoom.getRoomType());
            pr.setInt(5,newRoom.getStock());
            pr.setDouble(6,newRoom.getAdultPrice());
            pr.setDouble(7,newRoom.getChildPrice());
            pr.setInt(8,newRoom.getBedCapacity());
            pr.setInt(9,newRoom.getSquareMeter());
            pr.setBoolean(10,newRoom.isTelevision());
            pr.setBoolean(11,newRoom.isMinibar());
            pr.setBoolean(12,newRoom.isGameConsole());
            pr.setBoolean(13,newRoom.isCashBox());
            pr.setBoolean(14,newRoom.isProjection());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
    public Room getById(int id){
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                rooms.add(this.match(rs));
            }
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        return rooms;
    }
    public Room match(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setHotelId(rs.getInt("hotel_id"));
        room.setPansionId(rs.getInt("pansion_id"));
        room.setSeasonId(rs.getInt("season_id"));
        room.setRoomType(rs.getString("type"));
        room.setStock(rs.getInt("stock"));
        room.setAdultPrice(rs.getInt("adult_price"));
        room.setChildPrice(rs.getInt("child_price"));
        room.setBedCapacity(rs.getInt("bed_capacity"));
        room.setSquareMeter(rs.getInt("square_meter"));
        room.setTelevision(rs.getBoolean("television"));
        room.setMinibar(rs.getBoolean("minibar"));
        room.setGameConsole(rs.getBoolean("game_console"));
        room.setCashBox(rs.getBoolean("cash_box"));
        room.setProjection(rs.getBoolean("projection"));

        return room;
    }

    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                roomList.add(this.match(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return roomList;
    }


    public void dcStock(int selectdRoomId) {

        Room rmd = getById(selectdRoomId);
        int currentStock = rmd.getStock();
        int updatedStock = currentStock - 1;
        String updateQuery = "UPDATE public.room SET stock = ? WHERE id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(updateQuery);
            pr.setInt(1, updatedStock);
            pr.setInt(2, selectdRoomId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void ıncStock(int selectdRoomId) {

        Room rmd = getById(selectdRoomId);
        int currentStock = rmd.getStock();
        int updatedStock = currentStock + 1;
        String updateQuery = "UPDATE public.room SET stock = ? WHERE id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(updateQuery);
            pr.setInt(1, updatedStock);
            pr.setInt(2, selectdRoomId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
