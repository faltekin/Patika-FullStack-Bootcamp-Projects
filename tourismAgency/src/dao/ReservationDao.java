package dao;

import core.Db;
import entity.Pansion;
import entity.Reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
