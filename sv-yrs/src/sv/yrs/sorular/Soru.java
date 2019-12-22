package sv.yrs.sorular;

public class Soru {
    
    private String soru;
    private String a;
    private String b;
    private String c;
    private String cevap;

    public Soru(String soru, String a, String b, String c, String cevap) {
        this.soru = soru;
        this.a = a;
        this.b = b;
        this.c = c;
        this.cevap = cevap;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getSoru() {
        return soru;
    }

    public String getCevap() {
        return cevap;
    }
    
    
}
