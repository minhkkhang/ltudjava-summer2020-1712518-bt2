package pojo;

import java.util.HashSet;
import java.util.Set;

public class SinhVien implements java.io.Serializable{
    private int maSinhVien;
    private String hoTen;
    private int gioiTinh;
    private long CMND;
    private Lop lop;
    private Set<HocLop> hocLop=new HashSet<HocLop>(0);

    public SinhVien(int maSinhVien, String hoTen, int gioiTinh, long CMND, Lop lop) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.CMND = CMND;
        this.lop = lop;
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
}
