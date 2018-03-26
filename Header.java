public enum Header{

	PUTCHUNK("PUTCHUNK"), STORED("STORED"), GETCHUNK("GETCHUNK"), CHUNK("CHUNK"), DELETE("DELETE"), REMOVED("REMOVED");

	private String type;

	private Header(String messageType){
		this.type = type;
	}

	public String getType(){
		return type;
	}

}