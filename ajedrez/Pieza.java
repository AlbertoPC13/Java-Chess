package ajedrez;

//Clase abstracta Pieza
public abstract class Pieza {

    //Atributos: Numero identificador de pieza, coordenadas [X,Y] y color de pieza
    protected int numPieza;
    protected int posX;
    protected int posY;
    protected boolean color;

    //Constructor de Pieza
    public Pieza(int numPieza, int posX, int posY, boolean color) {
        this.numPieza = numPieza;
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }
    
    //Metodos abstractos de Pieza: Jaque y movimiento
    public abstract boolean Jaque(Pieza [][] p);
    public abstract boolean movimiento(int desX, int desY, Pieza[][] p);

    //Sobreescritura de metodo toString
    @Override
    public String toString() {
        if (this.color == false) {
            return Character.toString(this.getClass().getSimpleName().charAt(0)) + Character.toString(this.getClass().getSimpleName().charAt(2)) + "B";
        } else {
            return Character.toString(this.getClass().getSimpleName().charAt(0)) + Character.toString(this.getClass().getSimpleName().charAt(2)) + "W";
        }
    }
    
    //Metodos getter y setter
    public int getNumPieza() {
        return numPieza;
    }

    public void setNumPieza(int numPieza) {
        this.numPieza = numPieza;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
