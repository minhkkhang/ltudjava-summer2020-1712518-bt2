import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.HocLop;
import pojo.HocPhan;
import pojo.Lop;
import pojo.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class HocPhanDAO {
    public static List<HocPhan> layDanhSachHocPhan() {
        List<HocPhan> ds = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select hp from HocPhan hp";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
//Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static HocPhan layThongTinhHocPhan(String maHP) {
        HocPhan hp = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            hp = (HocPhan) session.get(HocPhan.class,
                    maHP);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return hp;
    }
    public static List<HocPhan> layThongTinHocPhanTheoLop(String maLop){
        List<HocPhan> ds = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select hp from HocPhan hp" +
                    " WHERE MaLop like :maLop";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("maLop", maLop);
            ds = new ArrayList<HocPhan>(query.getResultList());
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        if(ds==null)return null;
        return ds;
    }
    public static boolean themHocPhan(HocPhan hp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (HocPhanDAO.layThongTinhHocPhan(hp.getMaHocPhan())!=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(hp);
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
