package com.simplicity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekNonMakanan;
import com.simplicity.Objek.ObjekPekerjaan;

public class World {
    private static List<Rumah> listRumah = new ArrayList<Rumah>();;
    private static List<Sim> listSim = new ArrayList<Sim>();
    private int time;
    private final int panjangMap = 64;
    private final int lebarMap = 64;

    private List<ObjekPekerjaan> daftarPekerjaan = new ArrayList<ObjekPekerjaan>();
    private List<ObjekBahanMakanan> daftar_bahan = new ArrayList<ObjekBahanMakanan>();
    private List<ObjekNonMakanan> daftar_barang = new ArrayList<ObjekNonMakanan>();
    private List<ObjekMakanan> daftar_makanan = new ArrayList<ObjekMakanan>();

    Thread threadAksi;
    Thread threadBeliBarang;
    Thread threadUpgradeRumah;

    public World() {
        Inisiasi();
        time = 0;
    }

    public JSONObject toJson() {
        HashMap<String, Object> worldMap = new HashMap<String, Object>();
        List<JSONObject> listRumahJSON = new ArrayList<JSONObject>();
        for (Rumah rumah : listRumah) {
            listRumahJSON.add(rumah.toJson());
        }
        List<JSONObject> listSimJSON = new ArrayList<JSONObject>();
        for (Sim sim : listSim) {
            listSimJSON.add(sim.toJson());
        }

        worldMap.put("listRumah", listRumahJSON);
        worldMap.put("listSim", listSimJSON);
        worldMap.put("time", time);

        JSONObject worldJSON = new JSONObject(worldMap);
        return worldJSON;
    }

    public void Inisiasi() {
        // List objek pekerjaan
        daftarPekerjaan.add(new ObjekPekerjaan("Badut Sulap", 15));
        daftarPekerjaan.add(new ObjekPekerjaan("Koki", 30));
        daftarPekerjaan.add(new ObjekPekerjaan("Polisi", 35));
        daftarPekerjaan.add(new ObjekPekerjaan("Programmer", 45));
        daftarPekerjaan.add(new ObjekPekerjaan("Dokter", 50));

        ObjekBahanMakanan nasi = new ObjekBahanMakanan("Nasi", 5, 5);
        ObjekBahanMakanan kentang = new ObjekBahanMakanan("Kentang", 3, 4);
        ObjekBahanMakanan ayam = new ObjekBahanMakanan("Ayam", 10, 8);
        ObjekBahanMakanan sapi = new ObjekBahanMakanan("Sapi", 12, 15);
        ObjekBahanMakanan wortel = new ObjekBahanMakanan("Wortel", 3, 2);
        ObjekBahanMakanan bayam = new ObjekBahanMakanan("Bayam", 3, 2);
        ObjekBahanMakanan kacang = new ObjekBahanMakanan("Kacang", 2, 2);
        ObjekBahanMakanan susu = new ObjekBahanMakanan("Susu", 2, 1);

        // Menambahkan daftar bahan makanan
        daftar_bahan.add(nasi);
        daftar_bahan.add(kentang);
        daftar_bahan.add(ayam);
        daftar_bahan.add(sapi);
        daftar_bahan.add(wortel);
        daftar_bahan.add(bayam);
        daftar_bahan.add(kacang);
        daftar_bahan.add(susu);

        // List objek non makanan
        daftar_barang.add(new ObjekNonMakanan("Kasur Single", 4, 1, 50, "Tidur"));
        daftar_barang.add(new ObjekNonMakanan("Kasur Queen Size", 4, 2, 100, "Tidur"));
        daftar_barang.add(new ObjekNonMakanan("Kasur King Size", 5, 2, 150, "Tidur"));
        daftar_barang.add(new ObjekNonMakanan("Toilet", 1, 1, 50, "Buang air"));
        daftar_barang.add(new ObjekNonMakanan("Kompor Gas", 2, 1, 100, "Memasak"));
        daftar_barang.add(new ObjekNonMakanan("Kompor Listrik", 1, 1, 200, "Memasak"));
        daftar_barang.add(new ObjekNonMakanan("Meja dan Kursi", 3, 3, 50, "Makan"));
        daftar_barang.add(new ObjekNonMakanan("Jam", 1, 1, 10, "Melihat Waktu"));
        daftar_barang.add(new ObjekNonMakanan("Laptop", 1, 1, 50, "Bermain"));
        daftar_barang.add(new ObjekNonMakanan("Tv", 1, 1, 20, "Menonton"));
        daftar_barang.add(new ObjekNonMakanan("Matras", 2, 1, 8, "Olahraga"));
        daftar_barang.add(new ObjekNonMakanan("Sofa", 2, 1, 30, "Duduk"));

        // List objek makanan
        daftar_makanan.add(new ObjekMakanan("Nasi Ayam", new ObjekBahanMakanan[] { nasi, ayam }, 16));
        daftar_makanan.add(new ObjekMakanan("Nasi Kari", new ObjekBahanMakanan[] { nasi, kentang, wortel, sapi }, 30));
        daftar_makanan.add(new ObjekMakanan("Susu Kacang", new ObjekBahanMakanan[] { susu, kacang }, 5));
        daftar_makanan.add(new ObjekMakanan("Tumis Sayur", new ObjekBahanMakanan[] { wortel, bayam }, 5));
        daftar_makanan.add(new ObjekMakanan("Bistik", new ObjekBahanMakanan[] { kentang, sapi }, 22));
    }

    public String getTime() {
        int menit = time / 60;
        int detik = time % 60;
        return (menit < 10 ? "0" + menit : menit) + " : " + (detik < 10 ? "0" + detik : detik);
    }

    public void setTime(int aksi) {
        if (time + aksi < 720) {
            time += aksi;
        } else {
            time = (time + aksi) % 720;
        }
    }

    public List<Rumah> getDaftarRumah() {
        return listRumah;
    }

    public static void setListRumah(List<Rumah> listRumah) {
        World.listRumah = listRumah;
    }

    public boolean checkLahan(Point lokasi) {
        boolean check = false;
        for (Rumah rumah : listRumah) {
            if (rumah.getLocRumah().getX() == lokasi.getX() && rumah.getLocRumah().getY() == lokasi.getY()) {
                check = true;
            }
        }
        return check;
    }

    public boolean validPos(Point lokasi) {
        boolean check = false;
        if (lokasi.getX() <= panjangMap && lokasi.getY() <= lebarMap) {
            check = true;
        }
        return check;
    }

    public boolean checkSim(String namaSim) {
        boolean check = false;
        for (Sim sim : listSim) {
            if (sim.getNamaLengkap().equals(namaSim)) {
                check = true;
            }
        }
        return check;
    }

    public boolean checkRumah(String namaRumah) {
        boolean check = false;
        for (Rumah rumah : listRumah) {
            if (rumah.getNama().equals(namaRumah)) {
                check = true;
            }
        }
        return check;
    }

    public boolean addRumah(Point lokasi, String nama) {
        if (!checkLahan(lokasi) && validPos(lokasi) && !checkRumah(nama)) {
            Rumah rumah = new Rumah(lokasi, nama, listRumah.size());
            listRumah.add(rumah);
            return true;
        } else if (checkLahan(lokasi)) {
            System.out.println("Lahan sudah ditempati");
        } else if (!validPos(lokasi)) {
            System.out.println("Tidak bisa membangun rumah di luar map");
        }
        return false;
    }

    public List<Sim> getDaftarSim() {
        return listSim;
    }

    public static void setListSim(List<Sim> listSim) {
        World.listSim = listSim;
    }

    public boolean addSim(String namaSim) {
        if (!checkSim(namaSim)) {
            Sim newsim = new Sim(namaSim, daftarPekerjaan);
            listSim.add(newsim);
            return true;
        }
        return false;
    }

    public List<ObjekPekerjaan> getDaftarPekerjaan() {
        return daftarPekerjaan;
    }

    public List<ObjekBahanMakanan> getDaftar_bahan() {
        return daftar_bahan;
    }

    public List<ObjekNonMakanan> getDaftar_barang() {
        return daftar_barang;
    }

    public List<ObjekMakanan> getDaftar_makanan() {
        return daftar_makanan;
    }
}
