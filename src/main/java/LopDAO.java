import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Lop;
import pojo.SinhVien;

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

    public static boolean themLop(Lop lop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (LopDAO.layThongTinLop(lop.getMaLop())!=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(lop);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
}
