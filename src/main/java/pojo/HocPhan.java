package pojo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HocPhan implements java.io.Serializable {
    private String maHocPhan;
    private Lop lop;
    private MonHoc monHoc;
    private String phongHoc;
    private Set<HocLop> hocLop=new HashSet<HocLop>(0);

    public HocPhan(){
        maHocPhan="";
        lop=null;
        monHoc=null;
    }
    public HocPhan(String maHocPhan, Lop maLop, MonHoc maMonHoc, String phongHoc) {
        this.maHocPhan = maHocPhan;
        this.lop = maLop;
        this.monHoc = maMonHoc;
        this.phongHoc=phongHoc;
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

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public String toString(){
        StringBuilder builder=new StringBuilder();
        builder.append(maHocPhan);
        builder.append(" - ");
        builder.append(lop.getMaLop());
        builder.append(" - ");
        builder.append(monHoc.getMaMon());
        builder.append(" - ");
        builder.append(monHoc.getTenMon());
        builder.append(" - ");
        builder.append(phongHoc);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HocPhan hocPhan = (HocPhan) o;
        return Objects.equals(maHocPhan, hocPhan.maHocPhan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHocPhan);
    }
}
