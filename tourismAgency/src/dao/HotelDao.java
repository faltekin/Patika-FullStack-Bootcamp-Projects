package dao;

import core.Db;
import entity.Hotel;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    private Connection con;
    public HotelDao(){
        this.con = Db.getInstance();
    }

    public Hotel match(ResultSet rs) throws SQLException{
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setAddress(rs.getString("address"));
        obj.setMail(rs.getString("mail"));
        obj.setPhone(rs.getString("phone"));
        obj.setStar(rs.getString("star"));
        obj.setCar_park(rs.getBoolean("car_park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoom_service(rs.getBoolean("room_service"));
        return obj;
    }
    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return hotelList;
    }

    public boolean save(Hotel newHotel){
        String query = "INSERT INTO public.hotel (name, address, mail, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1,newHotel.getName());
            pr.setString(2,newHotel.getAddress());
            pr.setString(3,newHotel.getMail());
            pr.setString(4,newHotel.getPhone());
            pr.setString(5,newHotel.getStar());
            pr.setBoolean(6,newHotel.isCar_park());
            pr.setBoolean(7,newHotel.isWifi());
            pr.setBoolean(8,newHotel.isPool());
            pr.setBoolean(9,newHotel.isFitness());
            pr.setBoolean(10,newHotel.isConcierge());
            pr.setBoolean(11,newHotel.isSpa());
            pr.setBoolean(12,newHotel.isRoom_service());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public Hotel getById(int id){
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE id = ?";
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

    public boolean delete(int model_id){
        String query = "DELETE FROM public.hotel WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,model_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
