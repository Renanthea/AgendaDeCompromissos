package agenda.compromissos;
import java.sql.*;

/*
* Esta classe implementa somente os metodos para acesso ao banco de 
* dados especificos do Item de Agenda. Portanto esta altamente Coesa.
* Para fazer a conexao ao Bando de Dados, esta classe instancia a
* classe DatabaseConnect que herda os metodos da classe abstrata
* MysqlConnect. Isso permite que troquemos o banco de dados sem alterar
* a instancia de DatabaseConnect nesta classe garantindo o baixo acoplamento.
* Para garantir que a Classe que implementa a conexao com o banco nao
* deixe de implemetar os metodos necessarios a classe MysqlConnect
* implementa a Interface Connect que define as assinaturas dos metodos
* necessarios a uma classe de conexao ao banco impedindo que deixem de
* ser implementados por qualquer outra implementacao.
*/

public class ItemAgenda {


// atributos da classe
   private int codigo;
   private String descricao;
   private String local;
   private String data;
   private boolean cancelado;
   private PreparedStatement st;
   Connection conn;
   DatabaseConnect db;

// construtor basico
   public ItemAgenda(){
      codigo = 0;
      cancelado = false;
      db = new DatabaseConnect();
      conn = db.getConnection();
   }
   
// construtor padrao
   public ItemAgenda(int codigo, String descricao, String local, String data, boolean cancelado){
      this.codigo = codigo;
      this.descricao = descricao;
      this.local = local;
      this.data = data;
      this.cancelado = cancelado;
      db = new DatabaseConnect();
      conn = db.getConnection();
   }


// sets e gets
   public void setCodigo(int codigo){
      this.codigo=codigo;
   }

   public void setDescricao(String descricao){
      this.descricao=descricao;
   }

   public void setLocal(String local){
      this.local = local;
   }

   public void setData(String data){
      this.data =  data;
   }

   public void setCancelado(boolean cancelado){
      this.cancelado = cancelado;
   }

   public int getCodigo(){
      return this.codigo;
   }

   public String getDescricao(){
      return this.descricao;
   }

   public String getLocal(){
      return this.local;
   }

   public String getData(){
      return this.data;
   }

   public boolean isCancelado(){
      return this.cancelado;
   }
   
   public boolean incluir(){
      
      boolean sucesso = false;
      
      try{
         String sql = "insert into agenda (descricao,local,data,cancelado) values (?,?,?,?)";
         st = conn.prepareStatement(sql);
         st.setString(1,getDescricao());
         st.setString(2,getLocal());
         st.setString(3,getData());
         st.setInt(4,isCancelado() ?1:2);
         st.executeUpdate();
         sucesso = true;
         st.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
      db.closeConnection();
      return sucesso;
   }
      
   public String consultaPorCodigo(){
      
      String retorno = "Compromisso nao localizado !";
      String conteudo="";
   
      try{
         String sql = "select * from agenda where codigo = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1,getCodigo());
         ResultSet resultSet = st.executeQuery();
         
         
         if(resultSet.next()){ // so espero um resultado por isso uso o IF para verificar
            
            setDescricao(resultSet.getString("descricao"));	// coloca-se os dados
            setLocal(resultSet.getString("local"));
            setData(resultSet.getString("data"));
            setCancelado(resultSet.getInt("cancelado") == 1 ?true:false);
            conteudo += "Codigo   : "+resultSet.getInt("codigo")+"\n";
            conteudo += "Descricao: "+resultSet.getString("descricao")+"\n";
            conteudo += "Local    : "+resultSet.getString("local")+"\n";
            conteudo += "Data     : "+resultSet.getString("data")+"\n";
            conteudo += "Cancelado: "+(resultSet.getInt("cancelado")==1 ?"Sim":"Nao")+"\n\n";			
            retorno = conteudo;
         }
         st.close(); // fecha consulta
      }
      catch(Exception e){
         e.printStackTrace();
      }
      
      return retorno; 
   }
   
   
   public boolean alterar(){
       
      boolean sucesso = false;
      
      try{
         String sql = "update agenda set descricao = ?, local = ?, data = ?, cancelado = ? where codigo = ?";
         st = conn.prepareStatement(sql);
         st.setString(1,getDescricao());
         st.setString(2,getLocal());
         st.setString(3,getData());
         st.setInt(4,isCancelado() ?1:2);
         st.setInt(5,getCodigo());
         st.executeUpdate();
         sucesso = true;
         st.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
      db.closeConnection();
      return sucesso; // retorna true se conseguiu deletar e false se nao conseguiu
   }
		
   public boolean deletar(){
      
      boolean sucesso = false;
      
      try{
         String sql = "delete from agenda where codigo = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1,getCodigo());
         st.executeUpdate();
         sucesso = true;
      }
      catch(Exception e){
         e.printStackTrace();
      }
      db.closeConnection();
      return sucesso;
   }
		
   public String listarItensDeAgenda(){
   	
   
      String conteudo = "";
      String retorno = "Lista vazia !";
   	
      try{
      
         String sql = "select * from agenda order by codigo";
      
         st = conn.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
      
         while(resultSet.next()){
         
            conteudo += "Codigo   : "+resultSet.getInt("codigo")+"\n";
            conteudo += "Descricao: "+resultSet.getString("descricao")+"\n";
            conteudo += "Local    : "+resultSet.getString("local")+"\n";
            conteudo += "Data     : "+resultSet.getString("data")+"\n";
            conteudo += "Cancelado: "+(resultSet.getInt("cancelado")==1 ?"Sim":"Nao")+"\n\n";
            retorno = conteudo;
         }
      
      
      st.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
   	db.closeConnection();
      return retorno;
   }

   
}