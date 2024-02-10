package business;

import core.Helper;
import dao.HotelDao;
import dao.PansionDao;
import entity.Hotel;
import entity.Pansion;
import entity.User;

import java.util.ArrayList;

public class PansionManager {

    private PansionDao pansionDao;
    public PansionManager(){
        this.pansionDao = new PansionDao();
    }

    public boolean save(Pansion newPansion){
        if (newPansion.getId() != 0){
            Helper.showMsg("error");
            return false;
        }
        return this.pansionDao.save(newPansion);
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pansion> pansionList){
        ArrayList<Object[]> pansionObjList = new ArrayList<>();
        for (Pansion pansion : pansionList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = pansion.getId();
            rowObject[i++] = pansion.getHotelId();
            rowObject[i++] = pansion.getPansionType();
            pansionObjList.add(rowObject);
        }
        return pansionObjList;
    }
    public Pansion getById(int id){
        return this.pansionDao.getById(id);
    }
    public Pansion getByIdHotel(int id){
        return this.pansionDao.getByIdHotel(id);
    }
    public ArrayList<Pansion> findAll(){
        return this.pansionDao.findAll();
    }


}
