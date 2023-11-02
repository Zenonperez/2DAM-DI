/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import data.DataAccess;

/**
 *
 * @author Ziku
 */
public class InformacionIntento extends javax.swing.JDialog {

    private Main main;
    private DataAccess da = new DataAccess();
    /**
     * Creates new form InformacionIntento
     */
    public InformacionIntento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        main = (Main)parent;
        int seleccion = main.SeleccionFilaIntentosinfo();
        initComponents();
        String idS = String.valueOf(da.getAttemptsPendingReview().get(seleccion).getId());
        lbl_IDRespuesta.setText(idS);
        String idUserS = String.valueOf(da.getAttemptsPendingReview().get(seleccion).getIdUsuari());
        lbl_IDUsuarioRespuesta.setText(idUserS);
        lbl_NombreUsuarioRespuesta.setText(da.getAttemptsPendingReview().get(seleccion).getNombreUsuario());
        String idEjerS = String.valueOf(da.getAttemptsPendingReview().get(seleccion).getIdEjercicio());
        lbl_IDEjercicioRespuesta.setText(idEjerS);
        lbl_NombreEjercicioRespuesta.setText(da.getAttemptsPendingReview().get(seleccion).getNombreEjercicio());
        lbl_TimestampInicioRespuesta.setText(da.getAttemptsPendingReview().get(seleccion).getTimestamp_Inicio());
        lbl_TimestampFinRespuesta.setText(da.getAttemptsPendingReview().get(seleccion).getTimestamp_Fin());
        lbl_VideofileRespuesta.setText(da.getAttemptsPendingReview().get(seleccion).getVideofile());
        
        
     
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_IDIntentoEnunciado = new javax.swing.JLabel();
        lbl_IDUsuarioEnunciado = new javax.swing.JLabel();
        lbl_NombreUsuarioEnunciado = new javax.swing.JLabel();
        lbl_IDEjercicioEnunciado = new javax.swing.JLabel();
        lbl_NombreEjercicioEnunciado = new javax.swing.JLabel();
        lbl_TimestampInicioEnunciado = new javax.swing.JLabel();
        lbl_TimestampFinEnunciado = new javax.swing.JLabel();
        lbl_VideofileEnunciado = new javax.swing.JLabel();
        lbl_IDRespuesta = new javax.swing.JLabel();
        lbl_IDUsuarioRespuesta = new javax.swing.JLabel();
        lbl_NombreUsuarioRespuesta = new javax.swing.JLabel();
        lbl_IDEjercicioRespuesta = new javax.swing.JLabel();
        lbl_NombreEjercicioRespuesta = new javax.swing.JLabel();
        lbl_TimestampInicioRespuesta = new javax.swing.JLabel();
        lbl_TimestampFinRespuesta = new javax.swing.JLabel();
        lbl_VideofileRespuesta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbl_IDIntentoEnunciado.setText("ID:");

        lbl_IDUsuarioEnunciado.setText("ID del usuario");

        lbl_NombreUsuarioEnunciado.setText("Nombre del Usuario:");

        lbl_IDEjercicioEnunciado.setText("ID del Ejercicio:");

        lbl_NombreEjercicioEnunciado.setText("Nombre del Ejercicio:");

        lbl_TimestampInicioEnunciado.setText("Timestamp Inicio:");

        lbl_TimestampFinEnunciado.setText("Timestamp Fin:");

        lbl_VideofileEnunciado.setText("Videofile:");

        lbl_IDRespuesta.setText("jLabel9");

        lbl_IDUsuarioRespuesta.setText("jLabel10");

        lbl_NombreUsuarioRespuesta.setText("jLabel11");

        lbl_IDEjercicioRespuesta.setText("jLabel12");

        lbl_NombreEjercicioRespuesta.setText("jLabel13");

        lbl_TimestampInicioRespuesta.setText("jLabel14");

        lbl_TimestampFinRespuesta.setText("jLabel15");

        lbl_VideofileRespuesta.setText("jLabel16");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_IDIntentoEnunciado)
                    .addComponent(lbl_IDUsuarioEnunciado)
                    .addComponent(lbl_NombreUsuarioEnunciado)
                    .addComponent(lbl_IDEjercicioEnunciado)
                    .addComponent(lbl_NombreEjercicioEnunciado)
                    .addComponent(lbl_TimestampInicioEnunciado)
                    .addComponent(lbl_TimestampFinEnunciado)
                    .addComponent(lbl_VideofileEnunciado))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_VideofileRespuesta)
                    .addComponent(lbl_TimestampFinRespuesta)
                    .addComponent(lbl_TimestampInicioRespuesta)
                    .addComponent(lbl_NombreEjercicioRespuesta)
                    .addComponent(lbl_IDEjercicioRespuesta)
                    .addComponent(lbl_NombreUsuarioRespuesta)
                    .addComponent(lbl_IDUsuarioRespuesta)
                    .addComponent(lbl_IDRespuesta))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_IDIntentoEnunciado)
                    .addComponent(lbl_IDRespuesta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_IDUsuarioEnunciado)
                    .addComponent(lbl_IDUsuarioRespuesta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_NombreUsuarioEnunciado)
                    .addComponent(lbl_NombreUsuarioRespuesta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_IDEjercicioEnunciado)
                    .addComponent(lbl_IDEjercicioRespuesta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_NombreEjercicioEnunciado)
                    .addComponent(lbl_NombreEjercicioRespuesta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_TimestampInicioEnunciado)
                    .addComponent(lbl_TimestampInicioRespuesta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_TimestampFinEnunciado)
                    .addComponent(lbl_TimestampFinRespuesta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_VideofileEnunciado)
                    .addComponent(lbl_VideofileRespuesta))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_IDEjercicioEnunciado;
    private javax.swing.JLabel lbl_IDEjercicioRespuesta;
    private javax.swing.JLabel lbl_IDIntentoEnunciado;
    private javax.swing.JLabel lbl_IDRespuesta;
    private javax.swing.JLabel lbl_IDUsuarioEnunciado;
    private javax.swing.JLabel lbl_IDUsuarioRespuesta;
    private javax.swing.JLabel lbl_NombreEjercicioEnunciado;
    private javax.swing.JLabel lbl_NombreEjercicioRespuesta;
    private javax.swing.JLabel lbl_NombreUsuarioEnunciado;
    private javax.swing.JLabel lbl_NombreUsuarioRespuesta;
    private javax.swing.JLabel lbl_TimestampFinEnunciado;
    private javax.swing.JLabel lbl_TimestampFinRespuesta;
    private javax.swing.JLabel lbl_TimestampInicioEnunciado;
    private javax.swing.JLabel lbl_TimestampInicioRespuesta;
    private javax.swing.JLabel lbl_VideofileEnunciado;
    private javax.swing.JLabel lbl_VideofileRespuesta;
    // End of variables declaration//GEN-END:variables
}
