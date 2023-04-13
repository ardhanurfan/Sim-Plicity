public class ObjekBahanMakanan {
    String nama_bahan;
    int harga;
    int kekenyangan;
    
    public ObjekBahanMakanan(String nama_bahan, int harga, int kekenyangan){
        this.nama_bahan = nama_bahan;
        this.harga = harga;
        this.kekenyangan = kekenyangan;
    }

    public String getNama_bahan() {
        return nama_bahan;
    }

    public int getHarga() {
        return harga;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    // tidak ada setter karena tidak dibutuhkan

    public void print(){
        System.out.printf("\t%S \t%d \t%d\n", nama_bahan, harga, kekenyangan);
    }

    // print array of bahan_makanan

    public static void printArray(ObjekBahanMakanan[] daftar_bahan){
        System.out.println("Berikut adalah daftar bahan makanan yang tersedia.");
        System.out.println("No \tBahan Makanan \tHarga \tKekenyangan");
        for (int i = 0; i < daftar_bahan.length; i++) {
            System.out.print(i+1);
            daftar_bahan[i].print();
        }
    }

    // testing
    public static void main(String[] args){
        ObjekBahanMakanan[] daftar_bahan = new ObjekBahanMakanan[8];
        daftar_bahan[0] = new ObjekBahanMakanan("Nasi\t", 5, 5);
        daftar_bahan[1] = new ObjekBahanMakanan("Kentang", 3, 4);
        daftar_bahan[2] = new ObjekBahanMakanan("Ayam\t", 10, 8);
        daftar_bahan[3] = new ObjekBahanMakanan("Sapi\t", 12, 15);
        daftar_bahan[4] = new ObjekBahanMakanan("Wortel\t", 3, 2);
        daftar_bahan[5] = new ObjekBahanMakanan("Bayam\t", 3, 2);
        daftar_bahan[6] = new ObjekBahanMakanan("Kacang\t", 2, 2);
        daftar_bahan[7] = new ObjekBahanMakanan("Susu\t", 2, 1);
        printArray(daftar_bahan);

    }
    
}
