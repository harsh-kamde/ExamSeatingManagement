import com.mysql.cj.jdbc.exceptions.PacketTooBigException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class ManageExaminationPage {
    private JPanel panel1;
    private JComboBox department1ComboBox;
    private JTextField dep1SubjectCode;
    private JComboBox department2ComboBox;
    private JTextField dep2SubjectCode;
    private JButton getRoomsButton;
    private JTable table1;
    private JButton RESETButton;
    private JButton getSeatingPlanButton;
    private JTextField inputRoomField;
    private JButton ADDButton;
    private JButton REMOVEButton;
    private JLabel selectedRoomsTxt;
    private ArrayList<String> roomsData = new ArrayList<String>();
    private int roomDataCount = 0; //for counting total rooms added in roomData string

    ManageExaminationPage(){
        JFrame frame = new JFrame("Manage Examination");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        panel1.setBounds(0,0,600,380);
        frame.setSize(615,420);
        frame.setBackground(new Color(19,0,90));
        frame.setForeground(new Color(19,0,90));
        frame.setVisible(true);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);

        addItemsDepartmentComboBoxes(); // for adding dynamic department names from database
        selectedRoomsTxt.setText(String.valueOf(roomsData)); //setting arraylist to label

        getRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Here is the room list!");

                try{
                    String url = "jdbc:mysql://localhost:3306/ExamSeatingManagement";
                    String user = "root";
                    String dbpass = "root";

                    Connection con = DriverManager.getConnection(url,user,dbpass);
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //Insert data
                    Statement st = con.createStatement();
                    ResultSet rs= st.executeQuery("select * from rooms ");

                    Vector totalRecord=new Vector();
                    Vector Column_Heading=new Vector();
                    Column_Heading.addElement("Block");
                    Column_Heading.addElement("Floor");
                    Column_Heading.addElement("RoomName");
                    Column_Heading.addElement("Rows");
                    Column_Heading.addElement("totalSeats");

                    while(rs.next()){
                        Vector new_record=new Vector();
                        new_record.addElement(rs.getString("Block"));
                        new_record.addElement(rs.getString("Floor"));
                        new_record.addElement(rs.getString("RoomName"));
                        new_record.addElement(rs.getString("Rows"));
                        new_record.addElement(rs.getString("totalSeats"));
                        totalRecord.addElement(new_record);

                        DefaultTableModel model=new DefaultTableModel(totalRecord,Column_Heading);
                        table1.setModel(model);

                        }
                }
                catch (Exception exception){
                    System.out.println("Error occured: "+exception);
                }
            }
        });

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomsData.add(inputRoomField.getText());
                inputRoomField.setText("");
                selectedRoomsTxt.setText(String.valueOf(roomsData));
            }
        });
        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomsData.remove(roomsData.size()-1);
                selectedRoomsTxt.setText(String.valueOf(roomsData));
            }
        });

        getSeatingPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String txtSubjectCode = dep1SubjectCode.getText();
                String txtSubjectCode2 = dep2SubjectCode.getText();
                //calling seating plan page class
                String department1 = (String) department1ComboBox.getSelectedItem();
                String department2 = (String) department2ComboBox.getSelectedItem();

                department1 = department1.replaceAll("\\s.*","");
                department2 = department2.replaceAll("\\s.*","");

                if (!(txtSubjectCode.equals(txtSubjectCode2))){
                    new SeatingPlanPage(department1,department2,roomsData);
                    frame.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Exam code should be different inorder to arrange !");
                }

            }
        });

    }

    // Methods created from here

    void addItemsDepartmentComboBoxes(){
        try{
            String url = "jdbc:mysql://localhost:3306/ExamSeatingManagement";
            String user = "root";
            String dbpass = "root";

            Connection con = DriverManager.getConnection(url,user,dbpass);
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Insert data
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery("select * from ExamSeatingManagement.department;");

            while (rs.next()){
                department1ComboBox.addItem(rs.getString("Department_Name")+" ("+rs.getInt("totalStudents")+"students)");
                department2ComboBox.addItem(rs.getString("Department_Name")+" ("+rs.getInt("totalStudents")+"students)");
            }

        }
        catch (Exception exception){
            System.out.println("Error at comboBox Adding Custom data"+exception);
            JOptionPane.showMessageDialog(null,"Something went wrong!");
        }
    }

}
