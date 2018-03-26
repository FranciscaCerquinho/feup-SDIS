import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

//ligação ao servidor
public class App {

	public App(){}

	public static void main(String[] args){

		String peer_id = args[0];
		String command = args[1];
		String file = args[2];
		int RepDegree = Integer.parseInt(args[3]);
		RMIinterface stub;
		ArrayList<FileInformation> fileInfo = new ArrayList<FileInformation>();
			
		try{
			Registry registry = LocateRegistry.getRegistry(1099);
			stub = (RMIinterface) registry.lookup(peer_id);

		

		switch(command){

			case "backup":
			try{
				FileInformation fileInformation = new FileInformation(file,RepDegree, peer_id);
				fileInfo.add(fileInformation);
				stub.backup(file, fileInformation);
				//stub.message("Backed up");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}
				break;

			case "restore":
			try{
				stub.restore();
				stub.message("Chose " + command + " protocol");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}
			
				break;
	

			case "delete":
				try{
				stub.reclaim();
				stub.message("Chose " + command + " protocol");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}				break;

			case "reclaim":
				try{
				stub.delete();
				stub.message("Chose " + command + " protocol");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
			}			
			break;

			case "state":
				try{
				stub.state();
				stub.message("Chose " + command + " protocol");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}				break;

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