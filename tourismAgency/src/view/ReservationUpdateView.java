package view;

import business.ReservationManager;
import core.Helper;
import entity.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationUpdateView extends Layout{
    private JPanel container;
    private JTextField fld_reservation_up_name;
    private JTextField fld_reservation_up_guest_id;
    private JTextField fld_reservation_up_mail;
    private JTextField fld_reservation_up_phone;
    private JButton btn_reservation_up_update;

    private Reservation reservation;
    private ReservationManager reservationManager;

    public ReservationUpdateView(Reservation reservation){

        this.reservation=reservation;
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitilaze(300,600);


            btn_reservation_up_update.addActionListener(e -> {
                boolean result;

                this.reservation.setGuestName(fld_reservation_up_name.getText());
                this.reservation.setGuestCId(fld_reservation_up_guest_id.getText());
                this.reservation.setGuestMail(fld_reservation_up_mail.getText());
                this.reservation.setGuestPhone(fld_reservation_up_phone.getText());

                result = this.reservationManager.update(this.reservation);

                if (result){
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            });



    }

}
