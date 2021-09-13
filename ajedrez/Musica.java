package ajedrez;

import java.applet.AudioClip;

//Interfaz Musica
public interface Musica {

    //Metodo para reproducir Musica
    public AudioClip musicOn(AudioClip sound);

    //Metodo para detener Musica
    public void musicOff(AudioClip sound);

}
