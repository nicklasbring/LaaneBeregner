package sample;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{

    private int port = 8000;
    private String serverIP = "localhost";
    DataInputStream input;
    DataOutputStream output;
    double renteSats;
    int antalAar;
    double beloeb;
    ClientListener listener;

    public interface ClientListener{
        void answer(double monthly, double total);
    }

    public Client(ClientListener listener) {

        try {
            this.listener = listener;
            Socket socket = new Socket(serverIP, port);

            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTal(double renteSats, int antalAar, double beloeb) {
        this.renteSats = renteSats;
        this.antalAar = antalAar;
        this.beloeb = beloeb;
    }

    @Override
    public void run() {
        try {
            output.writeDouble(renteSats);
            output.writeInt(antalAar);
            output.writeDouble(beloeb);
            output.flush();

            double monthly = input.readDouble();
            double total = input.readDouble();
            listener.answer(monthly, total);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
