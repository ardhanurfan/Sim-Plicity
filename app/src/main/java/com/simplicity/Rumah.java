package com.simplicity;
import java.util.ArrayList;
import java.util.List;

public class Rumah {
    //Atribute
    private Point loc;
    private List<Ruangan> daftarRuangan;
    private String nama;

    //Constructor
    public Rumah(Point loc, String nama) {
        this.loc = loc;
        this.nama = nama;
        this.daftarRuangan = new ArrayList<Ruangan>();
        // Inisialisasi ruangan pertama saat rumah pertama kali dibuat
        this.daftarRuangan.add(new Ruangan("Kamar"));
    }

    // Getters and setters
    public Point getLocRumah() {
        return loc;
    }
    
    public void setLocRumah(Point p) {
        this.loc = loc;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(){
        this.nama = nama;
    }
    
    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }
    
    public void setDaftarRuangan(List<Ruangan> daftarRuangan) {
        this.daftarRuangan = daftarRuangan;
    }

    //Methods
    public void upgradeRumah(Ruangan ruanganacuan, String arah, String namaruangan){
        Ruangan newRuangan = new Ruangan(namaruangan);
        daftarRuangan.add(newRuangan);
        
        if (arah.equals("atas")){
            ruanganacuan.setAtas(newRuangan);
            newRuangan.setBawah(ruanganacuan);  
        } else if (arah.equals("bawah")){
            ruanganacuan.setBawah(newRuangan);
            newRuangan.setAtas(ruanganacuan);
        } else if (arah.equals("kanan")){
            ruanganacuan.setKanan(newRuangan);
            newRuangan.setKiri(ruanganacuan);
        } else if (arah.equals("kiri")){
            ruanganacuan.setKiri(newRuangan);
            newRuangan.setKanan(ruanganacuan);
        }
    }
    
}
