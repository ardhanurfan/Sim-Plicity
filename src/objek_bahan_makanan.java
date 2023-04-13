public class objek_bahan_makanan {
    String nama_bahan;
    int harga;
    int kekenyangan;
    
    public objek_bahan_makanan(String nama_bahan, int harga, int kekenyangan){
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

    public static void printArray(objek_bahan_makanan[] daftar_bahan){
        System.out.println("Berikut adalah daftar bahan makanan yang tersedia.");
        System.out.println("No \tBahan Makanan \tHarga \tKekenyangan");
        for (int i = 0; i < daftar_bahan.length; i++) {
            System.out.print(i+1);
            daftar_bahan[i].print();
        }
    }

    // testing
    public static void main(String[] args){
        objek_bahan_makanan[] daftar_bahan = new objek_bahan_makanan[8];
        daftar_bahan[0] = new objek_bahan_makanan("Nasi\t", 5, 5);
        daftar_bahan[1] = new objek_bahan_makanan("Kentang", 3, 4);
        daftar_bahan[2] = new objek_bahan_makanan("Ayam\t", 10, 8);
        daftar_bahan[3] = new objek_bahan_makanan("Sapi\t", 12, 15);
        daftar_bahan[4] = new objek_bahan_makanan("Wortel\t", 3, 2);
        daftar_bahan[5] = new objek_bahan_makanan("Bayam\t", 3, 2);
        daftar_bahan[6] = new objek_bahan_makanan("Kacang\t", 2, 2);
        daftar_bahan[7] = new objek_bahan_makanan("Susu\t", 2, 1);
        printArray(daftar_bahan);

    }
    
}
