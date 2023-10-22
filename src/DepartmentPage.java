import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DepartmentPage {
    private JTextField textField1;
    private JTextField textField2;
    private JButton RESETButton;
    private JButton SUBMITButton;

    private JPanel panel1;
    private JButton EXCELSHEETButton;

    DepartmentPage(){
        JFrame frame = new JFrame("Add Department");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        panel1.setBounds(0,0,600,350);
        frame.setSize(600,380);
        frame.setVisible(true);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);


        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setText("");
                textField1.setText("");

            }
        });


        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentName = textField1.getText();
                int totalStudents = Integer.valueOf(textField2.getText());

                try{
                    String url = "jdbc:mysql://localhost:3306/ExamSeatingManagement";
                    String user = "root";
                    String dbpass = "root";

                    Connection con = DriverManager.getConnection(url,user,dbpass);
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //Insert data
                    String query = "insert into ExamSeatingManagement.department values('"+departmentName+"',"+totalStudents+");";
                    Statement st = con.createStatement();
                    int i = st.executeUpdate(query);

                    //creating new database for the department
                    String query1="CREATE TABLE ExamSeatingManagement."+departmentName+"(\n" +
                            "  `Enrollment_ID` VARCHAR(30) NOT NULL,\n" +
                            "  `Name` VARCHAR(45) NOT NULL,\n" +
                            "  `ParentName` VARCHAR(45) NOT NULL,\n" +
                            "  `Mobile` VARCHAR(15) NOT NULL,\n" +
                            "  `Mail` VARCHAR(45) NOT NULL,\n" +
                            "  PRIMARY KEY (`Enrollment_ID`));";
                    Statement st2 = con.createStatement();
                    st2.executeUpdate(query1);

                    if(i>0){
                        JOptionPane.showMessageDialog(null,"Department created Successfully!");

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Something went wrong !");
                    }

                    //Calling Student add page class to add students to the department.
                    new StudentAddPage(totalStudents,departmentName);
                    frame.setVisible(false);


                }
                catch (Exception exception){
                    System.out.println("Error occured: "+exception);
                    JOptionPane.showMessageDialog(null,"Department exists or Something went wrong !");
                }

            }
        });



        
    }

}
