public class objek_non_makanan {
    String nama_barang;
    int panjang;
    int lebar;
    int harga;
    String aksi;

    public objek_non_makanan(String nama_barang, int panjang, int lebar, int harga, String aksi){
        this.nama_barang = nama_barang;
        this.panjang = panjang;
        this.lebar = lebar;
        this.harga = harga;
        this.aksi = aksi;
    }
    
    // getter

    public String getNama_barang() {
        return nama_barang;
    }

    public int getPanjang() {
        return panjang;
    }

    public int getLebar() {
        return lebar;
    }

    public int getHarga() {
        return harga;
    }

    public String getAksi() {
        return aksi;
    }

    // tidak ada setter karena barang hanya diinisialisasi sebelum game dimulai

    // print 
    public void print(){
        System.out.printf("\t%S \t%d \t%d \t%d \t%S\n", nama_barang, panjang, lebar, harga, aksi);
    }

    // print array of daftar barang 
    public static void printArray(objek_non_makanan[] daftar_barang){
        System.out.println("Berikut adalah daftar barang yang tersedia.");
        System.out.println("No \tNama Barang \t\tPanjang Lebar \tHarga \tAksi");
        for (int i = 0; i < daftar_barang.length; i++){
            System.out.print(i+1);
            daftar_barang[i].print();
        }
    }

    // testing
    public static void main(String[] args){
        // inisialisasi di bawah ini di letakan di MAIN

        // Inisiasi array daftar barang dengan tipe objek_non_makanan
        objek_non_makanan[] daftar_barang = new objek_non_makanan[8];

        // Menambahkan elemen ke dalam array daftar_barang
        daftar_barang[0] = new objek_non_makanan("Kasur Single\t", 4, 1, 50, "Tidur");
        daftar_barang[1] = new objek_non_makanan("Kasur Queen Size", 4, 2, 100, "Tidur");
        daftar_barang[2] = new objek_non_makanan("Kasur King Size", 5, 2, 150, "Tidur");
        daftar_barang[3] = new objek_non_makanan("Toilet\t\t", 1, 1, 50, "Buang air");
        daftar_barang[4] = new objek_non_makanan("Kompor Gas\t", 2, 1, 100, "Memasak");
        daftar_barang[5] = new objek_non_makanan("Kompor Listrik\t", 1, 1, 200, "Memasak");
        daftar_barang[6] = new objek_non_makanan("Meja dan Kursi\t", 3, 3, 50, "Makan");
        daftar_barang[7] = new objek_non_makanan("Jam\t\t", 1, 1, 10, "Melihat Waktu");

        // print array of daftar barang
        printArray(daftar_barang);
    }
}
