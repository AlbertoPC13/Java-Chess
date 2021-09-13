package ajedrez;

//Clase Rey hereda de Pieza
public class Rey extends Pieza {

    //Constructor de Rey
    public Rey(int numPieza, int posX, int posY, boolean color) {
        super(numPieza, posX, posY, color);
    }

    //Sobrescritura del metodo movimiento  para Rey
    @Override
    public boolean movimiento(int desX, int desY, Pieza[][] p) {
        
        //Comprobacion de movimiento valido de Rey
        if ((Math.abs(this.posX - desX) <= 1 && (Math.abs(this.posY - desY) <= 1))) {
            if (p[desY][desX] == null) {
                return true;
            } else if (this.color != p[desY][desX].color) {
                return true;
            }
        }
        return false;
    }

    //Sobrescritura de metodo Jaque para Rey (Intenta un movimiento de todas las piezas a la posicion del Rey)
    @Override
    public boolean Jaque(Pieza[][] p) {

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (p[i][j] != null && p[i][j].movimiento(this.getPosX(), this.getPosY(), p) && this.isColor() != p[i][j].isColor()) {
                    System.out.println("\nPone en Jaque: " + p[i][j] + "[" + j + "," + i + "]");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getNumPieza() {
        return numPieza;
    }

    @Override
    public void setNumPieza(int numPieza) {
        this.numPieza = numPieza;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public boolean isColor() {
        return color;
    }

    @Override
    public void setColor(boolean color) {
        this.color = color;
    }

}
