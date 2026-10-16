package serverPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        System.out.println("Serveur calculatrice en attente sur le port 1234...");

        Socket socket = ss.accept();
        System.out.println("Un client est connecté");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        int choix = dis.readInt();
        double a = dis.readDouble();
        double b = dis.readDouble();

        double resultat = 0;
        boolean ok = true;

        switch (choix) {
            case 1:
                resultat = a + b;
                break;
            case 2:
                resultat = a - b;
                break;
            case 3:
                resultat = a * b;
                break;
            case 4:
                if (b == 0) {
                    ok = false;
                } else {
                    resultat = a / b;
                }
                break;
            default:
                ok = false;
        }

        dos.writeBoolean(ok);
        if (ok) {
            dos.writeDouble(resultat);
            System.out.println("Résultat envoyé : " + resultat);
        } else {
            System.out.println("Erreur : division par zéro ou choix invalide");
        }

        socket.close();
        ss.close();
    }
}