package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUserView extends Layout{
    private JTextField fld_new_username;
    private JTextField fld_new_password;
    private JComboBox cmb_new_type;
    private JButton btn_new_save;
    private JPanel container;


    private User user;
    private UserManager userManager;

    public NewUserView(User user){
        this.user = user;
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(300,600);


        this.btn_new_save.addActionListener(e -> {


            boolean result;

            this.user.setUsername(fld_new_username.getText());
            this.user.setPassword(fld_new_password.getText());
            this.user.setUserType((String) cmb_new_type.getSelectedItem());

            result = this.userManager.update(this.user);

            if (result){
                Helper.showMsg("done");
                dispose();
            } else {
                Helper.showMsg("error");
            }

        });
    }


}
