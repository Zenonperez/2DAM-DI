/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal.JDialogos;

import java.awt.Color;
import javax.swing.BorderFactory;

/**
 * DialogoNoExisteReview es una ventana de dialogo que nos muestra un mensaje de error el cual indica que no existe la review de este intento al consultar la informacion de esta.
 * @author Zenon Perez
 */
public class DialogoNoExisteReview extends javax.swing.JDialog {

    /**
     * Aqui es donde se crea el nuevo form de DialogoNoExisteReview
     * @param parent nos muestra que esta ventana sale de un JDialog que en "InformacionUsuario".
     * @param modal nos dice si la ventana es modal o no, en este caso es modal.
     */
    public DialogoNoExisteReview(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    /**
     * Aqui se inician todos los componetes de la ventana. 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_OptionPaneConectado = new javax.swing.JPanel();
        btn_botonOK = new javax.swing.JButton();
        lbl_Mensaje = new javax.swing.JLabel();
        pnl_icono = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Error");
        setResizable(false);

        pnl_OptionPaneConectado.setBackground(new java.awt.Color(51, 51, 51));

        btn_botonOK.setText("OK");
        btn_botonOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_botonOKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_botonOKMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_botonOKMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_botonOKMouseReleased(evt);
            }
        });
        btn_botonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_botonOKActionPerformed(evt);
            }
        });
        btn_botonOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_botonOKKeyPressed(evt);
            }
        });

        lbl_Mensaje.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_Mensaje.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Mensaje.setText("No exite la review de este intento");

        pnl_icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/error.png"))); // NOI18N

        javax.swing.GroupLayout pnl_OptionPaneConectadoLayout = new javax.swing.GroupLayout(pnl_OptionPaneConectado);
        pnl_OptionPaneConectado.setLayout(pnl_OptionPaneConectadoLayout);
        pnl_OptionPaneConectadoLayout.setHorizontalGroup(
            pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_OptionPaneConectadoLayout.createSequentialGroup()
                .addGroup(pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_OptionPaneConectadoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_botonOK))
                    .addGroup(pnl_OptionPaneConectadoLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(pnl_icono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(lbl_Mensaje)))
                .addGap(15, 15, 15))
        );
        pnl_OptionPaneConectadoLayout.setVerticalGroup(
            pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_OptionPaneConectadoLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_OptionPaneConectadoLayout.createSequentialGroup()
                        .addComponent(lbl_Mensaje)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_OptionPaneConectadoLayout.createSequentialGroup()
                        .addComponent(pnl_icono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(btn_botonOK)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_OptionPaneConectado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_OptionPaneConectado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo que se activa al pulsar el boton "btn_botonOK" que cerrara el dialogo de la ventana.
     */
    private void btn_botonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_botonOKActionPerformed
        dispose();
    }//GEN-LAST:event_btn_botonOKActionPerformed
    
    private void btn_botonOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_botonOKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_botonOKKeyPressed
    /**
     * Metodo que se activara al salir el raton del "btn_botonOK" que hara que se ponga de color blanco.
     */
    private void btn_botonOKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonOKMouseExited
        btn_botonOK.setBackground(Color.white);
    }//GEN-LAST:event_btn_botonOKMouseExited
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_botonOK" que hara que se ponga de color azul.
     */
    private void btn_botonOKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonOKMouseEntered
        Color azulPastel = new Color(173,216,230); 
        btn_botonOK.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonOKMouseEntered
    /**
     * Metodo que se activara al soltar el boton "btn_botonOK" que hara que se ponga de color azul.
     */
    private void btn_botonOKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonOKMouseReleased
         Color azulPastel = new Color(173,216,230); 
        btn_botonOK.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonOKMouseReleased
    /**
     * Metodo que se activara al pulsar boton "btn_botonOK" que hara que se ponga de color gris claro.
     */
    private void btn_botonOKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonOKMousePressed
        btn_botonOK.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_botonOKMousePressed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_botonOK;
    private javax.swing.JLabel lbl_Mensaje;
    private javax.swing.JPanel pnl_OptionPaneConectado;
    private javax.swing.JLabel pnl_icono;
    // End of variables declaration//GEN-END:variables
}
