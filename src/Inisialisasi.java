public class Inisialisasi {
    Inisialisasi(){
        // List objek pekerjaan
        ObjekPekerjaan[] daftar_pekerjaan = new ObjekPekerjaan[5];
        daftar_pekerjaan[0] = new ObjekPekerjaan("Badut Sulap", 15);
        daftar_pekerjaan[1] = new ObjekPekerjaan("Koki\t", 30);
        daftar_pekerjaan[2] = new ObjekPekerjaan("Polisi\t", 35);
        daftar_pekerjaan[3] = new ObjekPekerjaan("Programmer", 45);
        daftar_pekerjaan[4] = new ObjekPekerjaan("Dokter\t", 50);

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

        
        // List objek non makanan
        ObjekNonMakanan[] daftar_barang = new ObjekNonMakanan[8];
        // Menambahkan elemen ke dalam array daftar_barang
        daftar_barang[0] = new ObjekNonMakanan("Kasur Single\t", 4, 1, 50, "Tidur");
        daftar_barang[1] = new ObjekNonMakanan("Kasur Queen Size", 4, 2, 100, "Tidur");
        daftar_barang[2] = new ObjekNonMakanan("Kasur King Size", 5, 2, 150, "Tidur");
        daftar_barang[3] = new ObjekNonMakanan("Toilet\t\t", 1, 1, 50, "Buang air");
        daftar_barang[4] = new ObjekNonMakanan("Kompor Gas\t", 2, 1, 100, "Memasak");
        daftar_barang[5] = new ObjekNonMakanan("Kompor Listrik\t", 1, 1, 200, "Memasak");
        daftar_barang[6] = new ObjekNonMakanan("Meja dan Kursi\t", 3, 3, 50, "Makan");
        daftar_barang[7] = new ObjekNonMakanan("Jam\t\t", 1, 1, 10, "Melihat Waktu");

        // List objek makanan
        ObjekMakanan[] daftar_makanan = new ObjekMakanan[5];
        daftar_makanan[0] = new ObjekMakanan("Nasi Ayam", new String[]{"Nasi", "Ayam"}, 16);
        daftar_makanan[1] = new ObjekMakanan("Nasi Kari", new String[]{"Nasi", "Kentang", "Wortel", "Sapi"}, 30);
        daftar_makanan[2] = new ObjekMakanan("Susu Kacang", new String[]{"Susu", "Kacang"}, 5);
        daftar_makanan[3] = new ObjekMakanan("Tumis Sayur", new String[]{"Wortel", "Bayam"}, 5);
        daftar_makanan[4] = new ObjekMakanan("Bistik\t", new String[]{"Kentang","Sapi"}, 22);    

    }
}
