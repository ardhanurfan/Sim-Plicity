package com.simplicity;
import java.util.ArrayList;
import java.util.List;

public class Rumah {
    //Atribute
    private int x;
    private int y;
    private List<Ruangan> daftarRuangan;

    //Constructor
    public Rumah(int x, int y) {
        this.x = x;
        this.y = y;
        this.daftarRuangan = new ArrayList<Ruangan>();
        // Inisialisasi ruangan pertama saat rumah pertama kali dibuat
        this.daftarRuangan.add(new Ruangan("Kamar"));
    }

    // Getters and setters
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }
    
    public void setDaftarRuangan(List<Ruangan> daftarRuangan) {
        this.daftarRuangan = daftarRuangan;
    }

    //Methods
    
}
