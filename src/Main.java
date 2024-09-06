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
            //Statement statement = connection.createStatement();
           // String query = "INSERT INTO students(name, age, marks) VALUES(?,?,?)";
            String query =  "SELECT marks FROM students WHERE id = ?";

             // String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)","Rhaul", 23, 74.5);
           // String query = String.format("UPDATE students SET marks = %f WHERE id = %d", 89.7,2);
          //  String query = "DELETE FROM students WHERE ID = 2";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, "Ankita");
//            preparedStatement.setInt(2,25);
//            preparedStatement.setDouble(3,84.7);
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double marks = resultSet.getDouble("marks");
                System.out.println("Marsks: " + marks);
            }else{
                System.out.println("Marks Not found: ");
            }

            // String query = "select * from students";
            //this is used for retrival data but if check for update; and hold the data by ResultSet
//            ResultSet resultSet = statement.executeQuery(query);
           // int rowsAffected  = statement.executeUpdate(query);

//            int rowsAffected = preparedStatement.executeUpdate();
//            if(rowsAffected > 0){
//                System.out.println("Data Inserted Successfully");
//            }else{
//                System.out.println(" Data Not Inserted");
//            }

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
            System.out.println(e.getMessage());
        }
    }
}
