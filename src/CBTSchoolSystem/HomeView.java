package CBTSchoolSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {
    private JLabel HomeLogin;
    private JButton registerButton;
    private JButton loginButton;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JTextField passwordTextField;
    private JTextField usernameTextField;
    private JPanel HomeViewJPanel;
    private JRadioButton studentRadioButton;
    private JRadioButton teacherRadioButton;

    private String role;


    public HomeView(String title, Controller controller, Comunicator comunicator){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(HomeViewJPanel);
        this.setPreferredSize(new Dimension(650,600));
        this.pack();

        ButtonGroup group = new ButtonGroup();
        group.add(studentRadioButton);
        group.add(teacherRadioButton);

        studentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                role = "Student";
            }
        });

        teacherRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                role = "Teacher";
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username =  usernameTextField.getText();
                String password = passwordTextField.getText();
                if(controller.addUser(username,password,role)){
                    JOptionPane.showMessageDialog(null,"User Successfull created");
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentRadioButton.isSelected()){
                    System.out.println(studentRadioButton.getText());
                }
                if(teacherRadioButton.isSelected()){
                    System.out.println(teacherRadioButton.getText());
                }
                String username =  usernameTextField.getText();
                String password = passwordTextField.getText();
                String checkUser = controller.loginUser(username,password);
                if(!checkUser.equals("")){
                    comunicator.closeHomeView();
                    if(checkUser.equals("Teacher")){
                        //teacher
                        comunicator.openSoalListTeacherView();
                    }else{
                        //student
                        comunicator.openStartCBT();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Failed To Login!!");
                }
            }
        });
    }
}
