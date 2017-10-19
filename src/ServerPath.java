import java.net.*;
import java.io.*;


/**
 * Created by Ivliev
 */
public class ServerPath {

    private static int port = 44502;
    private static Socket SocOfExp;


    public static void main(String[] args) throws Throwable {

        try {
            while (true)
            Power();
        }

        catch (Exception ex)//Открываем поток для вывода с сокетом передатчика и отправляем ему кодовое сообщение и  код ошибки
        {
            OutputStream sout = SocOfExp.getOutputStream();
            DataOutputStream out = new DataOutputStream(sout);

            out.writeUTF("EXCEPTION");
            out.write(1);

        }


    }

    private static void Power() throws Throwable
    {
        int countListInputFonts;
        ServerSocket ss = new ServerSocket(port);  // создаем сокет сервера и привязываем его к вышеуказанному порту


        Socket socket = ss.accept(); //Проверяет подключение

        SocOfExp=socket;



        // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
        InputStream sin = socket.getInputStream();
        OutputStream sout = socket.getOutputStream();

        DataInputStream in = new DataInputStream(sin);
        DataOutputStream out = new DataOutputStream(sout);

        countListInputFonts = in.readInt();

        for(int i=0;i<countListInputFonts;i++)
        {
            FontBase.ListInputFonts.add(in.readUTF());
        }





    }


}