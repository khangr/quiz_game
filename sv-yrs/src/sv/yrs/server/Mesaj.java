package sv.yrs.server;

import java.io.IOException;
import java.util.List;
import sv.yrs.sorular.Soru;

public class Mesaj {
    
    public static String liste(List<Yarismaci> liste) {
        String string = "0x1";
        String x = ":0x00::";
        string += x;
        String bl = ":0##:";
        for (int i = 0; i < liste.size(); i++) {
            if(liste.get(i).durum() == true) {
                string += liste.get(i).getKullaniciAdi();
            } else {
                string += liste.get(i).getKullaniciAdi();
                string += " (x)";
            }
            
            if (i + 1 < liste.size()) {
               string += bl; 
            }   
        }
        return string;
    }
    
    public static String yeni(List<Yarismaci> liste) {
        String string = "0x6";
        String x = ":0x00::";
        string += x;
        string += "reset";
        return string;
    }
    
    public static String kazanan(List<Yarismaci> liste) {
        String string = "0x4";
        String x = ":0x00::";
        string += x;
        String kazanan;
        for (int i = 0; i < liste.size(); i++) {
            if(liste.get(i).durum() == true) {
                kazanan = liste.get(i).getKullaniciAdi();
                string += kazanan;
                break;
            }
        }
        return string;
    }
    
    public static String kazananYok(List<Yarismaci> liste) {
        String string = "0x5";
        String x = ":0x00::";
        string += x;
        string += "Kimse Kazanamadı!";
        return string;
    }   
    
    
    public static void izin(List<Yarismaci> liste) throws IOException {
        String string = "0x3";
        String x = ":0x00::";
        string += x;
        for (int i = 0; i < liste.size(); i++) {
            if(liste.get(i).durum() == true) {
               liste.get(i).gonder(string + "true");
               Server.konsol(liste.get(i).getKullaniciAdi() + ":true"); 
            }
            else {
               liste.get(i).gonder(string + "false");
               Server.konsol(liste.get(i).getKullaniciAdi() + ":false");
            }
                
        }
    }
    
    public static String soru(Soru soru) {
        String string = "0x2";
        String x = ":0x00::";
        string += x;
        String bl = ":0##:";
        string += soru.getSoru();
        string += bl;
        string += soru.getA();
        string += bl;
        string += soru.getB();
        string += bl;
        string += soru.getC();
        return string;
    }
    
    public static void herkeseGonder(List<Yarismaci> liste, String string) throws IOException {
        for (int i = 0; i < liste.size(); i++) {
            liste.get(i).gonder(string);
        }
    }
}
