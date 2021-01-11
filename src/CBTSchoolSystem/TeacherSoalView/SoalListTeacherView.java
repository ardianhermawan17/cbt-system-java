package CBTSchoolSystem.TeacherSoalView;

import CBTSchoolSystem.Comunicator;
import CBTSchoolSystem.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusAdapter;

public class SoalListTeacherView extends JFrame{
    private JLabel soalListTeacherLabel;
    private JList soalJlist;
    private JButton addQuestionButton;
    private JButton logoutButton;
    private JPanel SoalListTeacherViewJList;
    //
    private Controller controller;
    private DefaultListModel<String> listSoal = new DefaultListModel<>();



    public SoalListTeacherView(String title, Controller controller, Comunicator comunicator){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(SoalListTeacherViewJList);
        this.setPreferredSize(new Dimension(650,600));
        this.pack();

        this.controller = controller;

        //Render soal
         for (int i = 0; i < controller.getSoalList().size(); i++){
             listSoal.addElement(
                     (i+1) + " " + controller.getSoalList().get(i).getSoal()
             );
         }

         soalJlist.setModel(listSoal);

        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.closeSoalListTeacherView();
                comunicator.openAddSoalView();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.closeSoalListTeacherView();
                comunicator.openHomeView();
            }
        });

        soalJlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(soalJlist.getSelectedIndex() != -1){
                    comunicator.setIndex(soalJlist.getSelectedIndex());
                    comunicator.updateEditSoalView();
                    comunicator.openEditSoalView();
                    comunicator.closeSoalListTeacherView();
                }
                System.out.println(comunicator.getIndex());
            }
        });
    }

    public void renderSoal(){
        // TODO :: SEDERHANAKNO AE NAK COMPOSISI ne nek sempet
        listSoal.clear();
        for (int i = 0; i < controller.getSoalList().size(); i++){
            listSoal.addElement(
                    (i+1) + " " + controller.getSoalList().get(i).getSoal()
            );
        }
    }
}
