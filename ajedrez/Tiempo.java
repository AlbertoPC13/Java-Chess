package ajedrez;

import javax.swing.*;
import static java.lang.Thread.sleep;

//Manejo de hilos para medir tiempo en paralelo
class Tiempo extends Thread {

	UserInterface bd;
	
	//Parar cronómetro
	boolean Salir=false;
	
	//Contador de segundos
	int seg=0;
	
	Tiempo (JFrame j){
		bd=(UserInterface)j;
	}
	
        //metodo run, obligatorio en el thread
        
	public void run()
	{
		while(!Salir){
			try
			{
			//Retardar 1000 milisegutndos
			sleep(1000);
			seg++;		
			bd.txtTiempo.setText(Integer.toString(seg));
			}
			catch(InterruptedException ie)
			{
				System.out.println(ie);
			}
		}
	}
	
	public void parar(boolean b)
	{
		//método para parar el cronómetro
		if(b)Salir=true;
		seg=0;
	}

}