import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage {
    private JButton ADDDEPARTMENTButton;
    private JButton MANAGEEXAMINATIONButton;
    private JButton ADDROOMButton;
    private JPanel mainPanel;

    MainPage(){
        JFrame frame = new JFrame("Main");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        mainPanel.setBounds(0,0,600,350);
        frame.setSize(600,380);
        frame.setVisible(true);
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);

        ADDDEPARTMENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepartmentPage();
                frame.setVisible(false);

            }
        });

        ADDROOMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRoom();
                frame.setVisible(false);

            }
        });

        MANAGEEXAMINATIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageExaminationPage();
                frame.setVisible(false);

            }
        });

    }

}
