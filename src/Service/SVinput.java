/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.File;
import javax.swing.JFileChooser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mode.Diem;
import mode.Lop;
import mode.Sinhvien;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dell
 */
public class SVinput {

    private static Workbook _wb;
    private static List<Sinhvien> _lstSV;
    private static Lop _lop;
    private static List<Diem> _lstDiem;

    public SVinput() {
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


    public static Lop getlop(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        Lop lop = new Lop();
        lop.setMalop(sheet.getRow(3).getCell(3).toString());
        lop.setMamon(sheet.getRow(2).getCell(3).toString());
        lop.setKihoc(sheet.getRow(5).getCell(3).toString());
        lop.setThoigian(sheet.getRow(4).getCell(3).toString());
        String row8 = sheet.getRow(8).getCell(3).toString();
        if (row8.equals("0.0")) {
            lop.setCheck(1);
            if (sheet.getRow(7).getCell(12).toString().toLowerCase().contains("quiz".toLowerCase())) {
                lop.setSodiemquiz(10);
            } else {
                lop.setSodiemquiz(8);
            }
        } else if (index(wb) > -1) {
            lop.setCheck(2);
        } else {
            lop.setCheck(0);
        }
        return lop;
    }

    private static int index(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        int i = 0;
        for (Cell cell : sheet.getRow(7)) {
            if (cell.toString().equals("Bài học online")) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static List<Sinhvien> getListSV(Workbook wb) {
        try {
            Sheet sheet = wb.getSheetAt(0);
            List<Sinhvien> list = new ArrayList<>();
            for (Row row : sheet) {
                Sinhvien sv = new Sinhvien();
                if (row.getRowNum() > 8 && row.getCell(0) != null) {
                    sv.setMasv(row.getCell(1).toString());
                    sv.setTensv(row.getCell(2).toString());
                    sv.setMalop(sheet.getRow(3).getCell(3).toString());
                    list.add(sv);
                }
            }
            return list;
        } catch (Exception e) {
            System.out.println("Loi: " + e);
            return null;
        }
    }

    public static List<Diem> getlstDiems(Workbook wb) {
        try {
            Sheet sheet = wb.getSheetAt(0);
            List<Diem> lst = new ArrayList<>();
            int index = index(wb);
            for (Row row : sheet) {
                if (row.getRowNum() > 8 && row.getCell(0) != null) {
                    Diem diem = new Diem();
                    if (index < 0) {
                        for (int i = 3; i < 13; i++) {
                            diem.setMasv(row.getCell(1).getStringCellValue());
                            diem.setDiem1(row.getCell(i).getNumericCellValue());
                            diem.setDiem2(row.getCell(i).getNumericCellValue());
                            diem.setDiem3(row.getCell(i).getNumericCellValue());
                            diem.setDiem4(row.getCell(i).getNumericCellValue());
                            diem.setDiem5(row.getCell(i).getNumericCellValue());
                            diem.setDiem6(row.getCell(i).getNumericCellValue());
                            diem.setDiem7(row.getCell(i).getNumericCellValue());
                            diem.setDiem8(row.getCell(i).getNumericCellValue());
                            if (i > 11 && getlop(wb).getSodiemquiz() > 8) {
                                diem.setDiem9(row.getCell(i).getNumericCellValue());
                                diem.setDiem10(row.getCell(i).getNumericCellValue());
                            } else {
                                break;
                            }
                        }
                    } else {
                        diem.setDiemonl(row.getCell(index).getNumericCellValue());
                    }
                    lst.add(diem);
                }
            }
            return lst;
        } catch (Exception e) {
            System.out.println("Loi: " + e);
            return null;
        }
    }



}
