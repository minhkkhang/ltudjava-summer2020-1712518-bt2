import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.HocLop;
import pojo.HocPhan;
import pojo.SinhVien;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HocLopDAO {
    public static List<HocLop> layDanhSachHocLop() {
        List<HocLop> ds = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select hl from HocLop hl";
            Query query = session.createQuery(hql);
            ds = new ArrayList<HocLop>(query.getResultList());
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static HocLop layThongTinhHocLopTheoCaHai(String maSinhVien,String maHocPhan) {
        List<HocLop> ds = null;
        int MSSV=Integer.parseInt(maSinhVien);
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select hl from HocLop hl" +
                    " WHERE MaHocPhan like :maHP and MSSV=:mssv";
            Query query = session.createQuery(hql);
            query.setParameter("maHP", maHocPhan);
            query.setParameter("mssv", MSSV);
            ds = new ArrayList<HocLop>(query.getResultList());
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        if(ds==null)return null;
        if(ds.isEmpty())return null;
        return ds.get(0);
    }
    public static boolean themHocLop(HocLop hl) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (HocLopDAO.layThongTinhHocLopTheoCaHai(String.valueOf(hl.getSinhVien().getMaSinhVien()),
                hl.getHocPhan().getMaHocPhan())!=null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(hl);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean capNhatThongTinHocLop(HocLop hl) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (HocLopDAO.layThongTinhHocLopTheoCaHai(String.valueOf(hl.getSinhVien().getMaSinhVien()),
                hl.getHocPhan().getMaHocPhan()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(hl);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean xoaHocPhan(String maSinhVien,String maHocPhan) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocLop hl = HocLopDAO.layThongTinhHocLopTheoCaHai(maSinhVien,maHocPhan);
        if(hl==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(hl);
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
