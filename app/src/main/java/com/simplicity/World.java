package com.simplicity;

import java.util.ArrayList;
import java.util.List;

import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekNonMakanan;
import com.simplicity.Objek.ObjekPekerjaan;

public class World { 
    private static List<Rumah> listrumah = new ArrayList<Rumah>();;
    private static List<Sim> listsim = new ArrayList<Sim>();
    private static int time;
    private final int panjangMap = 64;
    private final int lebarMap = 64;

    ArrayList<ObjekPekerjaan> daftarPekerjaan = new ArrayList<ObjekPekerjaan>();
    ObjekBahanMakanan[] daftar_bahan = new ObjekBahanMakanan[8];
    ObjekNonMakanan[] daftar_barang = new ObjekNonMakanan[8];
    ObjekMakanan[] daftar_makanan = new ObjekMakanan[5];
   
    public World(){
        Inisiasi();
        time = 0;
        addSim("Paijo0");
        addSim("Paijo1");
        addSim("Paijo2");
    }

    public void Inisiasi() {
        // List objek pekerjaan
        daftarPekerjaan.add(new ObjekPekerjaan("Badut Sulap", 15));
        daftarPekerjaan.add(new ObjekPekerjaan("Koki", 30));
        daftarPekerjaan.add(new ObjekPekerjaan("Polisi", 35));
        daftarPekerjaan.add(new ObjekPekerjaan("Programmer", 45));
        daftarPekerjaan.add( new ObjekPekerjaan("Dokter", 50));

        ObjekBahanMakanan nasi = new ObjekBahanMakanan("Nasi\t", 5, 5);
        ObjekBahanMakanan kentang = new ObjekBahanMakanan("Kentang\t", 3, 4);
        ObjekBahanMakanan ayam = new ObjekBahanMakanan("Ayam\t", 10, 8);
        ObjekBahanMakanan sapi = new ObjekBahanMakanan("Sapi\t", 12, 15);
        ObjekBahanMakanan wortel = new ObjekBahanMakanan("Wortel\t", 3, 2);
        ObjekBahanMakanan bayam = new ObjekBahanMakanan("Bayam\t", 3, 2);
        ObjekBahanMakanan kacang = new ObjekBahanMakanan("Kacang\t", 2, 2);
        ObjekBahanMakanan susu = new ObjekBahanMakanan("Susu\t", 2, 1);
        
        // Menambahkan daftar bahan makanan
        daftar_bahan[0] = nasi;
        daftar_bahan[1] = kentang;
        daftar_bahan[2] = ayam;
        daftar_bahan[3] = sapi;
        daftar_bahan[4] = wortel;
        daftar_bahan[5] = bayam;
        daftar_bahan[6] = kacang;
        daftar_bahan[7] = susu;
        
        // List objek non makanan
        daftar_barang[0] = new ObjekNonMakanan("Kasur Single\t", 4, 1, 50, "Tidur");
        daftar_barang[1] = new ObjekNonMakanan("Kasur Queen Size", 4, 2, 100, "Tidur");
        daftar_barang[2] = new ObjekNonMakanan("Kasur King Size ", 5, 2, 150, "Tidur");
        daftar_barang[3] = new ObjekNonMakanan("Toilet\t\t", 1, 1, 50, "Buang air");
        daftar_barang[4] = new ObjekNonMakanan("Kompor Gas\t", 2, 1, 100, "Memasak");
        daftar_barang[5] = new ObjekNonMakanan("Kompor Listrik\t", 1, 1, 200, "Memasak");
        daftar_barang[6] = new ObjekNonMakanan("Meja dan Kursi\t", 3, 3, 50, "Makan");
        daftar_barang[7] = new ObjekNonMakanan("Jam\t\t", 1, 1, 10, "Melihat Waktu");

        // List objek makanan
        daftar_makanan[0] = new ObjekMakanan("Nasi Ayam", new ObjekBahanMakanan[]{nasi, ayam}, 16);
        daftar_makanan[1] = new ObjekMakanan("Nasi Kari", new ObjekBahanMakanan[]{nasi, kentang, wortel, sapi}, 30);
        daftar_makanan[2] = new ObjekMakanan("Susu Kacang", new ObjekBahanMakanan[]{susu, kacang}, 5);
        daftar_makanan[3] = new ObjekMakanan("Tumis Sayur", new ObjekBahanMakanan[]{wortel, bayam}, 5);
        daftar_makanan[4] = new ObjekMakanan("Bistik\t", new ObjekBahanMakanan[]{kentang, sapi}, 22);   
    }
    
    public int getTime(){
        return time;
    }
    
    public void setTime(int aksi){
        if(time + aksi < 720){
            time += aksi;
        } else {
            time = (time+aksi)%720; 
        }
    }
    
    public List<Rumah> getDaftarRumah() {
        return listrumah;
    }

    public boolean checkLahan(Point lokasi){
        boolean check = false;
        for (Rumah rumah : listrumah) {
            if(rumah.getLocRumah().getX()==lokasi.getX() && rumah.getLocRumah().getY()==lokasi.getY()){
                check = true;
            }
        }
        return check;
    }

    public boolean validPos(Point lokasi){
        boolean check = false;
        if(lokasi.getX()<=panjangMap && lokasi.getY()<=lebarMap){
            check = true;
        }
        return check;
    }

    public boolean checkSim(String namaSim){
        boolean check = false;
        for (Sim sim : listsim) {
            if (sim.getNamaLengkap().equals(namaSim)) {
                check = true;
            }
        }
        return check;
    }

    public void addRumah(Point lokasi, String nama){
        if(!checkLahan(lokasi) && validPos(lokasi)){
            Rumah rumah = new Rumah(lokasi, nama);
            listrumah.add(rumah);
        } else if(checkLahan(lokasi)){
            System.out.println("Lahan sudah ditempati");
        } else if(!validPos(lokasi)){
            System.out.println("Tidak bisa membangun rumah di luar map");
        }
    }

    public List<Sim> getDaftarSim() {
        return listsim;
    }

    public boolean addSim(String namaSim){
        if (!checkSim(namaSim)) {
            Sim newsim = new Sim(namaSim, daftarPekerjaan);
            listsim.add(newsim);
            return true;
        }
        return false;
    }
}
