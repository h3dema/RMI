package rmicomum;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Files;

/*
 * Implementacao da interface de acesso a arquivos
 */
public class MgmtArquivos extends UnicastRemoteObject implements MgmtArquivosInterface {

 public MgmtArquivos() throws RemoteException {
  super();
 }

 @Override
 public ArrayList<String> lerDiretorios( String dir ) throws RemoteException {
   ArrayList<String> resultado = new ArrayList<>();
   File diretorio = new File( dir );
   File[] listagem = diretorio.listFiles();
   for(int i = 0; i < listagem.length; i++) {
     if (listagem[i].isDirectory()) { resultado.add(listagem[i].getName()); }
   }  
   return resultado;
 }
   
 @Override  
 public ArrayList<String> lerArquivos( String dir ) throws RemoteException {
   ArrayList<String> resultado = new ArrayList<>();
   File diretorio = new File( dir );
   File[] listagem = diretorio.listFiles();
   for(int i = 0; i < listagem.length; i++) {
     if (listagem[i].isFile()) { resultado.add(listagem[i].getName()); }
   } 
   return resultado;
 }

 @Override
 public boolean arquivoExiste( String nomeArquivo ) throws RemoteException{
   return new File(nomeArquivo).exists();
 }
 
 @Override
 public boolean podeLer( String nomeArquivo ) throws RemoteException {
   return new File(nomeArquivo).canRead();
 }
 
 @Override
 public boolean podeEscrever( String nomeArquivo ) throws RemoteException {
   return new File(nomeArquivo).canWrite();
 }
 
 @Override
 public void setarSomenteLeitura( String nomeArquivo, boolean readOnly ) throws RemoteException {
   new File(nomeArquivo).setWritable(!readOnly);
 }

 @Override
 public void criarDiretorio( String dir ) throws RemoteException, IOException {
   Path diretorio = Paths.get(dir);
   Files.createDirectory(diretorio);   
 }

 @Override
 public byte[] lerArquivo( String nomeArquivo ) throws RemoteException, IOException {
   Path f = Paths.get(nomeArquivo);
   byte[] dados = Files.readAllBytes(f);
   return dados;
 }

 @Override
 public long escreverArquivo( String nomeArquivo, byte[] conteudo ) throws RemoteException, IOException {
   Path f = Paths.get(nomeArquivo);
   
   byte[] dados = Files.readAllBytes(f);
   return Files.copy(new ByteArrayInputStream( conteudo ) , f, LinkOption.NOFOLLOW_LINKS);
 }

 @Override
 public void apagarArquivo( String nomeArquivo )  throws RemoteException, IOException {
   Path f = Paths.get(nomeArquivo);
   Files.delete(f);
 }


 private static String SERVIDOR = "localhost";
 private static Integer PORTA = 1099;
 private static String SERVICO = "ServicoMensagens";
 
 // retorna a URI padronizada para o servidor e para o cliente 
 public static String getURI() {
  String uri = String.format("rmi://%s:%d/$s", SERVIDOR, PORTA, SERVICO);
  return uri; //  rmi://<host_name>[:port_number]/<service_name>
 }

}

