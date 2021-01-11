package CBTSchoolSystem;

import CBTSchoolSystem.Data.SoalDataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SoalView extends JFrame{
    private JLabel soalLabel;
    private JTextArea listSoalTextArea;
    private JLabel noSoalLabel;
    private JLabel jawabanLabel;
    private JRadioButton jawaban1Button;
    private JRadioButton jawaban2Button;
    private JRadioButton jawaban3Button;
    private JButton previousButton;
    private JButton nextButton;
    private JPanel SoalViewJPanel;
    //
    Controller controller;
    Comunicator comunicator;

    private List<SoalDataModel> dataSoal;
    private int soalBenar = 0;

    public SoalView(String title, Controller controller, Comunicator comunicator){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(SoalViewJPanel);
        this.setPreferredSize(new Dimension(650,600));
        this.pack();

        this.comunicator = comunicator;
        this.controller = controller;

        ButtonGroup button = new ButtonGroup();
        button.add(jawaban1Button);
        button.add(jawaban2Button);
        button.add(jawaban3Button);

        previousButton.setVisible(false);

        listSoalTextArea.setEditable(false);

        try{
            previousButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String checkJawabanDatabase = dataSoal.get(comunicator.getIndexSoal()).getJawabanBenar();

                    if(jawaban1Button.isSelected()){
                        if(checkJawabanDatabase.equals(jawaban1Button.getText())){
                            soalBenar++;
                        }

                    }else if(jawaban2Button.isSelected()){
                        if(checkJawabanDatabase.equals(jawaban2Button.getText())){
                            soalBenar++;
                        }
                    }else if(jawaban3Button.isSelected()){
                        if(checkJawabanDatabase.equals(jawaban3Button.getText())){
                            soalBenar++;
                        }
                    }

                    //ROUTE TO NEXT SOAL
                    if(comunicator.getIndexSoal() != 0){
                        comunicator.setIndexSoal(comunicator.getIndexSoal() - 1);
                        renderSoal(comunicator.getIndexSoal());
                    }

                }
            });

            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(jawaban1Button.isSelected()||jawaban2Button.isSelected()||jawaban3Button.isSelected()){
                        String checkJawabanDatabase = dataSoal.get(comunicator.getIndexSoal()).getJawabanBenar();

                        if(jawaban1Button.isSelected()){
                            if(checkJawabanDatabase.equals(jawaban1Button.getText())){
                                soalBenar++;
                            }

                        }else if(jawaban2Button.isSelected()){
                            if(checkJawabanDatabase.equals(jawaban2Button.getText())){
                                soalBenar++;
                            }
                        }else if(jawaban3Button.isSelected()){
                            if(checkJawabanDatabase.equals(jawaban3Button.getText())){
                                soalBenar++;
                            }
                        }

                        //CHANGE BUTTON SUBMIT WHEN SOAL NEAR END
                        if(comunicator.getIndexSoal()== controller.getSoalList().size()-2){
                            nextButton.setText("Submit");
                        }

                        //ROUTE TO NEXT SOAL
                        if(comunicator.getIndexSoal() != controller.getSoalList().size()-1){
                            comunicator.setIndexSoal(comunicator.getIndexSoal()+1);
                            nextButton.setText("Selanjutnya");
                            renderSoal(comunicator.getIndexSoal());
                            button.clearSelection();
                        }

                        //IF all soal already done
                        else{
                            Double nilaiPerSoal = 100 / Double.valueOf(controller.getSoalList().size());
                            comunicator.setScore(soalBenar*nilaiPerSoal);
                            System.out.println(comunicator.getScore());
                            //go to score layout
                            comunicator.closeSoalView();
                            comunicator.openHasilCBT();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Pilih jawabanmu duluu!!");
                    }
                }
            });

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void renderSoal(int index){
        System.out.println("Render Soal");
        noSoalLabel.setText((index + 1) + " - " + controller.getSoalList().size());
        dataSoal = new ArrayList<>(controller.getSoalList());
        Collections.shuffle(dataSoal);

        //Hide previous button
        if(comunicator.getIndexSoal() == 0){
            previousButton.setVisible(false);
        }

        if(comunicator.getIndexSoal() != 0){
            previousButton.setVisible(true);
        }

        //Get fresh soal
        SoalDataModel newDataSoal = dataSoal.get(index);
        System.out.println(index + " " + newDataSoal.getJawabanBenar());

        //Get another copy from database
        String[] multipleAnswer = Arrays.copyOf(newDataSoal.getJawaban(), newDataSoal.getJawaban().length);
        List<String> listAnswer = Arrays.asList(multipleAnswer);
        Collections.shuffle(listAnswer);

        //Render data
        listSoalTextArea.setText(newDataSoal.getSoal());
        jawaban1Button.setText(listAnswer.get(0));
        jawaban2Button.setText(listAnswer.get(1));
        jawaban3Button.setText(listAnswer.get(2));
    }
}
