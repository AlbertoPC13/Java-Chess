package ajedrez;

//Clase Caballo hereda de Pieza
public class Caballo extends Pieza {

    //Constructor de Caballo
    public Caballo(int numPieza, int posX, int posY, boolean color) {
        super(numPieza, posX, posY, color);
    }

    //Sobrescritura del metodo movimiento  para Caballo
    @Override
    public boolean movimiento(int desX, int desY, Pieza[][] p) {
        //Comprobacion de movimiento valido de caballo
        if ((this.posX - desX) * (this.posX - desX) + (this.posY - desY) * (this.posY - desY) == 5 && (this.posX != desX) && (this.posY != desY)) {
            if (p[desY][desX] == null) {
                return true;
            } else if (this.color != p[desY][desX].color) {
                return true;
            }
        }
        return false;
    }

    //Metodo Jaque no asignado 
    @Override
    public boolean Jaque(Pieza[][] p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
