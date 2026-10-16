package clientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Je suis un client pas encore connecté...");
        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket("localhost", 1234);
        System.out.println("Je suis un client connecté");

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        int x = -1;
        while (x != 0) {
            System.out.print("Entrez un entier (0 pour quitter) : ");
            x = sc.nextInt();
            dos.writeInt(x);
            if (x != 0) {
                int res = dis.readInt();
                System.out.println("Le serveur a calculé : " + res);
            }
        }

        System.out.println("Fermeture du client.");
        socket.close();
        sc.close();
    }
}