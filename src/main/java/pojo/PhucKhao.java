package pojo;

public class PhucKhao {
    private int stt;
    private SinhVien sinhVien;
    private MonHoc monHoc;
    private int cotDiem=1;
    private float diemMongMuon;
    private String lyDo;
    private int trangThai=1;

    public PhucKhao() {
    }

    public PhucKhao(SinhVien sinhVien, MonHoc monHoc, int cotDiem, float diemMongMuon, String lyDo) {
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
        this.cotDiem = cotDiem;
        this.diemMongMuon = diemMongMuon;
        this.lyDo = lyDo;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public int getCotDiem() {
        return cotDiem;
    }

    public void setCotDiem(int cotDiem) {
        this.cotDiem = cotDiem;
    }

    public float getDiemMongMuon() {
        return diemMongMuon;
    }

    public void setDiemMongMuon(float diemMongMuon) {
        this.diemMongMuon = diemMongMuon;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

}
