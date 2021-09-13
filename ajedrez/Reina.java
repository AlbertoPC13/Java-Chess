package ajedrez;

//Clase Reina hereda de Pieza
public class Reina extends Pieza {

    //Constructor de Reina
    public Reina(int numPieza, int posX, int posY, boolean color) {
        super(numPieza, posX, posY, color);
    }

    //Sobrescritura del metodo movimiento  para Reina
    @Override
    public boolean movimiento(int desX, int desY, Pieza[][] p) {

        //Movimiento vertical a abajo
        if (desX == this.posX && desY > this.posY) {
            for (int i = this.posY + 1; i < desY; i++) {
                if (p[i][desX] != null) {
                    return false;
                }
            }
        } //Movimiento horizontal a la derecha
        else if (desY == this.posY && desX > this.posX) {
            for (int i = this.posX + 1; i < desX; i++) {
                if (p[desY][i] != null) {
                    return false;
                }
            }
        } //Movimiento vertical a arriba
        else if (desX == this.posX && desY < this.posY) {
            for (int i = this.posY - 1; i > desY; i--) {
                if (p[i][desX] != null) {
                    return false;
                }
            }
        } //Movimiento horizontal a la izquierda
        else if (desY == this.posY && desX < this.posX) {
            for (int i = this.posX - 1; i > desX; i--) {
                if (p[desY][i] != null) {
                    return false;
                }
            }
        }

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

        //Comprobacion de movimiento valido de Reina
        if ((this.posX == desX) || (this.posY == desY) || (Math.abs(this.posX - desX) == Math.abs(this.posY - desY))) {
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
