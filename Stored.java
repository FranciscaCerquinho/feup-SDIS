
import java.util.concurrent.ConcurrentHashMap;


public class Stored implements Runnable {

	private String[] splitAnswer;


	public Stored(String[] splitAnswer){

		this.splitAnswer = splitAnswer;
	}


	@Override
	public void run(){

		String toAshMap = splitAnswer[3]+splitAnswer[4];


		if(Peer.getDegreeControl().containsKey(toAshMap)){

			int actualValue = Integer.parseInt(Peer.getDegreeControl().get(toAshMap));
			String newValue = Integer.toString(actualValue+1);
			Peer.getDegreeControl().put(toAshMap,newValue);

			

		}else{

			Peer.getDegreeControl().putIfAbsent(toAshMap,"1");

		}
	


	}























}