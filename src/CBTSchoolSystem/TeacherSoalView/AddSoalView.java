package CBTSchoolSystem.TeacherSoalView;

import CBTSchoolSystem.Comunicator;
import CBTSchoolSystem.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSoalView extends JFrame{
    private JLabel addSoalLabel;
    private JTextField soalTextField;
    private JLabel jawaban1Label;
    private JTextField jawaban1TextField;
    private JLabel jawaban2Label;
    private JTextField jawaban2TextField;
    private JLabel jawaban3Label;
    private JTextField jawaban3TextField;
    private JLabel jawabanBenar;
    private JRadioButton jawaban1RadioButton;
    private JRadioButton jawaban2RadioButton;
    private JRadioButton jawaban3RadioButton;
    private JButton saveButton;
    private JPanel AddSoalViewJList;
    private JButton backButton;
    //
    private String answer = "";

    public AddSoalView(String title, Controller controller, Comunicator comunicator){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(AddSoalViewJList);
        this.setPreferredSize(new Dimension(650,600));
        this.pack();

        ButtonGroup answerGroup = new ButtonGroup();
        answerGroup.add(jawaban1RadioButton);
        answerGroup.add(jawaban2RadioButton);
        answerGroup.add(jawaban3RadioButton);

        saveButton.addActionListener(new ActionListener() {
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

                if(controller.addSoal(dataSoal, dataJawaban, answer)){
                    soalTextField.setText("");
                    jawaban1TextField.setText("");
                    jawaban2TextField.setText("");
                    jawaban3TextField.setText("");
                    answerGroup.clearSelection();

                    comunicator.openSoalListTeacherView();
                    comunicator.closeAddSoalView();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Input soal error!");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.openSoalListTeacherView();
                comunicator.closeAddSoalView();
            }
        });
    }
}
