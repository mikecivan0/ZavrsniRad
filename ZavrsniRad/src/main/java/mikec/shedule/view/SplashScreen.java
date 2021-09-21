/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.view;

import javax.swing.JOptionPane;
import mikec.shedule.util.Application;
import org.hibernate.Session;
import mikec.shedule.util.HibernateUtil;

/**
 *
 * @author Ivan
 */
public class SplashScreen extends javax.swing.JFrame {
    
    private boolean loadEnd;

    /**
     * Creates new form SplashScreen
     */
    public SplashScreen() {
        initComponents();          
        loadEnd = false;
        Load load = new Load();
        load.start(); 
        UpdateHeader updateHeader = new UpdateHeader();        
        updateHeader.start();        
    }
    
    private class Load extends Thread{          
        @Override
        public void run() {  
            Session s = HibernateUtil.getSession();
            if(s.getMetamodel().getEntities().size()>0){
                loadEnd = true;
                new Auth().setVisible(true);
                dispose();               
            }else{
                JOptionPane.showMessageDialog(getRootPane(), "A database error has occurred" );
            } 
        }       
    }
    
    private class UpdateHeader extends Thread{  
        private String sufix = "";
        @Override
        public void run() {          
            while (true) { 
                if(loadEnd){
                    break;
                }
                txtHeader.setText(generateHeaderMessage());
                try {                    
                    Thread.sleep(500);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }            
        }          
        
        private String generateHeaderMessage(){
            String hederBaseText = "Loadnig " + Application.APP_TITLE;
            switch(sufix){
                case "" -> { sufix = "."; }
                case "." -> { sufix = ".."; }
                case ".." -> { sufix = "..."; }
                case "..." -> { sufix = ""; }
            }            
            return hederBaseText + sufix;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtHeader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/schedule.jpg"))); // NOI18N

        txtHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHeader.setText("Starting Shedule App");
        txtHeader.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(txtHeader)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtHeader)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel txtHeader;
    // End of variables declaration//GEN-END:variables


}