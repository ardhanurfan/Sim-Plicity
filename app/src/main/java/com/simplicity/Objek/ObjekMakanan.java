package com.simplicity.Objek;

public class ObjekMakanan extends Objek {
    ObjekBahanMakanan[] bahan;
    int kekenyangan;

    private ThreeElementArray<String, ObjekBahanMakanan[], Integer> item;

    public ObjekMakanan(String nama_makanan, ObjekBahanMakanan[] bahan, int kekenyangan) {
        super(nama_makanan);
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;
        item = new ThreeElementArray<String, ObjekBahanMakanan[], Integer>(nama_makanan, bahan, kekenyangan);
    }

    // getter
    public ObjekBahanMakanan[] getBahan() {
        return item.getSecond();
    }

    public int getKekenyangan() {
        return item.getThird();
    }

    // tidak ada setter karena tidak diperlukan
}
