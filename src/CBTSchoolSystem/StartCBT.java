package CBTSchoolSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartCBT extends JFrame{
    private JButton startButton;
    private JPanel StartCBTJList;

    public StartCBT(String title, Comunicator comunicator){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(StartCBTJList);
        this.setPreferredSize(new Dimension(650,600));
        this.pack();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comunicator.closeStartCBT();
                comunicator.openSoalView();
            }
        });
    }
}
