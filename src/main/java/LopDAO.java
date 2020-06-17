import org.hibernate.HibernateException;
import org.hibernate.Session;
import pojo.Lop;

public class LopDAO {
    public static Lop layThongTinLop(String maLop) {
        Lop dm = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            dm = (Lop)session.get(Lop.class, maLop);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dm;
    }
}
