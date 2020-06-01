package pojo;

import java.util.HashSet;
import java.util.Set;

public class Lop implements java.io.Serializable {
    public Lop(String maLop) {
        this.maLop = maLop;
    }

    private String maLop;
    private Set<SinhVien> sv=new HashSet<SinhVien>(0);
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public Set<SinhVien> getSv() {
        return sv;
    }

    public void setSv(Set<SinhVien> sv) {
        this.sv = sv;
    }
}
