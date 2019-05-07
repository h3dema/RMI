package rmicomum;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Formatter;

/*
 * Implementacao da interface Mensageiro
 */
public class MensageiroImpl extends UnicastRemoteObject implements Mensageiro {

	private static String SERVIDOR = "localhost";
	private static Integer PORTA = 1099;
	private static String SERVICO = "ServicoMensagens";

	private String msgRecebida = "Sem mensagens";

	// retorna a URI padronizada para o servidor e para o cliente	
	public static String getURI() {
		String uri = String.format("rmi://%s:%d/$s", SERVIDOR, PORTA, SERVICO);
		return uri; //  rmi://<host_name>[:port_number]/<service_name>
	}

	public MensageiroImpl() throws RemoteException {
		super();
	}

	public void enviarMensagem( String msg ) throws RemoteException {
		System.out.println( msg ); // ecoa a mensagem na tela
		msgRecebida = msg;
	}

	public String lerMensagem() throws RemoteException {
		return msgRecebida;
	}


}

