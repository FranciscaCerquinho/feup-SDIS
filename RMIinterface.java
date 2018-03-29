import java.rmi.Remote;
import java.rmi.RemoteException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public interface RMIinterface extends Remote{


	void backup(String fileID,  int repDegree) throws RemoteException, UnknownHostException, InterruptedException;
	void restore() throws RemoteException, UnknownHostException, InterruptedException;
	void delete(String fileID) throws RemoteException, UnknownHostException, InterruptedException;
	void reclaim(int maxDiskSpace) throws RemoteException, UnknownHostException, InterruptedException;
	void state() throws RemoteException, UnknownHostException, InterruptedException;
	//static void messageMC(byte[] message) throws RemoteException, UnknownHostException, InterruptedException;
	//static void messageMDR(byte[] message) throws RemoteException, UnknownHostException, InterruptedException;
	//static void messageMDB(byte[] message) throws RemoteException, UnknownHostException, InterruptedException;

	
}