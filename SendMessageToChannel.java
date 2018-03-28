






public class SendMessageToChannel implements Runnable{

	private String nameChannel;
	private byte[] message;


	public SendMessageToChannel(String nameChannel, byte[] message){
		this.nameChannel= nameChannel;
		this.message = message;
		
	}

	@Override
	public void run(){
		try{
		switch(nameChannel){

			case "mc":
			Peer.messageMC(message);
			break;
			case "mdb":
			Peer.messageMDB(message);
			break;
			case "mdr":
			Peer.messageMDR(message);
			break;






			default:
			System.out.println("Invalid channel.");
			break;
		};


		}catch(Exception ex){
				ex.printStackTrace();
				}


	}




}