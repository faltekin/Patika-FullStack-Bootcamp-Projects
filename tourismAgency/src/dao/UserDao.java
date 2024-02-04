package dao;

import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private Connection con;
    public UserDao(){
        this.con = Db.getInstance();
    }
    public User findByLogin(String username,String password){

        User obj = null;
        String query = "SELECT * FROM public.user WHERE username = ? AND password = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public User match(ResultSet rs) throws SQLException{
        User obj = new User();
        obj.setId(rs.getInt("id"));
        obj.setUsername(rs.getString("username"));
        obj.setPassword(rs.getString("password"));
        obj.setUserType(rs.getString("usertype"));

        return obj;
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public boolean update(User user){
        String query = "UPDATE public.user SET " +
                " username = ?," +
                "password = ?," +
                "usertype = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getUserType());
            pr.setInt(4, user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public User getById(int id){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE id = ?";
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

    public boolean save(User newUser){
        String query = "INSERT INTO public.user (username, password, usertype) VALUES (?,?,?) ";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1,newUser.getUsername());
            pr.setString(2,newUser.getPassword());
            pr.setString(3,newUser.getUserType());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int model_id){
        String query = "DELETE FROM public.user WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,model_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<User> findByRole(String userSearchRole) {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user WHERE usertype=" +"'"+userSearchRole+"'";
        try {

            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userList;

    }
}
