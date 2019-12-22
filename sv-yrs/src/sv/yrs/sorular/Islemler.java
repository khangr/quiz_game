package sv.yrs.sorular;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Islemler {
    
    public static List<Soru> reset(List<Soru> sorular) {
        sorular = new ArrayList<Soru>();
        return sorular;
    }
    
    public static List<Soru> ekle(List<Soru> sorular, String soru, String a, String b, String c, String cevap) throws IOException {
        sorular.add(new Soru(soru, a, b, c, cevap));
        yaz(sorular);
        return sorular;
    }
    
    public static List<Soru> sil(List<Soru> sorular, int index) throws IOException {
        sorular.remove(index);
        yaz(sorular);
        return sorular;
    }
    
    public static void yaz(List<Soru> sorular) throws IOException {
        String bl = ":#:";
        File file = new File("sorular.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < sorular.size(); i++) {
            bufferedWriter.write(sorular.get(i).getSoru() + bl +
                                 sorular.get(i).getA() + bl +
                                 sorular.get(i).getB() + bl +
                                 sorular.get(i).getC() + bl +
                                 sorular.get(i).getCevap());
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }
    
    public static List<Soru> oku(List<Soru> sorular) throws FileNotFoundException, IOException {
        sorular = reset(sorular);
        String bl = ":#:";
        String[] temp;
        String test;
        File file = new File("sorular.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((test = bufferedReader.readLine()) != null) {            
            temp = test.split(bl, 5);
            sorular.add(new Soru(temp[0], temp[1], temp[2], temp[3], temp[4]));
        }
        return sorular;
    }
}
