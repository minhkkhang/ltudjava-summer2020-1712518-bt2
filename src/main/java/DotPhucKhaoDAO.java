import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.DotPhucKhao;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DotPhucKhaoDAO {
    public static DotPhucKhao currentDotPhucKhao;
    public static SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    public static void checkDotPK() {
        List<DotPhucKhao> ds = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select dpk from DotPhucKhao dpk";
            Query query = session.createQuery(hql);
            ds = new ArrayList<DotPhucKhao>(query.getResultList());
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        if(ds.isEmpty()){
            currentDotPhucKhao=new DotPhucKhao("giaovu","","");
            themDotPK(currentDotPhucKhao);
            return;
        }
        currentDotPhucKhao=ds.get(0);
    }
    public static void themDotPK(DotPhucKhao sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
    }
    public static boolean capNhatThongTinDotPK(DotPhucKhao sv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            Date start=dateFormat.parse(sv.getNgayBatDau());
            Date end=dateFormat.parse(sv.getNgayKetThuc());
            Calendar c=Calendar.getInstance();
            c.add(Calendar.DATE,-1);

            Date currentDate=c.getTime();
            if(start.compareTo(end)>0||start.compareTo(currentDate)<0)throw new Exception();
        }catch (Exception e){
            return false;
        }
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
    public static boolean dangTrongDotPK(){
        if(currentDotPhucKhao==null)return false;
        if(currentDotPhucKhao.getNgayBatDau().compareTo("")==0
                ||currentDotPhucKhao.getNgayKetThuc().compareTo("")==0)return false;
        try{
            Date start=dateFormat.parse(currentDotPhucKhao.getNgayBatDau());
            Date end=dateFormat.parse(currentDotPhucKhao.getNgayKetThuc());
            Date currentDate=java.util.Calendar.getInstance().getTime();
            if(currentDate.compareTo(end)<=0&&currentDate.compareTo(start)>=0)return true;
            else{
                currentDotPhucKhao=new DotPhucKhao("giaovu","","");
                themDotPK(currentDotPhucKhao);
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}
