import org.hibernate.HibernateException;
import org.hibernate.Session;
import pojo.SinhVien;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
    public static List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> ds = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select sv from SinhVien sv";
            Query query = session.createQuery(hql);
            ds = new ArrayList<SinhVien>(query.getResultList());
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }

}