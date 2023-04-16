package com.simplicity;

import java.util.ArrayList;

import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekNonMakanan;
import com.simplicity.Objek.ObjekPekerjaan;

public class Inisialisasi {
    Inisialisasi(){
        // List objek pekerjaan
        ArrayList<ObjekPekerjaan> daftarPekerjaan = new ArrayList<ObjekPekerjaan>();
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
        
        ObjekBahanMakanan[] daftar_bahan = new ObjekBahanMakanan[8];
        daftar_bahan[0] = nasi;
        daftar_bahan[1] = kentang;
        daftar_bahan[2] = ayam;
        daftar_bahan[3] = sapi;
        daftar_bahan[4] = wortel;
        daftar_bahan[5] = bayam;
        daftar_bahan[6] = kacang;
        daftar_bahan[7] = susu;
        //ObjekBahanMakanan.printArray(daftar_bahan);

        
        // List objek non makanan
        ObjekNonMakanan[] daftar_barang = new ObjekNonMakanan[8];
        // Menambahkan elemen ke dalam array daftar_barang
        daftar_barang[0] = new ObjekNonMakanan("Kasur Single\t", 4, 1, 50, "Tidur");
        daftar_barang[1] = new ObjekNonMakanan("Kasur Queen Size", 4, 2, 100, "Tidur");
        daftar_barang[2] = new ObjekNonMakanan("Kasur King Size ", 5, 2, 150, "Tidur");
        daftar_barang[3] = new ObjekNonMakanan("Toilet\t\t", 1, 1, 50, "Buang air");
        daftar_barang[4] = new ObjekNonMakanan("Kompor Gas\t", 2, 1, 100, "Memasak");
        daftar_barang[5] = new ObjekNonMakanan("Kompor Listrik\t", 1, 1, 200, "Memasak");
        daftar_barang[6] = new ObjekNonMakanan("Meja dan Kursi\t", 3, 3, 50, "Makan");
        daftar_barang[7] = new ObjekNonMakanan("Jam\t\t", 1, 1, 10, "Melihat Waktu");

        // List objek makanan
        ObjekMakanan[] daftar_makanan = new ObjekMakanan[5];

        daftar_makanan[0] = new ObjekMakanan("Nasi Ayam", new ObjekBahanMakanan[]{nasi, ayam}, 16);
        daftar_makanan[1] = new ObjekMakanan("Nasi Kari", new ObjekBahanMakanan[]{nasi, kentang, wortel, sapi}, 30);
        daftar_makanan[2] = new ObjekMakanan("Susu Kacang", new ObjekBahanMakanan[]{susu, kacang}, 5);
        daftar_makanan[3] = new ObjekMakanan("Tumis Sayur", new ObjekBahanMakanan[]{wortel, bayam}, 5);
        daftar_makanan[4] = new ObjekMakanan("Bistik\t", new ObjekBahanMakanan[]{kentang, sapi}, 22);    
    }
}
