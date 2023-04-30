public class Barco {
    private String nombre;
    private int tamaño;
    private int posX;
    private int posY;

    public Barco(String nombre, int tamaño, int posX, int posY) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.posX = posX;
        this.posY = posY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
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

    @Override
    public String toString() {
        return "\nNombre: " + nombre +
                "\nTamaño: " + tamaño +
                "\nPosición X: " + posX +
                "\nPosición Y: " + posY;
    }

    public static void main(String[] args) {
        Barco barco = new Barco("Barco1", 3, 1, 1);
        System.out.println(barco.toString());
    }
}