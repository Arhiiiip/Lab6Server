import command.Invoker;
import command.Receiver;
import org.xml.sax.SAXException;
import utility.Reader;
import utility.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Lab_5
 *
 * @author Arhiiiip
 * @version 1.0
 */

public class Main {

    public static LinkedList<ServerConector> clLst = new LinkedList<ServerConector>();

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        final int PORT = 8080;
        ServerSocket serverSocket = new ServerSocket(PORT);

        String link = args[0];
        FileWorker fileWorker = new FileWorker();
        CollectionManager collectionManager = new CollectionManager(fileWorker.parse(link), LocalDateTime.now(), LocalDateTime.now(), 0, link);
        HashSet hashSetId = fileWorker.takeHashSetId();
        MovieFactory movieFactory = new MovieFactory(hashSetId, collectionManager);
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker(receiver, movieFactory);
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader(scanner, invoker);
        Validator.setReader(reader);
        while (true) {
            Socket clientConnect = serverSocket.accept();
            Sender sender = new Sender();
            RRHandler rrHandler = new RRHandler(invoker, clientConnect, sender);
            System.out.println("подключился пользователь " + clientConnect.toString().substring(23, 33));
            try {
                ObjectForServer command;
                while (clientConnect.isConnected()) {
                    try {
                        InputStream stream = clientConnect.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
                        command = (ObjectForServer) objectInputStream.readObject();
                        if (command != null) {
                            invoker.execute(command.getCommandUser());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        clientConnect.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}