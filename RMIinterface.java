import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIinterface extends Remote{
	
	String helloWorld() throws RemoteException;
	
}