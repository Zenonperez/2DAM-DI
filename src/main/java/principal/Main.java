/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package principal;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
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
import principal.tablemodels.IntentosPendientesTableModel;
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
    
    public Main() {
        
        login = new Login(this, true, user,false);
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
        btn_VerVideo.setEnabled(false);
        btn_Valorar.setEnabled(false);
        btn_InformaciónIntentos.setEnabled(false);
        lbl_UsuarioConectado.setText(user.getNombre());
        tbl_IntentosPendientes.setModel(new IntentosPendientesTableModel(da.getAttemptsPendingReview()));
        tbl_Usuarios.setModel(new UsuariosTableModel(da.getAllUsers()));
        mediaPlayer = new EmbeddedMediaPlayerComponent();
        pnl_ReproductorVideos.add(mediaPlayer, BorderLayout.CENTER);
        pnl_customComponentEjercicio.setFocusable(true);
        pnl_customComponentEjercicio.addSwipeListener(new SwipeListener() {
            @Override
            public void arrastrar(String dirrecion, String idEjercicio) {
            System.out.println(dirrecion);
            System.out.println(idEjercicio);
            }
        });
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
        
        if (albl_animado.isAnimated()){
            animacion.start();
        } else {
            animacion.stop();
            albl_animado.setText(textoOriginal);
        }
        
        
    }
    
    private void animarLabel(){
        String textoAnimado = textoOriginal + albl_animado.getAppendedText().substring(0,indiceActual);
        albl_animado.setText(textoAnimado);
        indiceActual++;
        repaint();
        
        
        if (indiceActual > albl_animado.getAppendedText().length()){
            indiceActual = 0;
        }
        
    }
    
    public int SeleccionFilaIntentosinfo() {
        int seleccionado = tbl_IntentosPendientes.convertRowIndexToModel(tbl_IntentosPendientes.getSelectedRow());
        return seleccionado;
    }
    
    public int SeleccionFilaUsuariosIntentos() {
        int seleccionado = tbl_Usuarios.convertRowIndexToModel(tbl_Usuarios.getSelectedRow());
        return seleccionado;
    }
    
    public void UsuarioConectado(Usuari user) {
        this.user = user;      
    }
    
    public void video(String video) {
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
        if (!existe.exists()){
        blobClient.downloadToFile(downloadPath);
        }
        mediaPlayer.mediaPlayer().media().play(downloadPath);
        isPlaying = true;
        btn_ReproducirPausar.setEnabled(true);
        btn_ReproducirPausar.setText("Pausar");
        
    }
    
    public int getIDIntento() {
        int seleccionado = tbl_IntentosPendientes.convertRowIndexToModel(tbl_IntentosPendientes.getSelectedRow());
        int idIntento = (Integer) tbl_IntentosPendientes.getValueAt(seleccionado, 0);
        return idIntento;
    }
    
    public int getIDUsuario() {
        return user.getId();
    }
    
    public void ActualizarCambiosTablaIntentosPendientes() {
        tbl_IntentosPendientes.setModel(new IntentosPendientesTableModel(da.getAttemptsPendingReview()));
    }
    
    
    public void desconectarse(){
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
        pnl_IntentosPendientesRevision = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_IntentosPendientes = new javax.swing.JTable();
        btn_InformaciónIntentos = new javax.swing.JButton();
        btn_VerVideo = new javax.swing.JButton();
        btn_Valorar = new javax.swing.JButton();
        pnl_Usuarios = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Usuarios = new javax.swing.JTable();
        btn_SeleccionarUsuarios = new javax.swing.JButton();
        pnl_ReproductorVideos = new javax.swing.JPanel();
        btn_ReproducirPausar = new javax.swing.JButton();
        lbl_Usuario = new javax.swing.JLabel();
        lbl_UsuarioConectado = new javax.swing.JLabel();
        albl_animado = new AnimatedLabel.AnimatedLabel();
        pnl_customComponentEjercicio = new com.mycompany.customcomponentejercicio.CustomComponentEjercicio();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Fitnow");
        setResizable(false);
        setSize(new java.awt.Dimension(634, 541));

        btn_cerrarSesion.setText("Cerrar Sesion");
        btn_cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarSesionActionPerformed(evt);
            }
        });

        pnl_IntentosPendientesRevision.setBorder(javax.swing.BorderFactory.createTitledBorder("Intentos"));

        tbl_IntentosPendientes.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        tbl_IntentosPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "IDUsuario", "NomEjercicio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_IntentosPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_IntentosPendientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_IntentosPendientes);

        btn_InformaciónIntentos.setText("Info");
        btn_InformaciónIntentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InformaciónIntentosActionPerformed(evt);
            }
        });

        btn_VerVideo.setText("Ver Video");
        btn_VerVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VerVideoActionPerformed(evt);
            }
        });

        btn_Valorar.setText("Valorar");
        btn_Valorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ValorarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_IntentosPendientesRevisionLayout = new javax.swing.GroupLayout(pnl_IntentosPendientesRevision);
        pnl_IntentosPendientesRevision.setLayout(pnl_IntentosPendientesRevisionLayout);
        pnl_IntentosPendientesRevisionLayout.setHorizontalGroup(
            pnl_IntentosPendientesRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_IntentosPendientesRevisionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_IntentosPendientesRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnl_IntentosPendientesRevisionLayout.createSequentialGroup()
                        .addComponent(btn_Valorar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_VerVideo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_InformaciónIntentos))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(351, 351, 351))
        );
        pnl_IntentosPendientesRevisionLayout.setVerticalGroup(
            pnl_IntentosPendientesRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_IntentosPendientesRevisionLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnl_IntentosPendientesRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_InformaciónIntentos)
                    .addComponent(btn_VerVideo)
                    .addComponent(btn_Valorar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_Usuarios.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios"));

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
                tbl_UsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Usuarios);

        btn_SeleccionarUsuarios.setText("Seleccionar");
        btn_SeleccionarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeleccionarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_UsuariosLayout = new javax.swing.GroupLayout(pnl_Usuarios);
        pnl_Usuarios.setLayout(pnl_UsuariosLayout);
        pnl_UsuariosLayout.setHorizontalGroup(
            pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SeleccionarUsuarios)
                .addContainerGap())
        );
        pnl_UsuariosLayout.setVerticalGroup(
            pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_SeleccionarUsuarios)
                .addContainerGap())
        );

        pnl_ReproductorVideos.setBorder(javax.swing.BorderFactory.createTitledBorder("Video Reproductor"));
        pnl_ReproductorVideos.setLayout(new java.awt.BorderLayout());

        btn_ReproducirPausar.setText("Reproducir");
        btn_ReproducirPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReproducirPausarActionPerformed(evt);
            }
        });

        lbl_Usuario.setText("Usuario:");

        lbl_UsuarioConectado.setText("jLabel1");

        albl_animado.setText("B");
        albl_animado.setAnimated(true);
        albl_animado.setAppendedText("ienvenido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Usuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_UsuarioConectado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(albl_animado, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(btn_cerrarSesion))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnl_IntentosPendientesRevision, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(pnl_Usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(btn_ReproducirPausar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(pnl_ReproductorVideos, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnl_customComponentEjercicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cerrarSesion)
                    .addComponent(lbl_Usuario)
                    .addComponent(lbl_UsuarioConectado)
                    .addComponent(albl_animado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl_IntentosPendientesRevision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_Usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pnl_ReproductorVideos, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(pnl_customComponentEjercicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ReproducirPausar)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarSesionActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "Estas seguro que quieres cerrar la sesion?", "Cerrar Sesion", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            desconectarse();
            Login loginNuevo = new Login(this, true, null,true);
            loginNuevo.setVisible(true);
        }
        if (respuesta == JOptionPane.NO_OPTION) {
            
        }
    }//GEN-LAST:event_btn_cerrarSesionActionPerformed

    private void btn_InformaciónIntentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosActionPerformed
        InformacionIntento informacionIntento = new InformacionIntento(this, true);
        informacionIntento.setVisible(true);
        
    }//GEN-LAST:event_btn_InformaciónIntentosActionPerformed

    private void btn_SeleccionarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariosActionPerformed
        InformacionUsuario informacionUsuario = new InformacionUsuario(this, true);
        informacionUsuario.setVisible(true);
    }//GEN-LAST:event_btn_SeleccionarUsuariosActionPerformed

    private void btn_VerVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VerVideoActionPerformed
        int filaSeleccionada = tbl_IntentosPendientes.convertRowIndexToModel(tbl_IntentosPendientes.getSelectedRow());
        video = (String) tbl_IntentosPendientes.getValueAt(filaSeleccionada, 3);
        videoFilePath = "C:\\Users\\Administrator\\AppData\\Local\\Proyecto_Entrenamiento\\videos\\" + video;
        mediaPlayer.mediaPlayer().media().play(videoFilePath);
        isPlaying = true;
        btn_ReproducirPausar.setEnabled(true);
        btn_ReproducirPausar.setText("Pausar");
    }//GEN-LAST:event_btn_VerVideoActionPerformed

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

    private void btn_ValorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ValorarActionPerformed
        ValoracionIntentoSinReview valoracionIntentosSinReview = new ValoracionIntentoSinReview(this, true);
        valoracionIntentosSinReview.setVisible(true);
    }//GEN-LAST:event_btn_ValorarActionPerformed

    private void tbl_IntentosPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_IntentosPendientesMouseClicked
        if (tbl_IntentosPendientes.convertRowIndexToModel(tbl_IntentosPendientes.getSelectedRow()) != -1) {
            btn_InformaciónIntentos.setEnabled(true);
            btn_Valorar.setEnabled(true);
            btn_VerVideo.setEnabled(true);
        }
    }//GEN-LAST:event_tbl_IntentosPendientesMouseClicked

    private void tbl_UsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_UsuariosMouseClicked
        if (tbl_Usuarios.convertRowIndexToModel(tbl_Usuarios.getSelectedRow()) != -1) {
            btn_SeleccionarUsuarios.setEnabled(true);
        }
    }//GEN-LAST:event_tbl_UsuariosMouseClicked

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
    private AnimatedLabel.AnimatedLabel albl_animado;
    private javax.swing.JButton btn_InformaciónIntentos;
    private javax.swing.JButton btn_ReproducirPausar;
    private javax.swing.JButton btn_SeleccionarUsuarios;
    private javax.swing.JButton btn_Valorar;
    private javax.swing.JButton btn_VerVideo;
    private javax.swing.JButton btn_cerrarSesion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Usuario;
    private javax.swing.JLabel lbl_UsuarioConectado;
    private javax.swing.JPanel pnl_IntentosPendientesRevision;
    private javax.swing.JPanel pnl_ReproductorVideos;
    private javax.swing.JPanel pnl_Usuarios;
    private com.mycompany.customcomponentejercicio.CustomComponentEjercicio pnl_customComponentEjercicio;
    private javax.swing.JTable tbl_IntentosPendientes;
    private javax.swing.JTable tbl_Usuarios;
    // End of variables declaration//GEN-END:variables
}
