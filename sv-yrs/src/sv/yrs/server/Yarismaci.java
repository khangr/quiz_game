package sv.yrs.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Yarismaci extends Thread {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String kullaniciAdi;
    private String string;
    private boolean oyunda;

    public Yarismaci(int port, String kullaniciAdi) throws IOException {
        oyunda = true;
        this.kullaniciAdi = kullaniciAdi;
        Server.konsol(kullaniciAdi + " Kullanicisi için " + port + ":oluşruldu.");
        serverSocket = new ServerSocket(port);
        Server.konsol(kullaniciAdi + " Bağlanması bekleniyor...");
        socket = serverSocket.accept();
        Server.konsol(kullaniciAdi + " Bağlandı.");
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        Server.konsol("IO oluşturuldu.");
    }
    
    public void gonder(String string) throws IOException {
        dos.writeUTF(string);
        dos.flush();
        Server.konsol(kullaniciAdi + ":" + string + ":gonderildi.");
    }
    
    public int getPort() {
        return serverSocket.getLocalPort();
    }

    private String dinle() throws IOException {
        String string;
        while (true) {            
            if ((string = dis.readUTF()) != null) {
                Server.konsol(kullaniciAdi + ":" + string + ":alindi.");
                return string;
            }
        }
    }

    public void setString(String string) {
        this.string = string;
    }
    
    
    
    public void oyunda(boolean bool) {
        this.oyunda = bool;
    }
    
    public boolean durum() {
        return oyunda;
    }

    public String getString() {
        return string;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }
    
    @Override
    public void run() {
        try {
            Mesaj.herkeseGonder(Server.yarismacilar, Mesaj.liste(Server.yarismacilar));
        } catch (IOException ex) {
            Logger.getLogger(Yarismaci.class.getName()).log(Level.SEVERE, null, ex);
        }
        Server.konsol(kullaniciAdi + " dinleniyor.");
        while (true) {            
            try {
                string = dinle();
            } catch (IOException ex) {
                Server.konsol(kullaniciAdi + " ayrıldı.");
                Server.kopar(getPort());
                try {
                    Mesaj.herkeseGonder(Server.yarismacilar, Mesaj.liste(Server.yarismacilar));
                } catch (IOException ex1) {
                    Logger.getLogger(Yarismaci.class.getName()).log(Level.SEVERE, null, ex1);
                }
                break;
            }
        }
    }
    
    
}
