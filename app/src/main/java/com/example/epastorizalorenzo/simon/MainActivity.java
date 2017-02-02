package com.example.epastorizalorenzo.simon;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

      //MediaPlayer soniVer, soniAmar, soniRoj, soniAzl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* soniVer = MediaPlayer.create(this, R.raw.Crack);
        soniAmar = MediaPlayer.create(this, R.raw.NiñosCelebrando);
        soniRoj = MediaPlayer.create(this, R.raw.EestoQueEs);
        soniAzl = MediaPlayer.create(this, R.raw.sonidoSacudido);*/

        Button empezar = (Button) findViewById(R.id.Start);
        Button botonVerde = (Button) findViewById(R.id.Verde);
        Button botonAmarillo = (Button) findViewById(R.id.Amarillo);
        Button botonRojo = (Button) findViewById(R.id.Rojo);
        Button botonAzul = (Button) findViewById(R.id.Azul);

        botonVerde.setEnabled(false);
        botonAmarillo.setEnabled(false);
        botonRojo.setEnabled(false);
        botonAzul.setEnabled(false);
    }

    int[] botones = {R.id.Verde, R.id.Amarillo, R.id.Rojo, R.id.Azul};
    String[] colorBotones = {"#199630", "#a6ad1f", "#721411", "##0e1851"};
    int[] colorClaro = {R.id.Verde, R.id.Amarillo, R.id.Rojo, R.id.Azul};
    //int[] audios = {R.raw.Crack, R.raw.NiñosCelebrando, R.raw.EestoQueEs, R.raw.sonidoSacudido};

    ArrayList<Integer> coloresAleatorios = new ArrayList();
    ArrayList<Integer> jugador = new ArrayList();

    TimerTask tiempoProceso;
    Timer tiempo;

    protected static int CONTADOR = 0;
    protected static int CONT = 0;
    protected static int COLORES = 4;
    protected static int AUX = 0;

    public void empezar(View v) {


        findViewById(R.id.Verde).setEnabled(true);
        findViewById(R.id.Amarillo).setEnabled(true);
        findViewById(R.id.Rojo).setEnabled(true);
        findViewById(R.id.Azul).setEnabled(true);
        aleatorio();
        CONTADOR = 0;
        AUX = 0;
        empezarTimer();
        v.setEnabled(false);

    }


    public void eventos(View v) {

        CONTADOR++;

        if (v.getId() == R.id.Verde) {
            jugador.add(1);
            parpadear(1);
        } else if (v.getId() == R.id.Amarillo) {
            jugador.add(0);
            parpadear(0);
        } else if (v.getId() == R.id.Rojo) {
            jugador.add(2);
            parpadear(2);
        } else if (v.getId() == R.id.Azul) {
            jugador.add(3);
            parpadear(3);
        }
        resetear();
        comprobar();
    }

    void parpadear(final int parpadeo) {

        findViewById(botones[parpadeo]).setBackgroundColor(colorClaro[parpadeo]);
       // final MediaPlayer audio = MediaPlayer.create(this, audios[parpadeo]);
        //  audio.start();
        findViewById(botones[parpadeo]).postDelayed(new Runnable() {
            public void run() {
               //  audio.reset();
                findViewById(botones[parpadeo]).setBackgroundColor(Color.parseColor(colorBotones[parpadeo]));
            }
        }, 1000);

    }

    void aleatorio() {

        if (COLORES == 4) {
            for (int i = 0; i < 4; i++) {
                int valor1 = (int) Math.floor(Math.random() * 4);
                coloresAleatorios.add(valor1);
            }
        } else {
            int valor1 = (int) Math.floor(Math.random() * 4);
            coloresAleatorios.add(valor1);
        }
    }


    void resetear() {

        Button start = (Button) findViewById(R.id.Start);
        if (CONTADOR == COLORES) {
            comprobar();
            jugador.clear();
            CONT = 0;
            start.setEnabled(true);
        }
    }

    void comprobar() {
        if (coloresAleatorios.toString().equals(jugador.toString())) {
            Toast.makeText(this, "Ganaste :) ", Toast.LENGTH_SHORT).show();
            CONT++;


        } else {
            Toast.makeText(this, "Perdiste, sigue intentandolo", Toast.LENGTH_SHORT).show();
            CONT = 4;
            coloresAleatorios.clear();

        }
    }


    void inicializarTimer() {
        tiempoProceso = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (coloresAleatorios.get(AUX) == 0) {
                            parpadear(0);
                        }
                        if (coloresAleatorios.get(AUX) == 1) {
                            parpadear(1);
                        }
                        if (coloresAleatorios.get(AUX) == 2) {
                            parpadear(2);
                        }
                        if (coloresAleatorios.get(AUX) == 3) {
                            parpadear(3);
                        }
                        AUX++;
                        CONT++;
                        if (CONT == COLORES) {
                            pararTimer();
                        }

                    }
                }
                );

            }
        };

//comentario
    }


    public void empezarTimer() {
        tiempo = new Timer();
        inicializarTimer();
        tiempo.schedule(tiempoProceso, 0, 1000);
    }


    public void pararTimer() {
        if (tiempo != null) {
            tiempo.cancel();
            tiempo = null;
        }
    }
}




