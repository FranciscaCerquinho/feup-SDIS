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
		String file = args[2];


		RMIinterface stub;
 	
			try{
			Registry registry = LocateRegistry.getRegistry(1099);
			stub = (RMIinterface) registry.lookup(peer_id);

		

		switch(command){

			case "backup":
			try{
				int repDegree = Integer.parseInt(args[3]);
				stub.backup(file,  repDegree);


			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}
				break;

			case "restore":
			try{
				stub.restore();
			//	stub.message("Chose " + command + " protocol");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}
			
				break;
	

			case "delete":
				try{
					stub.delete(file);
				//	stub.message("Chose " + command + " protocol");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}				break;

			case "reclaim":
				try{

					int repDegree = Integer.parseInt(args[3]);
					int maxDiskSpace = Integer.parseInt(file);
				//	stub.reclaim(maxDiskSpace,fileInfo);
				//	stub.message("Chose " + command + " protocol");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
			}
			break;

			case "state":
			/*	try{
				stub.state();
				stub.message("Chose " + command + " protocol");
			}catch(Exception e){
				System.err.println("App exception: " + e.toString());
				e.printStackTrace();
		}	*/			break;

				default:

				System.out.println("Wrong usage of operation");
				break;




		}


	/*	for(int i=0; i < fileInfo.size(); i++) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
			String information= fileInfo.get(i).getFile() + ";" + fileInfo.get(i).getRepDegree() + ";" +fileInfo.get(i).getBackupServiceID();
			bw.write(information);
		}*/


		}catch(Exception e){
			System.err.println("App exception: " + e.toString());
			e.printStackTrace();
		}


		
	}
	
	
}