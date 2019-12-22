package cl.yrs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Baglanti extends Thread {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String kullaniciAdi;
    private Server server;

    public Server getServer() {
        return server;
    }
    
    
    
    public Baglanti(int port, String kullaniciAdi) throws IOException {
        this.kullaniciAdi = kullaniciAdi;
        socket = new Socket("localhost", port);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }
    
    private void gonder(String string) throws IOException {
        dos.writeUTF(string);
        dos.flush();
    }
    
    private String dinle() throws IOException {
        String string;
        while (true) {            
            if((string = dis.readUTF()) != null) {
                System.out.println(string);
                return string;
            }
        }
    }
    

    @Override
    public void run() {
        String port;
        while (true) {            
            try {
                port = dinle();
                gonder(kullaniciAdi);
                break;
            } catch (IOException ex) {
                Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(200);
            server = new Server(Integer.parseInt(port));
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    
}
