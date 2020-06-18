import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pojo.*;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileUtil {

    public static List<SinhVien> docFileThemSinhVien(JPanel parent){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            // user selects a file
            File selectedFile = fileChooser.getSelectedFile();
            ArrayList<SinhVien> data=new ArrayList<>();
            String csvFile="";
            try{
                csvFile = selectedFile.getCanonicalPath();
            }
            catch (IOException ex){
                System.out.println(ex.toString());
                JOptionPane.showMessageDialog(parent,"Gap loi",
                    "Failure",JOptionPane.WARNING_MESSAGE);
            }
            try (Reader reader = new FileReader(csvFile);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                         .withHeader("STT", "MSSV", "Ho Ten","Gioi Tinh","CMND")
                         .withIgnoreHeaderCase()
                         .withTrim());) {
                List<CSVRecord> records=csvParser.getRecords();
                Lop lop=new Lop(records.get(0).get(0));
                LopDAO.themLop(lop);
                for (int i=2;i<records.size();i++) {
                    // Accessing values by Header names
                    CSVRecord record=records.get(i);
                    SinhVien sv=new SinhVien(Integer.parseInt(record.get(1)),record.get(2),
                            record.get(3).compareTo("Nam")==0?1:0,Long.parseLong(record.get(4)),lop);
                    data.add(sv);
                }
                return data;
            }
            catch (Exception e){
                System.out.println(e.toString());
                JOptionPane.showMessageDialog(parent,"Gap loi",
                        "Failure",JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        else return null;
    }

    public static List<HocPhan> docFileThemHocPhan(JPanel parent){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            // user selects a file
            File selectedFile = fileChooser.getSelectedFile();
            ArrayList<HocPhan> data=new ArrayList<>();
            String csvFile="";
            try{
                csvFile = selectedFile.getCanonicalPath();
            }
            catch (IOException ex){JOptionPane.showMessageDialog(parent,"Gap loi",
                    "Failure",JOptionPane.WARNING_MESSAGE);}
            try (Reader reader = new FileReader(csvFile);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                         .withHeader("STT", "Ma Mon", "Ten Mon","Phong Hoc")
                         .withIgnoreHeaderCase()
                         .withTrim());) {
                List<CSVRecord> records=csvParser.getRecords();
                Lop lop=new Lop(records.get(0).get(0));
                LopDAO.themLop(lop);
                for (int i=2;i<records.size();i++) {
                    // Accessing values by Header names
                    CSVRecord record=records.get(i);
                    MonHoc mh=new MonHoc(record.get(1),record.get(2));
                    MonHocDAO.themMonHoc(mh);
                    HocPhan hp=new HocPhan(lop.getMaLop()+"–"+record.get(1),lop,mh,record.get(3));
                    data.add(hp);
                }
                return data;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(parent,"Gap loi",
                        "Failure",JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        else return null;
    }

    public static List<HocLop> docFileThemHocLop(JPanel parent){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            // user selects a file
            File selectedFile = fileChooser.getSelectedFile();
            ArrayList<HocLop> data=new ArrayList<>();
            String csvFile="";
            try{
                csvFile = selectedFile.getCanonicalPath();
            }
            catch (IOException ex){
                System.out.println(ex.toString());
                JOptionPane.showMessageDialog(parent,"Gap loi",
                    "Failure",JOptionPane.WARNING_MESSAGE);
            }
            try (Reader reader = new FileReader(csvFile);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                         .withHeader("STT", "MSSV", "Ho Ten","Gioi Tinh","CMND")
                         .withIgnoreHeaderCase()
                         .withTrim());) {
                List<CSVRecord> records=csvParser.getRecords();
                HocPhan hocPhan=HocPhanDAO.layThongTinhHocPhan(records.get(0).get(0));
                System.out.println(records.get(0).get(0));
                if(hocPhan==null){
                    System.out.println(HocPhanDAO.layDanhSachHocPhan().get(0).getMaHocPhan());
                    String[] tokens=records.get(0).get(0).split("–");
                    Lop lop=new Lop(tokens[0]);
                    MonHoc mon=new MonHoc(tokens[1],"Unknown");
                    hocPhan=new HocPhan(records.get(0).get(0),lop,mon,"...");
                    HocPhanDAO.themHocPhan(hocPhan);
                }

                for (int i=2;i<records.size();i++) {
                    // Accessing values by Header names
                    CSVRecord record=records.get(i);
                    SinhVien sv=SinhVienDAO.layThongTinhSinhVien(record.get(1));
                    if (sv==null){
                        sv=new SinhVien(Integer.parseInt(record.get(1)),record.get(2),
                                record.get(3).compareTo("Nam")==0?1:0,Long.parseLong(record.get(4)),hocPhan.getLop());
                        SinhVienDAO.themSinhVien(sv);
                    }
                    HocLop hl=new HocLop(sv,hocPhan);
                    data.add(hl);
                }
                return data;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(parent,"Gap loi",
                        "Failure",JOptionPane.WARNING_MESSAGE);

                return null;
            }
        }
        else return null;
    }

    public static List<HocLop> docFileThemDiem(JPanel parent){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            // user selects a file
            File selectedFile = fileChooser.getSelectedFile();
            ArrayList<HocLop> data=new ArrayList<>();
            String csvFile="";
            try{
                csvFile = selectedFile.getCanonicalPath();
            }
            catch (IOException ex){JOptionPane.showMessageDialog(parent,"Gap loi",
                    "Failure",JOptionPane.WARNING_MESSAGE);}
            try (Reader reader = new FileReader(csvFile);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                         .withHeader("STT", "MSSV", "Ho Ten","DiemGK","DiemCK","DiemKhac","DiemTong")
                         .withIgnoreHeaderCase()
                         .withTrim());) {
                List<CSVRecord> records=csvParser.getRecords();
                HocPhan hocPhan=HocPhanDAO.layThongTinhHocPhan(records.get(0).get(0));
                for (int i=2;i<records.size();i++) {
                    // Accessing values by Header names
                    CSVRecord record=records.get(i);
                    SinhVien sv=SinhVienDAO.layThongTinhSinhVien(record.get(1));
                    if (sv==null){
                        sv=new SinhVien(Integer.parseInt(record.get(1)),record.get(2),
                                record.get(3).compareTo("Nam")==0?1:0,Long.parseLong(record.get(4)),hocPhan.getLop());
                    }
                    HocLop hl=new HocLop(sv,hocPhan);
                    hl.setDiemGK(Float.parseFloat(record.get(3)));
                    hl.setDiemCK(Float.parseFloat(record.get(4)));
                    hl.setDiemKhac(Float.parseFloat(record.get(5)));
                    hl.setDiemTong(Float.parseFloat(record.get(6)));
                    data.add(hl);
                }
                return data;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(parent,"Gap loi",
                        "Failure",JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        else return null;
    }
}
