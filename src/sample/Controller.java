package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import javax.swing.text.AbstractDocument;

public class Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text anchorPaneWeather;

    @FXML
    private Button getData;

    @FXML
    private TextField TheCity;

    @FXML
    private Label info_temperatur;

    @FXML
    private Text temperaturField;

    @FXML
    private Text feelsField;

    @FXML
    private Text maximumField;

    @FXML
    private Text minimumField;

    @FXML
    private Text pressureField;


    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = TheCity.getText().trim();
            if (!getUserCity.equals("")) {
                String output = getUrlContent("https://history.openweathermap.org/data/2.5/aggregated/day?q=" + getUserCity + "&month=2&day=1&appid={API key}&appid=2a89f21336230aa91b30a239f17414e2&lang=ru\";");
                System.out.println(output);

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temperaturField.setText("Tемпература:" + obj.getJSONObject("main").getDouble("temperaturField"));
                    feelsField.setText("Oщущается:" + obj.getJSONObject("main").getDouble("feelsField"));
                    maximumField.setText("Максимум:" + obj.getJSONObject("main").getDouble("maximumField"));
                    minimumField.setText("Минимум:" + obj.getJSONObject("main").getDouble("minimumField"));
                    pressureField.setText("Давление:" + obj.getJSONObject("main").getDouble("pressureField"));
                }
            }
        });

    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Такой город был не найден!");
        }
        return content.toString();
    }
}