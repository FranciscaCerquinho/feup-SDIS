import java.rmi.Remote;
import java.rmi.RemoteException;
import java.net.UnknownHostException;
public interface RMIinterface extends Remote{
	
	void backup(String fileID, int repDegree) throws RemoteException, UnknownHostException, InterruptedException;
	void restore() throws RemoteException, UnknownHostException, InterruptedException;
	void delete() throws RemoteException, UnknownHostException, InterruptedException;
	void reclaim() throws RemoteException, UnknownHostException, InterruptedException;
	void state() throws RemoteException, UnknownHostException, InterruptedException;
	void message(String message) throws RemoteException, UnknownHostException, InterruptedException;
	
}