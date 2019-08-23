package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller implements Client.ClientListener {

    @FXML
    TextField textField_renteSats;
    @FXML
    TextField textField_antalAar;
    @FXML
    TextField textField_laaneBeloeb;
    @FXML
    TextArea textArea;
    @FXML
    Button btn_godkend;


    private Client client = new Client(this);

    public void sendRequest(ActionEvent actionEvent) {

        String rente = textField_renteSats.getText();
        double renteDouble = Double.parseDouble(rente);

        String aar = textField_antalAar.getText();
        int aarInt = Integer.parseInt(aar);

        String beloeb = textField_laaneBeloeb.getText();
        double beloebDouble = Double.parseDouble(beloeb);


        client.sendTal(renteDouble, aarInt, beloebDouble);
        client.run();
    }


    @Override
    public void answer(double monthly, double total) {
        textArea.appendText("Monthly: " + monthly + "\n Total: " + total + "\n");

    }
}
