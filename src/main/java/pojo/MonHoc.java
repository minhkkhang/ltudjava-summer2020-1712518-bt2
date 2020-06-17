package pojo;

public class MonHoc implements java.io.Serializable{
    private String maMon;
    private String tenMon;
    public MonHoc(){
        maMon="";
        tenMon="";
    }
    public MonHoc(String maMon, String tenMon) {
        this.maMon = maMon;
        this.tenMon = tenMon;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }
}
