package com.example.as_tp2_oyhambure;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    EditText Nombre;
    Spinner Records;
    ImageButton[] Personajes = new ImageButton[9];
    int[] NumeroImagen = new int[9];
    boolean Victoria = false;
    Button Restarting;
    int toques = 0;
    boolean PrimerToque = true;
    TextView Captcha;
    EditText CaptchaAns;
    Button IA;
    Button SolveRan;
    TextView Turno;
    int CaptchaRes;
    int Limite = 0;
    TextView Limits;
    Random selector = new Random();
    List<String> aahhhh = new ArrayList<>();
    public void Randomize(){
        boolean diferentes = false;
        int temp1 = selector.nextInt(10);
        int temp2 = selector.nextInt(10);
        CaptchaRes=temp1 + temp2;
        Captcha.setText(temp1 + " + " + temp2 + " =");
        while(diferentes == false){
            for (int i = 0; i < 9; i++) {
                int contador = 0;
                int toca = selector.nextInt(2);
                if (toca == 1) {
                    Personajes[i].setImageResource(R.drawable.brown);
                    NumeroImagen[i] = toca;
                    contador++;
                }
                else {
                    Personajes[i].setImageResource(R.drawable.marty);
                    NumeroImagen[i] = toca;
                }
                if (i == 8 && (contador == 9 || contador == 0)){
                    diferentes = false;
                }
                else{
                    diferentes = true;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Restarting = findViewById(R.id.Reinicio);
        Nombre = findViewById(R.id.Nombre);
        Records = findViewById(R.id.Records);
        Captcha = findViewById(R.id.Captcha);
        CaptchaAns = findViewById(R.id.Num);
        IA = findViewById(R.id.Arti);
        SolveRan = findViewById(R.id.Rand);
        Turno = findViewById(R.id.Turno);
        Limits = findViewById(R.id.Limits);
        Personajes[0] = findViewById(R.id.Img1);
        Personajes[1] = findViewById(R.id.Img2);
        Personajes[2] = findViewById(R.id.Img3);
        Personajes[3] = findViewById(R.id.Img4);
        Personajes[4] = findViewById(R.id.Img5);
        Personajes[5] = findViewById(R.id.Img6);
        Personajes[6] = findViewById(R.id.Img7);
        Personajes[7] = findViewById(R.id.Img8);
        Personajes[8] = findViewById(R.id.Img9);
        Randomize();
    }

    public void PresionaBoton(View Vistitia){
        if (Nombre.getText().toString().length() <= 0){
            Toast.makeText(this, "Por favor ingrese un nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if(CaptchaAns.length() <= 0 || Integer.parseInt(CaptchaAns.getText().toString()) != CaptchaRes){
            Toast.makeText(this, "Error con el Captcha", Toast.LENGTH_LONG).show();
            return;
        }
        if(Victoria){
            Restart(Vistitia);
            return;
        }
        if(Limite > 0){
            if(toques >= Limite){
                Toast.makeText(this, "Superaste el limite de toques", Toast.LENGTH_LONG).show();
                Restart(Vistitia);
                Limite = 0;
                return;
            }
            else{
                Limits.setText("Limite de Toques: " + Limite);
                toques++;
            }
        }
        else{
            toques++;
        }
        if(PrimerToque){
            Captcha.setVisibility(View.GONE);
            CaptchaAns.setVisibility(View.GONE);
            IA.setVisibility(View.GONE);
            SolveRan.setVisibility(View.GONE);
            Turno.setVisibility(View.VISIBLE);
            Turno.setText("Toques: " + toques);
            Records.setVisibility(View.VISIBLE);
            if(Limite > 0){
                Limits.setVisibility(View.VISIBLE);
            }
        }
        CambiarFoto(Personajes[4], 4);
        switch (Vistitia.getTag().toString()) {
            case "1":
                CambiarFoto(Personajes[0], 0);
                CambiarFoto(Personajes[1], 1);
                CambiarFoto(Personajes[3], 3);
                break;
            case "2":
                CambiarFoto(Personajes[0], 0);
                CambiarFoto(Personajes[1], 1);
                CambiarFoto(Personajes[2], 2);
                break;
            case "3":
                CambiarFoto(Personajes[5], 5);
                CambiarFoto(Personajes[1], 1);
                CambiarFoto(Personajes[2], 2);
                break;
            case "4":
                CambiarFoto(Personajes[0], 0);
                CambiarFoto(Personajes[3], 3);
                CambiarFoto(Personajes[6], 6);
                break;
            case "5":
                CambiarFoto(Personajes[1], 1);
                CambiarFoto(Personajes[3], 3);
                CambiarFoto(Personajes[5], 5);
                CambiarFoto(Personajes[7], 7);
                break;
            case "6":
                CambiarFoto(Personajes[2], 2);
                CambiarFoto(Personajes[5], 5);
                CambiarFoto(Personajes[8], 8);
                break;
            case "7":
                CambiarFoto(Personajes[3], 3);
                CambiarFoto(Personajes[6], 6);
                CambiarFoto(Personajes[7], 7);
                break;
            case "8":
                CambiarFoto(Personajes[6], 6);
                CambiarFoto(Personajes[7], 7);
                CambiarFoto(Personajes[8], 8);
                break;
            case "9":
                CambiarFoto(Personajes[7], 7);
                CambiarFoto(Personajes[8], 8);
                CambiarFoto(Personajes[5], 5);
                break;
            default:
                Toast.makeText(this, "GREAT SCOTT! NO DEBERIAS ESTAR ACA", Toast.LENGTH_LONG).show();
                break;

        }
        int results = 0;
        for (int i = 0; i < NumeroImagen.length; i++){
            if (NumeroImagen[i] == 1){
                results++;
            }
        }
        if(results == 0 || results == 9){
            Victoria = true;
        }
        if (Victoria){
            Restarting.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Felicidades, Ganaste", Toast.LENGTH_LONG).show();
            Limite = toques;
            ArrayAdapter<String> adapter;
            aahhhh.add(Nombre.getText().toString());
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, aahhhh);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Records.setAdapter(adapter);
            return;
        }
    }

    public void CambiarFoto (ImageButton Cambiado, int num){
        if(NumeroImagen[num] == 1){
            Cambiado.setImageResource(R.drawable.marty);
            NumeroImagen[num] = 0;
        }
        else {
            Cambiado.setImageResource(R.drawable.brown);
            NumeroImagen[num] = 1;
        }
    }

    public void Restart(View Vistasa){
        Randomize();
        Restarting.setVisibility(View.GONE);
        toques = 0;
        Nombre.setText("");
        Victoria=false;
        Captcha.setVisibility(View.VISIBLE);
        CaptchaAns.setVisibility(View.VISIBLE);
        CaptchaAns.setText("");
        IA.setVisibility(View.VISIBLE);
        SolveRan.setVisibility(View.VISIBLE);
        Turno.setVisibility(View.GONE);
        Records.setVisibility(View.GONE);
        Limits.setVisibility(View.GONE);
    }

    public void ResRan(View Viasasa){
        if (Nombre.getText().toString().length() <= 0){
        Toast.makeText(this, "Por favor ingrese un nombre", Toast.LENGTH_LONG).show();
        return;
        }
        if(CaptchaAns.length() <= 0 || Integer.parseInt(CaptchaAns.getText().toString()) != CaptchaRes){
            Toast.makeText(this, "Error con el Captcha", Toast.LENGTH_LONG).show();
            return;
        }
        final Timer MiReloj;
        MiReloj=new Timer();
        TimerTask MiTareaARepetir;
        MiTareaARepetir=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PresionaBoton((View)Personajes[selector.nextInt(9)]);
                        if(Victoria == true){
                            MiReloj.cancel();
                        }
                    }
                });
            }
        };
        MiReloj.schedule(MiTareaARepetir, 0, 500);
    }

    public void MasArtificial(View Vistosito){
        if (Nombre.getText().toString().length() <= 0){
            Toast.makeText(this, "Por favor ingrese un nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if(CaptchaAns.length() <= 0 || Integer.parseInt(CaptchaAns.getText().toString()) != CaptchaRes){
            Toast.makeText(this, "Error con el Captcha", Toast.LENGTH_LONG).show();
            return;
        }
        int Marts = 0;
        int Docs = 0;
        int[] pos = new int[8];
        for (int i = 0; i < Personajes.length; i++){
            if (NumeroImagen[i] == 1){
                Docs++;
            }
            else{
                Marts++;
            }
        }
        final int DocsF = Docs;
        final int MartsF = Marts;
        final Timer MiReloj;
        MiReloj=new Timer();
        TimerTask MiTareaARepetir;
        MiTareaARepetir=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<Integer> pos = new ArrayList<>();
                        int con = 0;
                        if(DocsF > MartsF){
                            for (int i = 0; i < NumeroImagen.length; i++) {
                                if (NumeroImagen[i] == 0){
                                    pos.add(i);
                                    con++;
                                }
                            }
                        }
                        else if(DocsF < MartsF){
                            for (int i = 0; i < NumeroImagen.length; i++) {
                                if (NumeroImagen[i] == 1){
                                    pos.add(i);
                                    con++;
                                }
                            }
                        }
                        for (int i = 0; i < pos.size(); i++){
                            PresionaBoton((View)Personajes[pos.get(i)]);
                        }
                        if(Victoria == true){
                            MiReloj.cancel();
                        }
                    }
                });
            }
        };
        MiReloj.schedule(MiTareaARepetir, 0, 500);
    }
}
