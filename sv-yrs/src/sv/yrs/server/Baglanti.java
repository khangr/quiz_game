package sv.yrs.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Baglanti extends Thread {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int yonlendirme = 4100;
    private final int limit = 10;
    
    public Baglanti(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        Server.konsol(port + ":Portu açıldı.");
    }
    
    public void gonder(String string) throws IOException {
        dos.writeUTF(string);
        dos.flush();
    }
    
    private String dinle() throws IOException {
        String string;
        while (true) {            
            if((string = dis.readUTF()) != null) {
                return string;
            }
        }
    }

    @Override
    public void run() {
        Yarismaci yarismaci;
        while (true) {            
            try {
                if(Server.yarismacilar.size() < limit) {
                    Server.konsol("Yeni Bağlantı Bekleniyor...");
                    socket = serverSocket.accept();
                    Server.konsol("Yeni Bağlantı.");
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());
                    Server.konsol("IO oluşturuldu.");
                    Thread.sleep(50);
                    gonder(yonlendirme + "");
                    Server.konsol(yonlendirme + ":Portuna yonlendirildi.");
                    String kullaniciAdi = dinle();
                    System.out.println(kullaniciAdi);
                    yarismaci = new Yarismaci(yonlendirme, kullaniciAdi);
                    yarismaci.start();
                    Server.yarismacilar.add(yarismaci);
                    Server.yenile();
                    yonlendirme++;
                } else {
                    Server.konsol("Yeni Bağlantı Bekleniyor...");
                    socket = serverSocket.accept();
                    Server.konsol("Yeni Bağlantı.");
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());
                    Server.konsol("IO oluşturuldu.");
                    Thread.sleep(50);
                    gonder("Dolu");
                    Server.konsol("Sunucu Dolu:Yönlendirilmedi.");
                }   
            } catch (IOException ex) {
                Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
}
