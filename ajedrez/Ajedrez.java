package ajedrez;

import java.util.Scanner;
import java.util.Random;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Ajedrez{

    public static void main(String[] args) {
        
        //Arreglo de tipo Pieza para formar el tablero
        Pieza[][] tablero = new Pieza[8][8];
        
        //Torres - 4
        tablero[0][0] = new Torre(4, 0, 0, false);
        tablero[0][7] = new Torre(4, 7, 0, false);
        tablero[7][0] = new Torre(4, 0, 7, true);
        tablero[7][7] = new Torre(4, 7, 7, true);
        //Caballos - 3
        tablero[0][1] = new Caballo(3, 1, 0, false);
        tablero[0][6] = new Caballo(3, 6, 0, false);
        tablero[7][1] = new Caballo(3, 1, 7, true);
        tablero[7][6] = new Caballo(3, 6, 7, true);
        //Alfiles - 2
        tablero[0][2] = new Alfil(2, 2, 0, false);
        tablero[0][5] = new Alfil(2, 5, 0, false);
        tablero[7][2] = new Alfil(2, 2, 7, true);
        tablero[7][5] = new Alfil(2, 5, 7, true);
        //Reina - 5
        tablero[0][3] = new Reina(5, 3, 0, false);
        tablero[7][3] = new Reina(5, 3, 7, true);
        //Reyes - 1
        tablero[0][4] = new Rey(1, 4, 0, false);
        tablero[7][4] = new Rey(1, 4, 7, true);
        //Peones - 6
        for (int i = 0; i < 8; i++) {
            tablero[1][i] = new Peon(6, i, 1, false, i, 1);
            tablero[6][i] = new Peon(6, i, 6, true, i, 6);
        }

        //Se genera grafico de tablero
        System.out.println("\n\n");
        for (int i = 0; i < 8; i++) {
            System.out.println("");
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j] != null) {
                    System.out.print(" " + tablero[i][j]);
                } else {
                    System.out.print(" [ ]");
                }
            }
        }
        
        //Se genera instancia de clase UserInterface
        UserInterface GUI = new UserInterface(tablero); 
    }
}
