package CBTSchoolSystem;

import javax.swing.*;
import java.awt.*;

public class HasilCBT extends JFrame {
    private JLabel topLabel;
    private JLabel nilaiLabel;
    private JLabel motivasiLabel;
    private JPanel HasilCBTJPanel;
    private Comunicator comunicator;

    public HasilCBT(String title, Comunicator comunicator){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(HasilCBTJPanel);
        this.setPreferredSize(new Dimension(650,600));
        this.pack();

        this.comunicator = comunicator;
    }

    public void setHasil() {
        //If bad score
        Double score = comunicator.getScore();

        if(score <= 30){
            motivasiLabel.setText("Semangat belajar!!");
            nilaiLabel.setText(Double.toString(score));
        }

        else if(score <= 80){
            motivasiLabel.setText("Tingkatkan lagi , you can do it !!!");
            nilaiLabel.setText(Double.toString(comunicator.getScore()));
        }

        else if(score <= 99){
            motivasiLabel.setText("Bagus pertahankan");
            nilaiLabel.setText(Double.toString(comunicator.getScore()));
        }

        else if(score == 100){
            motivasiLabel.setText("Buruan minta hadiah sama orangtuamu");
            nilaiLabel.setText(Double.toString(comunicator.getScore()));
        }

    }
}
