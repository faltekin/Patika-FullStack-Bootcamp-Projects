package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class HotelAddView extends Layout{
    private JPanel container;
    private JTextField fld_hotel_add_name;
    private JTextField fld_hotel_add_mail;
    private JTextField fld_hotel_add_phone;
    private JTextField fld_hotel_add_address;
    private JComboBox cmb_hotel_add_star;
    private JCheckBox cb_hotel_add_park;
    private JCheckBox cb_hotel_add_fitness;
    private JCheckBox cb_hotel_add_concierge;
    private JCheckBox cb_hotel_add_wifi;
    private JCheckBox cb_hotel_add_pool;
    private JCheckBox cb_hotel_add_spa;
    private JCheckBox cb_hotel_add_room_service;
    private JButton cb_hotel_add_save;


    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelAddView(Hotel hotel){
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitilaze(500,500);


        cb_hotel_add_save.addActionListener(new ActionListener() {      // Değerlendirme formu 10
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField[] fieldList = {fld_hotel_add_name,fld_hotel_add_mail,fld_hotel_add_phone,fld_hotel_add_address};
                if (Helper.isFieldListEmpty(fieldList)){
                    Helper.showMsg("fill");
                } else {
                    boolean result;
                    Hotel newHotel = new Hotel();
                    newHotel.setName(fld_hotel_add_name.getText());
                    newHotel.setMail(fld_hotel_add_mail.getText());
                    newHotel.setPhone(fld_hotel_add_phone.getText());
                    newHotel.setAddress(fld_hotel_add_address.getText());
                    newHotel.setStar((String) cmb_hotel_add_star.getSelectedItem());
                    newHotel.setCar_park(cb_hotel_add_park.isSelected());
                    newHotel.setWifi(cb_hotel_add_wifi.isSelected());
                    newHotel.setPool(cb_hotel_add_pool.isSelected());
                    newHotel.setFitness(cb_hotel_add_fitness.isSelected());
                    newHotel.setConcierge(cb_hotel_add_concierge.isSelected());
                    newHotel.setSpa(cb_hotel_add_spa.isSelected());
                    newHotel.setRoom_service(cb_hotel_add_room_service.isSelected());

                    result = hotelManager.save(newHotel);
                    if (result){
                        Helper.showMsg("done");
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
    }

}
