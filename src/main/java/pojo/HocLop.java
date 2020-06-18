package pojo;

import java.util.Objects;

public class HocLop implements java.io.Serializable {
    private SinhVien sinhVien;
    private HocPhan hocPhan;
    private float diemGK=-1,diemCK=-1,diemKhac=-1,diemTong=-1;
    public HocLop(){
        sinhVien=null;
        hocPhan=null;
    };
    public HocLop(SinhVien sinhVien, HocPhan hocPhan) {
        this.sinhVien = sinhVien;
        this.hocPhan = hocPhan;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public HocPhan getHocPhan() {
        return hocPhan;
    }

    public void setHocPhan(HocPhan hocPhan) {
        this.hocPhan = hocPhan;
    }

    public float getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(float diemGK) {
        this.diemGK = diemGK;
    }

    public float getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(float diemCK) {
        this.diemCK = diemCK;
    }

    public float getDiemKhac() {
        return diemKhac;
    }

    public void setDiemKhac(float diemKhac) {
        this.diemKhac = diemKhac;
    }

    public float getDiemTong() {
        return diemTong;
    }

    public void setDiemTong(float diemTong) {
        this.diemTong = diemTong;
    }

    public String xemDiemCuaMotSV(){
        StringBuilder builder=new StringBuilder();
        builder.append(hocPhan.getMaHocPhan());
        builder.append(" - ");
        builder.append(hocPhan.getMonHoc().getTenMon());
        builder.append(" - ");
        builder.append(String.valueOf(diemGK));
        builder.append(" - ");
        builder.append(String.valueOf(diemCK));
        builder.append(" - ");
        builder.append(String.valueOf(diemKhac));
        builder.append(" - ");
        builder.append(String.valueOf(diemTong));
        return builder.toString();
    }
    public String xemDiemCuaHocPhan(){
        StringBuilder builder=new StringBuilder();
        builder.append(sinhVien.getMaSinhVien());
        builder.append(" - ");
        builder.append(sinhVien.getHoTen());
        builder.append(" - ");
        builder.append(String.valueOf(diemGK));
        builder.append(" - ");
        builder.append(String.valueOf(diemCK));
        builder.append(" - ");
        builder.append(String.valueOf(diemKhac));
        builder.append(" - ");
        builder.append(String.valueOf(diemTong));
        return builder.toString();
    }
    public String xemDSSVThamGiaHP(){
        return sinhVien.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HocLop)) return false;
        HocLop hocLop = (HocLop) o;
        return Objects.equals(sinhVien, hocLop.sinhVien) &&
                Objects.equals(hocPhan, hocLop.hocPhan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sinhVien, hocPhan);
    }
}
