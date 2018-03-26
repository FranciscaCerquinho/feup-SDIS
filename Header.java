public enum MessageType{

	PUTCHUNK("PUTCHUNK"), STORED("STORED"), GETCHUNK("GETCHUNK"), CHUNK("CHUNK"), DELETE("DELETE"), REMOVED("REMOVED");

	private String type;

	public MessageType(String messageType){
		this.type = type;
	}

	public String getType(){
		return type;
	}

}