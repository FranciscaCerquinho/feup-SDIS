



public class Message{

	private final static String CR = "\\r";
		private final static String LF = "\\n";


	public Message(){
		
		}


		public void sendPutChunk(Chunk chunk,String peer_id){
			String message = "PUTCHUNK "+"1.0 "+peer_id + " "+chunk.getFileID()+" "+chunk.getChunkNo()+" "+chunk.getChunkReplication()+" "+CR+LF;


		}




















}