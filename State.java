import java.net.UnknownHostException;

public class State{

    private static MultiCastChannel mc_channel;
    public State(){

        try{
            this.mc_channel = new MultiCastChannel("228.0.0.4", 8080);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}