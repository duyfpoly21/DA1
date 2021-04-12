/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mode.Sinhvien;

/**
 *
 * @author dell
 */
public class SVChiacathi {

    static String[] ngaythi() {
        String[] a = new String[]{"a", "b", "c", "d", "e"};
        return a;
    }
    public static List<String> ngay(){
        List<String> x = new ArrayList<>();
        x.add("a"); x.add("b"); x.add("c"); x.add("d");x.add("e");
        return x;
    }

    public static List<Sinhvien> lstSVfinish(List<Sinhvien> _lstSV, List<String> ngaythi, int sobuoi, int sosv) {
        if (sobuoi == 0) {
            if (_lstSV.size() % sosv != 0) {
                sobuoi = _lstSV.size() / sosv;
            } else {
                sobuoi = _lstSV.size() / sosv + 1;
            }
        }
        for (int i = 0; 0 < sosv * sobuoi; i++) {
            _lstSV.get(i).setNgaythi(ngaythi.get(i/sosv));
            if (_lstSV.size() - i == 1) {
                break;
            }
        }
        return _lstSV;
    }

    public static List<Sinhvien> fake() {
        List<Sinhvien> a = new ArrayList<>();
        for (int i = 1; i < 41; i++) {
            Sinhvien x = new Sinhvien();
            x.setTensv("m" + i);
            a.add(x);
        }
        return a;
    }

    public static String formatdate(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public static void main(String[] args) {
        for (Sinhvien sinhvien : lstSVfinish(fake(), ngay(), 3, 15)) {
            System.out.println(sinhvien.getTensv() + "\t" + sinhvien.getNgaythi());
        }
    }
}
