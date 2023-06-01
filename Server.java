
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Dan
 */
public class Server {

    static String Func;

    static int WIDTH;
    static int HEIGHT;

    static int x;
    static int y;

    static ArrayList<Double> resList = new ArrayList();

    public static class MyThread extends Thread {

        ServerSocket server;
        Socket socket;

        MyThread(ServerSocket server, Socket socket) {
            this.server = server;
            this.socket = socket;
        }

        @Override
        public void run() {

            MathParser mp = new MathParser(Func, WIDTH, HEIGHT, x, y);
            resList = mp.fillList();

            ObjectOutputStream oos;
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(resList);
//                resList.forEach(System.out::println);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        
        ServerSocket server = new ServerSocket(7777);
        Socket socket = server.accept();
        while (true) {
            String str = "?";
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            str = (String) ois.readObject();
            if (str != "?") {
                //System.out.println(str);
                String[] resArr = str.split(" ");
                Func = resArr[0];
                WIDTH = Integer.valueOf(resArr[1]);
                HEIGHT = Integer.valueOf(resArr[2]);
                x = Integer.valueOf(resArr[3]);
                y = Integer.valueOf(resArr[4]);
                MyThread myThread = new MyThread(server, socket);

                myThread.start();
            }
            
        }
    }
}
