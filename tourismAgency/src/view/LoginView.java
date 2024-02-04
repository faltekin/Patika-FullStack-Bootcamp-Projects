package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout{
    private JPanel container;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_login_username;
    private JTextField fld_login_pass;
    private JButton btn_login;
    private UserManager userManager;

    public LoginView(){
        this.userManager = new UserManager();
        add(container);
        guiInitilaze(300,400);

        loginButton();
    }

    public void loginButton(){
        btn_login.addActionListener(e -> {
            JTextField[] fieldList = {fld_login_username,fld_login_pass};
            if (Helper.isFieldListEmpty(fieldList)){        // Değerlendirme formu 9
                Helper.showMsg("fill");
            } else {
                User loginUser = userManager.findByLogin(fld_login_username.getText(),fld_login_pass.getText());
                if (loginUser == null){
                    Helper.showMsg("notFound");
                } else if (loginUser.getUserType().equals("ADMIN")){
                    AdminView adminView = new AdminView(loginUser);
                    dispose();
                } else if (loginUser.getUserType().equals("EMPLOYEE")) {
                    EmployeeView employeeView = new EmployeeView(loginUser);
                    dispose();
                }
            }



        });
    }

}
