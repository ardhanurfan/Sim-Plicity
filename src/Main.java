// contoh main dari objek. silahkan dicoba

public class Main {
    public static void main (String[] args){
    
        objek_bahan_makanan[] daftar_bahan = new objek_bahan_makanan[8];
        daftar_bahan[0] = new objek_bahan_makanan("Nasi\t", 5, 5);
        daftar_bahan[1] = new objek_bahan_makanan("Kentang", 3, 4);
        daftar_bahan[2] = new objek_bahan_makanan("Ayam\t", 10, 8);
        daftar_bahan[3] = new objek_bahan_makanan("Sapi\t", 12, 15);
        daftar_bahan[4] = new objek_bahan_makanan("Wortel\t", 3, 2);
        daftar_bahan[5] = new objek_bahan_makanan("Bayam\t", 3, 2);
        daftar_bahan[6] = new objek_bahan_makanan("Kacang\t", 2, 2);
        daftar_bahan[7] = new objek_bahan_makanan("Susu\t", 2, 1);
        objek_bahan_makanan.printArray(daftar_bahan);
        

    }

    
}
