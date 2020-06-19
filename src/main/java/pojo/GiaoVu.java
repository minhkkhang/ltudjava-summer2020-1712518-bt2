package pojo;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class GiaoVu {
    String ten;
    String matKhau;
    public GiaoVu(){}
    public GiaoVu(String ten, String matKhau) {
        this.ten = ten;
        this.matKhau = matKhau;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

}
