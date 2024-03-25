/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import com.mycompany.customcomponentejercicio.SwipeListener;
import data.DataAccess;
import dto.Intent;
import dto.Review;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import principal.JDialogos.DialogoEliminacionReviewCompletada;
import principal.JDialogos.DialogoNoExisteReview;
import principal.JDialogos.DialogoNoHayReviewQueEliminar;

/**
 *
 * @author Ziku
 */
public class InformacionUsuario extends javax.swing.JDialog {

    private Main main;
    private DataAccess da = new DataAccess();
    private int seleccion;
    private ArrayList<Intent> intentosUsuario = new ArrayList<>();
    private ArrayList<com.mycompany.customcomponentejercicio.CustomComponentEjercicio> componenteIntentos = new ArrayList<>();
    private String direccion;
    private int idEjer;
    private Color verdePastel = new Color(205, 255, 205);
    private Color azulPastel = new Color(173, 216, 230);

    public InformacionUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        main = (Main) parent;
        seleccion = main.SeleccionFilaUsuariosIntentos();
        initComponents();
        btn_EditarReview.setEnabled(false);
        btn_EliminarReview.setEnabled(false);
        btn_RevisarReview.setEnabled(false);
        String idS = String.valueOf(da.getAllUsers().get(seleccion).getId());
        lbl_IDRespuesta.setText(idS);
        lbl_NombreRespuesta.setText(da.getAllUsers().get(seleccion).getNombre());
        lbl_EmailRespuesta.setText(da.getAllUsers().get(seleccion).getEmail());
        conseguirIntentosUsuario();
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    public int getIDReview(int idIntento) {
        int idReview = da.getAttemptReview(idIntento).getId();
        return idReview;
    }

    public int getIDIntento() {
        Intent intentoUsuario = buscarIntentoUsuario(idEjer);
        return intentoUsuario.getId();
    }

    public String getNombreUsuario() {
        Intent intentoUsuario = buscarIntentoUsuario(idEjer);
        return intentoUsuario.getNombreUsuario();
    }

    public int getIDEjercicio() {
        Intent intentoUsuario = buscarIntentoUsuario(idEjer);
        return intentoUsuario.getIdEjercicio();
    }

    public void conseguirIntentosUsuario() {
        intentosUsuario = da.getAttemptsPerUser(da.getAllUsers().get(seleccion));

        for (Intent intent : intentosUsuario) {
            com.mycompany.customcomponentejercicio.CustomComponentEjercicio pnl_customComponentEjercicio = new com.mycompany.customcomponentejercicio.CustomComponentEjercicio();
            pnl_customComponentEjercicio.setnombreEjercicio(intent.getNombreEjercicio());
            pnl_customComponentEjercicio.setnombreUsuario(intent.getNombreUsuario());
            pnl_customComponentEjercicio.setfechaIntento(intent.getTimestamp_Inicio());
            pnl_customComponentEjercicio.setIdEjercicio(intent.getId());
            int idreview = getIDReview(intent.getId());
            if (idreview == 0) {
                pnl_customComponentEjercicio.setestadoIntento(1);
            } else {
                pnl_customComponentEjercicio.setestadoIntento(2);
            }
            pnl_ejerciciosUsuario.add(pnl_customComponentEjercicio);
            pnl_customComponentEjercicio.setFocusable(true);
            componenteIntentos.add(pnl_customComponentEjercicio);
            pnl_customComponentEjercicio.addSwipeListener(new SwipeListener() {
                @Override
                public void arrastrar(String dirrecion, int idEjercicio) {
                    direccion = dirrecion;
                    idEjer = idEjercicio;
                    String videoFile = "";
                    if (direccion.equals("right")) {
                        Intent intentoUsuario = buscarIntentoUsuario(idEjer);
                        if (intentoUsuario != null) {
                            videoFile = intentoUsuario.getVideofile();
                        }
                        main.reproducirVideo(videoFile);
                        dispose();
                    }
                    if (direccion.equals("left")) {
                        btn_RevisarReview.setEnabled(true);
                        btn_EliminarReview.setEnabled(true);
                        btn_EditarReview.setEnabled(true);
                    }
                    SeleccionarComponente(idEjer);
                }
            });

        }

    }

    public void SeleccionarComponente(int idEjer) {

        for (com.mycompany.customcomponentejercicio.CustomComponentEjercicio componente : componenteIntentos) {

            if (componente.getIdEjercicio() == idEjer && direccion.equals("left")) {
                componente.cambiarColor(verdePastel);
            } else {
                componente.cambiarColor(Color.lightGray);
            }
        }
    }

    private void IniciarRevisionReview() {
        RevisionReview revisionReview = new RevisionReview(this, true);
        revisionReview.setVisible(true);
    }

    private void noReview() {
        DialogoNoExisteReview dialogoNoExisteReview = new DialogoNoExisteReview(this,true);
        dialogoNoExisteReview.setVisible(true);
    }

    public void ActualizarIntentosUsuario() {
        pnl_ejerciciosUsuario.removeAll();
        conseguirIntentosUsuario();
        dispose();
    }

    public Intent buscarIntentoUsuario(int idEjer) {
        for (Intent intento : intentosUsuario) {
            int id = intento.getId();
            if (id == idEjer) {
                return intento;
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Principal = new javax.swing.JPanel();
        btn_EditarReview = new javax.swing.JButton();
        btn_EliminarReview = new javax.swing.JButton();
        lbl_idEnunciado = new javax.swing.JLabel();
        lbl_verVideo = new javax.swing.JLabel();
        lbl_NombreEnunciado = new javax.swing.JLabel();
        lbl_activarBotones = new javax.swing.JLabel();
        lbl_EmailEnunciado = new javax.swing.JLabel();
        lbl_IDRespuesta = new javax.swing.JLabel();
        lbl_NombreRespuesta = new javax.swing.JLabel();
        lbl_EmailRespuesta = new javax.swing.JLabel();
        btn_RevisarReview = new javax.swing.JButton();
        lbl_iconoDeslizar = new javax.swing.JLabel();
        lbl_iconoClick = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnl_ejerciciosUsuario = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informacion del Usuario");
        setResizable(false);
        setSize(new java.awt.Dimension(476, 571));

        pnl_Principal.setBackground(new java.awt.Color(51, 51, 51));
        pnl_Principal.setPreferredSize(new java.awt.Dimension(476, 571));
        pnl_Principal.setLayout(null);

        btn_EditarReview.setText("Editar Review");
        btn_EditarReview.setToolTipText("");
        btn_EditarReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_EditarReviewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_EditarReviewMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_EditarReviewMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_EditarReviewMouseReleased(evt);
            }
        });
        btn_EditarReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarReviewActionPerformed(evt);
            }
        });
        pnl_Principal.add(btn_EditarReview);
        btn_EditarReview.setBounds(180, 470, 120, 23);

        btn_EliminarReview.setText("Eliminar Review");
        btn_EliminarReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_EliminarReviewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_EliminarReviewMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_EliminarReviewMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_EliminarReviewMouseReleased(evt);
            }
        });
        btn_EliminarReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarReviewActionPerformed(evt);
            }
        });
        pnl_Principal.add(btn_EliminarReview);
        btn_EliminarReview.setBounds(30, 470, 130, 23);

        lbl_idEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_idEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_idEnunciado.setText("ID:");
        pnl_Principal.add(lbl_idEnunciado);
        lbl_idEnunciado.setBounds(40, 60, 100, 20);

        lbl_verVideo.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_verVideo.setForeground(new java.awt.Color(255, 255, 204));
        lbl_verVideo.setText("Ver Video ");
        pnl_Principal.add(lbl_verVideo);
        lbl_verVideo.setBounds(290, 520, 70, 17);

        lbl_NombreEnunciado.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 18)); // NOI18N
        lbl_NombreEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NombreEnunciado.setText("INFORMACION DEL USUARIO:");
        pnl_Principal.add(lbl_NombreEnunciado);
        lbl_NombreEnunciado.setBounds(40, 20, 280, 20);

        lbl_activarBotones.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_activarBotones.setForeground(new java.awt.Color(204, 255, 204));
        lbl_activarBotones.setText("Activar botones");
        pnl_Principal.add(lbl_activarBotones);
        lbl_activarBotones.setBounds(30, 520, 100, 17);

        lbl_EmailEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_EmailEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_EmailEnunciado.setText("Email:");
        pnl_Principal.add(lbl_EmailEnunciado);
        lbl_EmailEnunciado.setBounds(40, 100, 70, 20);

        lbl_IDRespuesta.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_IDRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDRespuesta.setText("jLabel5");
        pnl_Principal.add(lbl_IDRespuesta);
        lbl_IDRespuesta.setBounds(110, 60, 140, 20);

        lbl_NombreRespuesta.setFont(new java.awt.Font("Visitor TT1 BRK", 0, 18)); // NOI18N
        lbl_NombreRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NombreRespuesta.setText("jLabel6");
        pnl_Principal.add(lbl_NombreRespuesta);
        lbl_NombreRespuesta.setBounds(320, 20, 140, 20);

        lbl_EmailRespuesta.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_EmailRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_EmailRespuesta.setText("jLabel7");
        pnl_Principal.add(lbl_EmailRespuesta);
        lbl_EmailRespuesta.setBounds(110, 100, 210, 20);

        btn_RevisarReview.setText("Revisar Review");
        btn_RevisarReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_RevisarReviewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_RevisarReviewMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_RevisarReviewMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_RevisarReviewMouseReleased(evt);
            }
        });
        btn_RevisarReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RevisarReviewActionPerformed(evt);
            }
        });
        pnl_Principal.add(btn_RevisarReview);
        btn_RevisarReview.setBounds(317, 470, 130, 23);

        lbl_iconoDeslizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/deslizar.png"))); // NOI18N
        lbl_iconoDeslizar.setText("jLabel1");
        pnl_Principal.add(lbl_iconoDeslizar);
        lbl_iconoDeslizar.setBounds(360, 500, 50, 50);

        lbl_iconoClick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/click.png"))); // NOI18N
        lbl_iconoClick.setText("jLabel2");
        pnl_Principal.add(lbl_iconoClick);
        lbl_iconoClick.setBounds(130, 500, 40, 50);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);

        pnl_ejerciciosUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_ejerciciosUsuario.setPreferredSize(new java.awt.Dimension(380, 316));
        pnl_ejerciciosUsuario.setLayout(new javax.swing.BoxLayout(pnl_ejerciciosUsuario, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(pnl_ejerciciosUsuario);

        pnl_Principal.add(jScrollPane1);
        jScrollPane1.setBounds(40, 150, 400, 290);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btn_RevisarReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RevisarReviewActionPerformed
        Intent intentoUsuario = buscarIntentoUsuario(idEjer);
        int id = intentoUsuario.getId();
        if (getIDReview(id) != 0) {
            IniciarRevisionReview();
        } else {
            noReview();
        }
    }//GEN-LAST:event_btn_RevisarReviewActionPerformed

    private void btn_EditarReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarReviewActionPerformed
        Intent intentoUsuario = buscarIntentoUsuario(idEjer);
        int id = intentoUsuario.getId();
        if (getIDReview(id) != 0) {
            RevalorarIntento revalorarIntento = new RevalorarIntento(this, true);
            revalorarIntento.setVisible(true);
        } else {
            DialogoNoExisteReview dialogoNoExisteReview = new DialogoNoExisteReview(this,true);
            dialogoNoExisteReview.setVisible(true);
        }
    }//GEN-LAST:event_btn_EditarReviewActionPerformed

    private void btn_EliminarReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarReviewActionPerformed
        Intent intentoUsuario = buscarIntentoUsuario(idEjer);
        int id = intentoUsuario.getId();
        Review eliminarReview = da.getAttemptReview(id);
        if (eliminarReview.getId() != 0){
        da.dropReview(eliminarReview.getId());
        ActualizarIntentosUsuario();
        main.ActualizarCambiosIntentosPendientes();
            DialogoEliminacionReviewCompletada eliminacionReviewCompletada = new DialogoEliminacionReviewCompletada(this,true);
            eliminacionReviewCompletada.setVisible(true);
        }else{
            DialogoNoHayReviewQueEliminar dialogoNoHayReviewQueEliminar = new DialogoNoHayReviewQueEliminar(this, true);
            dialogoNoHayReviewQueEliminar.setVisible(true);
        }
        
        

    }//GEN-LAST:event_btn_EliminarReviewActionPerformed

    private void btn_EliminarReviewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarReviewMouseEntered
        btn_EliminarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EliminarReviewMouseEntered

    private void btn_EliminarReviewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarReviewMousePressed
        btn_EliminarReview.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_EliminarReviewMousePressed

    private void btn_EliminarReviewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarReviewMouseReleased
        btn_EliminarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EliminarReviewMouseReleased

    private void btn_EliminarReviewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarReviewMouseExited
        btn_EliminarReview.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_EliminarReviewMouseExited

    private void btn_EditarReviewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EditarReviewMouseEntered
        btn_EditarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EditarReviewMouseEntered

    private void btn_EditarReviewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EditarReviewMouseExited
        btn_EditarReview.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_EditarReviewMouseExited

    private void btn_EditarReviewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EditarReviewMousePressed
        btn_EditarReview.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_EditarReviewMousePressed

    private void btn_EditarReviewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EditarReviewMouseReleased
        btn_EditarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EditarReviewMouseReleased

    private void btn_RevisarReviewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RevisarReviewMouseEntered
        btn_RevisarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_RevisarReviewMouseEntered

    private void btn_RevisarReviewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RevisarReviewMouseExited
        btn_RevisarReview.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_RevisarReviewMouseExited

    private void btn_RevisarReviewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RevisarReviewMousePressed
        btn_RevisarReview.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_RevisarReviewMousePressed

    private void btn_RevisarReviewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RevisarReviewMouseReleased
        btn_RevisarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_RevisarReviewMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_EditarReview;
    private javax.swing.JButton btn_EliminarReview;
    private javax.swing.JButton btn_RevisarReview;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_EmailEnunciado;
    private javax.swing.JLabel lbl_EmailRespuesta;
    private javax.swing.JLabel lbl_IDRespuesta;
    private javax.swing.JLabel lbl_NombreEnunciado;
    private javax.swing.JLabel lbl_NombreRespuesta;
    private javax.swing.JLabel lbl_activarBotones;
    private javax.swing.JLabel lbl_iconoClick;
    private javax.swing.JLabel lbl_iconoDeslizar;
    private javax.swing.JLabel lbl_idEnunciado;
    private javax.swing.JLabel lbl_verVideo;
    private javax.swing.JPanel pnl_Principal;
    private javax.swing.JPanel pnl_ejerciciosUsuario;
    // End of variables declaration//GEN-END:variables
}
