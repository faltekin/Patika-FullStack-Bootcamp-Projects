package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import dao.RoomDao;
import entity.Reservation;
import entity.Room;
import entity.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;

public class ReservationAddView extends Layout{
    private JPanel container;
    private JPanel w_top;
    private JPanel w_mid;
    private JPanel w_bot;
    private JTextField fld_rsv_hotel_name;
    private JTextField fld_rsv_hotel_address;
    private JTextField fld_rsv_hotel_star;
    private JCheckBox cb_rsv_park;
    private JCheckBox cb_rsv_wifi;
    private JCheckBox cb_rsv_pool;
    private JCheckBox cb_rsv_concierge;
    private JCheckBox cb_rsv_fitness;
    private JCheckBox cb_rsv_room_service;
    private JCheckBox cb_rsv_spa;
    private JTextField fld_rsv_room_type;
    private JTextField fld_rsv_pansion_type;
    private JTextField fld_rsv_room_bed;
    private JTextField fld_rsv_room_strt_date;
    private JTextField fld_rsv_room_square;
    private JTextField fld_rsv_room_fnsh_date;
    private JTextField fld_rsv_room_total_price;
    private JCheckBox cb_rsv_tv;
    private JCheckBox cb_rsv_minibar;
    private JCheckBox cb_rsv_console;
    private JCheckBox cb_rsv_cash_box;
    private JCheckBox cb_rsv_projection;
    private JTextField fld_rsv_guest_count;
    private JTextField fld_rsv_guest_name;
    private JTextField fld_rsv_guest_id;
    private JTextField fld_rsv_guest_mail;
    private JTextField fld_rsv_guest_phone;
    private JButton btn_rsv_save;


    private Reservation reservation;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private Room room;
    private RoomDao roomDao;
    private int selectdRoomId;
    private String selectedStart;
    private String selectedFinish;
    private double selectedPrice;

    public ReservationAddView(Reservation reservation,int selectdRoomId,String selectedStart,String selectedFinish,double selectedPrice){
        this.room = room;
        this.roomManager = new RoomManager();
        this.roomDao = new RoomDao();
        this.selectdRoomId = selectdRoomId;
        this.selectedStart = selectedStart;
        this.selectedFinish = selectedFinish;
        this.selectedPrice = selectedPrice;
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitilaze(800,800);

        btn_rsv_save.addActionListener(new ActionListener() {  // Değerlendirme formu 18
            @Override
            public void actionPerformed(ActionEvent e) {

                    JTextField[] fieldList = {fld_rsv_guest_count,fld_rsv_guest_name,fld_rsv_guest_id,fld_rsv_guest_mail,fld_rsv_guest_phone};
                    if (Helper.isFieldListEmpty(fieldList)){
                        Helper.showMsg("fill");
                    } else {
                        boolean result;
                        Reservation newReservation = new Reservation();
                        newReservation.setRoomId(selectdRoomId);
                        newReservation.setCheckIn(LocalDate.parse(selectedStart, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        newReservation.setCheckOut(LocalDate.parse(selectedFinish, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        newReservation.setTotalPrice(selectedPrice);
                        //newReservation.setTotalPrice(Double.parseDouble(String.valueOf(fld_rsv_guest_id)));
                        newReservation.setGuestCount(Integer.parseInt(fld_rsv_guest_count.getText()));
                        newReservation.setGuestName(fld_rsv_guest_name.getText());
                        newReservation.setGuestMail(fld_rsv_guest_mail.getText());
                        newReservation.setGuestCId(fld_rsv_guest_id.getText());

                        //newReservation.setGuestCId(Integer.parseInt(fld_rsv_guest_id.getText()));
                        //newReservation.setGuestCId(String.valueOf(fld_rsv_guest_id));

                        newReservation.setGuestMail(fld_rsv_guest_mail.getText());
                        newReservation.setGuestPhone(fld_rsv_guest_phone.getText());
                        result = reservationManager.save(newReservation);
                        if (result){
                            Helper.showMsg("done");
                            roomDao.dcStock(selectdRoomId);         // Değerlendirme formu 19 - stock değerini 1 azaltma
                        } else {
                            Helper.showMsg("error");
                        }
                    }
            }
        });
    }




}
