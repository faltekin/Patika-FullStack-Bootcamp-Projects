package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {

    private UserDao userDao;
    public UserManager(){
        this.userDao = new UserDao();
    }

    public User getById(int id){
        return this.userDao.getById(id);
    }
    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }
    public ArrayList<User> findByRole(String userSearchRole){
        return userDao.findByRole(userSearchRole);
    }
    public User findByLogin(String username, String password){
        return this.userDao.findByLogin(username,password);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList){
        ArrayList<Object[]> userObjList = new ArrayList<>();
        for (User user : userList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getUserType();
            userObjList.add(rowObject);
        }
        return userObjList;
    }

    public boolean update(User user){
        if (this.getById(user.getId()) == null){
            Helper.showMsg(user.getId()+ " ID kayıtlı bulunamadı");
            return false;
        }
        return this.userDao.update(user);
    }

    public boolean save(User newUser){
        if (newUser.getId() != 0){
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(newUser);
    }

    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id+" ID kayıtlı bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }


}
