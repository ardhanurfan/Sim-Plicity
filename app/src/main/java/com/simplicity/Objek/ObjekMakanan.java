package com.simplicity.Objek;

public class ObjekMakanan extends Objek {
    ObjekBahanMakanan[] bahan;
    int kekenyangan;

    public ObjekMakanan(String nama_makanan, ObjekBahanMakanan[] bahan, int kekenyangan){
        super(nama_makanan);
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;
    }

    // getter
    public ObjekBahanMakanan[] getBahan(){
        
        return bahan;
    }

    public int getKekenyangan(){
        return kekenyangan;
    }

    // tidak ada setter karena tidak diperlukan

    // print
    public void printBahan(){
        System.out.print(bahan[0]);
        for (int i = 1; i < bahan.length; i++){
            System.out.print("+" + bahan[0]);
        }
    }

    public void print(){
        System.out.print("\t"+ getNamaObjek() + "\t");
        printBahan();
        if (kekenyangan < 30){
            System.out.printf("\t\t\t%d\n", kekenyangan);
        } else {
            System.out.printf("\t%d\n", kekenyangan);
        }
    }

    // print array
    public static void printArray(ObjekMakanan[] daftar_makanan){
        System.out.println("Berikut adalah daftar masakan yang tersedia.");
        System.out.println("No \tNama Masakan \tBahan yang Diperlukan \t\tKekenyangan");
        for (int i = 0; i < daftar_makanan.length; i++) {
            System.out.print(i+1);
            daftar_makanan[i].print();
        }
    }
}
