import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton RESETButton;
    private JButton LOGINButton;

    LoginPage(){
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        panel1.setBounds(0,0,600,350);
        frame.setSize(600,380);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel1);

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                System.out.println(textField1.getText());
//                System.out.println(String.valueOf(passwordField1.getPassword()));

                if(validateAdmin(textField1.getText(), String.valueOf(passwordField1.getPassword()))){
                    new MainPage();
                    frame.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid User ID or password !");
                }

            }
        });

        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                passwordField1.setText("");
            }
        });

    }

    private boolean validateAdmin(String ID, String pass){
        boolean isValid = false;
        try {
            String url = "jdbc:mysql://localhost:3306/ExamSeatingManagement";
            String user = "root";
            String dbpass = "root";

            Connection con = DriverManager.getConnection(url,user,dbpass);
            Class.forName("com.mysql.cj.jdbc.Driver");

                    //for reading data
                    String query = "select * from ExamSeatingManagement.admins;";

                    Statement st = con.createStatement();

                    ResultSet rs = st.executeQuery(query);



//            System.out.println("Sent data: "+ID);
//            System.out.println("Sent data: "+pass);
//
//            System.out.println("Data : "+rs.getString("Admin_ID"));
//            System.out.println("Data : "+rs.getString("Password"));


            while (rs.next()) {
                if ((ID.equals(rs.getString("Admin_ID"))) && (pass.equals(rs.getString("Password")))) {
                    isValid = true;
//                    System.out.println("Everything okay!");
                }
            }
        }
        catch (Exception exception){
            System.out.println("Error occured: "+exception);
        }

//        System.out.println(isValid);

        return isValid;
    }

}
