/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import mode.Diem;
import mode.Sinhvien;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dell
 */
public class SVinputdd {

    static Workbook _wb;

    
    
    public static List<Sinhvien> getListdd(Workbook wb) {
        try {
            Sheet sheet = wb.getSheetAt(0);
            List<Sinhvien> list = new ArrayList<>();
            int index = getindextong(wb);
            for (Row row : sheet) {
                Sinhvien sv = new Sinhvien();
                if (row.getRowNum()>2&&row.getCell(3)!=null) {
                    sv.setMasv(row.getCell(1).toString());
                    sv.setTensv(row.getCell(2).toString());
                    if (true) {
                        sv.setTilenghi((int) (row.getCell(row.getLastCellNum()-1).getNumericCellValue()*100));
                    }
                    list.add(sv);
                }
            }
            return list;
        } catch (Exception e) {
            System.out.println("Loi: " + e);
            return null;
        }
    }
    
    static int getint(String a){
        String[] x = a.split("%", 0);
        return Integer.parseInt(x[0]);
    }
    
    

    public static boolean isfilediemdanh(List<Sinhvien> lstquz, List<Sinhvien> lstdd) {
        if (lstquz.size() == lstdd.size()) {
            for (int i = 0; i < lstdd.size(); i++) {
                if (lstdd.get(i).getMasv().equals(lstquz.get(i).getMasv()) == false) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static Workbook getwb(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook wb = new XSSFWorkbook(fis);
            fis.close();
            _wb = wb;
            return wb;
        } catch (Exception e) {
            System.out.println("Loi: " + e);
            return null;
        }
    }
    
    static int f(Workbook b){
        return b.getSheetAt(0).getRow(0).getLastCellNum();
    }

    static int getindextong(Workbook wb) {
        try {
            Sheet sheet = wb.getSheetAt(0);
            int i = -1;
            for (Cell cell : sheet.getRow(0)) {
                ++i;
            }
            return i;
        } catch (Exception e) {
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Sheet x = getwb("C:\\Users\\dell\\Desktop\\ppp.xlsx").getSheetAt(0);
        String b = x.getRow(3).getCell(1).toString();
        String l ="C:\\Users\\dell\\Desktop\\shit.xlsx";
        System.out.println(getListdd(getwb(l)).get(22).getTilenghi());
    }

}
