import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by israj on 9/27/2016.
 */
public class FormMahasiswaServlet extends HttpServlet {

    private MahasiswaDao mahasiswaDao = new MahasiswaDao();

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Mahasiswa mhs = new Mahasiswa();

        try {
            mhs.setNpm(httpServletRequest.getParameter("npm"));
            mhs.setNama(httpServletRequest.getParameter("nama"));
            mhs.setTempatLahir(httpServletRequest.getParameter("tempat_lahir"));

            String tglLahir = httpServletRequest.getParameter("tanggal_lahir");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            mhs.setTanggalLahir(simpleDateFormat.parse(tglLahir));
            mhs.setJenisKelamin(JenisKelamin.valueOf(httpServletRequest.getParameter("jenis_kelamin").toUpperCase()));
            mhs.setAlamat(httpServletRequest.getParameter("alamat"));

            mahasiswaDao.simpan(mhs);
            httpServletResponse.sendRedirect("index.html");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
