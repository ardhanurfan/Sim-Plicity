// contoh main dari objek. silahkan dicoba

public class Main {
    public static void main (String[] args){
    
        ObjekBahanMakanan[] daftar_bahan = new ObjekBahanMakanan[8];
        daftar_bahan[0] = new ObjekBahanMakanan("Nasi\t", 5, 5);
        daftar_bahan[1] = new ObjekBahanMakanan("Kentang", 3, 4);
        daftar_bahan[2] = new ObjekBahanMakanan("Ayam\t", 10, 8);
        daftar_bahan[3] = new ObjekBahanMakanan("Sapi\t", 12, 15);
        daftar_bahan[4] = new ObjekBahanMakanan("Wortel\t", 3, 2);
        daftar_bahan[5] = new ObjekBahanMakanan("Bayam\t", 3, 2);
        daftar_bahan[6] = new ObjekBahanMakanan("Kacang\t", 2, 2);
        daftar_bahan[7] = new ObjekBahanMakanan("Susu\t", 2, 1);
        ObjekBahanMakanan.printArray(daftar_bahan);
        

    }

    
}
