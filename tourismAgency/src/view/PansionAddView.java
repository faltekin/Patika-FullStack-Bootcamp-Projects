package view;

import business.HotelManager;
import business.PansionManager;
import core.Helper;
import entity.Pansion;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PansionAddView extends Layout {
    private JPanel container;
    private JLabel lbl_pansion_add_id;
    private JComboBox cmb_pansion_add_features;
    private JButton btn_pansion_add_save;


    private Pansion pansion;
    private PansionManager pansionManager;
    private int selectHotelId;

    public PansionAddView(Pansion pansion,int selectHotelId){
        this.selectHotelId = selectHotelId;
        this.pansion = pansion;
        this.pansionManager = new PansionManager();
        this.add(container);
        this.guiInitilaze(500,500);

        lbl_pansion_add_id.setText("Otel id : "+selectHotelId);

        btn_pansion_add_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean result;
                Pansion newPansion = new Pansion();
                newPansion.setHotelId(selectHotelId);
                newPansion.setPansionType((String) cmb_pansion_add_features.getSelectedItem());
                result = pansionManager.save(newPansion);
                if (result){
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }


}
