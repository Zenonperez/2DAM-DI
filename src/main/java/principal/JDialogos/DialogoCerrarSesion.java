/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal.JDialogos;

import dto.Usuari;
import java.awt.Color;
import javax.swing.BorderFactory;
import principal.Login;
import principal.Main;

/**
 *
 * @author Ziku
 */
public class DialogoCerrarSesion extends javax.swing.JDialog {

    
    
    private Main main;
    /**
     * Creates new form NewJDialog
     * @param parent
     * @param modal
     */
    public DialogoCerrarSesion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        main = (Main) parent;
        initComponents();
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_OptionPaneConectado = new javax.swing.JPanel();
        btn_botonSi = new javax.swing.JButton();
        lbl_Mensaje = new javax.swing.JLabel();
        lbl_icono = new javax.swing.JLabel();
        btn_botonNo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quieres cerrar la sesion?");
        setResizable(false);

        pnl_OptionPaneConectado.setBackground(new java.awt.Color(51, 51, 51));

        btn_botonSi.setText("Si");
        btn_botonSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_botonSiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_botonSiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_botonSiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_botonSiMouseReleased(evt);
            }
        });
        btn_botonSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_botonSiActionPerformed(evt);
            }
        });

        lbl_Mensaje.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_Mensaje.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Mensaje.setText("Estas seguro que quieres cerrar la sesion?");

        lbl_icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pregunta.png"))); // NOI18N

        btn_botonNo.setText("No");
        btn_botonNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_botonNoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_botonNoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_botonNoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_botonNoMouseReleased(evt);
            }
        });
        btn_botonNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_botonNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_OptionPaneConectadoLayout = new javax.swing.GroupLayout(pnl_OptionPaneConectado);
        pnl_OptionPaneConectado.setLayout(pnl_OptionPaneConectadoLayout);
        pnl_OptionPaneConectadoLayout.setHorizontalGroup(
            pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_OptionPaneConectadoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lbl_icono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Mensaje)
                    .addGroup(pnl_OptionPaneConectadoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_botonSi)
                        .addGap(39, 39, 39)
                        .addComponent(btn_botonNo)))
                .addGap(15, 15, 15))
        );
        pnl_OptionPaneConectadoLayout.setVerticalGroup(
            pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_OptionPaneConectadoLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_OptionPaneConectadoLayout.createSequentialGroup()
                        .addComponent(lbl_Mensaje)
                        .addGap(31, 31, 31)
                        .addGroup(pnl_OptionPaneConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_botonSi)
                            .addComponent(btn_botonNo))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_OptionPaneConectadoLayout.createSequentialGroup()
                        .addComponent(lbl_icono)
                        .addGap(50, 50, 50))))
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

    private void btn_botonSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_botonSiActionPerformed
            dispose();
            main.desconectarse();
            Login loginNuevo = new Login(main, true, null, true);
            loginNuevo.setVisible(true);
    }//GEN-LAST:event_btn_botonSiActionPerformed

    private void btn_botonSiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonSiMousePressed
        btn_botonSi.setBackground(Color.GRAY);
    }//GEN-LAST:event_btn_botonSiMousePressed

    private void btn_botonSiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonSiMouseExited
        btn_botonSi.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_botonSiMouseExited

    private void btn_botonSiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonSiMouseEntered
        Color azulPastel = new Color(173,216,230); 
        btn_botonSi.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonSiMouseEntered

    private void btn_botonSiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonSiMouseReleased
        Color azulPastel = new Color(173,216,230); 
        btn_botonSi.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonSiMouseReleased

    private void btn_botonNoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonNoMouseEntered
        Color azulPastel = new Color(173,216,230); 
        btn_botonNo.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonNoMouseEntered

    private void btn_botonNoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonNoMouseExited
        btn_botonSi.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_botonNoMouseExited

    private void btn_botonNoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonNoMousePressed
        btn_botonNo.setBackground(Color.GRAY);
    }//GEN-LAST:event_btn_botonNoMousePressed

    private void btn_botonNoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonNoMouseReleased
        Color azulPastel = new Color(173,216,230); 
        btn_botonNo.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonNoMouseReleased

    private void btn_botonNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_botonNoActionPerformed
        dispose();
    }//GEN-LAST:event_btn_botonNoActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_botonNo;
    private javax.swing.JButton btn_botonSi;
    private javax.swing.JLabel lbl_Mensaje;
    private javax.swing.JLabel lbl_icono;
    private javax.swing.JPanel pnl_OptionPaneConectado;
    // End of variables declaration//GEN-END:variables
}
