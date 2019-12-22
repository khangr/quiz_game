package cl.yrs;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends javax.swing.JFrame {

    public Client() throws IOException {
        initComponents();
        ba.setVisible(false);
        bb.setVisible(false);
        bc.setVisible(false);
        win.setVisible(false);
        kullaniciAdi = Baslat.jTextField1.getText();
        baglanti = new Baglanti(4000, kullaniciAdi);
        baglanti.start();
    }
    
    public static void area() {
        area.setText("");
        for (int i = 0; i < yarismacilar.size(); i++) {
            area.append(yarismacilar.get(i) + "\n");
        }
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }
    
    public static void timer() {
        Timer tmer = new Timer();
        TimerTask task = new TimerTask() {

            int seconds = 10;

            @Override
            public void run() {
                time.setText(seconds + "");
                seconds--;
                if (seconds < 0) {
                    ba.setVisible(false);
                    bb.setVisible(false);
                    bc.setVisible(false);
                    tmer.cancel();
                }
            }
        };
        tmer.schedule(task, 0, 1000); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        soru = new javax.swing.JLabel();
        a = new javax.swing.JLabel();
        b = new javax.swing.JLabel();
        c = new javax.swing.JLabel();
        ba = new javax.swing.JButton();
        bb = new javax.swing.JButton();
        bc = new javax.swing.JButton();
        time = new javax.swing.JLabel();
        win = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        area.setEditable(false);
        area.setColumns(20);
        area.setRows(5);
        jScrollPane1.setViewportView(area);

        soru.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        soru.setText("Soru");

        a.setText("a");

        b.setText("b");

        c.setText("c");

        ba.setText("A");
        ba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baActionPerformed(evt);
            }
        });

        bb.setText("B");
        bb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbActionPerformed(evt);
            }
        });

        bc.setText("C");
        bc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcActionPerformed(evt);
            }
        });

        time.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        time.setText("10");

        win.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        win.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soru)
                    .addComponent(a)
                    .addComponent(b)
                    .addComponent(c)
                    .addComponent(time)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ba)
                        .addGap(18, 18, 18)
                        .addComponent(bb)
                        .addGap(18, 18, 18)
                        .addComponent(bc))
                    .addComponent(win))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(soru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(a)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ba)
                            .addComponent(bb)
                            .addComponent(bc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(win)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(time))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void baActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baActionPerformed
        try {
            baglanti.getServer().gonder("a");
            ba.setVisible(false);
            bb.setVisible(false);
            bc.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_baActionPerformed

    private void bbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbActionPerformed
        try {
            baglanti.getServer().gonder("b");
            ba.setVisible(false);
            bb.setVisible(false);
            bc.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bbActionPerformed

    private void bcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcActionPerformed
        try {
            baglanti.getServer().gonder("c");
            ba.setVisible(false);
            bb.setVisible(false);
            bc.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bcActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Client().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static List<String> yarismacilar;
    private String kullaniciAdi;
    private Baglanti baglanti;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel a;
    private static javax.swing.JTextArea area;
    public static javax.swing.JLabel b;
    public static javax.swing.JButton ba;
    public static javax.swing.JButton bb;
    public static javax.swing.JButton bc;
    public static javax.swing.JLabel c;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel soru;
    private static javax.swing.JLabel time;
    public static javax.swing.JLabel win;
    // End of variables declaration//GEN-END:variables
}
