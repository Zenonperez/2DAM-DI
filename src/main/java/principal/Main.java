/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package principal;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.mycompany.customcomponentejercicio.SwipeListener;
import data.DataAccess;
import dto.Intent;
import dto.Usuari;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import principal.tablemodels.UsuariosTableModel;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author Ziku
 */
public class Main extends javax.swing.JFrame {

    private String textoOriginal;
    private int indiceActual;
    private Usuari user;
    private DataAccess da = new DataAccess();
    private InformacionIntento info;
    private EmbeddedMediaPlayerComponent mediaPlayer;
    private boolean isPlaying;
    private String video = "void";
    private String videoFilePath;
    private Login login;
    private ArrayList<Intent> intentos = new ArrayList<>();
    private int idEjer;
    private String direccion;

    public Main() {

        login = new Login(this, true, user, false);
        login.setVisible(true);
        if (login.EstasConectado()) {
            initComponents();

        } else {
            System.exit(0);
        }
        textoOriginal = albl_animado.getText();
        indiceActual = 0;
        btn_ReproducirPausar.setEnabled(false);
        btn_SeleccionarUsuarios.setEnabled(false);
        btn_InformaciónIntentos.setEnabled(false);
        btn_Valorar.setEnabled(false);
        lbl_UsuarioConectado.setText(user.getNombre());
        leerIntentos();
//        tbl_IntentosPendientes.setModel(new IntentosPendientesTableModel(da.getAttemptsPendingReview()));
        tbl_Usuarios.setModel(new UsuariosTableModel(da.getAllUsers()));
        mediaPlayer = new EmbeddedMediaPlayerComponent();
        pnl_ReproductorVideos.add(mediaPlayer, BorderLayout.CENTER);
//        pnl_customComponentEjercicio.setFocusable(true);
//        pnl_customComponentEjercicio.addSwipeListener(new SwipeListener() {
//            @Override
//            public void arrastrar(String dirrecion, String idEjercicio) {
//            System.out.println(dirrecion);
//            System.out.println(idEjercicio);
//            }
//        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                lbl_UsuarioConectado.setText(user.getNombre());
            }

        });
        Timer animacion = new Timer(albl_animado.getDelay(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animarLabel();
            }
        });

        if (albl_animado.isAnimated()) {
            animacion.start();
        } else {
            animacion.stop();
            albl_animado.setText(textoOriginal);
        }

    }

    private void leerIntentos() {
        intentos = da.getAttemptsPendingReview();
        for (Intent intent : intentos) {
            com.mycompany.customcomponentejercicio.CustomComponentEjercicio pnl_customComponentEjercicio = new com.mycompany.customcomponentejercicio.CustomComponentEjercicio();
            pnl_customComponentEjercicio.setnombreEjercicio(intent.getNombreEjercicio());
            pnl_customComponentEjercicio.setnombreUsuario(intent.getNombreUsuario());
            pnl_customComponentEjercicio.setfechaIntento(intent.getTimestamp_Inicio());
            pnl_customComponentEjercicio.setIdEjercicio(intent.getIdEjercicio());
            pnl_customComponentEjercicio.setestadoIntento(1);
            pnl_IntentosPendientes.add(pnl_customComponentEjercicio);
            pnl_customComponentEjercicio.setFocusable(true);
            pnl_customComponentEjercicio.addSwipeListener(new SwipeListener() {
                @Override
                public void arrastrar(String dirrecion, int idEjercicio) {
                    direccion = dirrecion;
                    idEjer = idEjercicio;
                    if (direccion.equals("right")) {
                        Intent intentoPendiente = buscarIntento(idEjer);
                        if (intentoPendiente != null) {
                            video = intentoPendiente.getVideofile();
                        }
                        reproducirVideo(video);
                    }
                    if (direccion.equals("left")){
                        btn_InformaciónIntentos.setEnabled(true);
                        btn_Valorar.setEnabled(true);
                    }
                }
            });

        }

    }

    private void animarLabel() {
        String textoAnimado = textoOriginal + albl_animado.getAppendedText().substring(0, indiceActual);
        albl_animado.setText(textoAnimado);
        indiceActual++;
        repaint();

        if (indiceActual > albl_animado.getAppendedText().length()) {
            indiceActual = 0;
        }

    }

    public Intent SeleccionIntento(int idEjercicio) {
        for (Intent intent : intentos){
            if(intent.getIdEjercicio() == idEjercicio){
                return intent;
            }
           
        }
        return null;
    }
    public int SeleccionFilaUsuariosIntentos() {
        int seleccionado = tbl_Usuarios.convertRowIndexToModel(tbl_Usuarios.getSelectedRow());
        return seleccionado;
    }

    public void UsuarioConectado(Usuari user) {
        this.user = user;
    }
    
   

    public void reproducirVideo(String video) {
        this.video = video;
        String connectionAzureStr = "DefaultEndpointsProtocol=https;AccountName=divideo;AccountKey=xr35oLT/BkabDUAQWAXzPX/EDvgFbNm8ecTZfaiU/CVvI47fvx/P9GHtshVzFOsY5O4Q+YZSYG6e+AStSfMFeQ==;EndpointSuffix=core.windows.net";
        String containerName = "videos";
        BlobClient blobClient = new BlobClientBuilder().connectionString(connectionAzureStr)
                .blobName(video)
                .containerName(containerName)
                .buildClient();
        String tempDir = System.getProperty("java.io.tmpdir");
        String downloadPath = tempDir + File.separator + video;
        File existe = new File(downloadPath);
        if (!existe.exists()) {
            blobClient.downloadToFile(downloadPath);
        }
        mediaPlayer.mediaPlayer().media().play(downloadPath);
        isPlaying = true;
        btn_ReproducirPausar.setEnabled(true);
        btn_ReproducirPausar.setText("Pausar");

    }

    public int getIDIntento() {
        for (Intent intento: intentos){
            if (intento.getIdEjercicio()== idEjer){
                return intento.getId();
            }
        }
        return 0;
    }
    public Intent buscarIntento(int idEjer) {
        for (Intent intento : intentos) {
            if (intento.getIdEjercicio() == idEjer) {
                return intento;
            }
        }
        return null;
    }

    public int getIDUsuario() {
        return user.getId();
    }

    public void ActualizarCambiosIntentosPendientes() {
        pnl_IntentosPendientes.removeAll();
        leerIntentos();
        
        
    }
    public void desconectarse() {
        setVisible(false);
        lbl_UsuarioConectado.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_cerrarSesion = new javax.swing.JButton();
        pnl_ReproductorVideos = new javax.swing.JPanel();
        btn_ReproducirPausar = new javax.swing.JButton();
        lbl_Usuario = new javax.swing.JLabel();
        tab_IntentosUsuarios = new javax.swing.JTabbedPane();
        pnl_GestorIntentosPedientes = new javax.swing.JPanel();
        btn_InformaciónIntentos = new javax.swing.JButton();
        lbl_verVideo = new javax.swing.JLabel();
        pnl_IntentosPendientes = new javax.swing.JPanel();
        btn_Valorar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnl_Usuarios = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_Usuarios = new javax.swing.JTable();
        btn_SeleccionarUsuarios = new javax.swing.JButton();
        lbl_UsuarioConectado = new javax.swing.JLabel();
        albl_animado = new com.mycompany.animatedlabel.AnimatedLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Fitnow");
        setResizable(false);
        setSize(new java.awt.Dimension(660, 541));

        btn_cerrarSesion.setText("Cerrar Sesion");
        btn_cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarSesionActionPerformed(evt);
            }
        });

        pnl_ReproductorVideos.setBorder(javax.swing.BorderFactory.createTitledBorder("Video Reproductor"));
        pnl_ReproductorVideos.setLayout(new java.awt.BorderLayout());

        btn_ReproducirPausar.setText("Reproducir");
        btn_ReproducirPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReproducirPausarActionPerformed(evt);
            }
        });

        lbl_Usuario.setText("Usuario:");

        tab_IntentosUsuarios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        pnl_GestorIntentosPedientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_GestorIntentosPedientes.setToolTipText("");

        btn_InformaciónIntentos.setText("Info");
        btn_InformaciónIntentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InformaciónIntentosActionPerformed(evt);
            }
        });

        lbl_verVideo.setText("Ver video (Desliza)-->");

        pnl_IntentosPendientes.setLayout(new javax.swing.BoxLayout(pnl_IntentosPendientes, javax.swing.BoxLayout.PAGE_AXIS));

        btn_Valorar.setText("Valorar");
        btn_Valorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ValorarActionPerformed(evt);
            }
        });

        jLabel1.setText("<--(Desliza) Activar Botones");

        javax.swing.GroupLayout pnl_GestorIntentosPedientesLayout = new javax.swing.GroupLayout(pnl_GestorIntentosPedientes);
        pnl_GestorIntentosPedientes.setLayout(pnl_GestorIntentosPedientesLayout);
        pnl_GestorIntentosPedientesLayout.setHorizontalGroup(
            pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_IntentosPendientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(lbl_verVideo))
                    .addGroup(pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addComponent(btn_Valorar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_InformaciónIntentos)))
                .addGap(24, 24, 24))
        );
        pnl_GestorIntentosPedientesLayout.setVerticalGroup(
            pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                .addComponent(pnl_IntentosPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_InformaciónIntentos)
                    .addComponent(btn_Valorar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_verVideo)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        tab_IntentosUsuarios.addTab("Intentos Pendientes", pnl_GestorIntentosPedientes);

        pnl_Usuarios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbl_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tbl_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_UsuariossMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_Usuarios);

        btn_SeleccionarUsuarios.setText("Seleccionar");
        btn_SeleccionarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeleccionarUsuariossActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_UsuariosLayout = new javax.swing.GroupLayout(pnl_Usuarios);
        pnl_Usuarios.setLayout(pnl_UsuariosLayout);
        pnl_UsuariosLayout.setHorizontalGroup(
            pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnl_UsuariosLayout.createSequentialGroup()
                        .addGap(0, 249, Short.MAX_VALUE)
                        .addComponent(btn_SeleccionarUsuarios)))
                .addGap(15, 15, 15))
        );
        pnl_UsuariosLayout.setVerticalGroup(
            pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_SeleccionarUsuarios)
                .addGap(16, 16, 16))
        );

        tab_IntentosUsuarios.addTab("Gestion de usuarios", pnl_Usuarios);

        lbl_UsuarioConectado.setText("jLabel1");

        albl_animado.setAnimated(true);
        albl_animado.setAppendedText("ienvenido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Usuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_UsuarioConectado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(albl_animado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183)
                        .addComponent(btn_cerrarSesion)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tab_IntentosUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnl_ReproductorVideos, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_ReproducirPausar)
                                .addGap(96, 96, 96))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cerrarSesion)
                            .addComponent(lbl_Usuario)
                            .addComponent(lbl_UsuarioConectado)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(albl_animado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(pnl_ReproductorVideos, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ReproducirPausar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tab_IntentosUsuarios)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarSesionActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "Estas seguro que quieres cerrar la sesion?", "Cerrar Sesion", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            desconectarse();
            Login loginNuevo = new Login(this, true, null, true);
            loginNuevo.setVisible(true);
        }
        if (respuesta == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_btn_cerrarSesionActionPerformed

    private void btn_ReproducirPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarActionPerformed
        if (isPlaying) {
            mediaPlayer.mediaPlayer().controls().pause();
            isPlaying = false;
            btn_ReproducirPausar.setText("Reproducir");
        } else {
            mediaPlayer.mediaPlayer().controls().start();
            isPlaying = true;
            btn_ReproducirPausar.setText("Pausar");
        }

    }//GEN-LAST:event_btn_ReproducirPausarActionPerformed

    private void btn_SeleccionarUsuariossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariossActionPerformed
        InformacionUsuario informacionUsuario = new InformacionUsuario(this, true);
        informacionUsuario.setVisible(true);
    }//GEN-LAST:event_btn_SeleccionarUsuariossActionPerformed

    private void tbl_UsuariossMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_UsuariossMouseClicked
        if (tbl_Usuarios.convertRowIndexToModel(tbl_Usuarios.getSelectedRow()) != -1) {
            btn_SeleccionarUsuarios.setEnabled(true);
        }
    }//GEN-LAST:event_tbl_UsuariossMouseClicked

    private void btn_InformaciónIntentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosActionPerformed
        InformacionIntento informacionIntento = new InformacionIntento(this, true,idEjer);
        informacionIntento.setVisible(true);

    }//GEN-LAST:event_btn_InformaciónIntentosActionPerformed

    private void btn_ValorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ValorarActionPerformed
       ValoracionIntentoSinReview valoracionIntentosSinReview = new ValoracionIntentoSinReview(this, true);
       valoracionIntentosSinReview.setVisible(true);
    }//GEN-LAST:event_btn_ValorarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.animatedlabel.AnimatedLabel albl_animado;
    private javax.swing.JButton btn_InformaciónIntentos;
    private javax.swing.JButton btn_ReproducirPausar;
    private javax.swing.JButton btn_SeleccionarUsuarios;
    private javax.swing.JButton btn_Valorar;
    private javax.swing.JButton btn_cerrarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_Usuario;
    private javax.swing.JLabel lbl_UsuarioConectado;
    private javax.swing.JLabel lbl_verVideo;
    private javax.swing.JPanel pnl_GestorIntentosPedientes;
    private javax.swing.JPanel pnl_IntentosPendientes;
    private javax.swing.JPanel pnl_ReproductorVideos;
    private javax.swing.JPanel pnl_Usuarios;
    private javax.swing.JTabbedPane tab_IntentosUsuarios;
    private javax.swing.JTable tbl_Usuarios;
    // End of variables declaration//GEN-END:variables
}
