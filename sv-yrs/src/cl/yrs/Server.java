package cl.yrs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {
    
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    public static String mesaj;
    private boolean izin;

    public Server(int port) throws IOException {
        izin = true;
        socket = new Socket("localhost", port);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }
    
    public void gonder(String string) throws IOException {
        dos.writeUTF(string);
        dos.flush();
    }

    private String dinle() throws IOException {
        String string;
        while(true) {
            if((string = dis.readUTF()) != null) {
                System.out.println("geldi");
                return string;
            }
        }
    }

    public String getMesaj() {
        return mesaj;
    }
    
    
    
    @Override
    public void run() {
        while (true) {            
            try {
                mesaj = dinle();
                String[] temp = mesaj.split(":0x00::");
                System.out.println(temp[0]);
                if(temp[0].equalsIgnoreCase("0x1")) {
                    Client.yarismacilar = new ArrayList<String>();
                    String[] tomp = temp[1].split(":0##:");
                    for (int i = 0; i < tomp.length; i++) {
                        System.out.println(tomp[i]);
                        Client.yarismacilar.add(tomp[i]);
                    }
                    Client.area();
                }
                if(temp[0].equalsIgnoreCase("0x2")) {
                    String[] tomp = temp[1].split(":0##:");
                    Client.soru.setText(tomp[0]);
                    Client.a.setText(tomp[1]);
                    Client.b.setText(tomp[2]);
                    Client.c.setText(tomp[3]);
                    if(izin == true) {
                        Client.ba.setVisible(true);
                        Client.bb.setVisible(true);
                        Client.bc.setVisible(true);
                    }
                    Client.timer();
                }
                if(temp[0].equalsIgnoreCase("0x3")) {
                    if(temp[1].equalsIgnoreCase("true")) {
                        izin = true;
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
                    }
                    else {
                        izin = false;
                    }
                }
                if(temp[0].equalsIgnoreCase("0x4")) {
                    Client.win.setText("Kazanan:" + temp[1]);
                    Client.win.setVisible(true);
                }
                if(temp[0].equalsIgnoreCase("0x5")) {
                    Client.win.setText(temp[1]);
                    Client.win.setVisible(true);
                }
                if(temp[0].equalsIgnoreCase("0x6")) {
                    Client.win.setVisible(false);
                    izin = true;
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
