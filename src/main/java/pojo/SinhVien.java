package pojo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SinhVien implements java.io.Serializable{
    private int maSinhVien;
    private String hoTen;
    private int gioiTinh;
    private long CMND;
    private Lop lop;
    private String matKhau;
    private Set<HocLop> hocLop=new HashSet<HocLop>(0);

    public SinhVien(){
        lop=null;
    }
    public SinhVien(int maSinhVien, String hoTen, int gioiTinh, long CMND, Lop lop) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.CMND = CMND;
        this.lop = lop;
        this.matKhau=String.valueOf(maSinhVien);
    }

    public int getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(int maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public long getCMND() {
        return CMND;
    }

    public void setCMND(long CMND) {
        this.CMND = CMND;
    }

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public Set<HocLop> getHocLop() {
        return hocLop;
    }

    public void setHocLop(Set<HocLop> hocLop) {
        this.hocLop = hocLop;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String toString(){
        StringBuilder builder=new StringBuilder();
        builder.append(String.valueOf(maSinhVien));
        builder.append(" - ");
        builder.append(hoTen);
        builder.append(" - ");
        if(gioiTinh==1)builder.append("Nam");
        else builder.append("Nu");
        builder.append(" - ");
        builder.append(CMND);
        builder.append(" - ");
        builder.append(lop.getMaLop());
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinhVien sinhVien = (SinhVien) o;
        return maSinhVien == sinhVien.maSinhVien;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSinhVien);
    }
}
