# AYKGYM - JavaFX Desktop Uygulaması

Bu proje, JavaFX kullanılarak geliştirilmiş bir **masaüstü spor salonu üye yönetim sistemidir**. Projede üyelik ekleme, listeleme, sınıflandırma ve filtreleme gibi temel işlemler kullanıcı dostu bir arayüzle sunulmaktadır.

## 💡 Proje Hakkında

- Geliştirme dili: **Java**
- GUI: **JavaFX**
- Veritabanı: **SQLite (JDBC)**
- IDE: **Eclipse**

Bu sistem, spor salonlarına yönelik dijital bir otomasyon çözümüdür. Üyelerin bilgileri kaydedilir, listelenir ve yönetici arayüzüyle kontrol edilir.

## 🛠️ Özellikler

- JavaFX ile kullanıcı dostu arayüz
- SQLite ile lokal veritabanı yönetimi
- Üye ekleme, silme ve güncelleme
- Üyeleri üyelik türüne göre filtreleme
- Sade ve anlaşılır tasarım

## 🚀 Kurulum

1. JDK 21 ve JavaFX SDK 21.0.2-7 sürümünü kurun.
2. SQLite JDBC kütüphanesini projeye dahil edin.
3. Eclipse'te projeyi açın.
4. Run Configurations > VM arguments kısmına şu satırı ekleyin:

```bash
--module-path /path/to/javafx-sdk-21.0.2-7/lib --add-modules javafx.controls,javafx.fxml
