package utils;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/bati_cuisine";
    private static final String USER = "postgres";
    private static final String PASSWORD = "User_Password";

    private static Connection connection;

    private DatabaseConnection() {
        // Constructeur privé pour éviter l'instanciation
    }

    // Méthode pour obtenir une connexion
    public static synchronized Connection getConnection() {
        try {
            // Vérifier si la connexion est fermée ou null, et tenter de la réouvrir si nécessaire
            if (connection == null || connection.isClosed()) {
                System.out.println("Initializing a new connection...");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez également gérer les exceptions plus finement
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;  // Réinitialiser à null après la fermeture
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
