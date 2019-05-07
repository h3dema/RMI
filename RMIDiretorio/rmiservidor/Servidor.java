package rmiservidor;

import java.rmi.Naming;
import rmicomum.MgmtArquivosInterface;
import rmicomum.MgmtArquivos;

public class Servidor {

 public Servidor() {
  try {
   MgmtArquivosInterface m = new MgmtArquivos();
   Naming.rebind(MgmtArquivos.getURI(), m);
  }
  catch( Exception e ) {
   System.out.println( "Erro: " + e );
  }
 }

 public static void main(String[] args) {
  // inicia o programa "server"
  new Servidor();
 }
}

