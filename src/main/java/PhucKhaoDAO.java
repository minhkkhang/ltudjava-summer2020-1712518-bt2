import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.PhucKhao;
import pojo.SinhVien;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PhucKhaoDAO {
    public static int stt;
    public static void inIt(){
        ArrayList<PhucKhao> list=new ArrayList<>(layDanhSachPhucKhao());
        stt=list.size()+1;
    }
    public static List<PhucKhao> layDanhSachPhucKhao() {
        List<PhucKhao> ds = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select pk from PhucKhao pk";
            Query query = session.createQuery(hql);
            ds = new ArrayList<PhucKhao>(query.getResultList());
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static PhucKhao layThongTinhPhucKhao(String STT) {
        PhucKhao pk = null;
        int id=Integer.parseInt(STT);
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            pk = (PhucKhao) session.get(PhucKhao.class,
                    id);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return pk;
    }
    public static boolean themPhucKhao(PhucKhao sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        sv.setStt(stt++);
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
    public static boolean capNhatThongTinPhucKhao(PhucKhao pk) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (PhucKhaoDAO.layThongTinhPhucKhao(String.valueOf(pk.getStt())) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(pk);
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
