import java.net.DatagramPacket;
import java.io.File;




public class Message{


	private final static String VERSION = "1.0";


	public Message(){}

		public  byte[] sendPutChunk(Chunk chunk,String peer_id){

			String message = Header.PUTCHUNK + " " + VERSION +" " + peer_id + " " + chunk.getFileID() +" " + chunk.getChunkNo() + " " + chunk.getChunkReplication() +" " +"\r\n\r\n";
			byte[] test = Utils.concatBytes(message.getBytes(), chunk.getContent());
			return test;
		}

		public byte[] sendStored(Chunk chunk, String peer_id){

			String message = Header.STORED + " " + VERSION +" " + peer_id + " " +chunk.getFileID() + " " + chunk.getChunkNo() + " "+"\r\n\r\n";
			byte[] test = message.getBytes();
			return test;
		}

		public byte[] getChunk(Chunk chunk, String peer_id){

			String message = Header.GETCHUNK + " " + VERSION +" " + peer_id + " " + chunk.getFileID() + " " + chunk.getChunkNo() +" "+"\r\n\r\n";
			byte[] test = message.getBytes();
			return test;
		}

		public byte[] chunked(Chunk chunk, String peer_id){

			String message = Header.CHUNK + " " + VERSION +" " + peer_id + " " + chunk.getChunkNo() +" "+"\r\n\r\n";
			byte[] test = Utils.concatBytes(message.getBytes(), chunk.getContent());
			return test;

		}

		public byte[] deleted(Chunk chunk, String peer_id){

			String message = Header.DELETE + " " + VERSION +" " + peer_id + " " + chunk.getFileID() +" "+"\r\n\r\n";
			byte[] test = message.getBytes();
			return test;
		}

		public byte[] removed(Chunk chunk, String peer_id){

			String message = Header.REMOVED + " " + VERSION +" " + peer_id + " " + chunk.getFileID() + " " + chunk.getChunkNo() + " "+"\r\n\r\n";
			byte[] test = message.getBytes();
			return test;
		}




















}