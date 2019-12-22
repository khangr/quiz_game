package sv.yrs.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sv.yrs.server.Server.sorular;
import sv.yrs.sorular.Islemler;
import sv.yrs.sorular.Soru;

public class Server extends javax.swing.JFrame {

    public Server() throws IOException {
        initComponents();
        sorular = Islemler.oku(sorular);
        new Baglanti(4000).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        konsol = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        yrs = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        time = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        konsol.setEditable(false);
        konsol.setColumns(20);
        konsol.setRows(5);
        konsol.setFocusable(false);
        jScrollPane1.setViewportView(konsol);

        yrs.setEditable(false);
        yrs.setColumns(20);
        yrs.setRows(5);
        yrs.setFocusable(false);
        jScrollPane2.setViewportView(yrs);

        jButton1.setText("Yeni Soru");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sorular");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Yeni Yarisma");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        time.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        time.setText("11");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(time)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(time))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new sv.yrs.sorular.Sorular().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            yeniSoru();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        for (int i = 0; i < yarismacilar.size(); i++) {
            yarismacilar.get(i).oyunda(true);
            try {
                Mesaj.herkeseGonder(yarismacilar, Mesaj.yeni(yarismacilar));
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            yenile();
            try {
                Mesaj.herkeseGonder(yarismacilar, Mesaj.liste(yarismacilar));
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void yeniSoru() throws IOException {
        random = new Random().nextInt(sorular.size());
        timer();
        if (sorular.get(random).getCevap().equalsIgnoreCase("a"))
            konsol("SORU:" + sorular.get(random).getSoru() + ":" + sorular.get(random).getA());
        if (sorular.get(random).getCevap().equalsIgnoreCase("b"))
            konsol("SORU:" + sorular.get(random).getSoru() + ":" + sorular.get(random).getB());
        if (sorular.get(random).getCevap().equalsIgnoreCase("c"))
            konsol("SORU:" + sorular.get(random).getSoru() + ":" + sorular.get(random).getC());
        try {
            Mesaj.herkeseGonder(yarismacilar, Mesaj.soru(sorular.get(random)));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Server().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static void konsol(String string) {
        konsol.append(string + "\n");
    }
    
    public static void kopar(int port) {
        for (int i = 0; i < yarismacilar.size(); i++) {
            if(yarismacilar.get(i).getPort() == port) {
                yarismacilar.remove(yarismacilar.get(i));
                break;
            }     
        }
        yenile();
    }
    
    public void kontrol() throws IOException {
        String cevap = sorular.get(random).getCevap();
        int counter = 0;
        for (int i = 0; i < yarismacilar.size(); i++) {
            if(yarismacilar.get(i).getString().equalsIgnoreCase(cevap)) {
                yarismacilar.get(i).oyunda(true);
                counter++;
            } else {
                yarismacilar.get(i).oyunda(false);
            }
            yarismacilar.get(i).setString("");
        }
        Mesaj.izin(yarismacilar);
        Mesaj.herkeseGonder(yarismacilar, Mesaj.liste(yarismacilar));
        yenile();
        if(counter == 1) {
            Mesaj.herkeseGonder(yarismacilar, Mesaj.kazanan(yarismacilar));
        } 
        if(counter == 0) {
            Mesaj.herkeseGonder(yarismacilar, Mesaj.kazananYok(yarismacilar));
        }
        
    }
    
    private void timer() {
        Timer tmer = new Timer();
        TimerTask task;
        task = new TimerTask() {
            
            int seconds = 11;

            @Override
            public void run() {
                time.setText(seconds + "");
                seconds--;
                if (seconds < 0) {
                    try {
                        kontrol();
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    tmer.cancel();
                }
            }
        };
        tmer.schedule(task, 0, 1000); 
    }
    
    public static void yenile() {
        yrs.setText("");
        for (int i = 0; i < yarismacilar.size(); i++) {
            if(yarismacilar.get(i).durum() == true) 
                yrs.append(yarismacilar.get(i).getKullaniciAdi() + "\n");
            else
                yrs.append(yarismacilar.get(i).getKullaniciAdi() + " (x)\n");
        }
    }
    
    public static List<Yarismaci> yarismacilar = new ArrayList<Yarismaci>();
    public static List<Soru> sorular;
    private int random;
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTextArea konsol;
    private static javax.swing.JLabel time;
    private static javax.swing.JTextArea yrs;
    // End of variables declaration//GEN-END:variables
}
