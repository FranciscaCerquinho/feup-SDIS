import java.net.DatagramPacket;
import java.io.File;




public class Message{

	private final static String CR = "\\r";
	private final static String LF = "\\n";
	private final static String VERSION = "1.0";


	public Message(){}

		public  byte[] sendPutChunk(Chunk chunk,String peer_id){

			String message = Header.PUTCHUNK + " " + VERSION +" " + peer_id + " " + chunk.getFileID() +" " + chunk.getChunkNo() + " " + chunk.getChunkReplication() +" "+CR+LF;
			byte[] test = Utils.concatBytes(message.getBytes(), chunk.getContent());
			return test;
		}

		public byte[] sendStored(Chunk chunk, String peer_id){

			String message = Header.STORED + " " + VERSION +" " + peer_id + " " +chunk.getFileID() + " " + chunk.getChunkNo() + " "+CR+LF;
			byte[] test = message.getBytes();
			return test;
		}

		public byte[] getChunk(Chunk chunk, String peer_id){

			String message = Header.GETCHUNK + " " + VERSION +" " + peer_id + " " + chunk.getFileID() + " " + chunk.getChunkNo() +" "+CR+LF;
			byte[] test = message.getBytes();
			return test;
		}

		public byte[] chunked(Chunk chunk, String peer_id){

			String message = Header.CHUNK + " " + VERSION +" " + peer_id + " " + chunk.getChunkNo() +" "+CR+LF;
			byte[] test = Utils.concatBytes(message.getBytes(), chunk.getContent());
			return test;

		}

		public byte[] deleted(Chunk chunk, String peer_id){

			String message = Header.DELETE + " " + VERSION +" " + peer_id + " " + chunk.getFileID() +" "+CR+LF;
			byte[] test = message.getBytes();
			return test;
		}

		public byte[] removed(Chunk chunk, String peer_id){

			String message = Header.REMOVED + " " + VERSION +" " + peer_id + " " + chunk.getFileID() + " " + chunk.getChunkNo() + " "+CR+LF;
			byte[] test = message.getBytes();
			return test;
		}




















}