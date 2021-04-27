package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.text.DecimalFormat;

public class Controller {

    public Button btnMain;
    public TextField textLicznik;
    public TextField textLicznik1;
    public Label waga;
    public Label cena;
    public TextField textLicznik2;
    public RadioButton stalZw;
    public RadioButton stalNierdz;
    public ToggleGroup toggleGroup;
    double obszar;
    double wysokosc;
    double promien;
    double grubosc;
    int clickCounter = 0;
    double fullCena;
    double wagaC;
    double obFig;
    final double gestoscStaliNie = 8.05; // g/cm^3
    final double gestoscStali = 7.8; // g/cm^3
    String error;

    public void calculation() {
        error = null;
        wysokosc = Double.parseDouble(textLicznik.getText()); // cm
        promien = Double.parseDouble(textLicznik1.getText()); // cm
        grubosc = (Double.parseDouble(textLicznik2.getText())); // cm
        obszar = 2 * Math.PI * promien * (promien + wysokosc); // cm
        if (grubosc > promien / 2 || grubosc > wysokosc / 2) {
            error = "nie wlasciwa grubosc";
        }
        if (toggleGroup.getSelectedToggle().equals(stalZw)) {
            fullCena = obszar / 10000 * (grubosc / 0.0025); // zl = cm^2 * cm
            obFig = obszar * grubosc; // cm^3 = cm^2*cm
            wagaC = (gestoscStali * obFig) / 1000; // kg = (g/cm^3 * cm^3)/1000
        } else if (toggleGroup.getSelectedToggle().equals(stalNierdz)) {
            fullCena = obszar / 10000 * (grubosc / 0.00071); // zl = cm^2 * cm
            obFig = obszar * grubosc; // cm^3 = cm^2*cm
            wagaC = (gestoscStaliNie * obFig) / 1000; // kg = (g/cm^3 * cm^3)/1000
        }
    }

    public void onBtnAction(ActionEvent actionEvent) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
     /*   ++clickCounter;
        if (clickCounter == 1) {
            btnMain.setText("I jeszsze raz");
        }
        textLicznik.setText(String.valueOf(clickCounter));
        System.out.println("Ilosc razy " + clickCounter);*/
        calculation();
        if (error == null) {
            cena.setText(String.valueOf(decimalFormat.format(fullCena)));
            waga.setText(String.valueOf(decimalFormat.format(wagaC)));
        } else if (error != null) {
            cena.setText("Error: " + error);
            waga.setText("Error: " + error);
        }
    }
}
