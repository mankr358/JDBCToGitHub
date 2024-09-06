import java.sql.*;

import static java.lang.Class.forName;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";

    private static final String username = "root";
    private static final String password = "M1n8shkum1r@#";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
           // String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)","Rhaul", 23, 74.5);
            String query = String.format("UPDATE students SET marks = %f WHERE id = %d", 89.7,2);

            // String query = "select * from students";
            //this is used for retrival data but if check for update; and hold the data by ResultSet
//            ResultSet resultSet = statement.executeQuery(query);
            int reowsAffected  = statement.executeUpdate(query);
            if(reowsAffected > 0){
                System.out.println("Data Updatad Successfully");
            }else{
                System.out.println("Data Not Updated!");
            }
//            while(resultSet.next()){
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                double marks = resultSet.getDouble("marks");
//                System.out.println("ID: " +id );
//                System.out.println("NAME: " + name);
//                System.out.println("AGE: " +age);
//                System.out.println("MARKS" + marks);
//            }


        }catch(SQLException e){

        }
    }
}
