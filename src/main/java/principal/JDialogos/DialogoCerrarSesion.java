/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal.JDialogos;

import java.awt.Color;
import javax.swing.BorderFactory;
import principal.Login;
import principal.Main;

/**
 * DialogoCerrarSesion es una ventana de dialogo la cual nos pregunta si de verdad queremos cerrar la sesion.
 * En caso afirmativo la cerrara y en negativo volvera atras.
 * @author Zenon Pèrez
 */
public class DialogoCerrarSesion extends javax.swing.JDialog {
    
    
    private Main main;
    /**
     * Aqui es donde se crea el nuevo form de DialogoCerrarSesion
     * @param parent nos muestra que esta ventana sale de un JFrame que en este caso sale de "Main".
     * @param modal nos dice si la ventana es modal o no, en este caso es modal.
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
    /**
     * Metodo se activa si se pulsa el boton "btn_botonSi" haciendo que se cierre la aplicacion usando el metodo desconectaese y volviendo al "Login".
     */
    private void btn_botonSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_botonSiActionPerformed
            dispose();
            main.desconectarse();
            Login loginNuevo = new Login(main, true, null, true);
            loginNuevo.setVisible(true);
    }//GEN-LAST:event_btn_botonSiActionPerformed
    /**
     * Metodo que se activara al pulsar boton "btn_botonSi" que hara que se ponga de color gris claro.
     */
    private void btn_botonSiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonSiMousePressed
        btn_botonSi.setBackground(Color.GRAY);
    }//GEN-LAST:event_btn_botonSiMousePressed
    /**
     * Metodo que se activara al salir el raton del "btn_botonSi" que hara que se ponga de color blanco.
     */
    private void btn_botonSiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonSiMouseExited
        btn_botonSi.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_botonSiMouseExited
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_botonSi" que hara que se ponga de color azul.
     */
    private void btn_botonSiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonSiMouseEntered
        Color azulPastel = new Color(173,216,230); 
        btn_botonSi.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonSiMouseEntered
    /**
     * Metodo que se activara al soltar el boton "btn_botonSi" que hara que se ponga de color azul.
     */
    private void btn_botonSiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonSiMouseReleased
        Color azulPastel = new Color(173,216,230); 
        btn_botonSi.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonSiMouseReleased
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_botonNo" que hara que se ponga de color azul.
     */
    private void btn_botonNoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonNoMouseEntered
        Color azulPastel = new Color(173,216,230); 
        btn_botonNo.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonNoMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_botonSi" que hara que se ponga de color blanco.
     */
    private void btn_botonNoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonNoMouseExited
        btn_botonSi.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_botonNoMouseExited
    /**
     * Metodo que se activara al pulsar boton "btn_botonNo" que hara que se ponga de color gris claro.
     */
    private void btn_botonNoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonNoMousePressed
        btn_botonNo.setBackground(Color.GRAY);
    }//GEN-LAST:event_btn_botonNoMousePressed
    /**
     * Metodo que se activara al soltar el boton "btn_botonNo" que hara que se ponga de color azul.
     */
    private void btn_botonNoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_botonNoMouseReleased
        Color azulPastel = new Color(173,216,230); 
        btn_botonNo.setBackground(azulPastel);
    }//GEN-LAST:event_btn_botonNoMouseReleased
    /**
     * Metodo que se activa al pulsar el boton "btn_botonNo" que cerrara la ventana de dialogo.
     */
    private void btn_botonNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_botonNoActionPerformed
        dispose();
    }//GEN-LAST:event_btn_botonNoActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_botonNo;
    private javax.swing.JButton btn_botonSi;
    private javax.swing.JLabel lbl_Mensaje;
    private javax.swing.JLabel lbl_icono;
    private javax.swing.JPanel pnl_OptionPaneConectado;
    // End of variables declaration//GEN-END:variables
}
