package agenda.compromissos;
import javax.swing.JOptionPane;

public class Agenda{


// construtor padrao
   public Agenda(){
   
   
   }


   public void exibirMenu(){
   
      int alternativa = 0;
   
      do{   
         
         // try catch para verificar se foi digitado somente numeros
         try{
            alternativa = Integer.parseInt(JOptionPane.showInputDialog("Agenda de Compromissos\n\n1) Incluir\n2) Consultar\n3) Alterar\n4) Excluir\n5) Terminar"));
         }
         catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Digite somente numeros!");
         }
         
         // verificacao do intervalo de alternativas validas
         if(alternativa < 0 || alternativa > 5){
            JOptionPane.showMessageDialog(null,"Digite um numero entre 1 e 5 !");
         }
         
         switch(alternativa){
            case 1:
               incluir();
               break;
            case 2:
               consultar();
               break;
            case 3:
               alterar();
               break;
            case 4:
               excluir();
               break;
            default:
               break;      
         }
      
      }while(alternativa >=0 && alternativa < 5);
   
   }


   public void incluir(){
   
      int continuar;
      ItemAgenda itemAgenda;
   
      do{
      
         itemAgenda = new ItemAgenda();
      
         itemAgenda.setDescricao(JOptionPane.showInputDialog("Entre descricao: "));
         itemAgenda.setLocal(JOptionPane.showInputDialog("Entre local: "));
         itemAgenda.setData(JOptionPane.showInputDialog("Entre data dd/mm/aaaa hh:mm: "));
         itemAgenda.setCancelado(false);
      
         if(itemAgenda.incluir()){
            JOptionPane.showMessageDialog(null,"Compromisso incluido com sucesso !");
         }
         else{
            JOptionPane.showMessageDialog(null,"Falha ao incluir compromisso !");
         }
      
         continuar = JOptionPane.showConfirmDialog(null,"Deseja incluir novo compromisso ?","Atencao",JOptionPane.YES_NO_OPTION);    
      
      }while(continuar == JOptionPane.YES_OPTION);
   
   }
   public void consultar(){
   
      int continuar;
      int escolha =0;
   
      do{
      
         // try catch para verificar se foi digitado somente numeros
         try{
            escolha = Integer.parseInt(JOptionPane.showInputDialog("Consulta de Compromissos\n\n1) Por codigo\n2) Todos\n"));
         }
         catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Digite somente numeros!");
         }
         
         // verificacao do intervalo de alternativas validas
         if(escolha < 1 || escolha > 2){
            JOptionPane.showMessageDialog(null,"Digite um numero entre 1 e 2 !");
         }
      
         if(escolha==1){
            consultaPorCodigo();
         }
         else{
            consultaTudo();
         }
      
         continuar = JOptionPane.showConfirmDialog(null,"Deseja fazer nova consulta ?","Atencao",JOptionPane.YES_NO_OPTION);    
      
      }while(continuar == JOptionPane.YES_OPTION);
   
   }
   
   public ItemAgenda consultaPorCodigo(){
   
      String conteudo = "Compromisso nao localizado !";   
      ItemAgenda itemAgenda = new ItemAgenda();
      int codigo = Integer.parseInt(JOptionPane.showInputDialog("Entre com codigo: "));
      itemAgenda.setCodigo(codigo);
      JOptionPane.showMessageDialog(null,itemAgenda.consultaPorCodigo());
   
      return itemAgenda;
   }
   
   public void consultaTudo(){
    
      ItemAgenda itemAgenda = new ItemAgenda();
      JOptionPane.showMessageDialog(null,itemAgenda.listarItensDeAgenda());
   }  
   
   
   public void alterar(){
   
      int continuar;
      int continuar2;
      int continuar3;
      ItemAgenda itemAgenda;
      
      do{
      
         do{
            itemAgenda = consultaPorCodigo();
            
            continuar2 = JOptionPane.showConfirmDialog(null,"Alterar este compromisso ?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);    
         
         }while(continuar2 == JOptionPane.NO_OPTION);
         
         if(itemAgenda.getCodigo()!=0 && continuar2 != JOptionPane.CANCEL_OPTION ){
         
            itemAgenda.setDescricao(JOptionPane.showInputDialog("Entre descricao : "));
            itemAgenda.setLocal(JOptionPane.showInputDialog("Entre local : "));
            itemAgenda.setData(JOptionPane.showInputDialog("Entre data dd/mm/aaaa hh:mm : "));
            continuar3 = JOptionPane.showConfirmDialog(null,"Cancelar este compromisso ?","Atencao",JOptionPane.YES_NO_OPTION);    
            if(continuar3 == JOptionPane.YES_OPTION){
               itemAgenda.setCancelado(true);
            }
            else{
               itemAgenda.setCancelado(false);
            }
            if(itemAgenda.alterar()){
               JOptionPane.showMessageDialog(null,"Compromisso alterado com sucesso !");
            }
            else{
               JOptionPane.showMessageDialog(null,"Falha ao alterar compromisso !");
            }
         
         }      
      
      
         continuar = JOptionPane.showConfirmDialog(null,"Deseja alterar outro compromisso ?","Atencao",JOptionPane.YES_NO_OPTION);    
      
      }while(continuar == JOptionPane.YES_OPTION);   
   }
   public void excluir(){
   
      int continuar;
      int continuar2;
      int continuar3;
      ItemAgenda itemAgenda;
      
      do{
      
         do{
            itemAgenda = consultaPorCodigo();
            
            continuar2 = JOptionPane.showConfirmDialog(null,"Excluir este compromisso ?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);    
         
         }while(continuar2 == JOptionPane.NO_OPTION);
         
         if(itemAgenda.getCodigo()!=0 && continuar2 != JOptionPane.CANCEL_OPTION ){
         
         
            if(itemAgenda.deletar()){
               JOptionPane.showMessageDialog(null,"Compromisso excluido com sucesso !");
            }
            else{
               JOptionPane.showMessageDialog(null,"Falha ao excluir compromisso !");
            }
         
         }      
      
      
         continuar = JOptionPane.showConfirmDialog(null,"Deseja excluir outro compromisso ?","Atencao",JOptionPane.YES_NO_OPTION);    
      
      }while(continuar == JOptionPane.YES_OPTION);     
   }


}