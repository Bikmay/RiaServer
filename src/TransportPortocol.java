import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TransportPortocol {


    //TODO Variator

    public static Socket clientSocket;


    public TransportPortocol() throws IOException {
    }

    public static void getFileFont() throws IOException {

        String fileName;

        int lengthfile;


        InputStream in = clientSocket.getInputStream();


        DataInputStream dataInputStream = new DataInputStream(in);


        fileName = dataInputStream.readUTF();//Get NAME file of font
        lengthfile = dataInputStream.readInt();//Get LENGTH file

        byte[] file = new byte[lengthfile];

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("basefonts//" + fileName + ".ttf"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());

        bufferedInputStream.read(file);//запись файла
        bufferedOutputStream.write(file);

        bufferedInputStream.close();
        bufferedOutputStream.close();
        dataInputStream.close();
        in.close();


    }


    public static void begindialog() throws IOException {                   //0

        ServerSocket s_s = new ServerSocket(44501, 1);
        clientSocket = s_s.accept();
    }
}
