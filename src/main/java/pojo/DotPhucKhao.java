package pojo;

public class DotPhucKhao {
    String dotPhucKhao;
    String ngayBatDau;
    String ngayKetThuc;

    public DotPhucKhao() {
    }

    public DotPhucKhao(String dotPhucKhao, String ngayBatDau, String ngayKetThuc) {
        this.dotPhucKhao = dotPhucKhao;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getDotPhucKhao() {
        return dotPhucKhao;
    }

    public void setDotPhucKhao(String dotPhucKhao) {
        this.dotPhucKhao = dotPhucKhao;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
