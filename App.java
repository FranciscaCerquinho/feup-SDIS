import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//ligação ao servidor
public class App {

	public App(){}

	public static void main(String[] args){

		String peer_id = args[0];

		System.out.println(peer_id);





			
		try{
			Registry registry = LocateRegistry.getRegistry(1099);
			RMIinterface stub = (RMIinterface) registry.lookup(peer_id);
			stub.message();

		}catch(Exception e){
			System.err.println("App exception: " + e.toString());
			e.printStackTrace();
		}
		
	}
	
	
}