/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import data.DataAccess;
import java.awt.Color;
import javax.swing.BorderFactory;

/**
 * RevisionReview es una pantalla que permite ver la informacion de una review de un intento evaluado.
 * @author Zenon Perez
 */
public class RevisionReview extends javax.swing.JDialog {
    
    //Declaramos las varaibles globales de la clase.
    /**
     * Variable la cual es el parent de esta ventana con la que utilizaremos algun metodo de este.
     */
    private InformacionUsuario infoUsuario;
    /**
     * Varaible con la que tendremos acceso a enviar o recibir datos de la base de datos.
     */
    private DataAccess da = new DataAccess();

    /**
     * Aqui es donde se crea el form de RevisionReview.
     * @param parent nos muestra que esta ventana sale de un JDialog que en este caso sale de "InformacionUsuario"
     * @param modal nos dice si la ventana es modal o no, en este caso es modal
     */
    public RevisionReview(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        infoUsuario = (InformacionUsuario) parent;
        //Se inician los componentes de la ventana.
        initComponents();
        //Resaltamos el borde para que se diferencie del resto de ventanas.
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        
        //Ponemos los datos de la review en sus labels y textos.
        lbl_suspendido.setVisible(false);
        int idIntento = infoUsuario.getIDIntento();
        String idStringIntento = String.valueOf(idIntento);
        lbl_nombreUsuario.setText(infoUsuario.getNombreUsuario());
        lbl_IDEjercicio.setText(String.valueOf(infoUsuario.getIDEjercicio()));
        lbl_IDintentoRespuesta.setText(idStringIntento);
        String idReviewer = String.valueOf(da.getAttemptReview(idIntento).getIdReviewer());
        lbl_IDReviewerRespuesta.setText(idReviewer);
        String idReview = String.valueOf(da.getAttemptReview(idIntento).getId());
        lbl_IDReviewRespuesta.setText(idReview);
        String valoracion = String.valueOf(da.getAttemptReview(idIntento).getValoracion());
        lbl_ValoracionRespuesta.setText(valoracion);
        txa_ComentarioRespuesta.setText(da.getAttemptReview(idIntento).getComentario());
        txa_ComentarioRespuesta.setEditable(false);
        if (da.getAttemptReview(idIntento).getValoracion() < 3) {
            lbl_suspendido.setVisible(true);
        }
    }

    /**
     * Metodo que inicializa el form y sus componentes.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        pnl_Principal = new javax.swing.JPanel();
        lbl_suspendido = new javax.swing.JLabel();
        lbl_IDReviewerRespuesta = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        txa_ComentarioRespuesta = new javax.swing.JTextArea();
        lbl_IDintentoRespuesta = new javax.swing.JLabel();
        lbl_ValoracionRespuesta = new javax.swing.JLabel();
        lbl_ComentarioEnunciado = new javax.swing.JLabel();
        lbl_IDIntentoEnunciado = new javax.swing.JLabel();
        lbl_IDReviewEnunciado = new javax.swing.JLabel();
        lbl_IDReviewerEnunciado = new javax.swing.JLabel();
        lbl_IDReviewRespuesta = new javax.swing.JLabel();
        lbl_ValoracionEnunciado = new javax.swing.JLabel();
        lbl_tituloReview = new javax.swing.JLabel();
        lbl_nombreUsuario = new javax.swing.JLabel();
        lbl_IDEjercicioEnunciado = new javax.swing.JLabel();
        lbl_IDEjercicio = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Revision de la Review");
        setResizable(false);
        setSize(new java.awt.Dimension(516, 302));

        pnl_Principal.setBackground(new java.awt.Color(51, 51, 51));
        pnl_Principal.setForeground(new java.awt.Color(255, 255, 255));
        pnl_Principal.setPreferredSize(new java.awt.Dimension(516, 302));
        pnl_Principal.setLayout(null);

        lbl_suspendido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_suspendido.setForeground(new java.awt.Color(255, 51, 51));
        lbl_suspendido.setText("Tienes que repetir el intento no has aprobado");
        pnl_Principal.add(lbl_suspendido);
        lbl_suspendido.setBounds(230, 170, 260, 16);

        lbl_IDReviewerRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDReviewerRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDReviewerRespuesta.setText("jLabel2");
        pnl_Principal.add(lbl_IDReviewerRespuesta);
        lbl_IDReviewerRespuesta.setBounds(130, 120, 60, 16);

        txa_ComentarioRespuesta.setLineWrap(true);
        ScrollPane.setViewportView(txa_ComentarioRespuesta);

        pnl_Principal.add(ScrollPane);
        ScrollPane.setBounds(30, 200, 460, 80);

        lbl_IDintentoRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDintentoRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDintentoRespuesta.setText("jLabel3");
        pnl_Principal.add(lbl_IDintentoRespuesta);
        lbl_IDintentoRespuesta.setBounds(300, 70, 110, 16);

        lbl_ValoracionRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValoracionRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ValoracionRespuesta.setText("jLabel4");
        pnl_Principal.add(lbl_ValoracionRespuesta);
        lbl_ValoracionRespuesta.setBounds(300, 120, 100, 16);

        lbl_ComentarioEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_ComentarioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ComentarioEnunciado.setText("Comentario:");
        pnl_Principal.add(lbl_ComentarioEnunciado);
        lbl_ComentarioEnunciado.setBounds(30, 170, 120, 26);

        lbl_IDIntentoEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDIntentoEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDIntentoEnunciado.setText("ID del Intento:");
        pnl_Principal.add(lbl_IDIntentoEnunciado);
        lbl_IDIntentoEnunciado.setBounds(200, 70, 90, 16);

        lbl_IDReviewEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDReviewEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDReviewEnunciado.setText("ID de la Review:");
        pnl_Principal.add(lbl_IDReviewEnunciado);
        lbl_IDReviewEnunciado.setBounds(30, 70, 100, 16);

        lbl_IDReviewerEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDReviewerEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDReviewerEnunciado.setText("ID del Reviewer:");
        pnl_Principal.add(lbl_IDReviewerEnunciado);
        lbl_IDReviewerEnunciado.setBounds(30, 120, 100, 16);

        lbl_IDReviewRespuesta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDReviewRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDReviewRespuesta.setText("jLabel1");
        pnl_Principal.add(lbl_IDReviewRespuesta);
        lbl_IDReviewRespuesta.setBounds(130, 70, 50, 16);

        lbl_ValoracionEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValoracionEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ValoracionEnunciado.setText("Valoracion:");
        pnl_Principal.add(lbl_ValoracionEnunciado);
        lbl_ValoracionEnunciado.setBounds(200, 120, 80, 16);

        lbl_tituloReview.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 24)); // NOI18N
        lbl_tituloReview.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tituloReview.setText("REVIEW DE:");
        pnl_Principal.add(lbl_tituloReview);
        lbl_tituloReview.setBounds(30, 20, 150, 22);

        lbl_nombreUsuario.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 18)); // NOI18N
        lbl_nombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombreUsuario.setText("ANA");
        pnl_Principal.add(lbl_nombreUsuario);
        lbl_nombreUsuario.setBounds(190, 20, 100, 20);

        lbl_IDEjercicioEnunciado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDEjercicioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDEjercicioEnunciado.setText("ID del Ejercicio:");
        pnl_Principal.add(lbl_IDEjercicioEnunciado);
        lbl_IDEjercicioEnunciado.setBounds(360, 70, 100, 16);

        lbl_IDEjercicio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_IDEjercicio.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDEjercicio.setText("jLabel4");
        pnl_Principal.add(lbl_IDEjercicio);
        lbl_IDEjercicio.setBounds(460, 70, 50, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_ComentarioEnunciado;
    private javax.swing.JLabel lbl_IDEjercicio;
    private javax.swing.JLabel lbl_IDEjercicioEnunciado;
    private javax.swing.JLabel lbl_IDIntentoEnunciado;
    private javax.swing.JLabel lbl_IDReviewEnunciado;
    private javax.swing.JLabel lbl_IDReviewRespuesta;
    private javax.swing.JLabel lbl_IDReviewerEnunciado;
    private javax.swing.JLabel lbl_IDReviewerRespuesta;
    private javax.swing.JLabel lbl_IDintentoRespuesta;
    private javax.swing.JLabel lbl_ValoracionEnunciado;
    private javax.swing.JLabel lbl_ValoracionRespuesta;
    private javax.swing.JLabel lbl_nombreUsuario;
    private javax.swing.JLabel lbl_suspendido;
    private javax.swing.JLabel lbl_tituloReview;
    private javax.swing.JPanel pnl_Principal;
    private javax.swing.JTextArea txa_ComentarioRespuesta;
    // End of variables declaration//GEN-END:variables
}
