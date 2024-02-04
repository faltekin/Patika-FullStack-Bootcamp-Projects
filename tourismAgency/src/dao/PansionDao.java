package dao;

import core.Db;
import entity.Pansion;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PansionDao {

    private Connection con;
    public PansionDao(){
        this.con = Db.getInstance();
    }

    public boolean save(Pansion newPansion){
        String query = "INSERT INTO public.hotel_pansion (hotel_id, pansion_type) VALUES (?,?) ";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,newPansion.getHotelId());
            pr.setString(2,newPansion.getPansionType());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Pansion> findAll() {
        ArrayList<Pansion> pansionList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_pansion";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                pansionList.add(this.match(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return pansionList;
    }
    public Pansion match(ResultSet rs) throws SQLException{
        Pansion obj = new Pansion();
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setPansionType(rs.getString("pansion_type"));
        return obj;
    }
}
