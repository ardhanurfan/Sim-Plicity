package com.simplicity.Objek;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class ObjekPekerjaan extends Objek {
    double gaji;

    public ObjekPekerjaan(String nama_pekerjaan, double gaji) {
        super(nama_pekerjaan);
        this.gaji = gaji;
    }

    public ObjekPekerjaan(JSONObject jsonObject) {
        super(jsonObject.get("nama").toString());
        gaji = Double.parseDouble(jsonObject.get("gaji").toString());
    }

    public JSONObject toJson() {
        HashMap<String, Object> objekPekerjaanMap = new HashMap<String, Object>();

        objekPekerjaanMap.put("nama", getNamaObjek());
        objekPekerjaanMap.put("gaji", gaji);

        JSONObject objekPekerjaanJSON = new JSONObject(objekPekerjaanMap);
        return objekPekerjaanJSON;
    }

    public double getGaji() {
        return gaji;
    }

    // tidak perlu setter karena pekerjaan hanya diinisialisasi saat game dimulai

    // print
    public void print() {
        System.out.println("\t" + getNamaObjek() + "\t" + gaji);

    }

    // print array of daftar_pekerjaan
    public void printDaftarPekerjaan(ObjekPekerjaan[] daftar_pekerjaan) {
        System.out.println("Berikut adalah daftar pekerjaan yang tersedia.");
        System.out.println("No \tNama Pekerjaan \tGaji");
        for (int i = 0; i < daftar_pekerjaan.length; i++) {
            // System.out.println((i+1) , daftar_pekerjaan[i].print());
            System.out.print(i + 1);
            daftar_pekerjaan[i].print();
        }
    }

    // testing
    public static void main(String[] args) {

        // inisialisasi di bawah ini di letakan di MAIN

        // Inisiasi array daftar pekerjaan dengan tipe objek_pekerjaan
        ObjekPekerjaan[] daftar_pekerjaan = new ObjekPekerjaan[5];

        // Menambahkan elemen ke dalam array daftar_pekerjaan
        daftar_pekerjaan[0] = new ObjekPekerjaan("Badut Sulap", 15);
        daftar_pekerjaan[1] = new ObjekPekerjaan("Koki\t", 30);
        daftar_pekerjaan[2] = new ObjekPekerjaan("Polisi\t", 35);
        daftar_pekerjaan[3] = new ObjekPekerjaan("Programmer", 45);
        daftar_pekerjaan[4] = new ObjekPekerjaan("Dokter\t", 50);

        // print array of daftar pekerjaan
        daftar_pekerjaan[0].printDaftarPekerjaan(daftar_pekerjaan);
    }
}
