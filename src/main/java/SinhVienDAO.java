import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public static SinhVien layThongTinhSinhVien(String maSinhVien) {
        SinhVien sv = null;
        int MSSV=Integer.parseInt(maSinhVien);
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            sv = (SinhVien) session.get(SinhVien.class,
                    MSSV);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return sv;
    }
    public static boolean themSinhVien(SinhVien sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SinhVienDAO.layThongTinhSinhVien(String.valueOf(sv.getMaSinhVien()))!=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sv);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean capNhatThongTinSinhVien(SinhVien sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SinhVienDAO.layThongTinhSinhVien(String.valueOf(sv.getMaSinhVien())) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(sv);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean xoaSinhVien(String maSinhVien) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SinhVien sv = SinhVienDAO.layThongTinhSinhVien(maSinhVien);
        if(sv==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(sv);
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