import java.rmi.Remote;
import java.rmi.RemoteException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public interface RMIinterface extends Remote{


	void backup(String fileID,  int repDegree) throws RemoteException, UnknownHostException, InterruptedException;
	void restore(String fileID) throws RemoteException, UnknownHostException, InterruptedException;
	void delete(String fileID) throws RemoteException, UnknownHostException, InterruptedException;
	void reclaim(int space) throws RemoteException, UnknownHostException, InterruptedException;
	void state() throws RemoteException, UnknownHostException, InterruptedException;


	
}