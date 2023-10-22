import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StudentAddPage {

    private JTextField textField1;
    private JButton RESETButton;
    private JButton ADDNEXTSTUDENTButton;
    private JButton FINISHButton;
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel remainStudents;
    private JTextField textField5;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel parentNameLabel;
    private JLabel mobileLabel;
    private JLabel mailLabel;
    private JLabel topHeading;

    int students;

    StudentAddPage(int totalStudents, String departmentName){

        students = totalStudents;
        FINISHButton.setVisible(false);

        JFrame frame = new JFrame("Add Department");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        panel1.setBounds(0,0,600,350);
        frame.setSize(600,380);
        frame.setVisible(true);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);

        remainStudents.setText("Remaining students: "+String.valueOf(students));

        ADDNEXTSTUDENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ID = textField1.getText();
                String name = textField2.getText();
                String parentName = textField3.getText();
                String mobile = textField4.getText();
                String mail = textField5.getText();

                if (dataSubmit(ID, name, parentName, mobile, mail, departmentName)) {
                    remainStudents.setText("Remaining students: " + String.valueOf(--students));
                    JOptionPane.showMessageDialog(null, "Student added successully!");

                    //remaining student value be change here

                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");

                    if (students <= 0) {

                        idLabel.setVisible(false);
                        topHeading.setVisible(false);
                        mailLabel.setVisible(false);
                        mobileLabel.setVisible(false);
                        nameLabel.setVisible(false);
                        parentNameLabel.setVisible(false);
                        ADDNEXTSTUDENTButton.setVisible(false);
                        textField1.setVisible(false);
                        RESETButton.setVisible(false);
                        ADDNEXTSTUDENTButton.setVisible(false);
                        textField2.setVisible(false);
                        textField3.setVisible(false);
                        textField4.setVisible(false);
                        remainStudents.setVisible(false);
                        textField5.setVisible(false);

                        FINISHButton.setVisible(true);
                    }
                }
            }
        });



        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");

            }
        });

        FINISHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Department added successfully! ");
                frame.setVisible(false);
                new MainPage();

            }
        });

    }

    private boolean dataSubmit(String id, String name, String parentName, String mobile, String mail,String departmentName){
        boolean isSubmit = false;

        try{
            String url = "jdbc:mysql://localhost:3306/ExamSeatingManagement";
            String user = "root";
            String dbpass = "root";

            Connection con = DriverManager.getConnection(url,user,dbpass);
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Insert data
            String query = "insert into ExamSeatingManagement."+departmentName+" values('"+id+"','"+name+"','"+parentName+"','"+mobile+"','"+mail+"');";
            Statement st = con.createStatement();
            int i=st.executeUpdate(query);
            if(i>0){
                System.out.println("Student added");
                isSubmit = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Something went wrong !");
            }



        }
        catch (Exception exception){
            System.out.println("Error occured: "+exception);
            System.out.println("Problem is here in studentAddPage");
        }

        return isSubmit;
    }
}
