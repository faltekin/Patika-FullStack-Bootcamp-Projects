package dao;

import core.Db;
import entity.Pansion;
import entity.Reservation;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {

    private Connection con;
    public ReservationDao(){
        this.con = Db.getInstance();
    }

    public boolean save(Reservation newReservation){
        String query = "INSERT INTO public.reservation (room_id, check_in_date,check_out_date,total_price,guest_count,guest_name,guest_citizen_id,guest_mail,guest_phone) VALUES (?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,newReservation.getRoomId());
            pr.setDate(2, Date.valueOf(newReservation.getCheckIn()));
            pr.setDate(3, Date.valueOf(newReservation.getCheckOut()));
            //pr.setDate(2, newReservation.getCheckIn());
            //pr.setDate(3, newReservation.getCheckOut());
            pr.setDouble(4,newReservation.getTotalPrice());
            pr.setInt(5,newReservation.getGuestCount());
            pr.setString(6,newReservation.getGuestName());
            pr.setString(7,newReservation.getGuestCId());
            pr.setString(8,newReservation.getGuestMail());
            pr.setString(9,newReservation.getGuestPhone());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> reservList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                reservList.add(this.match(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return reservList;
    }
    public Reservation match(ResultSet rs) throws SQLException{
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("id"));
        obj.setRoomId(rs.getInt("room_id"));
        obj.setCheckIn(LocalDate.parse(rs.getString("check_in_date")));
        obj.setCheckOut(LocalDate.parse(rs.getString("check_out_date")));
        obj.setTotalPrice(rs.getInt("total_price"));
        obj.setGuestCount(rs.getInt("guest_count"));
        obj.setGuestName(rs.getString("guest_name"));
        obj.setGuestCId(rs.getString("guest_citizen_id"));
        obj.setGuestMail(rs.getString("guest_mail"));
        obj.setGuestPhone(rs.getString("guest_phone"));
        return obj;
    }

    public Reservation getById(int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ?";
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

    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservation SET " +
                " guest_name = ?," +
                "guest_citizen_id = ?," +
                "guest_mail = ?," +
                "guest_phone = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, reservation.getGuestName());
            pr.setString(2, reservation.getGuestCId());
            pr.setString(3, reservation.getGuestMail());
            pr.setString(4, reservation.getGuestPhone());
            pr.setInt(5, reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
}