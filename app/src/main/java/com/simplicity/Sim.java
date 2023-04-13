package com.simplicity;

import java.util.ArrayList;
import java.util.Collections;

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

    public Sim(String namaLengkap, ArrayList<ObjekPekerjaan> daftarPekerjaan) {
        this.namaLengkap = namaLengkap;
        Collections.shuffle(daftarPekerjaan);
        this.pekerjaan = daftarPekerjaan.get(0);
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
    }

    public class LokasiSim {
        Rumah rumah;
        Ruangan ruangan;

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


    // AKSI YANG DAPAT DILAKUKAN
    public void pindahRuangan(Ruangan goingRuangan) {
        // System.out.println("Anda sekarang berada di ruangan " + LokasiSim.getRuanga ...);
        // System.out.println("Anda akan berpindah ke ruangan " + goingRungan ...);
        currLokasi.setRuangan(goingRuangan);
    }

    
}