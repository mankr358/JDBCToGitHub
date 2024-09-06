import java.sql.*;
import java.util.Scanner;

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
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.print("Enter The Name: ");
                String name = sc.next();
                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                System.out.print("Enter Marks: ");
                double marks = sc.nextDouble();
                System.out.print("Enter more Data(Y/N): ");
                String choice = sc.next();
               // String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %d,%f)",name,age,marks);
                String query = "INSERT INTO students(name, age, marks) VALUES(?,?,?)";
                statement.addBatch(query);
                if(choice.toUpperCase().equals("N")){
                    break;
                }
            }
            int[] arr = statement.executeBatch();
for(int i = 0; i< arr.length; i++){
    if(arr[i] ==0){
        System.out.println("Query; "+i+" not executed Successfully");
    }
}
            //Statement statement = connection.createStatement();
            //String query = "INSERT INTO students(name, age, marks) VALUES(?,?,?)";
           // String query =  "SELECT marks FROM students WHERE id = ?";
           // String query = "UPDATE students SET marks = ? WHERE ID = ?";

             // String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)","Rhaul", 23, 74.5);
           // String query = String.format("UPDATE students SET marks = %f WHERE id = %d", 89.7,2);
          //  String query = "DELETE FROM students WHERE ID = 2";
       //     PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, "Ankita");
//            preparedStatement.setInt(2,25);
//            preparedStatement.setDouble(3,84.7);
//            preparedStatement.setInt(1,1);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){
//                double marks = resultSet.getDouble("marks");
//                System.out.println("Marsks: " + marks);
//            }else{
//                System.out.println("Marks Not found: ");
//            }
//            preparedStatement.setDouble(1,87.5);
//            preparedStatement.setInt(2,3);

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
