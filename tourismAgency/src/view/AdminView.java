package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {

    private JPanel w_top;
    private JPanel w_bot;
    private JButton btn_admin_exit;
    private JTable tbl_admin;
    private JTextField fld_admin_username;
    private JTextField fld_admin_pass;
    private JComboBox cmb_admin_type;
    private JButton btn_admin_save;
    private JComboBox cmb_admin_type_search;
    private JButton btn_admin_search;
    private JLabel lbl_admin_welcome;


    private JPanel container;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private Object[] col_user;
    private JPopupMenu user_menu;
    private User user;
    private UserManager userManager;


    public AdminView(User user){
        this.userManager = new UserManager();
        this.user = user;
        add(container);
        guiInitilaze(1000,700);

        lbl_admin_welcome.setText("Hoş geldin : "+user.getUsername());

        loadUserTable(null);
        loadUserComponent();


    }

    public void loadUserTable(ArrayList<Object[]> userList){

        this.col_user = new Object[]{"ID", "Username", "Password", "Role"};
        if (userList == null){
            userList = this.userManager.getForTable(this.col_user.length,this.userManager.findAll());
        }
        createTable(this.tmdl_user,this.tbl_admin,this.col_user,userList);
    }
    public void loadUserComponent(){

        tableRowSelect(this.tbl_admin); // tabloya tıklandığında seçilmesini sağlar
        btn_admin_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] fieldList = {fld_admin_username,fld_admin_pass};
                if (Helper.isFieldListEmpty(fieldList)){
                    Helper.showMsg("fill");
                } else {
                    boolean result;
                    User newUser = new User();
                    newUser.setUsername(fld_admin_username.getText());
                    newUser.setPassword(fld_admin_pass.getText());
                    newUser.setUserType((String) cmb_admin_type.getSelectedItem());

                    result = userManager.save(newUser);
                    if (result){
                        Helper.showMsg("done");
                    } else {
                        Helper.showMsg("error");
                    }
                }
                loadUserTable(null); // yeni user kaydettikten sonra tablonun güncellenmesi için
            }
        });

        user_menu = new JPopupMenu();                       // Sağ tık - sil
        this.user_menu.add("Sil").addActionListener(e-> {
            if (Helper.confirm("sure")){
                int selectUserId = this.getTableSelectedRow(tbl_admin,0);
                if (this.userManager.delete(selectUserId)){
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }

            /// UPDATE


        });
        this.user_menu.add("Güncelle").addActionListener(e ->{      // Değerlendirme formu 7
            int selectUserId = this.getTableSelectedRow(tbl_admin,0);
            NewUserView newUserView = new NewUserView(this.userManager.getById(selectUserId));
            newUserView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });


        this.tbl_admin.setComponentPopupMenu(this.user_menu);

        btn_admin_search.addActionListener(new ActionListener() {           // Arama yapılır
            @Override
            public void actionPerformed(ActionEvent e) {
                String userSearchRole = (String) cmb_admin_type_search.getSelectedItem();
                ArrayList<User> searchedUserList = userManager.findByRole(userSearchRole);                              // Verilen role göre userler çekilir
                ArrayList<Object[]> searchedRowUserList = userManager.getForTable(col_user.length,searchedUserList);    // DefaultTable a setlenebilecek şekilde dönüşüm yapılır
                //createTable(tmdl_user,tbl_admin,col_user,searchedRowUserList);                                          // Tablo oluşturulur
                loadUserTable(searchedRowUserList);


            }
        });


    }

}
