import java.net.DatagramPacket;



public class Message{

	private final static String CR = "\\r";
	private final static String LF = "\\n";
	private final static String VERSION = "1.0";


	public Message(){}

		public byte[] sendPutChunk(Chunk chunk,String peer_id){

			String message = Header.PUTCHUNK + " " + VERSION +" " + peer_id + " " + chunk.getFileID() +" " + chunk.getChunkNo() + " " + chunk.getChunkReplication() +" "+CR+LF;
			message.getBytes();
			return message;
		}

		public byte[] sendStored(Chunk chunk, String peer_id){

			String message = Header.STORED + " " + VERSION +" " + peer_id + " " +chunk.getFileID() + " " + chunk.getChunkNo() + " "+CR+LF;
			message.getBytes();
			return message;
		}

		public byte[] getChunk(Chunk chunk, String peer_id){

			String message = Header.GETCHUNK + " " + VERSION +" " + peer_id + " " + chunk.getFileID() + " " + chunk.getChunkNo() +" "+CR+LF;
			message.getBytes();
			return message;
		}

		public byte[] chunked(Chunk chunk, String peer_id){

			String message = Header.CHUNK + " " + VERSION +" " + peer_id + " " + chunk.getChunkNo() +" "+CR+LF;
			message.getBytes();
			return message;

		}

		public byte[] deleted(Chunk chunk, String peer_id){

			String message = Header.DELETE + " " + VERSION +" " + peer_id + " " + chunk.getFileID() +" "+CR+LF;
			message.getBytes();
			return message;
		}




















}