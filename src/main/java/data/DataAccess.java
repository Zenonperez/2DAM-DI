package data;

import dto.Intent;
import dto.Review;
import dto.Usuari;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * DataAccess es una clase que se encarga de manejar las setencias SQL de la base de datos para poder tanto conseguir datos de esta como para modificar o eliminar datos.
 * Con esta base de datos conseguimos que nuestra aplicación funcione.
 * @author Miguel
 */
public class DataAccess {

    /**
     * Este metodo hace que podamos conectarnos a la base datos y poder realizar diferentes operaciones en esta.
     * @return devolvera la conexion del url proporcionado.
     */
    private Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            //properties.load(DataAccess.class.getClassLoader().getResourceAsStream("properties/application.properties"));
            //connection = DriverManager.getConnection(properties.getProperty("connectionUrl"));
            //String connectionUrl = "jdbc:sqlserver://localhost:1433;database=simulapdb;user=sa;password=12345678Zpc!;encrypt=false;loginTimeout=10;";
            String connectionUrlAzure = "jdbc:sqlserver://simulapsqlserver.database.windows.net:1433;database=simulapdb;user=simulapdbadmin@simulapsqlserver;password=Pwd1234.;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            connection = DriverManager.getConnection(connectionUrlAzure);
            //connection = DriverManager.getConnection(connectionUrlAzure);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Metodo que devuelve al usuario cuyo email coincida con el que se ha puesto en el parametro.
     * @param email este parametro sirve para usarlo para encontrar al usuario que tenga el mismo mail.
     * @return devolvera al usuario que coincida con ese mail.
     */
    public Usuari getUser(String email) {
        Usuari user = null;
        String sql = "SELECT * FROM Usuaris WHERE Email = ?";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setString(1, email);
            ResultSet resultSet = selectStatement.executeQuery();
            user = new Usuari();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("Id"));
                user.setNombre(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPasswordHash(resultSet.getString("PasswordHash"));
                user.setInstructor(resultSet.getBoolean("Instructor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    /**
     * Metodo que devolvera a todos los usuarios de la base de datos que no sean intructores.
     * @return devuelve a todos los usuarios menos a los instructores como una lista.
     */
    public ArrayList<Usuari> getAllUsers() {
        ArrayList<Usuari> usuaris = new ArrayList<>();
        String sql = "SELECT * FROM Usuaris WHERE Instructor=0";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {

            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Usuari user = new Usuari();
                user.setId(resultSet.getInt("Id"));
                user.setNombre(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPasswordHash(resultSet.getString("PasswordHash"));
                user.setInstructor(resultSet.getBoolean("Instructor"));
                usuaris.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuaris;
    }
    /**
     * Metodo que registra a un nuevo usuario en la base de datos.
     * @param u es el usuario que se metera por parametros el cual se quiere agregar a la base de datos.
     * @return devolvera un int con el valor de la id del usuario, si no consigue crearlo sera 0.
     */
    public int registerUser(Usuari u) {
        String sql = "INSERT INTO dbo.Usuaris (Nom, Email, PasswordHash, Instructor)"
                + " VALUES (?,?,?,?)"
                + " SELECT CAST(SCOPE_IDENTITY() as int)";
        try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql)) {
            insertStatement.setString(1, u.getNombre());
            insertStatement.setString(2, u.getEmail());
            insertStatement.setString(3, u.getPasswordHash());
            insertStatement.setBoolean(4, u.isInstructor());

            int newUserId = insertStatement.executeUpdate();
            return newUserId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * Metodo que consigue coger todos los intentos pendientes de review.
     * @return devuelve una lista de intentos de los cuales ninguno tiene review.
     */
    public ArrayList<Intent> getAttemptsPendingReview() {
        ArrayList<Intent> intents = new ArrayList<>();
        String sql = "SELECT Intents.Id, Intents.IdUsuari, Usuaris.Nom,"
                + " Intents.IdExercici, Exercicis.NomExercici, Timestamp_Inici,"
                + " Timestamp_Fi, VideoFile"
                + " FROM Intents INNER JOIN Usuaris ON Intents.IdUsuari=Usuaris.Id"
                + " INNER JOIN Exercicis ON Intents.IdExercici=Exercicis.Id"
                + " WHERE Intents.Id NOT IN"
                + " (SELECT IdIntent FROM Review)"
                + " ORDER BY Timestamp_Inici";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {

            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Intent attempt = new Intent();
                attempt.setId(resultSet.getInt("Id"));
                attempt.setIdUsuari(resultSet.getInt("IdUsuari"));
                attempt.setNombreUsuario(resultSet.getString("Nom"));
                attempt.setIdEjercicio(resultSet.getInt("IdExercici"));
                attempt.setNombreEjercicio(resultSet.getString("NomExercici"));
                attempt.setTimestamp_Inicio(resultSet.getString("Timestamp_Inici"));
                attempt.setTimestamp_Fin(resultSet.getString("Timestamp_Fi"));
                attempt.setVideofile(resultSet.getString("VideoFile"));
                intents.add(attempt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return intents;
    }

    /**
     * Metodo que inserta una nueva review en la base datos que va asociada a un intento.
     * @param r es la review que se quiere agregar en la base de datos.
     * @return devuelve un int con valor el valor de resultado de la actualizacion, si no hay ningun cambio sera 0.
     */
    public int insertReview(Review r) {
        int result = 0;
        String sql = "INSERT INTO dbo.Review (IdIntent, IdReviewer, Valoracio, Comentari)"
                + " VALUES (?,?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, r.getIdIntent());
            insertStatement.setInt(2, r.getIdReviewer());
            insertStatement.setInt(3, r.getValoracion());
            insertStatement.setString(4, r.getComentario());

            result = insertStatement.executeUpdate();
            if (result == 0) {
                throw new SQLException("Creating review failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long longResult = generatedKeys.getLong(1);
                    result = longResult.intValue();
                } else {
                    throw new SQLException("Creating review failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Mètode per comprovar si un intent es la repetició de un exercici
     * 'failed'. Comprova si ja existeix un intent amb el mateix IdUsuari i
     * IdExercici i la \n data es anterior a la de intent.
     *
     * @param intent El intent a comprovar
     * @return el id del intent anterior o 0 si no existeix un intent anterior.
     */
    public int getPreviousFailedAttempt(Intent intent) {
        return 0;
    }
    /**
     * Metodo que consigue todos los intentos que tiene un usuario.
     * @param user es el usuario del cual queremos conseguir todos sus intentos.
     * @return devuelve una lista de los intentos que tiene un usuario.
     */
    public ArrayList<Intent> getAttemptsPerUser(Usuari user) {
        ArrayList<Intent> intents = new ArrayList<>();
        String sql = "SELECT Intents.Id, Intents.IdUsuari, Usuaris.Nom,"
                + " Intents.IdExercici, Exercicis.NomExercici, Timestamp_Inici,"
                + " Timestamp_Fi, VideoFile"
                + " FROM Intents INNER JOIN Usuaris ON Intents.IdUsuari=Usuaris.Id"
                + " INNER JOIN Exercicis ON Intents.IdExercici=Exercicis.Id"
                + " WHERE Intents.IdUsuari=?"
                + " ORDER BY Intents.IdExercici";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, user.getId());
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Intent attempt = new Intent();
                attempt.setId(resultSet.getInt("Id"));
                attempt.setIdUsuari(resultSet.getInt("IdUsuari"));
                attempt.setNombreUsuario(resultSet.getString("Nom"));
                attempt.setIdEjercicio(resultSet.getInt("IdExercici"));
                attempt.setNombreEjercicio(resultSet.getString("NomExercici"));
                attempt.setTimestamp_Inicio(resultSet.getString("Timestamp_Inici"));
                attempt.setTimestamp_Fin(resultSet.getString("Timestamp_Fi"));
                attempt.setVideofile(resultSet.getString("VideoFile"));
                intents.add(attempt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return intents;

    }
    /**
     * Metodo que consigue la review de un intento.
     * @param idIntent es el id del intento del cual queremos conseguir la review
     * @return nos devolvera la review del intento cuya id coincida con esta.
     */
    public Review getAttemptReview(int idIntent) {
        Review review = null;
        String sql = "SELECT * FROM Review WHERE IdIntent = ?";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, idIntent);
            ResultSet resultSet = selectStatement.executeQuery();
            review = new Review();
            while (resultSet.next()) {
                review.setId(resultSet.getInt("Id"));
                review.setIdIntent(resultSet.getInt("IdIntent"));
                review.setIdReviewer(resultSet.getInt("IdReviewer"));
                review.setValoracion(resultSet.getInt("Valoracio"));
                review.setComentario(resultSet.getString("Comentari"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    /**
     * Metodo que actualiza la review de un intento.
     * @param r el parametro que hemos metido es nueva review que modificara a la que ya estaba.
     * @return nos devolvera un int con el valor de la actualizacion, si no se actualizara nada el resultado sera 0.
     */
    public int updateReview(Review r) {
        int result = 0;
        String sql = "UPDATE Review SET Valoracio=?, Comentari=? WHERE Id=?";
        try (Connection conn = getConnection(); PreparedStatement updateStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            updateStatement.setInt(3, r.getId());
            updateStatement.setInt(1, r.getValoracion());
            updateStatement.setString(2, r.getComentario());

            result = updateStatement.executeUpdate();
            if (result == 0) {
                throw new SQLException("Updating review failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Metodo el cual elimina una review existente.
     * @param id la id de la review que se pretende eliminar.
     */
    public void dropReview(int id) {
        String sql = "DELETE FROM Review WHERE Id=?";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
