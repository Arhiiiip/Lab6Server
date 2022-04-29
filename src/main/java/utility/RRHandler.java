package utility;

import command.Invoker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RRHandler {

    Invoker invoker;
    static Serializator serializator = new Serializator();
    static Socket socketChannel;
    static Sender sender;

    public RRHandler(Invoker invoker, Socket socketChannel, Sender sender) {
        this.invoker = invoker;
        this.socketChannel = socketChannel;
        this.sender = sender;
    }

    public static void res(String result) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketChannel.getOutputStream());
        objectOutputStream.writeObject(serializator.serialize(new ObjectForServer(result)));
        objectOutputStream.flush();
        objectOutputStream.close();
    }

}
