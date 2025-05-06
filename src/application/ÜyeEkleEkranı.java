package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ÜyeEkleEkranı {

    public void goster() {
        Stage pencere = new Stage();
        pencere.setTitle("Üye Ekle");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField adSoyadField = new TextField();
        TextField telefonField = new TextField();
        TextField yasField = new TextField();

        ComboBox<String> salonBox = new ComboBox<>();
        salonBox.getItems().addAll("Zorlu", "Trump Towers", "Kanyon");

        ComboBox<String> uyelikBox = new ComboBox<>();
        uyelikBox.getItems().addAll("GOLD (4500 TL/ay)", "SILVER (2500 TL/ay)", "BRONZE (1500 TL/ay)");

        CheckBox yillikCheck = new CheckBox("Yıllık Üye");

        ComboBox<String> ptBox = new ComboBox<>();
        ptBox.getItems().addAll("Abdulaziz Abdukhamidov", "Yunus Talha Tekneci", "Koray Karadağ");

        ComboBox<String> ptSaatBox = new ComboBox<>();
        ptSaatBox.getItems().addAll("10 saat / 5000 TL", "15 saat / 6500 TL");
        ptSaatBox.setPromptText("PT Ders Saati Seçimi");
        ptSaatBox.setDisable(true);

        ptBox.setPromptText("PT Seçimi");
        ptBox.setOnAction(e -> {
            if (ptBox.getValue() != null && !ptBox.getValue().isEmpty()) {
                ptSaatBox.setDisable(false);
            } else {
                ptSaatBox.setDisable(true);
                ptSaatBox.getSelectionModel().clearSelection();
            }
        });

        Label uyelikDetayLabel = new Label();
        uyelikDetayLabel.setWrapText(true);

        uyelikBox.setOnAction(e -> {
            String secim = uyelikBox.getValue();
            if (secim != null) {
                String bilgi = switch (secim) {
                    case "GOLD (4500 TL/ay)" -> "GOLD: Sauna + Havuz + Fitness + Tüm salonlara giriş";
                    case "SILVER (2500 TL/ay)" -> "SILVER: Havuz + Fitness + GOLD hariç tüm salonlara giriş";
                    default -> "BRONZE: Fitness + Sadece kayıtlı salona giriş";
                };
                uyelikDetayLabel.setText(bilgi);
            }
        });

        Button kaydetBtn = new Button("Kaydet");
        kaydetBtn.setOnAction(e -> {
            try {
                String adSoyad = adSoyadField.getText();
                String telefon = telefonField.getText();
                int yas = Integer.parseInt(yasField.getText());
                String salonAdi = salonBox.getValue();
                String uyelikSecimi = uyelikBox.getValue();
                boolean yillikMi = yillikCheck.isSelected();
                String ptAdi = ptBox.getValue();
                String ptSaatSecimi = ptSaatBox.getValue();

                if (salonAdi == null || uyelikSecimi == null || ptAdi == null || ptSaatSecimi == null) {
                    showAlert("Hata", "Lütfen tüm seçimleri eksiksiz yapınız.");
                    return;
                }

                int ucret;
                String uyelikTuru;
                if (uyelikSecimi.contains("GOLD")) {
                    ucret = 4500;
                    uyelikTuru = "GOLD";
                } else if (uyelikSecimi.contains("SILVER")) {
                    ucret = 2500;
                    uyelikTuru = "SILVER";
                } else {
                    ucret = 1500;
                    uyelikTuru = "BRONZE";
                }

                int saat = ptSaatSecimi.contains("10") ? 10 : 15;
                PT pt = new PT(ptAdi, saat);
                SporSalonu salon = new SporSalonu(salonAdi, ucret, true, true, pt);

                Üyelik uye = new Üyelik(adSoyad, telefon, yas, salon, yillikMi);
                uye.setPt(pt);

                Veritabani.uyeEkle(adSoyad, telefon, yas, salonAdi, uyelikTuru, yillikMi, ptAdi);
                showAlert("Başarılı", "Üye başarıyla eklendi.");
                pencere.close();

            } catch (NumberFormatException ex) {
                showAlert("Hata", "Lütfen yaş alanına geçerli bir sayı girin.");
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Hata", "Bir hata oluştu: " + ex.getMessage());
            }
        });

        grid.add(new Label("Ad Soyad:"), 0, 0);
        grid.add(adSoyadField, 1, 0);

        grid.add(new Label("Telefon:"), 0, 1);
        grid.add(telefonField, 1, 1);

        grid.add(new Label("Yaş:"), 0, 2);
        grid.add(yasField, 1, 2);

        grid.add(new Label("Salon Seçimi:"), 0, 3);
        grid.add(salonBox, 1, 3);

        grid.add(new Label("Üyelik Tipi:"), 0, 4);
        grid.add(uyelikBox, 1, 4);

        grid.add(uyelikDetayLabel, 1, 5);

        grid.add(new Label("PT Seçimi:"), 0, 6);
        grid.add(ptBox, 1, 6);

        grid.add(new Label("Ders Saati:"), 0, 7);
        grid.add(ptSaatBox, 1, 7);

        grid.add(yillikCheck, 1, 8);
        grid.add(kaydetBtn, 1, 9);

        Scene scene = new Scene(grid, 500, 520);
        pencere.setScene(scene);
        pencere.show();
    }

    private void showAlert(String baslik, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(baslik);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }
}
