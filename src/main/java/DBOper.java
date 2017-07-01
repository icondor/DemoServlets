import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOper {

    // 2. define connection params to db
    private static final String URL = "jdbc:postgresql://54.93.65.5:5432/6IulianaUser23";
    private static final String USERNAME = "fasttrackit_dev";
    private static final String PASSWORD = "fasttrackit_dev";

    public static void insert(String a, String b) {
        try {
            // 1. load driver, no longer needed in new versions of JDBC
            Class.forName("org.postgresql.Driver");

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO stringuri (intrare, iesire) VALUES (?,?)");
            pSt.setString(1, a);
            pSt.setString(2,b);

            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeContact(int id) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("DELETE FROM agenda WHERE id = ?");
            pSt.setInt(1, id);

            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            System.out.println("contact removed. ID = " + id);
            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertContact(String firstName, String lastName, String phone) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO agenda (name, prenume, phone) VALUES (?,?,?)");
            pSt.setString(1, lastName);
            pSt.setString(2, firstName);
            pSt.setString(3, phone);

            int rowsInserted = pSt.executeUpdate();

            System.out.println("am inserat " + rowsInserted + " randuri.");
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Conctact> getContacts() {
        List<Conctact> contacts = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM agenda");

            // 6. iterate the result set and print the values
            while (rs.next()) {
                int id = rs.getInt("id");
                String lastName = rs.getString("name").trim();
                String firstName = rs.getString("prenume").trim();
                String phone = rs.getString("phone").trim();
                Conctact conctact = new Conctact(id, firstName, lastName, phone);
                contacts.add(conctact);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public static void main(String[] args) {
        //removeContact(3);
        //insertContact("Matei", "Nicolae", "02222232");
        List<Conctact> contacts = getContacts();
        System.out.println(contacts);
    }
}