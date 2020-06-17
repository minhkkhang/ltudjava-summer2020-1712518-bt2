package pojo;

import java.util.HashSet;
import java.util.Set;

public class HocPhan implements java.io.Serializable {
    private String maHocPhan;
    private Lop lop;
    private MonHoc monHoc;
    private Set<HocLop> hocLop=new HashSet<HocLop>(0);

    public HocPhan(){
        maHocPhan="";
        lop=null;
        monHoc=null;
    }
    public HocPhan(String maHocPhan, Lop maLop, MonHoc maMonHoc) {
        this.maHocPhan = maHocPhan;
        this.lop = maLop;
        this.monHoc = maMonHoc;
    }

    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public Set<HocLop> getHocLop() {
        return hocLop;
    }

    public void setHocLop(Set<HocLop> hocLop) {
        this.hocLop = hocLop;
    }

}
