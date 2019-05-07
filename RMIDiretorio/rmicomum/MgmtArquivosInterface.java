package rmicomum;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.io.IOException;

/*
 * interface que define o padrao de comunicacao
 */
public interface MgmtArquivosInterface extends Remote {

 public ArrayList<String> lerDiretorios( String dir ) throws RemoteException;
 public ArrayList<String> lerArquivos( String dir ) throws RemoteException;
 public boolean arquivoExiste( String nomeArquivo ) throws RemoteException;
 public boolean podeLer( String nomeArquivo ) throws RemoteException;
 public boolean podeEscrever( String nomeArquivo ) throws RemoteException;
 public void setarSomenteLeitura( String nomeArquivo, boolean readOnly ) throws RemoteException;
 public void criarDiretorio( String dir ) throws RemoteException, IOException;
 public byte[] lerArquivo( String nomeArquivo ) throws RemoteException, IOException;
 public long escreverArquivo( String nomeArquivo, byte[] conteudo )  throws RemoteException, IOException;
 public void apagarArquivo( String nomeArquivo )  throws RemoteException, IOException;
}


