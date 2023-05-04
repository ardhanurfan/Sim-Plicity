package com.simplicity.Objek;

public class ObjekBahanMakanan extends Objek {
    int harga;
    int kekenyangan;

    private ThreeElementArray<String, Integer, Integer> item;

    public ObjekBahanMakanan(String nama_bahan, int harga, int kekenyangan) {
        super(nama_bahan);
        this.harga = harga;
        this.kekenyangan = kekenyangan;
        item = new ThreeElementArray<String, Integer, Integer>(nama_bahan, harga, kekenyangan);
    }

    public int getHarga() {
        return item.getSecond();
    }

    public int getKekenyangan() {
        return item.getThird();
    }

    // tidak ada setter karena tidak dibutuhkan
}
