//package utility;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//
//public class SendAnswer {
//
//    public static void sender(Object serializator) throws IOException {
//
//        ByteArrayOutputStream byteArrayToServerStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectInputStream = new ObjectOutputStream(byteArrayToServerStream);
//
//        try{
//            byteArrayToServerStream.write((byte[]) serializator);
//        }catch(Exception e){
//        }
//    }
//}
