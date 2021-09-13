package ajedrez;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

//Clase UserInterface hereda de JFrame e implementa interfaces ActionListener y Musica 
public class UserInterface extends JFrame implements ActionListener, Musica {

    //Atributos para generar arreglo de tipo pieza para el tablero y matriz de botones
    Pieza[][] tablero;
    JButton botones[][];

    //Atributos para identificar tiro realizado, tiro no permitido y turno actual
    Boolean turno;
    Boolean tiro;
    Boolean na;

    //Atributos para posicion de pieza seleccionada, destino, posicion de Rey blanco y negro, numero de intentos para jaque mate y numero de pieza a coronar
    int x, y, xd, yd, xB, yB, xW, yW, mate, corona;

    //Genera barra de menu superior
    private JMenu jmenuFile, jmenuHelp;
    private JMenuItem jmenuitemExit, jmenuitemAbout;

    //Genera tipo de fuente
    Font f12 = new Font("Times New Roman", 0, 12);
    Font f121 = new Font("Times New Roman", 1, 12);

    //Genera label para mostrar tiempo
    public JTextField txtTiempo = new JTextField(3);
    JLabel lTiempo = new JLabel("Tiempo transcurrido");
    Tiempo tp;

    //Constructor para UserInterface con parametro arreglo de tipo Pieza (Tablero)
    UserInterface(Pieza[][] tablero) {

        //Se genera Menu superior para opcion File e Info con hotkey
        jmenuFile = new JMenu("File");
        jmenuFile.setFont(f121);
        jmenuFile.setMnemonic(KeyEvent.VK_F);

        jmenuitemExit = new JMenuItem("Exit");
        jmenuitemExit.setFont(f12);
        jmenuitemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        jmenuFile.add(jmenuitemExit);

        jmenuHelp = new JMenu("Info");
        jmenuHelp.setFont(f121);
        jmenuHelp.setMnemonic(KeyEvent.VK_I);

        jmenuitemAbout = new JMenuItem("About Game");
        jmenuitemAbout.setFont(f12);
        jmenuHelp.add(jmenuitemAbout);

        JMenuBar mb = new JMenuBar();
        mb.add(jmenuFile);
        mb.add(jmenuHelp);
        setJMenuBar(mb);

        //Se agrega action listener para responder a interacciones
        jmenuitemAbout.addActionListener(this);
        jmenuitemExit.addActionListener(this);

        //Se inician en (0,0) pieza seleccionada y destino
        x = 0;
        y = 0;
        xd = 0;
        yd = 0;

        //Se inicializan en las coordenadas (4,0) y (4,7) para las posiciones de los reyes
        xB = 4;
        yB = 0;
        xW = 4;
        yW = 7;

        //Se inicializa el turno para Blancas (true), tiro realizado en false y tiro no permitido en false
        turno = true;
        tiro = false;
        na = false;

        //Se asigna el atributo tablero como el tablero recibido por el parametro del constructor
        this.tablero = tablero;

        //Se genera un objeto de AudioClip para reproducir musica y se usa el metodo musicOn para reproducir audio
        AudioClip sound = null;
        sound = this.musicOn(sound);

        //Se inicializa la matriz de botones 8x8
        botones = new JButton[8][8];

        //Se agrega panel que muestra tiempo de juego
        JPanel panelSup = new JPanel();
        panelSup.add(lTiempo);
        panelSup.add(txtTiempo);
        add(panelSup, "North");
        txtTiempo.setEditable(false);

        //Se genera panel para colocar botones
        JPanel panelMedio = new JPanel(new GridLayout(8, 8));
        //Crear y colocar botones
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //Crear boton
                botones[i][j] = new JButton();
                botones[i][j].setSize(75, 75);
                if ((i + j) % 2 == 0) {
                    botones[i][j].setBackground(new Color(253, 233, 194));
                } else {
                    botones[i][j].setBackground(new Color(102, 53, 5));
                }
                //Colocando piezas
                if (tablero[i][j] != null && tablero[i][j].isColor()) {
                    ImageIcon pieza = new ImageIcon(tablero[i][j].getNumPieza() + "W.png");
                    Icon icono = new ImageIcon(pieza.getImage().getScaledInstance(botones[i][j].getWidth(), botones[i][j].getHeight(), Image.SCALE_DEFAULT));
                    botones[i][j].setIcon(icono);
                } else if (tablero[i][j] != null && tablero[i][j].isColor() == false) {
                    ImageIcon pieza = new ImageIcon(tablero[i][j].getNumPieza() + "B.png");
                    Icon icono = new ImageIcon(pieza.getImage().getScaledInstance(botones[i][j].getWidth(), botones[i][j].getHeight(), Image.SCALE_DEFAULT));
                    botones[i][j].setIcon(icono);
                }
                //Colocar en el panel
                panelMedio.add(botones[i][j]);
                //Action Listener
                botones[i][j].addActionListener(this);
            }
        }
        this.add(panelMedio, "Center");

        //Comienza a correr el tiempo
        tp = new Tiempo(this);
        tp.start();

        //Se definen opciones para la ventana generada
        setResizable(true);
        setTitle("CHESS");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Sobreescritura de metodo MusicOn
    @Override
    public AudioClip musicOn(AudioClip sound) {

        //Se genera un numero aleatorio que permite accesar a un archivo tipo WAV con ese numero que reproduce en loop (Audio aleatorio al entrar al juego)
        Random r = new Random();
        sound = java.applet.Applet.newAudioClip(getClass().getResource("/ajedrez/" + r.nextInt(3) + ".WAV"));
        sound.loop();
        return sound;
    }

    //Sobreescritura de metodo musicOff
    @Override
    public void musicOff(AudioClip sound) {
        sound.stop();
    }

    //Sobreescritura de actionPerformed
    @Override
    public void actionPerformed(ActionEvent ae) {

        //Comprueba si se interactuo la barra de menu
        if (ae.getSource() == jmenuitemAbout) {
            JDialog dlgAbout = new CustomABOUTDialog(this, "CHESS", true);
            dlgAbout.setVisible(true);
        } else if (ae.getSource() == jmenuitemExit) {
            System.exit(0);
        }

        //Busca fuente del evento invocador
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ae.getSource() == botones[i][j]) {

                    //Comprueba si el Rey esta en Jaque durante el turno
                    if (turno) {
                        System.out.println("\n\nTurno de blancas");
                        try {
                            if (tablero[yW][xW].Jaque(tablero)) {
                                System.out.println("El Rey Blanco esta en Jaque");
                            }
                        } catch (UnsupportedOperationException uoe) {
                            System.out.println("ERROR: Rey no encontrado");
                        }
                    } else {
                        System.out.println("\n\nTurno de negras");
                        try {
                            if (tablero[yB][xB].Jaque(tablero)) {
                                System.out.println("El Rey Negro esta en Jaque");
                            }
                        } catch (UnsupportedOperationException uoe) {
                            System.out.println("ERROR: Rey no encontrado");
                        }
                    }

                    //Se comprueba si en el arreglo de tipo Pieza existe una pieza en las coordenas que activaron el evento, se ve si la pieza es de color del turno
                    //Se comprueba si el tiro no ha sido realizado
                    if (tablero[i][j] != null && turno == tablero[i][j].isColor() && tiro == false) {
                        x = j;
                        y = i;
                        tiro = true;

                        System.out.println("Valor de x: " + x + " Valor de y: " + y);
                    } //Se comprueba si la pieza que se desea desplazar es del color del turno y si el tiro ya fue realizado 
                    else if (turno == tablero[y][x].isColor() && tiro == true) {
                        xd = j;
                        yd = i;

                        //Se cambian las posiciones de la pieza en tablero
                        if (tablero[y][x] != null) {
                            tablero[y][x].setPosX(x);
                            tablero[y][x].setPosY(y);
                        }

                        //Comprueba si en la coordenada seleccionada hay una pieza, el movimiento de la pieza al destino es valida y el turno es el de la pieza
                        if (tablero[y][x] != null && tablero[y][x].movimiento(xd, yd, tablero) && tablero[y][x].color == turno) {

                            //Copia ultima posicion de la pieza a mover y actualiza despues la posicion
                            Pieza aux = tablero[yd][xd];
                            tablero[yd][xd] = tablero[y][x];
                            tablero[yd][xd].setPosX(xd);
                            tablero[yd][xd].setPosY(yd);
                            tablero[y][x] = null;

                            //Comprobacion de movimiento valido para Rey blanco
                            if (tablero[yd][xd] != null && tablero[yd][xd].getNumPieza() == 1 && turno == true) {
                                xW = xd;
                                yW = yd;
                                System.out.println("Nueva posicion de Rey: " + xW + "," + yW);

                                if (tablero[yd][xd].Jaque(tablero)) {
                                    JOptionPane.showMessageDialog(null, "El Rey Blanco esta en Jaque", "JAQUE", JOptionPane.WARNING_MESSAGE);
                                    xW = x;
                                    yW = y;

                                    tablero[y][x] = tablero[yd][xd];

                                    if (aux != null) {
                                        tablero[yd][xd] = aux;
                                    } else {
                                        tablero[yd][xd] = null;
                                    }
                                    na = true;
                                    mate++;
                                }
                            } //Comprobacion de movimiento valido para Rey negro
                            else if (tablero[yd][xd] != null && tablero[yd][xd].getNumPieza() == 1 && turno == false) {
                                xB = xd;
                                yB = yd;
                                System.out.println("Nueva posicion de Rey: " + xB + "," + yB);

                                if (tablero[yd][xd].Jaque(tablero)) {
                                    JOptionPane.showMessageDialog(null, "El Rey Negro esta en Jaque", "JAQUE", JOptionPane.WARNING_MESSAGE);
                                    xB = x;
                                    yB = y;

                                    tablero[y][x] = tablero[yd][xd];

                                    if (aux != null) {
                                        tablero[yd][xd] = aux;
                                    } else {
                                        tablero[yd][xd] = null;
                                    }

                                    na = true;
                                    mate++;
                                }
                            } //Comprueba si el Rey blanco esta en Jaque 
                            else if (tablero[yW][xW].Jaque(tablero) && turno) {
                                JOptionPane.showMessageDialog(null, "El Rey Blanco esta en Jaque", "JAQUE", JOptionPane.WARNING_MESSAGE);
                                tablero[y][x] = tablero[yd][xd];

                                if (aux != null) {
                                    tablero[yd][xd] = aux;
                                } else {
                                    tablero[yd][xd] = null;
                                }
                                na = true;
                                mate++;
                            } //Comprueba si el Rey negro esta en Jaque  
                            else if (tablero[yB][xB].Jaque(tablero) && turno == false) {
                                JOptionPane.showMessageDialog(null, "El Rey Negro esta en Jaque", "JAQUE", JOptionPane.WARNING_MESSAGE);
                                tablero[y][x] = tablero[yd][xd];

                                if (aux != null) {
                                    tablero[yd][xd] = aux;
                                } else {
                                    tablero[yd][xd] = null;
                                }
                                na = true;
                                mate++;
                            } else {
                                //Comprueba si un Peon blanco se puede coronar
                                if (yd == 0 && tablero[yd][xd].getNumPieza() == 6 && tablero[yd][xd].isColor() == true) {

                                    corona = Integer.parseInt(JOptionPane.showInputDialog("Elige una pieza: \n2 - Alfil \n3 - Caballo \n4 - Torre \n5 - Reina"));

                                    switch (corona) {
                                        case 2:
                                            tablero[yd][xd] = new Alfil(2, xd, yd, true);
                                            break;
                                        case 3:
                                            tablero[yd][xd] = new Caballo(3, xd, yd, true);
                                            break;
                                        case 4:
                                            tablero[yd][xd] = new Torre(4, xd, yd, true);
                                            break;
                                        case 5:
                                            tablero[yd][xd] = new Reina(5, xd, yd, true);
                                            break;
                                    }
                                } //Comprueba si un Peon negro se puede coronar 
                                else if (yd == 7 && tablero[yd][xd].getNumPieza() == 6 && tablero[yd][xd].isColor() == false) {
                                    corona = Integer.parseInt(JOptionPane.showInputDialog("Elige una pieza: \n2 - Alfil \n3 - Caballo \n4 - Torre \n5 - Reina"));

                                    switch (corona) {
                                        case 2:
                                            tablero[yd][xd] = new Alfil(2, xd, yd, false);
                                            break;
                                        case 3:
                                            tablero[yd][xd] = new Caballo(3, xd, yd, false);
                                            break;
                                        case 4:
                                            tablero[yd][xd] = new Torre(4, xd, yd, false);
                                            break;
                                        case 5:
                                            tablero[yd][xd] = new Reina(5, xd, yd, false);
                                            break;
                                    }
                                }
                                na = false;
                            }

                            //Comprueba si el movimiento logró realizarse con exito
                            if (na == false) {
                                turno = !turno;
                                mate = 0;
                            }
                            //Comprueba si se logro hacer jaque mate (Jaque mate se consigue al realizar 5 movimientos no permitidos)
                            if (na == true && mate == 5) {
                                tp.stop();
                                if (turno) {
                                    JOptionPane.showMessageDialog(null, "¡Ganan negras!", "JAQUE MATE", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "¡Ganan blancas!", "JAQUE MATE", JOptionPane.WARNING_MESSAGE);
                                }
                                this.dispose();
                                System.exit(0);
                            }
                        } //Notifica que el movimiento no es valido
                        else {
                            JOptionPane.showMessageDialog(null, "Movimiento invalido", "MOVIMIENTO INVALIDO", JOptionPane.WARNING_MESSAGE);
                            na = true;
                        }

                        //Imprime en consola un tablero actualizado
                        System.out.println("\n\n");
                        for (i = 0; i < 8; i++) {
                            System.out.println("");
                            for (j = 0; j < 8; j++) {
                                if (tablero[i][j] != null) {
                                    System.out.print(" " + tablero[i][j]);
                                } else {
                                    System.out.print(" [ ]");
                                }
                            }
                        }

                        //Actualiza el panel de botones con los cambios realizados en la jugada
                        for (i = 0; i < 8; i++) {
                            for (j = 0; j < 8; j++) {
                                if ((i + j) % 2 == 0) {
                                    botones[i][j].setBackground(new Color(253, 233, 194));
                                } else {
                                    botones[i][j].setBackground(new Color(102, 53, 5));
                                }
                                //Colocando piezas
                                if (tablero[i][j] != null && tablero[i][j].isColor()) {
                                    ImageIcon pieza = new ImageIcon(tablero[i][j].getNumPieza() + "W.png");
                                    Icon icono = new ImageIcon(pieza.getImage().getScaledInstance(botones[i][j].getWidth(), botones[i][j].getHeight(), Image.SCALE_DEFAULT));
                                    botones[i][j].setIcon(icono);
                                } else if (tablero[i][j] != null && tablero[i][j].isColor() == false) {
                                    ImageIcon pieza = new ImageIcon(tablero[i][j].getNumPieza() + "B.png");
                                    Icon icono = new ImageIcon(pieza.getImage().getScaledInstance(botones[i][j].getWidth(), botones[i][j].getHeight(), Image.SCALE_DEFAULT));
                                    botones[i][j].setIcon(icono);
                                } else {
                                    botones[i][j].setIcon(null);
                                }

                            }
                        }
                        tiro = false;
                    } //Notifica movimiento invalido 
                    else {
                        JOptionPane.showMessageDialog(null, "Movimiento Invalido", "INVALIDO", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }

    //Genera File e Info en el panel de Menu superior
    class CustomABOUTDialog extends JDialog implements ActionListener {

        JButton jbnOk;

        CustomABOUTDialog(JFrame parent, String title, boolean modal) {
            super(parent, title, modal);
            setBackground(Color.black);

            JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

            StringBuffer text = new StringBuffer();
            text.append("CREDITOS\n\n");
            text.append("             DESARROLADORES:\n");
            text.append(" CERVANTES SOLIS ALAN GIBRAN\n");
            text.append("   PALACIOS CABRERA ALBERTO\n\n");
            text.append("                     VERSION: 1.0\n");
            text.append("                         --ESCOM--");

            JTextArea jtAreaAbout = new JTextArea(5, 21);
            jtAreaAbout.setText(text.toString());
            jtAreaAbout.setFont(new Font("Times New Roman", 1, 13));
            jtAreaAbout.setEditable(false);

            p1.add(jtAreaAbout);
            p1.setBackground(Color.blue);
            getContentPane().add(p1, BorderLayout.CENTER);

            JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            jbnOk = new JButton(" OK ");
            jbnOk.addActionListener(this);

            p2.add(jbnOk);
            getContentPane().add(p2, BorderLayout.SOUTH);

            setLocation(408, 270);
            setResizable(false);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Window aboutDialog = e.getWindow();
                    aboutDialog.dispose();
                }
            }
            );

            pack();
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbnOk) {
                this.dispose();
            }
        }

    }
}
