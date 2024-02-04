package business;

import core.Helper;
import dao.PansionDao;
import dao.SeasonDao;
import entity.Pansion;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {

    private SeasonDao seasonDao;
    public SeasonManager(){
        this.seasonDao = new SeasonDao();
    }

    public boolean save(Season newSeason){
        if (newSeason.getId() != 0){
            Helper.showMsg("error");
            return false;
        }
        return this.seasonDao.save(newSeason);
    }

    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }
    public ArrayList<Season> findSeasonListByHotelId(int selectHotelId){
        return this.seasonDao.findSeasonListByHotelId(selectHotelId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasonList){
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for (Season season : seasonList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = season.getHotelId();
            rowObject[i++] = season.getStartDate();
            rowObject[i++] = season.getFinishDate();
            seasonObjList.add(rowObject);
        }
        return seasonObjList;
    }


}
