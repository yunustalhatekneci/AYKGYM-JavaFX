package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Veritabani.tabloOlustur();  // Veritabanı başlatılıyor

        primaryStage.setTitle("Üye Yönetimi");

        // Başlık
        Label baslik = new Label("AYK GYM");
        baslik.setStyle(
            "-fx-font-size: 26px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #2E3B55;" +
            "-fx-padding: 10px;"
        );

        // Üye ekle butonu
        Button ekleBtn = new Button("Üye Ekle");
        ekleBtn.setStyle(
            "-fx-background-color: #ff7043;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-pref-width: 200px;" +
            "-fx-padding: 10px;"
        );
        ekleBtn.setOnAction(e -> new ÜyeEkleEkranı().goster());

        // Kayıtları listele butonu
        Button listeleBtn = new Button("Kayıtları Listele");
        listeleBtn.setStyle(
            "-fx-background-color: #42a5f5;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-pref-width: 200px;" +
            "-fx-padding: 10px;"
        );
        listeleBtn.setOnAction(e -> new ListelemeEkranı().goster());

        // Ana yerleşim
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(baslik, ekleBtn, listeleBtn);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
