package utility;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Sender {

    public Sender() {
    }

    public void send(ByteBuffer serializeObject){
        try {
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bytesOut);
            objectOutputStream.writeObject(serializeObject.array());
            objectOutputStream.close();
            bytesOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

