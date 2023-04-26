package com.simplicity.Objek;

public class ObjekMakanan extends Objek {
    ObjekBahanMakanan[] bahan;
    int kekenyangan;

    private ThreeElementArray<String, ObjekBahanMakanan[], Integer> item;

    public ObjekMakanan(String nama_makanan, ObjekBahanMakanan[] bahan, int kekenyangan){
        super(nama_makanan);
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;
    }

    // getter
    public ObjekBahanMakanan[] getBahan(){
        return item.getSecond();
    }

    public int getKekenyangan(){
        return item.getThird();
    }

    // tidak ada setter karena tidak diperlukan

    // print
    public void printBahan(){
        System.out.print(bahan[0].getNamaObjek());
        for (int i = 1; i < bahan.length; i++){
            if (bahan[i].getNamaObjek().equals("Kentang\t")){
                System.out.print("+Kentang");
            }else{
                System.out.print("+" + bahan[i].getNamaObjek());
            }
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
    public void printDaftarMakanan(ObjekMakanan[] daftar_makanan){
        System.out.println("Berikut adalah daftar masakan yang tersedia.");
        System.out.println("No \tNama Masakan \tBahan yang Diperlukan \t\tKekenyangan");
        for (int i = 0; i < daftar_makanan.length; i++) {
            System.out.print(i+1);
            daftar_makanan[i].print();
        }
    }

    // testing
    public static void main (String[] args){
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

        ObjekMakanan[] daftar_makanan = new ObjekMakanan[5];

        daftar_makanan[0] = new ObjekMakanan("Nasi Ayam", new ObjekBahanMakanan[]{nasi, ayam}, 16);
        daftar_makanan[1] = new ObjekMakanan("Nasi Kari", new ObjekBahanMakanan[]{nasi, kentang, wortel, sapi}, 30);
        daftar_makanan[2] = new ObjekMakanan("Susu Kacang", new ObjekBahanMakanan[]{susu, kacang}, 5);
        daftar_makanan[3] = new ObjekMakanan("Tumis Sayur", new ObjekBahanMakanan[]{wortel, bayam}, 5);
        daftar_makanan[4] = new ObjekMakanan("Bistik\t", new ObjekBahanMakanan[]{kentang, sapi}, 22);    

        daftar_makanan[0].printDaftarMakanan(daftar_makanan);
    }
}
