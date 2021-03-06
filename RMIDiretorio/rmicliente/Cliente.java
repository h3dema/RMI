package rmicliente;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import rmicomum.MgmtArquivosInterface;
import rmicomum.MgmtArquivos;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.StandardOpenOption;

public class Cliente {

 public static void main( String args[] ) {
  try {
   // cria um cliente RMI baseado na URI
   MgmtArquivosInterface m = (MgmtArquivosInterface) Naming.lookup( MgmtArquivos.getURI() );
   String diretorioBase = ".";
   System.out.println("Diretorios");
   System.out.println(m.lerDiretorios(diretorioBase));
   System.out.println("Arquivos no diretorio");
   System.out.println(m.lerArquivos(diretorioBase));
   
   String a1 = "sh.compilar";
   String s1 = (m.arquivoExiste(a1)) ? "Achei" : "Nao achei";
   System.out.println( s1 + " o arquivo "+a1);
   
   String a2 = "sh.Compilar";
   String s2 = (m.arquivoExiste(a2)) ? "Achei" : "Nao achei";
   System.out.println( s2 + " o arquivo "+a2);
   
   String a3 = "sh. compilar";
   String s3 = (m.arquivoExiste(a3)) ? "Achei" : "Nao achei";
   System.out.println( s3 + " o arquivo "+a3);
      
   String s4 = (m.podeLer(a1)) ? "Pode ler" : "Nao pode ler";
   System.out.println(s4 + " o arquivo "+a1);
   
   String s5 = (m.podeEscrever(a1)) ? "Pode escrever" : "Nao pode escrever";
   System.out.println(s5 + " o arquivo "+a1);
   
   m.setarSomenteLeitura(a1, true);
   
   s5 = (m.podeEscrever(a1)) ? "Pode escrever" : "Nao pode escrever";
   System.out.println(s5 + " o arquivo "+a1);

   m.setarSomenteLeitura(a1, false);
   
   s5 = (m.podeEscrever(a1)) ? "Pode escrever" : "Nao pode escrever";
   System.out.println(s5 + " o arquivo "+a1);
   
   m.criarDiretorio("teste");
   System.out.println(m.lerDiretorios(diretorioBase));
   
   m.apagarArquivo("teste");
   System.out.println(m.lerDiretorios(diretorioBase));
   
   Files.write(FileSystems.getDefault().getPath("arquivoGravado.txt"), m.lerArquivo("rmicomum/MgmtArquivos.java~"), StandardOpenOption.CREATE);
   System.out.println(m.lerArquivos(diretorioBase));

   
  }
  catch( MalformedURLException e ) {
   System.out.println();
   System.out.println( "MalformedURLException: " + e.toString() );
  }
  catch( RemoteException e ) {
   System.out.println();
   System.out.println( "RemoteException: " + e.toString() );
  }
  catch( NotBoundException e ) {
   System.out.println();
   System.out.println( "NotBoundException: " + e.toString() );
  }
  catch( Exception e ) {
   System.out.println();
   System.out.println( "Exception: " + e.toString() );
  }
 }
}

