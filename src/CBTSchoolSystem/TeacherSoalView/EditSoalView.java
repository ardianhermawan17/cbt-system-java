package CBTSchoolSystem.TeacherSoalView;

import CBTSchoolSystem.Comunicator;
import CBTSchoolSystem.Controller;
import CBTSchoolSystem.Data.SoalDataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditSoalView extends JFrame{
    private JLabel editSoalLabel;
    private JTextField soalTextField;
    private JLabel soalLabel;
    private JTextField jawaban1TextField;
    private JTextField jawaban2TextField;
    private JTextField jawaban3TextField;
    private JLabel jawaban1Label;
    private JLabel jawaban2Label;
    private JLabel jawaban3Label;
    private JLabel jawabanBenar;
    private JRadioButton jawaban1RadioButton;
    private JRadioButton jawaban2RadioButton;
    private JRadioButton jawaban3RadioButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel EditSoalViewJPanel;
    private JButton backButton;
    //
    private Comunicator comunicator;
    private Controller controller;
    private String answer = "";

    public EditSoalView(String title, Controller controller, Comunicator comunicator){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(EditSoalViewJPanel);
        this.setPreferredSize(new Dimension(650,600));
        this.pack();

        this.comunicator = comunicator;
        this.controller = controller;

        ButtonGroup answerGroup = new ButtonGroup();
        answerGroup.add(jawaban1RadioButton);
        answerGroup.add(jawaban2RadioButton);
        answerGroup.add(jawaban3RadioButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.openSoalListTeacherView();
                comunicator.closeEditSoalView();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jawaban1RadioButton.isSelected()){
                    answer = jawaban1TextField.getText();
                }else if(jawaban2RadioButton.isSelected()){
                    answer = jawaban2TextField.getText();
                }else if(jawaban3RadioButton.isSelected()){
                    answer = jawaban3TextField.getText();
                }

                String dataSoal = soalTextField.getText();
                String[] dataJawaban = new String[]{
                        jawaban1TextField.getText(),
                        jawaban2TextField.getText(),
                        jawaban3TextField.getText()
                };

                if(controller.updateSoal(comunicator.getIndex() ,dataSoal, dataJawaban, answer)){
                    soalTextField.setText("");
                    jawaban1TextField.setText("");
                    jawaban2TextField.setText("");
                    jawaban3TextField.setText("");
                    answerGroup.clearSelection();
                    comunicator.openSoalListTeacherView();
                    comunicator.closeEditSoalView();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Update soal error!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.deleteSoal(comunicator.getIndex())){
                    comunicator.closeEditSoalView();
                    comunicator.openSoalListTeacherView();
                    JOptionPane.showMessageDialog(null,"Delete soal success!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Delete soal error!");
                }
            }
        });
    }

    public void renderSoal(){
        SoalDataModel soal = controller.getSoalList().get(comunicator.getIndex());
        System.out.println("soal" + soal);
        soalTextField.setText(soal.getSoal());
        jawaban1TextField.setText(soal.getJawaban()[0]);
        jawaban2TextField.setText(soal.getJawaban()[1]);
        jawaban3TextField.setText(soal.getJawaban()[2]);

        String rightAnswer = soal.getJawabanBenar();

        if(rightAnswer.equals(soal.getJawaban()[0])){
            jawaban1RadioButton.setSelected(true);
        }
        else if(rightAnswer.equals(soal.getJawaban()[1])){
            jawaban2RadioButton.setSelected(true);
        }
        else if(rightAnswer.equals(soal.getJawaban()[2])){
            jawaban3RadioButton.setSelected(true);
        }
    }
}
