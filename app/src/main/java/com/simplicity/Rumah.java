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
    
    public void setLocRumah(Point loc) {
        this.loc = loc;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
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
            if (ruanganacuan.getAtas()==null){
                ruanganacuan.setAtas(newRuangan);
                newRuangan.setBawah(ruanganacuan);
            }
            else{
                System.out.println("Ruangan tidak tersedia karena sudah terisi.");
            }  
        } else if (arah.equals("bawah")){
            if (ruanganacuan.getBawah()==null){
                ruanganacuan.setBawah(newRuangan);
                newRuangan.setAtas(ruanganacuan);
            }
            else{
                System.out.println("Ruangan tidak tersedia karena sudah terisi.");
            }
        } else if (arah.equals("kanan")){
            if (ruanganacuan.getKanan()==null){
                ruanganacuan.setKanan(newRuangan);
                newRuangan.setKiri(ruanganacuan);
            }
            else{
                System.out.println("Ruangan tidak tersedia karena sudah terisi.");
            }
        } else if (arah.equals("kiri")){
            if (ruanganacuan.getKiri()==null){
                ruanganacuan.setKiri(newRuangan);
                newRuangan.setKanan(ruanganacuan);
            }
            else{
                System.out.println("Ruangan tidak tersedia karena sudah terisi.");
            }
        }
    }
    
}
