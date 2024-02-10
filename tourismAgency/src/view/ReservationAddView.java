package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import dao.RoomDao;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import entity.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ChronoUnit;

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
    private HotelManager hotelManager;
    private Hotel hotel;
    private int selectdRoomId;
    private String selectedStart;
    private String selectedFinish;
    private double selectedPrice;
    private int sum;
    private String selectedHotelName;
    private int adultCnt;
    private int childCnt;
    private int selectedPansionId;
    private double totalPrice;

    public ReservationAddView(Reservation reservation,int selectdRoomId,String selectedStart,String selectedFinish,int sum,String selectedHotelName,int adultCnt,int childCnt,int selectedPansionId){
        this.room = room;
        this.roomManager = new RoomManager();
        this.roomDao = new RoomDao();
        this.selectdRoomId = selectdRoomId;
        this.selectedStart = selectedStart;
        this.selectedFinish = selectedFinish;
        this.selectedPrice = selectedPrice;
        this.selectedPansionId = selectedPansionId;
        this.selectedHotelName = selectedHotelName;
        this.totalPrice = totalPrice;
        this.reservation = reservation;
        this.hotel = hotel;
        this.adultCnt = adultCnt;
        this.childCnt = childCnt;
        this.hotelManager = new HotelManager();
        this.sum = sum;
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitilaze(800,800);

        this.fld_rsv_room_type.setEnabled(false);
        this.fld_rsv_pansion_type.setEnabled(false);
        this.fld_rsv_room_bed.setEnabled(false);
        this.fld_rsv_room_square.setEnabled(false);
        this.fld_rsv_room_strt_date.setEnabled(false);
        this.fld_rsv_room_fnsh_date.setEnabled(false);
        this.fld_rsv_room_total_price.setEnabled(false);
        this.cb_rsv_tv.setEnabled(false);
        this.cb_rsv_minibar.setEnabled(false);
        this.cb_rsv_console.setEnabled(false);
        this.cb_rsv_cash_box.setEnabled(false);
        this.cb_rsv_projection.setEnabled(false);
        this.fld_rsv_guest_count.setEnabled(false);

        this.fld_rsv_hotel_name.setEnabled(false);
        this.fld_rsv_hotel_address.setEnabled(false);
        this.fld_rsv_hotel_star.setEnabled(false);
        this.cb_rsv_park.setEnabled(false);
        this.cb_rsv_wifi.setEnabled(false);
        this.cb_rsv_pool.setEnabled(false);
        this.cb_rsv_concierge.setEnabled(false);
        this.cb_rsv_fitness.setEnabled(false);
        this.cb_rsv_room_service.setEnabled(false);
        this.cb_rsv_spa.setEnabled(false);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate entryDate = LocalDate.parse(selectedStart, formatter);
        LocalDate exitDate = LocalDate.parse(selectedFinish, formatter);

        this.fld_rsv_pansion_type.setText(String.valueOf(selectedPansionId));


        int selectedHotelId = 0;                     // Reservation işleminde kullanılacak room id buldum
        for (Room room : roomManager.findAll()) {
            if (room.getId() == selectdRoomId) {
                selectedHotelId = room.getHotelId();

                this.fld_rsv_room_type.setText(room.getRoomType());



                double adultPrice = room.getAdultPrice();
                double childPrice = room.getChildPrice();



                long days = ChronoUnit.DAYS.between(entryDate, exitDate);
                this.totalPrice = (int) (days *  ((adultCnt * adultPrice) + (childCnt * childPrice)));  // Değerlendirme formu 17
                this.fld_rsv_room_total_price.setText(String.valueOf(totalPrice));



                this.fld_rsv_room_bed.setText(String.valueOf(room.getBedCapacity()));
                this.fld_rsv_room_square.setText(String.valueOf(room.getSquareMeter()));
                this.fld_rsv_room_strt_date.setText(selectedStart);
                this.fld_rsv_room_fnsh_date.setText(selectedFinish);
                this.cb_rsv_tv.setSelected(room.isTelevision());
                this.cb_rsv_minibar.setSelected(room.isMinibar());
                this.cb_rsv_console.setSelected(room.isGameConsole());
                this.cb_rsv_cash_box.setSelected(room.isCashBox());
                this.cb_rsv_projection.setSelected(room.isProjection());
                this.fld_rsv_guest_count.setText(String.valueOf(sum));



            }
        }

        int selectedHtelId = 0;
        for (Hotel hotel : hotelManager.findAll()) {
            if (hotel.getName().equals(selectedHotelName)) {
                selectedHtelId = hotel.getId();
                this.fld_rsv_hotel_name.setText(hotel.getName());
                this.fld_rsv_hotel_address.setText(hotel.getAddress());
                this.fld_rsv_hotel_star.setText(hotel.getStar());
                this.cb_rsv_park.setSelected(hotel.isCar_park());
                this.cb_rsv_wifi.setSelected(hotel.isWifi());
                this.cb_rsv_pool.setSelected(hotel.isPool());
                this.cb_rsv_concierge.setSelected(hotel.isConcierge());
                this.cb_rsv_fitness.setSelected(hotel.isFitness());
                this.cb_rsv_room_service.setSelected(hotel.isRoom_service());
                this.cb_rsv_spa.setSelected(hotel.isSpa());

            }
        }

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
                    newReservation.setTotalPrice(totalPrice);
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
