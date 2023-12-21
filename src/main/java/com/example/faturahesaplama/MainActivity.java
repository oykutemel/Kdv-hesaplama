package com.example.faturahesaplama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Kullanıcıdan alınacak fiyat ve KDV bilgileri için EditText'ler
    EditText edt_fiyat, edt_kdv;

    // Hesaplama işlemini gerçekleştirecek buton
    Button btn_hesapla;

    // Uygulama içinde kullanılacak bağlam (context)
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML dosyasındaki öğeleri kod ile eşleştirme
        edt_fiyat = findViewById(R.id.edt_fiyat);
        edt_kdv = findViewById(R.id.edt_kdv);
        btn_hesapla = findViewById(R.id.btn_hesapla);

        // Hesaplama butonuna tıklama dinleyicisi eklenmesi
        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kullanıcının girdiği fiyat ve KDV bilgilerini alınması
                float fiyat = Float.valueOf(edt_fiyat.getText().toString());
                float kdv = Float.valueOf(edt_kdv.getText().toString());

                // Toplam fiyatın hesaplanması
                float toplam_fiyat = (fiyat * kdv) / 100 + fiyat;

                // Sonuçları göstermek için özel bir diyalog penceresi oluşturulması
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog);

                // Diyalog içindeki öğelerin tanımlanması
                ImageView carpi = dialog.findViewById(R.id.carpi);
                TextView aciklama = dialog.findViewById(R.id.aciklama);
                Button tamam = dialog.findViewById(R.id.tamam);

                // Sonuçların diyalog içindeki TextView'e yazdırılması
                aciklama.setText("Girilen fiyat: " + fiyat + "\nKDV oranı: " + kdv + "\nToplam fiyat: " + toplam_fiyat);

                // Kapatma butonuna tıklanınca diyalog penceresinin kapatılması
                carpi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // Tamam butonuna tıklanınca Toast mesajı gösterilmesi ve diyalog penceresinin kapatılması
                tamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Sonuçlar incelendi", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                // Diyalog penceresinin gösterilmesi
                dialog.show();
            }
        });
    }
}
