import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.io.*;

//ligação ao servidor
public class App {

	public App(){}

	public static void main(String[] args){

		String peer_id = args[0];
		String command = args[1];
		
		
		RMIinterface stub;
 	
			try{
			Registry registry = LocateRegistry.getRegistry(1099);
			stub = (RMIinterface) registry.lookup(peer_id);

		

		switch(command){

			case "backup":
			try{
				String file = args[2];
				int repDegree = Integer.parseInt(args[3]);
				stub.backup(file,  repDegree);	
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}
				break;

			case "restore":
			try{
				String file = args[2];
				stub.restore(file);
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}
			
				break;
	

			case "delete":
				try{
					String file = args[2];
					stub.delete(file);
				
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
			}

				break;

			case "reclaim":
				try{
					int space = Integer.parseInt(args[2]);
					stub.reclaim(space);
				
					
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
			}			
			break;


				default:

				System.out.println("Wrong usage of operation");
				break;




		}


	

		}catch(Exception e){
			System.err.println("App exception: " + e.toString());
			e.printStackTrace();
		}


		
	}
	
	
}