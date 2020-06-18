import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Lop;
import pojo.MonHoc;

public class MonHocDAO {
    public static MonHoc layThongTinMonHoc(String maMonHoc) {
        MonHoc dm = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            dm = (MonHoc)session.get(MonHoc.class, maMonHoc);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dm;
    }

    public static boolean themMonHoc(MonHoc monHoc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (MonHocDAO.layThongTinMonHoc(monHoc.getMaMon())!=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(monHoc);
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
