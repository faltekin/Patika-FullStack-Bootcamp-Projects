package view;

import business.PansionManager;
import business.SeasonManager;
import core.Helper;
import entity.Pansion;
import entity.Season;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonAddView extends Layout{
    private JPanel container;
    private JLabel lbl_season_add_id;
    private JFormattedTextField fld_season_strtdate;
    private JFormattedTextField fld_season_fnsh_date;
    private JButton btn_season_save;

    private Season season;
    private SeasonManager seasonManager;
    private int selectHotelId;

    public SeasonAddView(Season season,int selectHotelId){
        this.selectHotelId = selectHotelId;
        this.season = season;
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.guiInitilaze(500,500);

        lbl_season_add_id.setText("Otel id : "+selectHotelId);

        btn_season_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] fieldList = {fld_season_strtdate,fld_season_fnsh_date};
                if (Helper.isFieldListEmpty(fieldList)){
                    Helper.showMsg("fill");
                } else {
                    boolean result;
                    Season newSeason = new Season();
                    newSeason.setHotelId(selectHotelId);
                    newSeason.setStartDate(LocalDate.parse(fld_season_strtdate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    newSeason.setFinishDate(LocalDate.parse(fld_season_fnsh_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));


                    result = seasonManager.save(newSeason);
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
