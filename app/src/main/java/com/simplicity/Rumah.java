package com.simplicity;
import com.simplicity.Objek.ObjekNonMakanan;
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
        inisialisasi();
    }

    public void inisialisasi(){
        Point p1= new Point(0,5);
        Point p2= new Point(5,5);
        Point p3= new Point(5,1);
        Point p4= new Point(2,2);
        Point p5= new Point(1,1);
  
        ObjekNonMakanan o1 = ObjekNonMakanan.returnObject("kasur single 4x1");
        ObjekNonMakanan o2 = ObjekNonMakanan.returnObject("toilet 1x1");
        ObjekNonMakanan o3 = ObjekNonMakanan.returnObject("kompor gas 2x1");
        ObjekNonMakanan o4 = ObjekNonMakanan.returnObject("meja kursi 3x3");
        ObjekNonMakanan o5 = ObjekNonMakanan.returnObject("jam 1x1");

        daftarRuangan.get(0).tambahObjek(o1, p1,"h");
        daftarRuangan.get(0).tambahObjek(o2, p2,"h");
        daftarRuangan.get(0).tambahObjek(o3, p3,"v");
        daftarRuangan.get(0).tambahObjek(o4, p4,"v");
        daftarRuangan.get(0).tambahObjek(o5, p5,"v");
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
