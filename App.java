import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class App {

	private App(){}

	public static void main(String[] args){

		peer_id = args[0];




			
		try{
			Registry registry = LocateRegistry.getRegistry(1099);
			RMIinterface stub = (RMIinterface) registry.lookup(peer_id);
			String response = stub.helloWorld();
			System.out.println("response: " + response);
		}catch(Exception e){
			System.err.println("App exception: " + e.toString());
			e.printStackTrace();
		}
		
	}
	
	
}