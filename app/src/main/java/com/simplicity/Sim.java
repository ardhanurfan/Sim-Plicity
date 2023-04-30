package com.simplicity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekNonMakanan;
import com.simplicity.Objek.ObjekPekerjaan;

public class Sim {
    private String namaLengkap;
    private ObjekPekerjaan pekerjaan;
    private double uang;
    private double kekenyangan;
    private double mood;
    private double kesehatan;
    private String status;
    private LokasiSim currLokasi;
    private Inventory inventory;
    private Rumah rumah;

    // STATE VARIABLE
    // Reset jika selesai upgrade rumah
    private int waktuUpgradeRumah = 0;

    // Reset saat ganti kerja
    private int totalWaktuKerja = 0;
    private int jedaGantiKerja = 0;
    private boolean sudahGantiKerja = false; // untuk pekerjaan pertama false, begitu sudah ganti selalu true;

    // Reset jika ganti hari
    private int totalWaktuTidur = 0;
    private int waktuTidakTidur = 0;

    // Reset jika sudah buang air
    private int waktuTidakBuangAir = -30;
    private boolean isTidakBuangAir = false;

    public Sim(Sim sim) {
        this.namaLengkap = sim.namaLengkap;
        this.pekerjaan = sim.pekerjaan;
        this.uang = sim.uang;
        this.kekenyangan = sim.kekenyangan;
        this.mood = sim.mood;
        this.kesehatan = sim.kesehatan;
        this.status = sim.status;
        this.currLokasi = sim.currLokasi;
        this.inventory = sim.inventory;
    }

    public Sim(String namaLengkap, List<ObjekPekerjaan> daftarPekerjaan) {
        this.namaLengkap = namaLengkap;
        Collections.shuffle(daftarPekerjaan);
        this.pekerjaan = daftarPekerjaan.get(0);
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.inventory = new Inventory();
        this.currLokasi = new LokasiSim();
    }

    public Sim(JSONObject jsonObject, List<Rumah> listRumah) {
        namaLengkap = jsonObject.get("namaLengkap").toString();
        pekerjaan = new ObjekPekerjaan((JSONObject) jsonObject.get("pekerjaan"));
        uang = Double.parseDouble(jsonObject.get("uang").toString());
        kekenyangan = Double.parseDouble(jsonObject.get("kekenyangan").toString());
        mood = Double.parseDouble(jsonObject.get("mood").toString());
        kesehatan = Double.parseDouble(jsonObject.get("kesehatan").toString());
        status = jsonObject.get("status") != null ? jsonObject.get("status").toString() : null;
        rumah = jsonObject.get("rumah") != null ? listRumah.get(Integer.parseInt(jsonObject.get("rumah").toString()))
                : null;
        currLokasi = new LokasiSim((JSONObject) jsonObject.get("currLokasi"), listRumah);
        inventory = new Inventory((JSONObject) jsonObject.get("inventory"));

        totalWaktuKerja = Integer.parseInt(jsonObject.get("totalWaktuKerja").toString());
        jedaGantiKerja = Integer.parseInt(jsonObject.get("jedaGantiKerja").toString());
        sudahGantiKerja = Boolean.parseBoolean(jsonObject.get("sudahGantiKerja").toString());
        totalWaktuTidur = Integer.parseInt(jsonObject.get("totalWaktuTidur").toString());
        waktuTidakTidur = Integer.parseInt(jsonObject.get("waktuTidakTidur").toString());
        waktuTidakBuangAir = Integer.parseInt(jsonObject.get("waktuTidakBuangAir").toString());
        isTidakBuangAir = Boolean.parseBoolean(jsonObject.get("isTidakBuangAir").toString());
        waktuUpgradeRumah = Integer.parseInt(jsonObject.get("waktuUpgradeRumah").toString());
    }

    public JSONObject toJson() {
        HashMap<String, Object> simMap = new HashMap<String, Object>();

        simMap.put("namaLengkap", namaLengkap);
        simMap.put("pekerjaan", pekerjaan.toJson());
        simMap.put("uang", uang);
        simMap.put("kekenyangan", kekenyangan);
        simMap.put("mood", mood);
        simMap.put("kesehatan", kesehatan);
        simMap.put("status", status);
        simMap.put("currLokasi", currLokasi.toJson());
        simMap.put("inventory", inventory.toJson());
        simMap.put("rumah", rumah == null ? null : rumah.getId());

        simMap.put("totalWaktuKerja", totalWaktuKerja);
        simMap.put("jedaGantiKerja", jedaGantiKerja);
        simMap.put("sudahGantiKerja", sudahGantiKerja);
        simMap.put("totalWaktuTidur", totalWaktuTidur);
        simMap.put("waktuTidakTidur", waktuTidakTidur);
        simMap.put("waktuTidakBuangAir", waktuTidakBuangAir);
        simMap.put("isTidakBuangAir", isTidakBuangAir);
        simMap.put("waktuUpgradeRumah", waktuUpgradeRumah);

        JSONObject simJSON = new JSONObject(simMap);
        return simJSON;
    }

    public class LokasiSim {
        private Rumah rumah;
        private Ruangan ruangan;

        public LokasiSim() {
        }

        public LokasiSim(JSONObject jsonObject, List<Rumah> listRumah) {
            rumah = jsonObject.get("rumah") != null
                    ? listRumah.get(Integer.parseInt(jsonObject.get("rumah").toString()))
                    : null;
            ruangan = jsonObject.get("ruangan") != null
                    ? rumah.getDaftarRuangan().get(Integer.parseInt(jsonObject.get("ruangan").toString()))
                    : null;
        }

        public JSONObject toJson() {
            HashMap<String, Object> lokasiMap = new HashMap<String, Object>();

            lokasiMap.put("rumah", rumah == null ? null : rumah.getId());
            lokasiMap.put("ruangan", ruangan == null ? null : ruangan.getId());

            JSONObject lokasiJSON = new JSONObject(lokasiMap);
            return lokasiJSON;
        }

        public Ruangan getRuangan() {
            return ruangan;
        }

        public Rumah getRumah() {
            return rumah;
        }

        public void setRuangan(Ruangan ruangan) {
            this.ruangan = ruangan;
        }

        public void setRumah(Rumah rumah) {
            this.rumah = rumah;
        }
    }

    public Rumah getRumah() {
        return rumah;
    }

    public void setRumah(Rumah rumah) {
        this.rumah = rumah;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getPekerjaan() {
        return pekerjaan.getNamaObjek();
    }

    public boolean getSudahGantiKerja() {
        return sudahGantiKerja;
    }

    public int getJedaGantiKerja() {
        return jedaGantiKerja;
    }

    public int getTotalWaktuKerja() {
        return totalWaktuKerja;
    }

    public void setPekerjaan(ObjekPekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
        uang -= pekerjaan.getGaji() * 0.5;
        jedaGantiKerja = 0;
        totalWaktuKerja = 0;
        sudahGantiKerja = true;
    }

    public String getUang() {
        return String.format("%.2f", uang);
    }

    public void setUang(double uang) {
        this.uang = uang;
    }

    public String getKekenyangan() {
        return String.format("%.2f", kekenyangan);
    }

    public void setKekenyangan(double waktuKerja, double ratio, double value) {
        this.kekenyangan += waktuKerja / ratio * value;
        if (this.kekenyangan > 100) {
            this.kekenyangan = 100;
        }
    }

    public String getMood() {
        return String.format("%.2f", mood);
    }

    public void setMood(double waktuKerja, double ratio, double value) {
        this.mood += waktuKerja / ratio * value;
        if (this.mood > 100) {
            this.mood = 100;
        }
    }

    public String getKesehatan() {
        return String.format("%.2f", kesehatan);
    }

    public void setKesehatan(double waktuKerja, double ratio, double value) {
        this.kesehatan += waktuKerja / ratio * value;
        if (this.kesehatan > 100) {
            this.kesehatan = 100;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LokasiSim getCurrLokasi() {
        return currLokasi;
    }

    public void setCurrLokasi(LokasiSim currLokasi) {
        this.currLokasi = currLokasi;
    }

    public int getWaktuUpgradeRumah(){
        return waktuUpgradeRumah;
    }

    public void setwaktuUpgradeRumah(int waktu){
        waktuUpgradeRumah += waktu;
    }

    // Bertambah dan dilakukan cek setiap saat
    public void addOnTimeWorld(int waktuAksi) {
        this.jedaGantiKerja += waktuAksi;
        this.waktuTidakTidur += waktuAksi;

        if (isTidakBuangAir) {
            waktuTidakBuangAir += waktuAksi;
            efekTidakBuangAir();
        }
        efekTidakTidur();
    }

    // reset tiap berganti hari
    public void resetWaktuKegiatanharian() {
        totalWaktuTidur = 0;
        waktuTidakTidur = 0;
    }

    public void kerja(double waktuKerja) {
        setKekenyangan(waktuKerja, 30, -10);
        setMood(waktuKerja, 30, -10);
        if (pekerjaan.getNamaObjek().equals("Badut Sulap")) {
            uang += waktuKerja / 240 * 15;
        } else if (pekerjaan.getNamaObjek().equals("Koki")) {
            uang += waktuKerja / 240 * 30;
        } else if (pekerjaan.getNamaObjek().equals("Polisi")) {
            uang += waktuKerja / 240 * 35;
        } else if (pekerjaan.getNamaObjek().equals("Programmer")) {
            uang += waktuKerja / 240 * 45;
        } else if (pekerjaan.getNamaObjek().equals("Dokter")) {
            uang += waktuKerja / 240 * 50;
        }
        totalWaktuKerja += waktuKerja;
    }

    public void olahraga(int waktuOlahraga) {
        setKesehatan(waktuOlahraga, 20, 5);
        setKekenyangan(waktuOlahraga, 20, -5);
        setMood(waktuOlahraga, 20, 10);

        System.out.println("Olahraga selesai selama " + (waktuOlahraga < 60 ? (waktuOlahraga + " detik")
                : (waktuOlahraga / 60 + ":" + waktuOlahraga % 60 + " menit")));
        // System.out.println("Kesehatan Anda sekarang " + kesehatan);
        // System.out.println("Kekenyangan Anda sekarang " + kekenyangan);
        // System.out.println("Mood Anda sekarang " + mood);
    }

    public void tidur(int waktuTidur) {
        setMood(waktuTidur, 240, 30);
        setKesehatan(waktuTidur, 240, 20);

        totalWaktuTidur += waktuTidur;

        // System.out.println("Sim telah tidur selama " + waktuTidur + " detik");
        // System.out.println("Kesehatan Anda sekarang " + kesehatan);
        // System.out.println("Mood Anda sekarang " + mood);
    }

    public void efekTidakTidur() {
        if (waktuTidakTidur >= 600 && totalWaktuTidur < 180) {
            setKesehatan(1, 1, -5);
            setMood(1, 1, -5);
        }
    }

    public double makan(String namaMakanan, int kekenyangan) {
        // Cek apakah makanan ada di inventory
        setKekenyangan(1, 1, kekenyangan);

        // kurangi stok makanan pada inventory
        inventory.kurangiItem(namaMakanan, 1);

        // menangani kalo belum 4 menit udah makan lagi, acuan 4 menit yang awal
        if (!isTidakBuangAir) {
            isTidakBuangAir = true;
            waktuTidakBuangAir = -30;
        }

        // System.out.println("Yammy! " + namaMakanan + " enak sekali...");
        return 30;
    }

    public double masak(ObjekMakanan makanan) {
        // validasi bahan-bahan dari inventory
        boolean isBahanAda = true;
        for (ObjekBahanMakanan bahan : makanan.getBahan()) {
            if (!inventory.isContains(bahan)) {
                isBahanAda = false;
                System.out.println("Bahan " + bahan.getNamaObjek() + " tidak tersedia!");
            }
        }

        if (isBahanAda) {
            ObjekMakanan newmakanan = new ObjekMakanan(makanan.getNamaObjek(), makanan.getBahan(),
                    makanan.getKekenyangan());
            // kurangi bahan di inventory
            for (ObjekBahanMakanan bahan : makanan.getBahan()) {
                inventory.kurangiItem(bahan.getNamaObjek(), 1);
            }
            // masukan makanan baru ke inventory
            inventory.addItemMakanan(newmakanan, 1);

            setMood(1, 1, 10);
            System.out.println(
                    "Srenggg.... " + newmakanan.getNamaObjek() + " berhasil dibuat. Sudah dimasukkan ke inventory");
            System.out.println("Selamat menikmati ...");
            return newmakanan.getKekenyangan() * 1.5;
        } else {
            return 0;
        }
    }

    public double berkunjung(Rumah rumahDiKunjungi) {
        Point currPoint = currLokasi.getRumah().getLocRumah();
        Point toPoin = rumahDiKunjungi.getLocRumah();
        int selisihX = currPoint.getX() - toPoin.getX();
        int selisihY = currPoint.getY() - toPoin.getY();

        double waktu = Math.sqrt(Math.pow(selisihX, 2) + Math.pow(selisihY, 2));
        ; // dari perhitungan point jarak pada rumah
        setMood((int) waktu, 30, 10);
        setKekenyangan((int) waktu, 30, -10);

        System.out.println("Anda akan berkunjung ke " + rumahDiKunjungi.getNama());
        System.out.println("Dengan lama perjalanan " + waktu + " detik");
        System.out.println("========================================");
        System.out.println("Anda sudah sampai di " + rumahDiKunjungi.getNama());
        return waktu;
    }

    public double buangAir() {
        setKekenyangan(1, 1, -20);
        setMood(1, 1, 10);
        waktuTidakBuangAir = -30;
        isTidakBuangAir = false;
        System.out.println("Uhhh lega... udah buang air");
        return 10;
    }

    public void efekTidakBuangAir() {
        if (waktuTidakBuangAir >= 240 && isTidakBuangAir) {
            isTidakBuangAir = false;
            waktuTidakBuangAir = -30;
            setKesehatan(1, 1, -5);
            setMood(1, 1, -5);
            JOptionPane.showMessageDialog(null, "Anda tidak buang air dalam 4 menit setelah makan");
        }
    }

    public void pindahRuangan(Ruangan goingRuangan) {
        System.out.println("Anda sekarang berada di ruangan " + currLokasi.getRuangan().getNama());
        System.out.println("Anda akan berpindah ke ruangan " + goingRuangan.getNama());
        System.out.println("========================================");
        currLokasi.setRuangan(goingRuangan);
        System.out.println("Anda sudah berada di ruangan " + currLokasi.getRuangan().getNama() + " di rumah "
                + currLokasi.getRumah().getNama());
    }

    public void lihatInventory() {
        // Print inventory
        inventory.viewInventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void pasangBarang(ObjekNonMakanan objek, Point point, String posisi) {
        Ruangan currRuangan = currLokasi.getRuangan();
        // tampilkan map ruangan
        currRuangan.tampilkanRuangan();
        // pasang
        currRuangan.tambahObjek(objek, point, posisi);
        // barang diinventory dikurangi stoknya
        inventory.kurangiItem(objek.getNamaObjek(), 1);
    }

    public void bermain() {
        setMood(30, 30, 20);
        setKekenyangan(30, 30, -10);
        // System.out.println("Horee... Seru sekali gamenya");
    }

    public void nontonTv() {
        setMood(30, 30, 15);
        setKekenyangan(30, 30, -10);
        // System.out.println("Horee... Seru sekali acaranya");
    }

    public void duduk() {
        setMood(30, 30, 5);
        setKekenyangan(30, 30, -5);
        setKesehatan(30, 30, 5);
        // System.out.println("Enaknya... Santai sekali");
    }

    public void ngoding() {
        setMood(30, 30, 5);
        setKekenyangan(30, 30, -5);
        // System.out.println("Ngoding seru euyy..");
    }

    public void ngudud() {
        setMood(30, 30, 5);
        setKekenyangan(30, 30, -5);
        setKesehatan(30, 30, -5);
        // System.out.println("Fiuhh.. Dunhill emang mantep cuy..");
    }

    public void meditasi() {
        setMood(30, 30, 5);
        setKekenyangan(30, 30, -5);
        setKesehatan(30, 30, 5);
        // System.out.println("Meditasi itu membuat lebih tenang..");
    }

    public void mainPS() {
        setMood(30, 30, 5);
        setKekenyangan(30, 30, -5);
        setKesehatan(30, 30, -5);
        // System.out.println("Game PS itu seru euyy.. T-tapi mataku kok rasanya agak
        // sakit ya..");
    }
}