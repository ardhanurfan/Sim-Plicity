package com.simplicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekPekerjaan;

public class Sim {
    private String namaLengkap;
    private ObjekPekerjaan pekerjaan;
    private int uang;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;
    private LokasiSim currLokasi;

    // STATE VARIABLE
    // Reset saat ganti kerja
    private int totalWaktuKerja = 0;
    private int jedaGantiKerja = 0;

    // Reset Harian
    private int totalWaktuTidur = 0;

    // Reset jika sudah tidur
    private int waktuTidakTidur = 0;

    // Reset jika sudah buang air
    private int waktuTidakBuangAir = 0;
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
    }

    public Sim(String namaLengkap, ObjekPekerjaan[] daftarPekerjaan) {
        this.namaLengkap = namaLengkap;
        Collections.shuffle(Arrays.asList(daftarPekerjaan));
        this.pekerjaan = daftarPekerjaan[0];
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
    }

    public class LokasiSim {
        private Rumah rumah;
        private Ruangan ruangan;

        public LokasiSim(Rumah rumah, Ruangan ruangan) {
            this.ruangan = ruangan;
            this.rumah = rumah;
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

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getPekerjaan() {
        return pekerjaan.getNamaObjek();
    }

    public void setPekerjaan(ObjekPekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
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

    // Waktu bertambah karena melakukan aksi
    public void addOnAksi(int waktuAksi) {
        this.jedaGantiKerja += waktuAksi;
        this.waktuTidakTidur += waktuAksi;
        if (isTidakBuangAir) {
            waktuTidakBuangAir += waktuAksi;
        }
    }

    // reset tiap berganti hari
    public void resetWaktuKegiatanharian() {
        totalWaktuTidur = 0;
    }

    // AKSI YANG DAPAT DILAKUKAN
    public void viewSim() {
        System.out.println("========== Data SIM ==========");
        System.out.println(" Nama\t: " + namaLengkap);
        System.out.println(" Pekerjaan\t: " + pekerjaan);
        System.out.println(" Kesehatan\t: " + kesehatan);
        System.out.println(" Kekenyangan\t: " + kekenyangan);
        System.out.println(" Mood\t: " + mood);
        System.out.println(" Uang\t: " + uang);
    }

    public void viewCurrLokasi() {
        System.out.println("==============================");
        System.out.println("=========== LOCATION =========");
        System.out.println("Rumah\t: " + currLokasi.getRumah().getNama());
        System.out.println("Ruangan\t: " + currLokasi.getRuangan().getNama());
    }

    public void kerja(int waktuKerja) {
        if (jedaGantiKerja <= 0) {
            kekenyangan -= waktuKerja/30*10;
            mood -= waktuKerja/30*10;
            if (pekerjaan.getNamaObjek().equals("Badut Sulap")) {
                uang += waktuKerja/240*15;
            } else if (pekerjaan.getNamaObjek().equals("Koki")) {
                uang += waktuKerja/240*30;
            } else if (pekerjaan.getNamaObjek().equals("Polisi")) {
                uang += waktuKerja/240*35;
            } else if (pekerjaan.getNamaObjek().equals("Programmer")) {
                uang += waktuKerja/240*45;
            } else if (pekerjaan.getNamaObjek().equals("Dokter")) {
                uang += waktuKerja/240*50;
            }
            totalWaktuKerja += waktuKerja;

            System.out.println("Kerja selesai selama " + (waktuKerja < 60 ? (waktuKerja + " detik") : (waktuKerja/60 + ":" + waktuKerja%60 + " menit")));
            System.out.println("Kekenyangan Anda sekarang " + kekenyangan);
            System.out.println("Mood Anda sekarang " + mood);
            System.out.println("Uang Anda sekarang " + uang);
        } else {
            System.out.println("Anda belum bisa bekerja karena belum sehari setelah ganti pekerjaan");
        }
    }

    public void olahraga(int waktuOlahraga) {
        kesehatan += waktuOlahraga/20*5;
        kekenyangan -= waktuOlahraga/20*5;
        mood += waktuOlahraga/20*10;

        System.out.println("Olahraga selesai selama " + (waktuOlahraga < 60 ? (waktuOlahraga + " detik") : (waktuOlahraga/60 + ":" + waktuOlahraga%60 + " menit")));
        System.out.println("Kesehatan Anda sekarang " + kesehatan);
        System.out.println("Kekenyangan Anda sekarang " + kekenyangan);
        System.out.println("Mood Anda sekarang " + mood);
    }

    public void tidur(int waktuTidur) {
        mood += waktuTidur/240*30;
        kesehatan += waktuTidur/240*20;

        totalWaktuTidur += waktuTidur;
    }

    public void efekTidakTidur() {
        if (waktuTidakTidur >= 10) {
            kesehatan -= 5;
            mood -= 5;
            waktuTidakTidur = waktuTidakTidur%10;
        } else {
            waktuTidakTidur++;
        }
    }

    public void makan(ObjekMakanan makanan) {
        // Cek apakah makanan ada di inventory
        
        kekenyangan += makanan.getKekenyangan();

        // kurangi stok makanan pada inventory

        if (!isTidakBuangAir) {
            isTidakBuangAir = true;
            waktuTidakBuangAir = 0;
        }

        //waktu makan 30 detik
    }

    public double masak() {
        // Show menu

        // validasi bahan-bahan dari inventory
        // Jika valid
        ObjekBahanMakanan[] bahan = {};
        // tambahkan bahan dari menu dan yang dari inventory bahan dikurangi stoknya
        ObjekMakanan newmakanan = new ObjekMakanan(namaLengkap, bahan, kekenyangan);
        return newmakanan.getKekenyangan()*1.5;
        // Jika tidak valid kembali ke menu pilih masakan
    }

    public double berkunjung(Rumah yangAkandikunjungi) {
        // POINT
        double waktu = 0; // dari perhitungan point jarak pada rumah
        mood += waktu/30*10;
        kekenyangan -= waktu/30*10;
        return waktu;
    }

    public void buangAir() {
        // waktu 10 detik

        kekenyangan -= 20;
        mood += 10;
        waktuTidakBuangAir = 0;
        isTidakBuangAir = false;
    }

    public void efekTidakBuangAir() {
        if (waktuTidakBuangAir >= 240 && isTidakBuangAir) {
            waktuTidakBuangAir = waktuTidakBuangAir%240;
            kesehatan -= 5;
            mood -= 5;
        }
    }

    public void pindahRuangan(Ruangan goingRuangan) {
        // System.out.println("Anda sekarang berada di ruangan " + LokasiSim.getRuanga ...);
        // System.out.println("Anda akan berpindah ke ruangan " + goingRungan ...);
        currLokasi.setRuangan(goingRuangan);
    }

    public void lihatInventory() {
        // Print inventory
    }

    public void pasangBarang() {
        Ruangan currRuangan = currLokasi.getRuangan();
        currRuangan.addLuasSisa(uang);
        // tampilkan map ruangan

        // tampilkan inventory untuk memilih barang yang akan dipasang

        // pilih barang dari inventory, barang diinventory dikurangi stoknya

        // currRuangan.tambahObjek(); pasang ke ruangan
    }
}