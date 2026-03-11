class ShipPattern {
    public int size;

    public ShipPattern(int size) {
        this.size = size;
    }

    public void drawSails() {
        System.out.println("  *"); 
        
        System.out.println(" **");
        
        System.out.println("  *"); 
    }

    public void drawHull() {
        for (int j = 0; j < 5; j++) {
            System.out.print("*");
        }
        System.out.println();

        System.out.print(" "); 
        for (int j = 0; j < 3; j++) {
            System.out.print("*");
        }
        System.out.println();
    }
}

public class OOP {
    public static void main(String[] args) {
        ShipPattern polaKapal = new ShipPattern(5);
        
        System.out.println("Pola 3 (Centered):");
        polaKapal.drawSails();
        polaKapal.drawHull();
    }
}   