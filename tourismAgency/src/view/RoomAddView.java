package view;

import business.HotelManager;
import business.PansionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomAddView extends Layout{

    private JComboBox cmb_room_add_hotel;
    private JComboBox cmb_room_add_pansion;
    private JComboBox cmb_room_add_season;
    private JComboBox cmb_room_add_room_type;
    private JTextField fld_room_add_stock;
    private JTextField fld_room_add_adult;
    private JTextField fld_room_add_child;
    private JTextField fld_room_add_bed_capacity;
    private JTextField fld_room_add_square_meter;
    private JCheckBox cb_room_add_tv;
    private JCheckBox cb_room_add_minibar;
    private JCheckBox cb_room_add_console;
    private JCheckBox cb_room_add_cash_box;
    private JCheckBox cb_room_add_projection;
    private JButton btn_room_add_save;

    private Hotel hotel;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private Room room;
    private PansionManager pansionManager;

    private SeasonManager seasonManager;

    private JPanel container;

    public RoomAddView(Room room){
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.pansionManager = new PansionManager();
        this.seasonManager = new SeasonManager();
        this.room = room;
        add(container);
        this.guiInitilaze(700,700);

        for (Hotel ho : this.hotelManager.findAll()){       // cmb_room_add_hotel otel isimleriyle doldurulur
            //this.cmb_room_add_hotel.addItem(ho.getName());
            this.cmb_room_add_hotel.addItem(ho.getComboItem());
        }

        this.cmb_room_add_hotel.addActionListener(e -> {    // Değerlendirme formu 14 - Oda ekleme
            this.cmb_room_add_pansion.removeAllItems();
            this.cmb_room_add_season.removeAllItems();

            int selectedHotelId = ((ComboItem) this.cmb_room_add_hotel.getSelectedItem()).getKey();

            for (Season season : this.seasonManager.findAll()) {
                if(season.getHotelId() == selectedHotelId){
                    this.cmb_room_add_season.addItem(season.getComboItem());
                }
            }
            for (Pansion pansion : this.pansionManager.findAll()) {
                if(pansion.getHotelId() == selectedHotelId){
                    this.cmb_room_add_pansion.addItem(pansion.getComboItem());
                }
            }
        });

        loadRoomAddComponent(this.pansionManager);

    }


    public void loadRoomAddComponent(PansionManager pm){
        btn_room_add_save.addActionListener(new ActionListener() {      // Değerlendirme formu 13
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField[] fieldList = {fld_room_add_stock,fld_room_add_adult,fld_room_add_child,fld_room_add_bed_capacity,fld_room_add_square_meter};
                if (Helper.isFieldListEmpty(fieldList)){
                    Helper.showMsg("fill");
                } else {



                    ComboItem selectedHotelItem = (ComboItem) cmb_room_add_hotel.getSelectedItem();
                    int selectedHotelId = selectedHotelItem.getKey();

                    boolean result;
                    Room newRoom = new Room();
                    newRoom.setHotelId(selectedHotelId);

                    ComboItem selectenPansion = (ComboItem) cmb_room_add_pansion.getSelectedItem();
                    newRoom.setPansionId(selectenPansion.getKey());
                    System.out.println(selectenPansion.getKey());

                    ComboItem selectenSeason = (ComboItem) cmb_room_add_season.getSelectedItem();
                    newRoom.setSeasonId(selectenSeason.getKey());

                    System.out.println(selectenPansion);

                    newRoom.setRoomType((String) cmb_room_add_room_type.getSelectedItem());
                    newRoom.setStock(Integer.parseInt(fld_room_add_stock.getText()));
                    newRoom.setAdultPrice(Integer.parseInt(fld_room_add_adult.getText()));
                    newRoom.setChildPrice(Integer.parseInt(fld_room_add_child.getText()));
                    newRoom.setBedCapacity(Integer.parseInt(fld_room_add_bed_capacity.getText()));
                    newRoom.setSquareMeter(Integer.parseInt(fld_room_add_square_meter.getText()));
                    newRoom.setTelevision(cb_room_add_tv.isSelected());
                    newRoom.setMinibar(cb_room_add_minibar.isSelected());
                    newRoom.setGameConsole(cb_room_add_console.isSelected());
                    newRoom.setCashBox(cb_room_add_cash_box.isSelected());
                    newRoom.setProjection(cb_room_add_projection.isSelected());

                    result = roomManager.save(newRoom);
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