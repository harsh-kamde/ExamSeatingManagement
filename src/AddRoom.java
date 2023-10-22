import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddRoom {
    private JRadioButton aRadioButton;
    private JRadioButton bRadioButton;
    private JRadioButton groundRadioButton;
    private JRadioButton firstRadioButton;
    private JRadioButton secondRadioButton;
    private JRadioButton thirdRadioButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JButton RESETButton;
    private JButton SUBMITButton;
    private JPanel panel1;

    private String block;
    private String floor;
    private String roomName;
    private int rowsInRoom;
    private int seatsInRoom;

    AddRoom(){
        JFrame frame = new JFrame("Add Room");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        panel1.setBounds(0,0,600,350);
        frame.setSize(600,380);
        frame.setVisible(true);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);



        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 block=" ";
                 floor=" ";
                 roomName = textField1.getText();
                 rowsInRoom = Integer.parseInt((String) comboBox1.getSelectedItem());
                 seatsInRoom = Integer.parseInt(textField2.getText());

                // selecting text for block
                if(aRadioButton.isSelected()){
                    block = aRadioButton.getText();
                }
                if(bRadioButton.isSelected()){
                    block = bRadioButton.getText();
                }

               // selecting text for floor
                if(groundRadioButton.isSelected()){
                    floor = groundRadioButton.getText();
                }
                if(firstRadioButton.isSelected()){
                    floor = firstRadioButton.getText();
                }
                if(secondRadioButton.isSelected()){
                    floor = secondRadioButton.getText();
                }
                if(thirdRadioButton.isSelected()){
                    floor = thirdRadioButton.getText();
                }

                if(dataSubmit(block,floor,roomName,rowsInRoom,seatsInRoom)) {


                    textField1.setText("");
                    textField2.setText("");
                    aRadioButton.setSelected(false);
                    bRadioButton.setSelected(false);
                    groundRadioButton.setSelected(false);
                    firstRadioButton.setSelected(false);
                    secondRadioButton.setSelected(false);
                    thirdRadioButton.setSelected(false);
                    comboBox1.setSelectedIndex(0);
                }


            }
        });

        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               textField1.setText("");
               textField2.setText("");
               aRadioButton.setSelected(false);
               bRadioButton.setSelected(false);
               groundRadioButton.setSelected(false);
               firstRadioButton.setSelected(false);
               secondRadioButton.setSelected(false);
               thirdRadioButton.setSelected(false);
               comboBox1.setSelectedIndex(0);

            }
        });

    }

    private boolean dataSubmit(String block, String floor, String RoomName, int rows, int seats){
        boolean isSubmit = false;

        try{
            String url = "jdbc:mysql://localhost:3306/ExamSeatingManagement";
            String user = "root";
            String dbpass = "root";

            Connection con = DriverManager.getConnection(url,user,dbpass);
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Insert data
            String query = "insert into ExamSeatingManagement.Rooms values('"+block+"','"+floor+"','"+RoomName+"',"+rows+","+seats+");";
            Statement st = con.createStatement();
            int i = st.executeUpdate(query);
            if(i>0){
                JOptionPane.showMessageDialog(null,"Room added Successfully!");
                isSubmit = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Something went wrong !");
            }



        }
        catch (Exception exception){
            System.out.println("Error occured: "+exception);
        }

        return isSubmit;
    }

}
