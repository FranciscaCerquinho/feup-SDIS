import java.rmi.Remote;
import java.rmi.RemoteException;
import java.net.UnknownHostException;
public interface RMIinterface extends Remote{
	
	//void backup() throws RemoteException;
	//void restore() throws RemoteException;
	//void delete() throws RemoteException;
	//void reclaim() throws RemoteException;
	void message(String peer_id) throws RemoteException, UnknownHostException, InterruptedException;
	
}