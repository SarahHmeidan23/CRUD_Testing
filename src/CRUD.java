import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CRUD {
	Connection con = null;
	java.sql.Statement stm = null;
	ResultSet rs;

	@BeforeTest
	public void Setup() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "2522");
	}

	@Test(enabled = false)
	public void insertData() throws SQLException {

		stm = con.createStatement();

		String myQuery = "insert into customers (customerNumber,customerName,contactFirstName,contactLastName,city,postalCode,phone,addressLine1,country)values('122','ahmad mohammad','dana','ahmad','amman','1234','1223','sportCity','jordan')";
		int rowEffected = stm.executeUpdate(myQuery);
		if (rowEffected > 0) {
			System.out.println("Query is passed");
		} else {
			System.out.println("Query is failed");
		}
	}

	@Test(enabled = false)
	public void updateData() throws SQLException {
		stm = con.createStatement();
		String myQuery = "update customers set city ='Amman' where customerNumber= 122";
		int rowEffected = stm.executeUpdate(myQuery);
		Assert.assertEquals(rowEffected > 0, true, "the update has a problem");
	}

	@Test(enabled = false)
	public void readData() throws SQLException {
		stm = con.createStatement();
		String myQuery = "select * from customers  where customerNumber= 242";
		rs = stm.executeQuery(myQuery);
		while (rs.next()) {
			String thecustomerNumber = rs.getString("customerNumber");
			String thephone = rs.getString("phone");
			Assert.assertEquals(thecustomerNumber, "242");
			Assert.assertEquals(thephone, "61.77.6555");
		}

	}

	@Test()
	public void deleteData() throws SQLException {
		stm = con.createStatement();
		String myQuery = "delete from customers where customerName='ahmad mohammad' and customerNumber='122'";
		int rowEffected = stm.executeUpdate(myQuery);
		Assert.assertEquals(rowEffected > 0, true, "the delete has a problem");
	}

	@AfterTest()
	public void Post() {
	}

}
