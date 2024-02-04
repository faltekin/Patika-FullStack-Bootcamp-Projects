package business;

import core.Helper;
import dao.ReservationDao;
import dao.RoomDao;
import entity.Reservation;
import entity.Room;

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
}
