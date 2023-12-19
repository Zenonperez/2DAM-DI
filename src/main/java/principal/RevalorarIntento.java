/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import com.sun.jna.platform.mac.DiskArbitration;
import data.DataAccess;
import dto.Review;
import javax.swing.JOptionPane;

/**
 *
 * @author Ziku
 */
public class RevalorarIntento extends javax.swing.JDialog {

    private DataAccess da = new DataAccess();
    private InformacionUsuario infoUsuario;
    Review review;
    private int valoracion;

    /**
     * Creates new form ValoracionIntentoSinReview
     */
    public RevalorarIntento(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        infoUsuario = (InformacionUsuario) parent;
        review = da.getAttemptReview(infoUsuario.getIDIntento());
        initComponents();
        String valoracion = String.valueOf(review.getValoracion());
        txa_ValoracionRespuesta.setText(valoracion);
        txa_ComentarioRespuesta.setText(review.getComentario());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_ValoracionEnunciado = new javax.swing.JLabel();
        txa_ValoracionRespuesta = new javax.swing.JTextField();
        lbl_Comentario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_ComentarioRespuesta = new javax.swing.JTextArea();
        btn_Enviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Review");
        setResizable(false);
        setSize(new java.awt.Dimension(400, 300));

        lbl_ValoracionEnunciado.setText("Valoración:");

        txa_ValoracionRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txa_ValoracionRespuestaActionPerformed(evt);
            }
        });

        lbl_Comentario.setText("Comentario:");

        txa_ComentarioRespuesta.setColumns(20);
        txa_ComentarioRespuesta.setRows(5);
        jScrollPane1.setViewportView(txa_ComentarioRespuesta);

        btn_Enviar.setText("Enviar");
        btn_Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_Enviar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_Comentario)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbl_ValoracionEnunciado)
                            .addGap(18, 18, 18)
                            .addComponent(txa_ValoracionRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ValoracionEnunciado)
                    .addComponent(txa_ValoracionRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(lbl_Comentario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Enviar)
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txa_ValoracionRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txa_ValoracionRespuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txa_ValoracionRespuestaActionPerformed

    private void btn_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EnviarActionPerformed
        try {
            valoracion = Integer.parseInt(txa_ValoracionRespuesta.getText());
            review.setValoracion(valoracion);
            review.setComentario(txa_ComentarioRespuesta.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Solo puedes poner numeros en la valoracion");
            valoracion = -1;
        }
        if (valoracion < 0 || valoracion > 10) {
            JOptionPane.showMessageDialog(this, "Solo puedes poner un numero de 0 al 10");
        } else {
            da.updateReview(review);
        }
        infoUsuario.ActualizarIntentosUsuario();
        JOptionPane.showMessageDialog(this, "Se ha actualizado la review correctamente");

        dispose();


    }//GEN-LAST:event_btn_EnviarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Enviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Comentario;
    private javax.swing.JLabel lbl_ValoracionEnunciado;
    private javax.swing.JTextArea txa_ComentarioRespuesta;
    private javax.swing.JTextField txa_ValoracionRespuesta;
    // End of variables declaration//GEN-END:variables
}
