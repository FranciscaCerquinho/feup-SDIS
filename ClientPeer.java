import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ClientPeer {

	private ClientPeer(){}

	public static void main(String[] args){
			
		try{
			Registry registry = LocateRegistry.getRegistry(1099);
			RMIinterface stub = (RMIinterface) registry.lookup("trial");
			String response = stub.helloWorld();
			System.out.println("response: " + response);
		}catch(Exception e){
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		
	}
	
	
}