package ajedrez;

//Clase Torre hereda de Pieza
public class Torre extends Pieza {

    //Constructor de Torre
    public Torre(int numPieza, int posX, int posY, boolean color) {
        super(numPieza, posX, posY, color);
    }

    //Sobrescritura del metodo movimiento  para Torre
    @Override
    public boolean movimiento(int desX, int desY, Pieza[][] p) {
        if ((this.posX == desX) || (this.posY == desY)) {

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
            else if(desX == this.posX && desY < this.posY)
            {
                for (int i = this.posY - 1; i > desY; i--) {
                    if (p[i][desX] != null) {
                        return false;
                    }
                }
            } //Movimiento horizontal a la izquierda
            else if(desY == this.posY && desX < this.posX)
            {
                for (int i = this.posX - 1; i > desX; i--) {
                    if (p[desY][i] != null) {
                        return false;
                    }
                }
            }
            
            //Comprobacion de movimiento valido de Torre
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
