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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import principal.JDialogos.DialogoCerrarSesion;
import principal.tablemodels.UsuariosTableModel;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 *Main sera la ventana y el menu principal de la aplicacion.
 * Es a su vez el JFrame que ejecuta la aplicacion donde primero cargara el login y si este es verificado correctamente cargara esta ventana.
 * @author Zenon Perez
 */
public class Main extends javax.swing.JFrame {
    //Declaramos las variabbles globales de la ventana
    
    /**
     * Usuario con el cual nos hemos conectado.
     */
    private Usuari user;
    /**
     * Variable para poder enviar o recibir datos de la base de datos.
     */
    private DataAccess da = new DataAccess();
    /**
     * Variable la cual nos permite reproducir videos en el programa. Y sus acciones como pausar entre otras.
     */
    private EmbeddedMediaPlayerComponent mediaPlayer;
    /**
     * Booleano el cual indica si un video se esta repoduciendo o no.
     */
    private boolean isPlaying;
    /**
     * String del video que se esta reproducira. Ahora tiene el valor void pero luego tendra otro 
     */
    private String video = "void";
    /**
     * Varaible que representa la ventana de login la cual usaremos algunos de sus metodos aqui. 
     */
    private Login login;
    /**
     * Lista que usaremos para guardar todos los intentos pendientes de review.
     */
    private ArrayList<Intent> intentos = new ArrayList<>();
    /**
     * Lista la cual usaremos para guardar todos los componentes personalizados de lso intentos pendientes.
     */
    private ArrayList<com.mycompany.customcomponentejercicio.CustomComponentEjercicio> componenteIntentos = new ArrayList<>();
    /**
     * Variable para guardar la id de un intento pendiente al interactuar con su componente.
     */
    private int idIntento;
    /**
     * Variable que guardara la accion que hemos realizado sobre un componente personalizado y determinara si reproducir el video o si realizar las acciones de los botones.
     */
    private String direccion;
    /**
     * Color que usaremos para seleccionar un componente.
     */
    private Color verdePastel = new Color(205,255,205);
    /**
     * Color que usaremos cuando el cursor pase por encima de un boton o componente.
     */
    private Color azulPastel = new Color(173,216,230);
    /**
     * Color que usaremos para decir que un componente esta reproduciendo el video del intento al cual representa.
     */
    private Color amarilloPastel = new Color(255,255,205);
   
    /**
     * Aqui se crear el form de Main por primera y unica vez.
     */
    public Main() {
        //Antes del main primero creamos el Login donde al ser modal primero se ha de resolver para crear el main.
        login = new Login(this, true, user, false);
        login.setVisible(true);
        //Si login es resuelto y se verifica todo correctamente se creara e iniciara el main.
        if (login.EstasConectado()) {
            initComponents();
        //De lo contrario se cerrara la aplicacion.
        } else {
            System.exit(0);
        }
        //Se les pone color a los botones
        btn_Valorar.setBackground(Color.WHITE);
        btn_InformaciónIntentos.setBackground(Color.WHITE);
        btn_cerrarSesion.setBackground(Color.WHITE);
        //Se le pone color a la tabla de usuarios
        tbl_Usuarios.setSelectionForeground(Color.BLACK);
        tbl_Usuarios.setSelectionBackground(verdePastel);
        //Se seleciona el "logoIcono" como icono de aplicacion.
        Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/logoIcono.jpg"));
        setIconImage(icono);
        //Se desactivan los botones
        btn_ReproducirPausar.setEnabled(false);
        btn_SeleccionarUsuarios.setEnabled(false);
        btn_InformaciónIntentos.setEnabled(false);
        btn_Valorar.setEnabled(false);
        //Se le pone el nombre del usario conectado
        lbl_UsuarioConectado.setText(user.getNombre());
        //Se cargan los intentos pendientes
        leerIntentos();
        //Se cargan los usuarios en la tabla.
        tbl_Usuarios.setModel(new UsuariosTableModel(da.getAllUsers()));
        //Se prepara la funcionalidad del video
        mediaPlayer = new EmbeddedMediaPlayerComponent();
        pnl_ReproductorVideos.add(mediaPlayer, BorderLayout.CENTER);
        //Se establece el tamanyo minimo de la aplicacion
        setMinimumSize(getPreferredSize());
        this.addComponentListener(new ComponentAdapter(){
            /**
             * Metodo para que si si el tamanyo es menor al tamanyo minimo se ponga de nuevo el tamanyo minimo.
             * Esto es para que no se puede hacer más pequenya la aplicacion de lo que se puede.
             */
            @Override
            public void componentResized(ComponentEvent e){
            Dimension tamanoActual = getSize();
            Dimension tamanoPreferido = getPreferredSize();
            
            
            if (tamanoActual.width < tamanoPreferido.width || tamanoActual.height < tamanoPreferido.height){
                setSize(tamanoPreferido);
                }
                }
        });
        
        
        this.addComponentListener(new ComponentAdapter() {
            /**
             * Metodo para que en caso de cerrar la aplicacion e iniciar con otro usuario cambie el texto de "lbl_UusarioConectado".
             */
            @Override
            public void componentShown(ComponentEvent e) {
                lbl_UsuarioConectado.setText(user.getNombre());
            }
        });
    }
    
    /**
     * Metodo que carga los intentos pendientes de review como componentes `personalizados.
     * Al interacturar con uno de estos componentes podremos o ver el video de este intento, sus datos o poner una review de este.
     */
    private void leerIntentos() {
        //Primero se busca los intentos pendientes de review en la base de datos y los guarda dentro de una lista.
        intentos = da.getAttemptsPendingReview();
        //Por cada intento se crea un componente personalizado de este y se guardara en una lista de componentes personalizados con los datos de dicho intento. 
        for (Intent intent : intentos) {
            com.mycompany.customcomponentejercicio.CustomComponentEjercicio pnl_customComponentEjercicio = new com.mycompany.customcomponentejercicio.CustomComponentEjercicio();
            pnl_customComponentEjercicio.setnombreEjercicio(intent.getNombreEjercicio());
            pnl_customComponentEjercicio.setnombreUsuario(intent.getNombreUsuario());
            pnl_customComponentEjercicio.setfechaIntento(intent.getTimestamp_Inicio());
            pnl_customComponentEjercicio.setIdEjercicio(intent.getId());
            pnl_customComponentEjercicio.setestadoIntento(1);
            pnl_IntentosPendientes.add(pnl_customComponentEjercicio);
            pnl_customComponentEjercicio.setFocusable(true);
            //Ahora se establecera el listener de cada componente personalizado
            pnl_customComponentEjercicio.addSwipeListener(new SwipeListener() {
                /**
                 * Metodo que al arrastrar el componente a la derecha nos permitira ver el video y al hacerlo a la izquierda o hacer click podremos hacer otras gestiones como ver la informacion del intento o ponerle una review a este intento.
                 * @param dirrecion parametro que determinara las acciones del componente si se arrastra a la derecha se podra ver el video del intento, si hace click o se mueve a la izquierda se activaran los botones y se podran realizar diferentes gestiones
                 * @param IdIntento determina el intento que esta relacionado con el componente para poder ver su video o su info al interactuar con este. 
                 */
                @Override
                public void arrastrar(String dirrecion, int IdIntento) {
                    direccion = dirrecion;
                    idIntento = IdIntento;
                    //Si se arrastra a la derecha se reproducira el video del intento que represente el componente arrastrado
                    if (direccion.equals("right")) {
                        Intent intentoPendiente = buscarIntento(idIntento);
                        //Si el intento no es nulo se reproducira el video
                        if (intentoPendiente != null) {
                            video = intentoPendiente.getVideofile();
                        }
                        reproducirVideo(video);
                    }
                    //Si se hace click o arrastra a la derecha se activaran los botones, seleccionara al componente y se podra mirar la informacion del intento o valorar este.
                    if (direccion.equals("left")){
                        btn_InformaciónIntentos.setEnabled(true);
                        btn_Valorar.setEnabled(true);
                    }
                    SeleccionarComponente(idIntento);
                }
            });
            //Finalmente se agregara el componente a la lista de componentes personalizados
            componenteIntentos.add(pnl_customComponentEjercicio);
        }

    }
    
    
    /**
     * Metodo que utiliza la lista de componentes con intentos pendientes de review para selecionar el componente que contiene el intento relacionado con la id del intento para cambiar el color del componente dependiendo lo que se haga con este.
     * Sirve para aplicar usabilidad al componente. Si se arrastra a la erecha se pondra amarillo, si se arrastra al reves o se hace click se pondra de color verde.
     * Al arrastrar otro componente para ver el video o para selecionarlo el anterior se pondra gris y lo substituira el que se seleccione.
     * çPuede haber un componente amarillo y uno verde, esto da a entender que un se esta reproduciendo el video y otro es seleccionado para realziar gestiones.
     * No puede haber dos componentes seleccionados simultaneamente tanto para realizar gestiones o ver video.
     * @param idIntento determina cual es el componente que cambia de color al se arrastrado o al hacer click.
     */
    public void SeleccionarComponente(int idIntento){
        //Primero busca al componente que coincida con idIntento en la lista de componetes.
        for (com.mycompany.customcomponentejercicio.CustomComponentEjercicio componente : componenteIntentos){
            //Los componentes tiene una propiedad llamada Intentoselecionado si esta en false se pondra gris si no se matendra el color
            if(direccion.equals("right")){
                componente.setIntentoSeleccionado(false);
                componente.cambiarColor(Color.LIGHT_GRAY);
            }
                      
            //Si el componente coicide con la id del intento y es arrastrado a la derecha cambiara el color a amarillo.
            if (componente.getIdEjercicio() == idIntento && direccion.equals("right")){
                componente.cambiarColor(amarilloPastel);
                componente.setIntentoSeleccionado(true);
                
            }
          
            //Si el componente coicide con la id del intento y se le hace click o se arrastra a la izquierda se cambiara de color verde.
            if (componente.getIdEjercicio() == idIntento && direccion.equals("left")){ 
                componente.cambiarColor(verdePastel);
                //Si el componente del cual estamos viendo el video se hace click o se arrastra a la izquierda mantendra su color amarillo pese a estar seleccionado y se activaran los botones.
                if (componente.getIntentoSeleccionado()){
                   componente.mantenerColor(amarilloPastel);
                }
               
                
            }
            //Si el componente no es selecionado cambiara a su color por defecto
            else{
                if (!componente.getIntentoSeleccionado()){
                componente.cambiarColor(Color.lightGray);
                componente.setIntentoSeleccionado(false);
                }
          
                       
            }
        }
        
    }

    /**
     * Metodo que buscara en la lista de intentos el intento que coincida con la idIntento.
     * @param idIntento es el parametro que determina que intento hay que encontrar
     * @return devuelve el intento que coincida con la id del Intento, de lo contrario devolvera null.
     */
    public Intent SeleccionIntento(int idIntento) {
        for (Intent intent : intentos){
            if(intent.getId() == idIntento){
                return intent;
            }
           
        }
        return null;
    }
    /**
     * Metodo que sirve para poder conseguir el numero del indice de filas que ha sido seleccionado de la tabla usuarios
     * @return devuelve el numero de la fila selecionado en la tabla usuarios
     */
    public int SeleccionFilaUsuariosIntentos() {
        int seleccionado = tbl_Usuarios.convertRowIndexToModel(tbl_Usuarios.getSelectedRow());
        return seleccionado;
    }

    /**
     * Metodo que se llama desde login es un setter de usuario que servira para poner el usuario que se ha conectado.
     * @param user el usuario que se mostrara en el main es el que se pasa por parametro
     */
    public void UsuarioConectado(Usuari user) {
        this.user = user;
    }
    
   
    /**
     * Metodo que se utiliza para reproducir el video de un intento.
     * @param video es el nombre del videoFile que se utilizara para pillar el video y reproducirlo
     */
    public void reproducirVideo(String video) {
        this.video = video;
        //Se ponen las dirrecciones.
        //String connectionAzureStr = "secret";
        //El string que esta como secret es donde hay que poner la key de la base de datos que utiliza el programa y que ha pasado el profesor de nuestro curso.
        String connectionAzureStr = "secret";
        //String containerName = "videos";        
        String containerName = "simulapvideoscontainer";
        BlobClient blobClient = new BlobClientBuilder().connectionString(connectionAzureStr)
                .blobName("uploaded_user_videos/" + video)
                .containerName(containerName)
                .buildClient();
        String tempDir = System.getProperty("java.io.tmpdir");
        String downloadPath = tempDir + File.separator + video;
        //Descarga el video en el directorio temporal siempre que exista
        File existe = new File(downloadPath);
        if (!existe.exists()) {
            blobClient.downloadToFile(downloadPath);
        }
        //Se reproduce el video
        mediaPlayer.mediaPlayer().media().play(downloadPath);
        isPlaying = true;
        btn_ReproducirPausar.setEnabled(true);
        btn_ReproducirPausar.setText("Pausar");

    }

    /**
     * Metodo getter buscar el intento usando la id Intento dentro de la lista de los intentos pendientes.
     * @return devuelve el intento que coincida con la idIntento, de lo contrario sino coincide ninguna devolvera 0.
     */
    public int getIDIntento() {
        for (Intent intento: intentos){
            if (intento.getId()== idIntento){
                return intento.getId();
            }
        }
        return 0;
    }
    
    /**
     * Metodo que similar a getIDintento() pero a este se le pasa un parametro para buscar el intento que coincida con id dada por parametro.
     * @param idIntento es la id del intento que se pretende buscar
     * @return devulve el intento si es encontrado dentro de la lista y si no devolvera null.
     */
    public Intent buscarIntento(int idIntento) {
        for (Intent intento : intentos) {
            if (intento.getId() == idIntento) {
                return intento;
            }
        }
        return null;
    }
    
    /**
     * Metodo que al valorar un intento pediente al desaparecer ese intento de la lista de intentos pendientes, revertira todo lo asociado a ese intento.
     * Se desactivaran los botones de tramites de los intentos y se parara el video.
     */
    public void revertirAlValorar(){
        mediaPlayer.mediaPlayer().controls().stop();
        isPlaying = false;
        btn_ReproducirPausar.setText("Reproducir");
        btn_ReproducirPausar.setEnabled(false);
        btn_InformaciónIntentos.setEnabled(false);
        btn_Valorar.setEnabled(false);
    }
    
    /**
     * Metodo que busca utilizando la id de los intentos el nombre del usuario el cual pertenece dicho intento.
     * @param idIntento es con lo que se buscar al usuario, ya que cada intento va asociado a un usuario.
     * @return devuelve el nombre del usuario si coincide con el intento, de lo contrario devolvera null.
     */
    public String buscarIDUsuario(int idIntento){
        for (Intent intento : intentos){
            if (intento.getId() == idIntento){
                return intento.getNombreUsuario();
            }
        }
        return null;
    }

    /**
     * Metodo que realiza un getter de la ID del usuario.
     * @return devuelve el valor que tenga la id del usuario en el momento de utilizar este getter.
     */
    public int getIDUsuario() {
        return user.getId();
    }

    /**
     * Metodo que se realiza para actualizar los cambios y se utiliza normalmente tras a ver realizado una gestion con un intento, ya se haberlo valorado o eliminado su review.
     * Esto resetea y cambia todos los componentes personalizados que representan los intentos.
     */
    public void ActualizarCambiosIntentosPendientes() {
        componenteIntentos.clear();
        pnl_IntentosPendientes.removeAll();
        leerIntentos();
        repaint();
        
        
    }
    
    /**
     * Metodo que cambia hace que el main deje de ser visible y elimine el nombre del usuario conectado al desconectarse.
     */
    public void desconectarse() {
        setVisible(false);
        lbl_UsuarioConectado.setText("");
    }

    /**
     * Este metodo inicializa el form de Main y sus componentes.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_panelPrincipal = new javax.swing.JPanel();
        btn_cerrarSesion = new javax.swing.JButton();
        pnl_ReproductorVideos = new javax.swing.JPanel();
        btn_ReproducirPausar = new javax.swing.JButton();
        lbl_Usuario = new javax.swing.JLabel();
        tab_IntentosUsuarios = new javax.swing.JTabbedPane();
        pnl_GestorIntentosPedientes = new javax.swing.JPanel();
        btn_InformaciónIntentos = new javax.swing.JButton();
        lbl_verVideo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnl_IntentosPendientes = new javax.swing.JPanel();
        btn_Valorar = new javax.swing.JButton();
        lbl_ActivarBotones = new javax.swing.JLabel();
        lbl_iconosDeslizar = new javax.swing.JLabel();
        lbl_IconoClick = new javax.swing.JLabel();
        pnl_Usuarios = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_Usuarios = new javax.swing.JTable();
        btn_SeleccionarUsuarios = new javax.swing.JButton();
        lbl_textoInfoTablaUsuarios = new javax.swing.JLabel();
        lbl_UsuarioConectado = new javax.swing.JLabel();
        albl_animado = new com.mycompany.animatedlabel.AnimatedLabel();
        lbl_nombrePrograma = new javax.swing.JLabel();
        lbl_logoImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Fitnow");
        setBackground(new java.awt.Color(51, 51, 51));
        setMinimumSize(new java.awt.Dimension(652, 810));
        setSize(new java.awt.Dimension(810, 651));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        pnl_panelPrincipal.setBackground(new java.awt.Color(51, 51, 51));
        pnl_panelPrincipal.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        pnl_panelPrincipal.setMinimumSize(new java.awt.Dimension(810, 651));
        pnl_panelPrincipal.setPreferredSize(new java.awt.Dimension(810, 651));

        btn_cerrarSesion.setText("Cerrar Sesion");
        btn_cerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMouseReleased(evt);
            }
        });
        btn_cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarSesionActionPerformed(evt);
            }
        });

        pnl_ReproductorVideos.setBackground(new java.awt.Color(51, 51, 51));
        pnl_ReproductorVideos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Video Reproductor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        pnl_ReproductorVideos.setForeground(new java.awt.Color(255, 255, 255));
        pnl_ReproductorVideos.setMinimumSize(new java.awt.Dimension(273, 201));
        pnl_ReproductorVideos.setPreferredSize(new java.awt.Dimension(273, 201));
        pnl_ReproductorVideos.setLayout(new java.awt.BorderLayout());

        btn_ReproducirPausar.setText("Reproducir");
        btn_ReproducirPausar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ReproducirPausarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ReproducirPausarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ReproducirPausarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_ReproducirPausarMouseReleased(evt);
            }
        });
        btn_ReproducirPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReproducirPausarActionPerformed(evt);
            }
        });

        lbl_Usuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Usuario.setText("Usuario:");

        tab_IntentosUsuarios.setBackground(new java.awt.Color(153, 153, 153));

        pnl_GestorIntentosPedientes.setBackground(new java.awt.Color(51, 51, 51));
        pnl_GestorIntentosPedientes.setForeground(new java.awt.Color(255, 255, 255));
        pnl_GestorIntentosPedientes.setToolTipText("");

        btn_InformaciónIntentos.setText("Info");
        btn_InformaciónIntentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_InformaciónIntentosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_InformaciónIntentosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_InformaciónIntentosMouseExited(evt);
            }
        });
        btn_InformaciónIntentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InformaciónIntentosActionPerformed(evt);
            }
        });

        lbl_verVideo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_verVideo.setForeground(new java.awt.Color(255, 255, 204));
        lbl_verVideo.setText("Deslizar para ver video:");

        pnl_IntentosPendientes.setBackground(new java.awt.Color(51, 51, 51));
        pnl_IntentosPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnl_IntentosPendientes.setLayout(new javax.swing.BoxLayout(pnl_IntentosPendientes, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(pnl_IntentosPendientes);

        btn_Valorar.setText("Valorar");
        btn_Valorar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ValorarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ValorarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ValorarMouseExited(evt);
            }
        });
        btn_Valorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ValorarActionPerformed(evt);
            }
        });
        btn_Valorar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_ValorarKeyPressed(evt);
            }
        });

        lbl_ActivarBotones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_ActivarBotones.setForeground(new java.awt.Color(204, 255, 204));
        lbl_ActivarBotones.setText("Activar Botones:");

        lbl_iconosDeslizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/deslizar.png"))); // NOI18N

        lbl_IconoClick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/click.png"))); // NOI18N

        javax.swing.GroupLayout pnl_GestorIntentosPedientesLayout = new javax.swing.GroupLayout(pnl_GestorIntentosPedientes);
        pnl_GestorIntentosPedientes.setLayout(pnl_GestorIntentosPedientesLayout);
        pnl_GestorIntentosPedientesLayout.setHorizontalGroup(
            pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                                .addComponent(lbl_ActivarBotones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_IconoClick))
                            .addComponent(btn_Valorar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                                .addComponent(lbl_verVideo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_iconosDeslizar))
                            .addComponent(btn_InformaciónIntentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnl_GestorIntentosPedientesLayout.setVerticalGroup(
            pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_InformaciónIntentos)
                    .addComponent(btn_Valorar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addComponent(lbl_verVideo)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_IconoClick, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_iconosDeslizar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addComponent(lbl_ActivarBotones)
                        .addGap(48, 48, 48))))
        );

        tab_IntentosUsuarios.addTab("Intentos Pendientes", pnl_GestorIntentosPedientes);

        pnl_Usuarios.setBackground(new java.awt.Color(51, 51, 51));
        pnl_Usuarios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbl_Usuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
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
        tbl_Usuarios.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_UsuariossMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_Usuarios);

        btn_SeleccionarUsuarios.setText("Seleccionar");
        btn_SeleccionarUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SeleccionarUsuariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_SeleccionarUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_SeleccionarUsuariosMouseExited(evt);
            }
        });
        btn_SeleccionarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeleccionarUsuariossActionPerformed(evt);
            }
        });

        lbl_textoInfoTablaUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_textoInfoTablaUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        lbl_textoInfoTablaUsuarios.setText("Haz click en un usuario de la tabla");

        javax.swing.GroupLayout pnl_UsuariosLayout = new javax.swing.GroupLayout(pnl_Usuarios);
        pnl_Usuarios.setLayout(pnl_UsuariosLayout);
        pnl_UsuariosLayout.setHorizontalGroup(
            pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_SeleccionarUsuarios)
                        .addGap(15, 15, 15))))
            .addGroup(pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_textoInfoTablaUsuarios)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnl_UsuariosLayout.setVerticalGroup(
            pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_SeleccionarUsuarios)
                .addGap(52, 52, 52)
                .addComponent(lbl_textoInfoTablaUsuarios)
                .addGap(142, 142, 142))
        );

        tab_IntentosUsuarios.addTab("Gestion de usuarios", pnl_Usuarios);

        lbl_UsuarioConectado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_UsuarioConectado.setText("jLabel1");

        albl_animado.setForeground(new java.awt.Color(255, 255, 255));
        albl_animado.setAnimated(true);
        albl_animado.setAppendedText("ienvenido");
        albl_animado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lbl_nombrePrograma.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 50)); // NOI18N
        lbl_nombrePrograma.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombrePrograma.setText("FITNOW");

        lbl_logoImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FitnowSoloImagenMain.jpg"))); // NOI18N

        javax.swing.GroupLayout pnl_panelPrincipalLayout = new javax.swing.GroupLayout(pnl_panelPrincipal);
        pnl_panelPrincipal.setLayout(pnl_panelPrincipalLayout);
        pnl_panelPrincipalLayout.setHorizontalGroup(
            pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                                .addComponent(lbl_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_UsuarioConectado, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(albl_animado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_nombrePrograma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cerrarSesion)
                        .addGap(34, 34, 34))
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addComponent(tab_IntentosUsuarios)
                        .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(pnl_ReproductorVideos, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                .addGap(16, 16, 16))
                            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_ReproducirPausar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_logoImagen))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        pnl_panelPrincipalLayout.setVerticalGroup(
            pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(albl_animado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Usuario)
                            .addComponent(lbl_UsuarioConectado))
                        .addGap(23, 23, 23))
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cerrarSesion)
                            .addComponent(lbl_nombrePrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tab_IntentosUsuarios)
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(pnl_ReproductorVideos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ReproducirPausar)
                        .addGap(34, 34, 34)
                        .addComponent(lbl_logoImagen)
                        .addGap(35, 35, 35))))
        );

        getContentPane().add(pnl_panelPrincipal);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Metodo que al pulsar el boton "btn_cerrarSesion" saltara un dialogo con el que nos dira si queremos cerrar la sesion.
     */
    private void btn_cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarSesionActionPerformed
        DialogoCerrarSesion cerrarSesion = new DialogoCerrarSesion(this, true);
        cerrarSesion.setVisible(true);
    }//GEN-LAST:event_btn_cerrarSesionActionPerformed
    /**
     *Metodo que al pulsar el boton "btn_ReproducirPausar" parara la reproduccion del video de un intento si esta reproduciendose o reproducira el video si esta pausado. 
     */
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

    /**
     *Metodo que al pulsar el boton "SeleccionarUsuarios" abrira un nueva ventana con los datos del usuario seleccionado en la tabla. 
     */
    private void btn_SeleccionarUsuariossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariossActionPerformed
        InformacionUsuario informacionUsuario = new InformacionUsuario(this, true);
        informacionUsuario.setVisible(true);
    }//GEN-LAST:event_btn_SeleccionarUsuariossActionPerformed
    /**
    *Metodo que al pulsar en una celda de la tabla de "tbl_Usuarios" seleccionara la fila de dicha tabla y activara el boton de seleccionar usuarios.
    */
    private void tbl_UsuariossMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_UsuariossMouseClicked
        if (tbl_Usuarios.convertRowIndexToModel(tbl_Usuarios.getSelectedRow()) != -1) {
            btn_SeleccionarUsuarios.setEnabled(true);
        }
    }//GEN-LAST:event_tbl_UsuariossMouseClicked
    /**
     * Metodo que mostrara la toda informacion del intento pendiente de valorar que se haya seleccionado en una nueva ventana. 
     */
    private void btn_InformaciónIntentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosActionPerformed
        InformacionIntento informacionIntento = new InformacionIntento(this, true,idIntento);
        informacionIntento.setVisible(true);
    }//GEN-LAST:event_btn_InformaciónIntentosActionPerformed
    /**
     * Metodo que valorara el intento pendiente que se haya seleccionado dandole click a un componente abriendo una nueva ventana para comenzar alli con la valoracion.
     */
    private void btn_ValorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ValorarActionPerformed
       ValoracionIntentoSinReview valoracionIntentosSinReview = new ValoracionIntentoSinReview(this, true);
       valoracionIntentosSinReview.setVisible(true);
       //Al terminar de valorar el intento se actualizaran los cambios en los componetes personalizados.
       ActualizarCambiosIntentosPendientes();
    }//GEN-LAST:event_btn_ValorarActionPerformed

    private void btn_ValorarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_ValorarKeyPressed
    }//GEN-LAST:event_btn_ValorarKeyPressed
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_Valorar" que hara que se ponga de color azul.
     */
    private void btn_ValorarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ValorarMouseEntered
        if (btn_Valorar.isEnabled()){
            btn_Valorar.setBackground(azulPastel);
        }
    }//GEN-LAST:event_btn_ValorarMouseEntered
    /**
     * Metodo que se activara al pulsar boton "btn_Valorar" que hara que se ponga de color gris claro.
     */
    private void btn_ValorarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ValorarMouseClicked
           btn_Valorar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_ValorarMouseClicked
    /**
     * Metodo que se activara al salir el raton del "btn_Valorar" que hara que se ponga de color blanco.
     */
    private void btn_ValorarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ValorarMouseExited
        if(btn_Valorar.isEnabled()){
            btn_Valorar.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_btn_ValorarMouseExited
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_InformaciónIntentos" que hara que se ponga de color azul.
     */
    private void btn_InformaciónIntentosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosMouseEntered
        if (btn_InformaciónIntentos.isEnabled()){
            btn_InformaciónIntentos.setBackground(azulPastel);
        }
    }//GEN-LAST:event_btn_InformaciónIntentosMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_InformaciónIntentos" que hara que se ponga de color blanco.
     */
    private void btn_InformaciónIntentosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosMouseExited
        if(btn_InformaciónIntentos.isEnabled()){
            btn_InformaciónIntentos.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_btn_InformaciónIntentosMouseExited
    /**
     * Metodo que se activara al pulsar boton "btn_InformaciónIntentos" que hara que se ponga de color gris claro.
     */
    private void btn_InformaciónIntentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosMouseClicked
            btn_InformaciónIntentos.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_InformaciónIntentosMouseClicked
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_cerrarSesion" que hara que se ponga de color azul.
     */
    private void btn_cerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMouseEntered
        btn_cerrarSesion.setBackground(azulPastel);
    }//GEN-LAST:event_btn_cerrarSesionMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_cerrarSesion" que hara que se ponga de color blanco.
     */
    private void btn_cerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMouseExited
        btn_cerrarSesion.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_cerrarSesionMouseExited
    /**
     * Metodo que se activara al pulsar boton "btn_cerrarSesion" que hara que se ponga de color gris claro y que devolvera a todos los componentes a su color por defecto.
     */
    private void btn_cerrarSesionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMousePressed
        btn_cerrarSesion.setBackground(Color.LIGHT_GRAY);
        for (com.mycompany.customcomponentejercicio.CustomComponentEjercicio componente : componenteIntentos){
            componente.cambiarColor(Color.LIGHT_GRAY);
            componente.setIntentoSeleccionado(false);
        }
    }//GEN-LAST:event_btn_cerrarSesionMousePressed
    /**
     * Metodo que se activara al soltar el "btn_cerrarSesion" que hara que se ponga de color azul.
     */
    private void btn_cerrarSesionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMouseReleased
        btn_cerrarSesion.setBackground(azulPastel);
    }//GEN-LAST:event_btn_cerrarSesionMouseReleased
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_ReproducirPausar" que hara que se ponga de color azul.
     */
    private void btn_ReproducirPausarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarMouseEntered
        btn_ReproducirPausar.setBackground(azulPastel);
    }//GEN-LAST:event_btn_ReproducirPausarMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_ReproducirPausar" que hara que se ponga de color blanco.
     */
    private void btn_ReproducirPausarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarMouseExited
        btn_ReproducirPausar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_ReproducirPausarMouseExited
    /**
     * Metodo que se activara al pulsar boton "btn_ReproducirPausar" que hara que se ponga de color gris claro.
     */
    private void btn_ReproducirPausarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarMouseClicked
        btn_ReproducirPausar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_ReproducirPausarMouseClicked
    /**
     * Metodo que se activara al soltar el "btn_ReproducirPausar" que hara que se ponga de color azul.
     */
    private void btn_ReproducirPausarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarMouseReleased
        btn_ReproducirPausar.setBackground(azulPastel);
    }//GEN-LAST:event_btn_ReproducirPausarMouseReleased
    /**
     * Metodo que se activara al pulsar boton "btn_SeleccionarUsuarios" que hara que se ponga de color gris claro.
     */
    private void btn_SeleccionarUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariosMouseClicked
        btn_SeleccionarUsuarios.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_SeleccionarUsuariosMouseClicked
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_SeleccionarUsuarios" que hara que se ponga de color azul.
     */
    private void btn_SeleccionarUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariosMouseEntered
        btn_SeleccionarUsuarios.setBackground(azulPastel);
    }//GEN-LAST:event_btn_SeleccionarUsuariosMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_SeleccionarUsuarios" que hara que se ponga de color blanco.
     */
    private void btn_SeleccionarUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariosMouseExited
        btn_SeleccionarUsuarios.setBackground(Color.white);
    }//GEN-LAST:event_btn_SeleccionarUsuariosMouseExited

    /**
     * Aqui se encuentra la parte ejecutable del programa donde iniciara la aplicacion.
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_ActivarBotones;
    private javax.swing.JLabel lbl_IconoClick;
    private javax.swing.JLabel lbl_Usuario;
    private javax.swing.JLabel lbl_UsuarioConectado;
    private javax.swing.JLabel lbl_iconosDeslizar;
    private javax.swing.JLabel lbl_logoImagen;
    private javax.swing.JLabel lbl_nombrePrograma;
    private javax.swing.JLabel lbl_textoInfoTablaUsuarios;
    private javax.swing.JLabel lbl_verVideo;
    private javax.swing.JPanel pnl_GestorIntentosPedientes;
    private javax.swing.JPanel pnl_IntentosPendientes;
    private javax.swing.JPanel pnl_ReproductorVideos;
    private javax.swing.JPanel pnl_Usuarios;
    private javax.swing.JPanel pnl_panelPrincipal;
    private javax.swing.JTabbedPane tab_IntentosUsuarios;
    private javax.swing.JTable tbl_Usuarios;
    // End of variables declaration//GEN-END:variables
}
