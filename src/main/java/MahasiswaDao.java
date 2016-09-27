import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by israj on 9/27/2016.
 */
public class MahasiswaDao {
    private Connection koneksiDatabase;
    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost/javaservlet";
    private String dbUsername = "root";
    private String dbPassword = "";

    public void connect() {

        try {
            Class.forName(dbDriver);
            koneksiDatabase = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disConnect() {

        try {
            if (koneksiDatabase != null) {
                koneksiDatabase.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void simpan(Mahasiswa m){
        try{
            connect();
            String sql = "insert into mahasiswa (npm,nama,tempat_lahir,tanggal_lahir,jenis_kelamin,alamat) values (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = koneksiDatabase.prepareStatement(sql);
            preparedStatement.setString(1,m.getNpm());
            preparedStatement.setString(2,m.getNama());
            preparedStatement.setString(3,m.getTempatLahir());
            preparedStatement.setString(4, String.valueOf(new Date(m.getTanggalLahir().getTime())));
            preparedStatement.setString(5,m.getJenisKelamin().toString());
            preparedStatement.setString(6,m.getAlamat());

            preparedStatement.executeUpdate();

            disConnect();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
