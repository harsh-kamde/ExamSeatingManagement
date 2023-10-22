import javax.swing.*;

public class CustomManagement {
    private JPanel panel1;
    private JTextField branchNameField;
    private JTextField enrollmentStartField;
    private JTextField enrollementEndField;
    private JTextField subjectCodeField;
    private JTextField roomNameField;
    private JTextField rowsInRoomField;
    private JTextField columnsInRoomField;
    private JButton deleteButton1;
    private JLabel selectedRoomsLabel;
    private JButton getSeatingPlanButton;
    private JButton addToSelectedRoomsBtn;
    private JLabel selectedBranchesLabel;
    private JButton addToSelectedBranchesButton;
    private JButton deleteButton;

    CustomManagement(){
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        panel1.setBounds(0,0,435,445);
        frame.setSize(450,480);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel1);
    }


}
