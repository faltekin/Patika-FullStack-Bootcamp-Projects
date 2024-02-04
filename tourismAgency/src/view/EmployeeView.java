package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeView extends Layout{

    private JLabel lbl_emp_welcome;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotelmng;
    private JPanel pnl_roommng;
    private JPanel pnl_seasonmng;
    private JTable tbl_hotelmng;
    private JButton btn_emp_exit;
    private JTable tbl_hotelmng_pansion;
    private JTable tbl_hotelmng_season;
    private JButton btn_hotelmng_add;
    private JTable tbl_roommng;
    private JTextField fld_roommng_hotel_name;
    private JTextField fld_roommng_address;
    private JTextField fld_roommng_adult;
    private JTextField fld_roommng_child;
    private JButton btn_roommng_search;
    private JButton btn_roommng_add;
    private JButton btn_roommng_reset;
    private JTable tbl_rsvmng;


    private JPanel container;
    private JFormattedTextField fld_roommng_strt_date;
    private JFormattedTextField fld_roommng_fnsh_date;
    private JPopupMenu room_menu;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_pansion = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();


    private Object[] col_hotel;
    private Object[] col_pansion;
    private Object[] col_season;
    private Object[] col_room;
    private Object[] col_resarvation;
    private JPopupMenu hotel_menu;
    private Hotel hotel;
    private HotelManager hotelManager;
    private PansionManager pansionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;


    public EmployeeView(User user){
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
        this.pansionManager = new PansionManager();
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.add(container);
        this.guiInitilaze(1000,500);


        loadHotelTable(null);   // filtreleme işlemi yapıyorsak - null kullanırız
        loadHotelComponent();

        loadRoomTable(null);
        loadRoomComponent();

        loadReservationTable(null);

    }


    public void loadHotelTable(ArrayList<Object[]> hotelList){

        this.col_hotel = new Object[]{"ID", "Name", "Address", "Mail", "Phone", "Star", "CarPark", "Wifi", "Pool", "Fitness", "Concierge", "Spa", "RoomService"};
        if (hotelList == null){
            hotelList = this.hotelManager.getForTable(this.col_hotel.length,this.hotelManager.findAll());
        }
        createTable(this.tmdl_hotel,this.tbl_hotelmng,this.col_hotel,hotelList);
        //tmdl_hotel.setColumnIdentifiers(col_hotel);
    }
    private void loadHotelComponent() {
        tableRowSelect(this.tbl_hotelmng);                                      // Dinleyeceğimiz tablo seçilir

        this.hotel_menu = new JPopupMenu();
        btn_hotelmng_add.addActionListener(new ActionListener() {                // Değerlendirme formu 8 - Otel ekleme
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelAddView hotelAddView = new HotelAddView(new Hotel());      // AdminWiew gelir

                hotelAddView.addWindowListener(new WindowAdapter() {            // Otel eklendikten sonra AdminWiew kapanır ve loadHotelTable güncellenir
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable(null);
                    }
                });
            }
        });

        hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Sil").addActionListener(e-> {      // Değerlendirme formu 8
            if (Helper.confirm("sure")){
                int selectUserId = this.getTableSelectedRow(tbl_hotelmng,0); // Tabloda üzerine tıklanan yerin id si alınır
                if (this.hotelManager.delete(selectUserId)){
                    Helper.showMsg("done");
                    loadHotelTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }

            /// UPDATE

        });
        this.hotel_menu.add("Pansiyon Tipi Ekle").addActionListener(e-> {       // Değerlendirme formu 12
            int selectHotelId = this.getTableSelectedRow(tbl_hotelmng,0);
            PansionAddView pansionAddView = new PansionAddView(new Pansion(),selectHotelId);
            pansionAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPansionTable(null);
                }
            });


            /// UPDATE

        });
        this.hotel_menu.add("Sezon Ekle").addActionListener(e-> {               // Değerlendirme formu 11
            int selectHotelId = this.getTableSelectedRow(tbl_hotelmng,0);
            SeasonAddView seasonAddView = new SeasonAddView(new Season(),selectHotelId);
            seasonAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null);
                }
            });


            /// UPDATE

        });
        this.tbl_hotelmng.setComponentPopupMenu(this.hotel_menu);

        tbl_hotelmng.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

            }
        });

    }

    public void loadPansionTable(ArrayList<Object[]> pansionList){

        this.col_pansion = new Object[]{"ID", "Hotel ID", "Pansion Type"};
        if (pansionList == null){
            pansionList = this.pansionManager.getForTable(this.col_pansion.length,this.pansionManager.findAll());
        }
        createTable(this.tmdl_pansion,this.tbl_hotelmng_pansion,this.col_pansion,pansionList);
    }

    // TO-Do
    public void loadSeasonTable(ArrayList<Object[]> seasonList){

        int selectHotelId = this.getTableSelectedRow(tbl_hotelmng,0);
        this.col_season = new Object[]{"ID", "Hotel ID", "Start Date", "Finish Date"};
        if (seasonList == null){
            seasonList = this.seasonManager.getForTable(this.col_season.length,this.seasonManager.findSeasonListByHotelId(selectHotelId));
        }
        createTable(this.tmdl_season,this.tbl_hotelmng_season,this.col_season,seasonList);
        tableRowSelect(this.tbl_hotelmng);


    }

    private void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"ID", "Hotel ID", "Pansion", "Room Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "m2", "Tv", "Minibar", "Game Console", "Cash Box", "Projection"};
        createTable(this.tmdl_room, this.tbl_roommng, col_room, roomList);
    }
    public void loadRoomComponent(){

        tableRowSelect(this.tbl_roommng);
        btn_roommng_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomAddView roomAddView = new RoomAddView(new Room());
            }
        });

        btn_roommng_search.addActionListener(new ActionListener() {     // Değerlendirme formu 15 - 16
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Room> roomList = roomManager.searchForRoomList(
                        fld_roommng_hotel_name.getText(),
                        fld_roommng_address.getText(),
                        fld_roommng_strt_date.getText(),
                        fld_roommng_fnsh_date.getText()
                );
                col_room = new Object[]{"ID", "Hotel ID", "Pansion", "Room Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "m2", "Tv", "Minibar", "Game Console", "Cash Box", "Projection"};
                ArrayList<Object[]> roomRow = roomManager.getForTable(col_room.length,roomList);
                loadRoomTable(roomRow);
            }
        });

        tableRowSelect(this.tbl_roommng);
        room_menu = new JPopupMenu();
        this.room_menu.add("Rezerve").addActionListener(e-> {   // Değerlendirme formu 18
            JTextField[] fieldList = {fld_roommng_hotel_name,fld_roommng_address,fld_roommng_strt_date,fld_roommng_fnsh_date,fld_roommng_adult,fld_roommng_child};
            if (Helper.isFieldListEmpty(fieldList)){
                Helper.showMsg("fill");
            } else {

                int selectedRowIndex = tbl_roommng.getSelectedRow();
                //String selectedHotelIndex = (String) tbl_roommng.getValueAt(selectedRowIndex, 0);
                //String selectedIndex = (String) tbl_roommng.getValueAt(selectedRowIndex, 1);

                int selectedHotelId = (int) tbl_roommng.getValueAt(selectedRowIndex, 0);
                int selectedPansionId = (int) tbl_roommng.getValueAt(selectedRowIndex, 1);
                int selectedSeasonId = (int) tbl_roommng.getValueAt(selectedRowIndex, 2);
                String selectedRoomType = (String) tbl_roommng.getValueAt(selectedRowIndex, 3);
                int selectedStock = (int) tbl_roommng.getValueAt(selectedRowIndex, 4);
                double selectedAdultPrice = (double) tbl_roommng.getValueAt(selectedRowIndex, 5);
                double selectedChildPrice = (double) tbl_roommng.getValueAt(selectedRowIndex, 6);
                int selectedBedCapacity = (int) tbl_roommng.getValueAt(selectedRowIndex, 7);
                int selectedM2 = (int) tbl_roommng.getValueAt(selectedRowIndex, 8);


                String selectedHotelName = fld_roommng_hotel_name.getText();
                String selectedAdress = fld_roommng_address.getText();


                int selectedRoomId = 0;                     // Reservation işleminde kullanılacak room id buldum
                for (Room room : roomManager.findAll()) {
                    if (room.getHotelId() == selectedHotelId && room.getPansionId() == selectedPansionId && room.getSeasonId() == selectedSeasonId) {
                        selectedRoomId = room.getId();
                        break;
                    }
                }
                System.out.println(selectedRoomId);

                int selectdRoomId = selectedRoomId;
                String selectedStart = fld_roommng_strt_date.getText();
                String selectedFinish = fld_roommng_fnsh_date.getText();

                double selectedPrice = selectedAdultPrice+selectedChildPrice;   // Değerlendirme formu 14 - 17

                ReservationAddView reservationAddView = new ReservationAddView(new Reservation(),selectdRoomId,selectedStart,selectedFinish,selectedPrice);
                reservationAddView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });
            }


        });
        this.tbl_roommng.setComponentPopupMenu(this.room_menu);

    }

    public void loadReservationTable(ArrayList<Object[]> hotelList){
        this.col_resarvation = new Object[]{"ID", "Room ID", "Chek In", "Check Out", "Total Price", "Guest Count", "Guest Name", "Guest Cıtızen ID", "Guest Mail", "Guest Phone"};
        createTable(this.tmdl_reservation, this.tbl_rsvmng, col_resarvation, hotelList);

    }


}
