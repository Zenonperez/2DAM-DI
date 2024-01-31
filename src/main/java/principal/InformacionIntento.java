/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import data.DataAccess;
import dto.Intent;

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
    public InformacionIntento(java.awt.Frame parent, boolean modal, int idEjer) {
        super(parent, modal);
        main = (Main) parent;
        Intent intento = main.SeleccionIntento(idEjer);
        initComponents();
        String idS = String.valueOf(intento.getId());
        lbl_IDRespuesta.setText(idS);
        String idUserS = String.valueOf(intento.getIdUsuari());
        lbl_IDUsuarioRespuesta.setText(idUserS);
        lbl_NombreUsuarioRespuesta.setText(intento.getNombreUsuario());
        String idEjerS = String.valueOf(intento.getIdEjercicio());
        lbl_IDEjercicioRespuesta.setText(idEjerS);
        lbl_NombreEjercicioRespuesta.setText(intento.getNombreEjercicio());
        lbl_TimestampInicioRespuesta.setText(intento.getTimestamp_Inicio());
        lbl_TimestampFinRespuesta.setText(intento.getTimestamp_Fin());
        lbl_VideofileRespuesta.setText(intento.getVideofile());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_IDRespuesta = new javax.swing.JLabel();
        lbl_IDUsuarioRespuesta = new javax.swing.JLabel();
        lbl_NombreUsuarioRespuesta = new javax.swing.JLabel();
        lbl_IDIntentoEnunciado = new javax.swing.JLabel();
        lbl_IDEjercicioRespuesta = new javax.swing.JLabel();
        lbl_IDUsuarioEnunciado = new javax.swing.JLabel();
        lbl_NombreEjercicioRespuesta = new javax.swing.JLabel();
        lbl_NombreUsuarioEnunciado = new javax.swing.JLabel();
        lbl_TimestampInicioRespuesta = new javax.swing.JLabel();
        lbl_IDEjercicioEnunciado = new javax.swing.JLabel();
        lbl_TimestampFinRespuesta = new javax.swing.JLabel();
        lbl_NombreEjercicioEnunciado = new javax.swing.JLabel();
        lbl_VideofileRespuesta = new javax.swing.JLabel();
        lbl_TimestampInicioEnunciado = new javax.swing.JLabel();
        lbl_TimestampFinEnunciado = new javax.swing.JLabel();
        lbl_VideofileEnunciado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informacion del Intento");
        setPreferredSize(new java.awt.Dimension(615, 406));
        setResizable(false);
        setSize(new java.awt.Dimension(615, 406));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(615, 406));
        jPanel1.setLayout(null);

        lbl_IDRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDRespuesta.setText("jLabel9");
        jPanel1.add(lbl_IDRespuesta);
        lbl_IDRespuesta.setBounds(210, 90, 130, 16);

        lbl_IDUsuarioRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDUsuarioRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDUsuarioRespuesta.setText("jLabel10");
        jPanel1.add(lbl_IDUsuarioRespuesta);
        lbl_IDUsuarioRespuesta.setBounds(210, 120, 130, 16);

        lbl_NombreUsuarioRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_NombreUsuarioRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NombreUsuarioRespuesta.setText("jLabel11");
        jPanel1.add(lbl_NombreUsuarioRespuesta);
        lbl_NombreUsuarioRespuesta.setBounds(210, 150, 130, 16);

        lbl_IDIntentoEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDIntentoEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDIntentoEnunciado.setText("ID:");
        jPanel1.add(lbl_IDIntentoEnunciado);
        lbl_IDIntentoEnunciado.setBounds(50, 90, 16, 16);

        lbl_IDEjercicioRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDEjercicioRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDEjercicioRespuesta.setText("jLabel12");
        jPanel1.add(lbl_IDEjercicioRespuesta);
        lbl_IDEjercicioRespuesta.setBounds(210, 180, 120, 16);

        lbl_IDUsuarioEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDUsuarioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDUsuarioEnunciado.setText("ID del usuario:");
        jPanel1.add(lbl_IDUsuarioEnunciado);
        lbl_IDUsuarioEnunciado.setBounds(50, 120, 110, 16);

        lbl_NombreEjercicioRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_NombreEjercicioRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NombreEjercicioRespuesta.setText("jLabel13");
        jPanel1.add(lbl_NombreEjercicioRespuesta);
        lbl_NombreEjercicioRespuesta.setBounds(210, 220, 140, 16);

        lbl_NombreUsuarioEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_NombreUsuarioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NombreUsuarioEnunciado.setText("Nombre del Usuario:");
        jPanel1.add(lbl_NombreUsuarioEnunciado);
        lbl_NombreUsuarioEnunciado.setBounds(50, 150, 150, 16);

        lbl_TimestampInicioRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_TimestampInicioRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TimestampInicioRespuesta.setText("jLabel14");
        jPanel1.add(lbl_TimestampInicioRespuesta);
        lbl_TimestampInicioRespuesta.setBounds(210, 250, 280, 16);

        lbl_IDEjercicioEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDEjercicioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDEjercicioEnunciado.setText("ID del Ejercicio:");
        jPanel1.add(lbl_IDEjercicioEnunciado);
        lbl_IDEjercicioEnunciado.setBounds(50, 180, 120, 16);

        lbl_TimestampFinRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_TimestampFinRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TimestampFinRespuesta.setText("jLabel15");
        jPanel1.add(lbl_TimestampFinRespuesta);
        lbl_TimestampFinRespuesta.setBounds(210, 290, 100, 16);

        lbl_NombreEjercicioEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_NombreEjercicioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NombreEjercicioEnunciado.setText("Nombre del Ejercicio:");
        jPanel1.add(lbl_NombreEjercicioEnunciado);
        lbl_NombreEjercicioEnunciado.setBounds(50, 220, 140, 16);

        lbl_VideofileRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_VideofileRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_VideofileRespuesta.setText("jLabel16");
        jPanel1.add(lbl_VideofileRespuesta);
        lbl_VideofileRespuesta.setBounds(210, 330, 130, 16);

        lbl_TimestampInicioEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_TimestampInicioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TimestampInicioEnunciado.setText("Timestamp Inicio:");
        jPanel1.add(lbl_TimestampInicioEnunciado);
        lbl_TimestampInicioEnunciado.setBounds(50, 250, 120, 16);

        lbl_TimestampFinEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_TimestampFinEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TimestampFinEnunciado.setText("Timestamp Fin:");
        jPanel1.add(lbl_TimestampFinEnunciado);
        lbl_TimestampFinEnunciado.setBounds(50, 290, 100, 16);

        lbl_VideofileEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_VideofileEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_VideofileEnunciado.setText("Videofile:");
        jPanel1.add(lbl_VideofileEnunciado);
        lbl_VideofileEnunciado.setBounds(50, 330, 120, 16);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sentadilla.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(400, 140, 159, 160);

        jLabel2.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INFORMACION DEL INTENTO");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(127, 31, 257, 17);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
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
