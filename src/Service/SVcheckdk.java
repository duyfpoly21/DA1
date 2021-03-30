/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.ArrayList;
import java.util.List;
import mode.Diem;
import mode.Lop;
import mode.Sinhvien;

/**
 *
 * @author dell
 */
public class SVcheckdk {

    static boolean quiz8(double a1, double a2, double a3, double a4, double a5,
            double a6, double a7, double a8,double b) {
        if (a1 < b || a2 < b || a3 < b || a4 < b || a5 < b || a6 < b || a7 < b || a8 < b) {
            return true;
        }
        return false;
    }

   public static boolean checksv(Diem x, Lop lop, double a) {
            if (lop.getCheck() == 1) {
                    if (quiz8(x.getDiem1(), x.getDiem2(), x.getDiem3(), x.getDiem4(),
                            x.getDiem5(), x.getDiem6(), x.getDiem7(), x.getDiem8(),a)) {
                        if (lop.getSodiemquiz()==8) {
                            return false;
                        }
                        if (lop.getSodiemquiz()==10||x.getDiem9()<a||x.getDiem10()<a) {
                            return false;
                        }
                    }
            }
            if (lop.getCheck()==2) {
                if (x.getDiemonl()<a) {
                    return false;
                }
            }
            return true;
    }

    public static void main(String[] args) {

    }
}
