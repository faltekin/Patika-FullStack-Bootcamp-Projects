package business;

import core.Helper;
import dao.ReservationDao;
import dao.RoomDao;
import entity.Reservation;
import entity.Room;
import entity.User;

import java.util.ArrayList;

public class ReservationManager {

    private ReservationDao reservationDao;
    public ReservationManager(){
        this.reservationDao = new ReservationDao();
    }

    public boolean save(Reservation newReservation){
        if (newReservation.getId() != 0){
            Helper.showMsg("error");
            return false;
        }
        return this.reservationDao.save(newReservation);
    }
    public ArrayList<Reservation> findAll(){
        return this.reservationDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservList){
        ArrayList<Object[]> reservObjList = new ArrayList<>();
        for (Reservation reserv : reservList){
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = reserv.getId();
            rowObject[i++] = reserv.getRoomId();
            rowObject[i++] = reserv.getCheckIn();
            rowObject[i++] = reserv.getCheckOut();
            rowObject[i++] = reserv.getTotalPrice();
            rowObject[i++] = reserv.getGuestCount();
            rowObject[i++] = reserv.getGuestName();
            rowObject[i++] = reserv.getGuestCId();
            rowObject[i++] = reserv.getGuestMail();
            rowObject[i++] = reserv.getGuestPhone();
            reservObjList.add(rowObject);
        }
        return reservObjList;
    }

    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id+" ID kayıtlı bulunamadı");
            return false;
        }
        return this.reservationDao.delete(id);
    }

    public Reservation getById(int id){
        return this.reservationDao.getById(id);
    }

    public boolean update(Reservation reservation){
        if (this.getById(reservation.getId()) == null){
            Helper.showMsg(reservation.getId()+ " ID kayıtlı bulunamadı");
            return false;
        }
        return this.reservationDao.update(reservation);
    }
}