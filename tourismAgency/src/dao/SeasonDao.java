package dao;

import core.Db;
import entity.Hotel;
import entity.Pansion;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private Connection con;
    private HotelDao hotelDao;
    public SeasonDao(){
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
    }


    public boolean save(Season newSeason){
        String query = "INSERT INTO public.hotel_season (hotel_id, start_date, finish_date) VALUES (?,?,?) ";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,newSeason.getHotelId());
            pr.setDate(2,Date.valueOf(newSeason.getStartDate()));
            pr.setDate(3,Date.valueOf(newSeason.getFinishDate()));
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_season";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return seasonList;
    }
    public ArrayList<Season> findSeasonListByHotelId(int selectHotelId){
        return this.selectByQuery("SELECT * FROM public.hotel_season WHERE hotel_id = "+selectHotelId);
    }
    public ArrayList<Season> selectByQuery(String query){
        ArrayList<Season> modelList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                modelList.add(this.match(rs));
            }
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        return modelList;
    }
    public Season match(ResultSet rs) throws SQLException{
        Season obj = new Season();
        obj.setId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setStartDate(LocalDate.parse(rs.getString("start_date")));
        obj.setFinishDate(LocalDate.parse(rs.getString("finish_date")));
        obj.setHotel(hotelDao.getById(rs.getInt("hotel_id")));
        return obj;
    }
}
