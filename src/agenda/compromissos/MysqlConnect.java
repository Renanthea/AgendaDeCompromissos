package agenda.compromissos;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

abstract class MysqlConnect implements Connect {

   // atributo
   public Connection conn;

   // construtor
   public MysqlConnect() {
        conn            = null;
	     String url      = "jdbc:mysql://localhost/"; // localizacao do servidor
        String dbName   = "root";                    // nome do banco de dados
        String driver   = "com.mysql.jdbc.Driver";   // nome do driver de conexao
        String userName = "root";                    // nome do usuario do banco
//        String password = "";                    // respectiva senha
        String password = "";                    // respectiva senha
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(
                     null,
                    "Erro no Banco de Dados!\n\nContate seu Administrador do Sistema!",
                    "Erro...",
                    JOptionPane.WARNING_MESSAGE
            );
            e.printStackTrace();
        }
    }
    
    // fornece a conexao com o banco de dados Mysql
    public Connection getConnection(){
      return conn;
    }
    
    //Fecha a conexao com bco de dados
    public void closeConnection() {
        try                { 
        conn.close(); 
        }
        catch(Exception e) { 
        e.printStackTrace(); 
        }
    } 
}