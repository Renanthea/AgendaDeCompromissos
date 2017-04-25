package agenda.compromissos;
import java.sql.Connection;

public interface Connect{

    public Connection getConnection();    
    public void closeConnection();
}