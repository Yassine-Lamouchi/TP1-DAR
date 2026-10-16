package serverPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        System.out.println("Le serveur attend un client...");

        Socket socket = ss.accept();
        System.out.println("Un client est connecté");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        int nb = -1;
        while (nb != 0) {
            nb = dis.readInt();
            if (nb != 0) {
                System.out.println("Le serveur a reçu : " + nb);
                int res = nb * 5;
                dos.writeInt(res);
                System.out.println("Le serveur a envoyé : " + res);
            }
        }

        System.out.println("Le client a envoyé 0, fermeture du serveur.");
        socket.close();
        ss.close();
    }
}