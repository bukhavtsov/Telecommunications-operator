import java.io.Serializable;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomerAccountContainer implements Serializable {
    private int count = 0;
    private CustomerAccount[] caContainer = new CustomerAccount[10];
    private String write = "";

    public CustomerAccount[] getCaContainer() {
        return caContainer;
    }

    public int getCount() {
        return count;
    }

    public String getWrite() {
        return write;
    }


    public void add(CustomerAccount customerAccount) throws SQLException {
        new org.sqlite.JDBC();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:BM.db")) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO CustomerAccount (balance, internet, minutes, phoneNumber, priceOfRate, rate, name, surname, address) " + "VALUES(?,?,?,?,?,?,?,?,?)");
            statement.setDouble(1, customerAccount.getBalance());
            statement.setInt(2, customerAccount.getInternet());
            statement.setInt(3, customerAccount.getMinutes());
            statement.setString(4, customerAccount.getPhoneNumber());
            statement.setDouble(5, customerAccount.getPriceOfRate());
            statement.setString(6, customerAccount.getRate());
            statement.setString(7, customerAccount.name);
            statement.setString(8, customerAccount.surname);
            statement.setString(9, customerAccount.address);
            statement.executeUpdate();
            statement.close();
        }
    }

    public void delete(int id) {
        new org.sqlite.JDBC();

        String sql = "DELETE FROM CustomerAccount WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:BM.db")) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM CustomerAccount WHERE id = ?");
            // set the corresponding param
            statement.setInt(1, id);
            // execute the delete statement
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(int i, CustomerAccount newCustomerAccount) throws SQLException {
        new org.sqlite.JDBC();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:BM.db")) {
            PreparedStatement statement = conn.prepareStatement("UPDATE CustomerAccount SET balance = ?, internet = ?, minutes = ?, phoneNumber = ?, priceOfRate = ?, rate = ?, name = ?, surname = ?, address = ?");
            statement.setDouble(1, newCustomerAccount.getBalance());
            statement.setInt(2, newCustomerAccount.getInternet());
            statement.setInt(3, newCustomerAccount.getMinutes());
            statement.setString(4, newCustomerAccount.getPhoneNumber());
            statement.setDouble(5, newCustomerAccount.getPriceOfRate());
            statement.setString(6, newCustomerAccount.getRate());
            statement.setString(7, newCustomerAccount.name);
            statement.setString(8, newCustomerAccount.surname);
            statement.setString(9, newCustomerAccount.address);
            statement.executeUpdate();
            statement.close();
        }
    }


    public void aboutClient() throws SQLException {
        new org.sqlite.JDBC();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:BM.db")) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT *FROM CustomerAccount");
            while (result.next()) {
                System.out.println(result.getString("id")+" "+result.getString("rate")+" "+
                        result.getString("internet")+" "+result.getString("phoneNumber")+" "+
                        result.getString("balance")+" "+result.getString("priceOfRate")+" "+
                        result.getString("minutes")+" "+ result.getString("name") + " " +
                        result.getString("surname")+" "+result.getString("address"));

            }
            statement.close();
        }
    }
}