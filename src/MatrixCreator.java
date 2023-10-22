//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//// Use this class to add demo RollNumbers to database
//
//public class MatrixCreator {
//
//    public static void main(String[] args) {
//        for (int i = 10; i <=60 ; i++) {
//            MatrixCreator m = new MatrixCreator();
//            String Id = "0536CS2110"+i;
//            m.addData(Id);
//        }
//    }
//    void addData(String enrollmentID){
//        try {
//            String url = "jdbc:mysql://localhost:3306/ExamSeatingManagement";
//            String user = "root";
//            String dbpass = "root";
//
//            Connection con = DriverManager.getConnection(url, user, dbpass);
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            //Insert data
//            String query = "insert into ExamSeatingManagement.CSE_E values('" + enrollmentID+"','A','B',0123456789,'hello@mail.com');";
//            Statement st = con.createStatement();
//            int i = st.executeUpdate(query);
//
//        }
//        catch (Exception exception){
//            System.out.println("Eror: "+exception);
//        }
//    }
//}