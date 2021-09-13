package ajedrez;

//Clase Peon hereda de Pieza
public class Peon extends Pieza {

    //Atributos para posicion inical del peon
    protected int posInX;
    protected int posInY;

    //Constructor de Peon
    public Peon(int numPieza, int posX, int posY, boolean color, int posInX, int posInY) {
        super(numPieza, posX, posY, color);
        this.posInX = posInX;
        this.posInY = posInY;
    }

    //Sobrescritura del metodo movimiento para Peon
    @Override
    public boolean movimiento(int desX, int desY, Pieza[][] p) {

        //Comprobacion de movimiento doble al inicio del tablero (2 casillas o 1)
        if (this.posInY == this.posY && (this.posX == desX) && (Math.abs(this.posY - desY) <= 2) && p[desY][desX] == null) {
            if (this.color == false && this.posY < desY) {
                return true;
            } else if (this.color == true && this.posY > desY) {
                return true;
            } else {
                return false;
            }
        } //Comprobacion de movimiento para comer pieza
        else if (Math.abs(this.posX - desX) == Math.abs(this.posY - desY) && Math.abs(this.posX - desX) == 1 && p[desY][desX] != null && this.color != p[desY][desX].color) {
            if (this.color == false && this.posY < desY) {
                return true;
            } else if (this.color == true && this.posY > desY) {
                return true;
            } else {
                return false;
            }
        } //Comprobacion de movimiento normal
        else if (this.posX == desX && Math.abs(desY - this.posY) == 1 && p[desY][desX] == null) {
            if (this.color == false && this.posY < desY) {
                return true;
            } else if (this.color == true && this.posY > desY) {
                return true;
            } else {
                return false;
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
