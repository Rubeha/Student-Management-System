package smsproject;
import java.sql.*;
import java.util.*;

public class Student {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="scott";
	String pass="tiger";
	String sql1="INSERT INTO STUDENT VALUES(?,?,?,?)";
	String sql3="UPDATE STUDENT SET MARKS=? WHERE ID=?";
	String sql4="DELETE FROM STUDENT WHERE ID=?";
	String sql5="SELECT * FROM STUDENT WHERE ID=?";
	int ch=0;
	try (Connection conn=DriverManager.getConnection(url, user, pass);
		 PreparedStatement pst1=conn.prepareStatement(sql1);
		 PreparedStatement pst3=conn.prepareStatement(sql3);
		 PreparedStatement pst4=conn.prepareStatement(sql4);
		 PreparedStatement pst5=conn.prepareStatement(sql5);){
	while(ch!=6) {
	System.out.println("------WELCOME TO STUDENT MANAGEMENT SYSTEM------");
	System.out.println("1.Add a new Student");
	System.out.println("2.View all Students");
	System.out.println("3.Update Student marks by ID");
	System.out.println("4.Delete a student by ID");
	System.out.println("5.Search a student by ID");
	System.out.println("6.Exit");
	System.out.println("Enter your Option:");
	ch=sc.nextInt();
	switch(ch) {
	case 1:
		System.out.println("Enter ID:");
		pst1.setInt(1,sc.nextInt());
		sc.nextLine();
		System.out.println("Enter Name:");
		pst1.setString(2,sc.next());
		System.out.println("Enter Marks:");
		pst1.setInt(3,sc.nextInt());
		sc.nextLine();
	    System.out.println("Enter the department:");
	    pst1.setString(4,sc.next());
	    pst1.executeUpdate();
	    System.out.println("New Student added successfully");
	    break;
	case 2:
		Statement st=conn.createStatement();
		String sql2="SELECT * FROM STUDENT";
	    ResultSet rs=st.executeQuery(sql2);
	    while(rs.next()) {
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" ");
	    }
		break;
	case 3:
		System.out.println("Enter the student ID to update marks:");
		pst3.setInt(2,sc.nextInt());
		System.out.println("Enter the marks to update:");
		pst3.setInt(1,sc.nextInt());
		int row=pst3.executeUpdate();
		System.out.println(row+" Updated successfully");
		break;
	case 4:
		System.out.println("Enter the student ID to delete the record:");
		pst4.setInt(1,sc.nextInt());
		pst4.executeUpdate();
		System.out.println("Student data deleted successfully");
		break;
	case 5:
		System.out.println("Enter the student ID to search:");
		pst5.setInt(1,sc.nextInt());
        ResultSet rs1=pst5.executeQuery();
        if(rs1.next()) {
          System.out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+rs1.getInt(3)+" "+rs1.getString(4)+" ");
        }
        else {
        	System.out.println("Student not found.Please check the ID entered");
        }
        break;
	case 6:
		System.out.println("Closing the system");
		break;
    default:
    	System.out.println("Enter a valid option");
    	break;
    }
	}
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}

