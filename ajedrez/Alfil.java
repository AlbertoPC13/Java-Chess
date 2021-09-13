package ajedrez;

//Clase Alfil hereda de Pieza
public class Alfil extends Pieza {

    //Constructor de Alfil
    public Alfil(int numPieza, int posX, int posY, boolean color) {
        super(numPieza, posX, posY, color);
    }

    //Sobrescritura del metodo movimiento  para Alfil
    @Override
    public boolean movimiento(int desX, int desY, Pieza[][] p) {

        //Movimiento en diagonal inferior derecha
        if (desX > this.posX && desY > this.posY) {
            int j = this.posY + 1;
            int i = this.posX + 1;

            while (j < desY && i > desX) {

                if (p[j][i] != null) {
                    return false;
                }
                j++;
                i--;
            }
        } //Movimiento en diagonal inferior izquierdo
        else if (desX < this.posX && desY > this.posY) {
            int j = this.posY + 1;
            int i = this.posX - 1;

            while (j < desY && i > desX) {

                if (p[j][i] != null) {
                    return false;
                }
                j++;
                i--;
            }
        } //Movimiento en diagonal superior derecho
        else if (desX > this.posX && desY < this.posY) {
            int j = this.posY - 1;
            int i = this.posX + 1;

            while (j < desY && i > desX) {

                if (p[j][i] != null) {
                    return false;
                }
                j++;
                i--;
            }
        } //Movimiento en diagonal superior izquierdo
        else if (desX < this.posX && desY < this.posY) {
            int j = this.posY - 1;
            int i = this.posX - 1;

            while (j < desY && i > desX) {

                if (p[j][i] != null) {
                    return false;
                }
                j++;
                i--;
            }
        }

        //Comprobacion de movimiento valido de Alfil
        if (Math.abs(this.posX - desX) == Math.abs(this.posY - desY) && (this.posX != desX) && (this.posY != desY)) {
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
