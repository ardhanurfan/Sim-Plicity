package com.simplicity;

import java.util.ArrayList;

import com.simplicity.Objek.Objek;
import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekNonMakanan;
import com.simplicity.Objek.ThreeElementArray;

public class Inventory {
    private ArrayList<InventoryItem> data = new ArrayList<InventoryItem>();

    public class InventoryItem{
        
        private ThreeElementArray<String, String, Integer> item;

        public InventoryItem(String namaBarang, String kategori, int jumlah) {
            this.item = new ThreeElementArray<>(namaBarang, kategori, jumlah);
        }

        public String getNamaBarang() {
            return item.getFirst();
        }

        public void setNamaBarang(String namaBarang) {
            item.setFirst(namaBarang);
        }

        public String getKategori() {
            return item.getSecond();
        }

        public void setKategori(String kategori) {
            item.setSecond(kategori);
        }

        public int getJumlah() {
            return item.getThird();
        }

        public void setJumlah(int jumlah) {
            item.setThird(jumlah);
        }
    }

    public boolean isContains(Objek objek) {
        boolean isAda = false;
        int i = 0;
        while (isAda && i<data.size()) {
            if (data.get(i).getNamaBarang().equals(objek.getNamaObjek())) {
                isAda = true;
            }
            i++;
        }
        return isAda;
    }

    // Menambah item objek non makanan ke inventory
    public void addItemPeralatan(ObjekNonMakanan objekNonMakanan, int banyak) {
        boolean isAda = false; // cek barang udah ada di inventory ato belum. kalo ada tinggal tambahin jumlah
        for (InventoryItem item : data) {
            if (item.getNamaBarang().equals(objekNonMakanan.getNamaObjek())) {
                item.setJumlah(item.getJumlah() + banyak);
                isAda = true;
            }
        }
        if (!isAda) { // kalo barangnya blum ada di inventory
            data.add(new InventoryItem(objekNonMakanan.getNamaObjek(), "Peralatan", banyak));
        }
    }

    // menambah item objek bahan makanan ke inventory
    public void addItemBahanMakanan(ObjekBahanMakanan objekBahanMakanan, int banyak) {
        boolean isAda = false; // cek barang udah ada di inventory ato belum. kalo ada tinggal tambahin jumlah
        for (InventoryItem item : data) {
            if (item.getNamaBarang().equals(objekBahanMakanan.getNamaObjek())) {
                item.setJumlah(item.getJumlah() + banyak);
                isAda = true;
            }
        }
        if (!isAda) { // kalo barangnya blum ada di inventory
            data.add(new InventoryItem(objekBahanMakanan.getNamaObjek(), "Bahan Makanan", banyak));;
        }
    }
    
    // menambah objek makanan jadi ke inventory
    public void addItemMakanan(ObjekMakanan objekMakanan, int banyak) {
        boolean isAda = false; // cek barang udah ada di inventory ato belum. kalo ada tinggal tambahin jumlah
        for (InventoryItem item : data) {
            if (item.getNamaBarang().equals(objekMakanan.getNamaObjek())) {
                item.setJumlah(item.getJumlah() + banyak);
                isAda = true;
            }
        }
        if (!isAda) { // kalo barangnya blum ada di inventory
            data.add(new InventoryItem(objekMakanan.getNamaObjek(), "Makanan", banyak));;
        }
    }

    // Mengurangi jumlah objek dalam inventory 
    public void kurangiItem(String namaItem, int banyak) {
        for (InventoryItem item : data) {
            if (item.getNamaBarang().equals(namaItem)) {
                if (item.getJumlah() - banyak <= 0) { // menghapus item
                    data.removeIf(element -> element.equals(item));
                    break;
                } else {
                    item.setJumlah(item.getJumlah() - banyak);
                }
            }
        }
    }

    // print inventory
    public void viewInventory() { // melihat semua inventory
        System.out.println("========== Inventory ==========");
        int  i = 1;
        if (data.isEmpty())
        {
            System.out.println(" Inventory Kosong");
        }
        else {
            System.out.println("No \tNama Barang \t\tKategori \tJumlah");
        }
        for (InventoryItem item : data) {
            // jika inventory adalah barang peralatan
            if (item.getKategori().equals("Peralatan") ){
                System.out.println(i + ". \t" + item.getNamaBarang() + "\t" + item.getKategori() + "\t" + item.getJumlah());
            }
            // jika inventory adalah bahan makanan
            else if (item.getKategori().equals("Bahan Makanan")) {
                System.out.println(i + ". \t" + item.getNamaBarang() + "\t\t" + item.getKategori() + "\t" + item.getJumlah());
            }
            // jika inventory adalah makanan
            else if (item.getKategori().equals("Makanan")) {
                System.out.println(i + ". \t" + item.getNamaBarang() + "\t\t" + item.getKategori() + "\t\t" + item.getJumlah());
            }
            i++;
        }
    }

    // print inventory khusus peralatan
    public void viewInventoryPeralatan() { // melihat inventory peralatan
        System.out.println("========== Inventory Peralatan ==========");
        int  i = 1;
        if (data.isEmpty())
        {
            System.out.println(" Tidak Ada Peralatan");
        }
        else {
            System.out.println("No \tNama Barang \t\tKategori \tJumlah");
        }
        for (InventoryItem item : data) {
            if (item.getKategori().equals("Peralatan")) {
                System.out.println(i + ". \t" + item.getNamaBarang() + "\t" + item.getKategori() + "\t" + item.getJumlah());
                i++;
            }
        }
    }

    // print inventory khusus makanan dan bahan makanan
    public void viewInventoryMakanan() { // melihat inventory makanan
        System.out.println("========== Inventory Makanan ==========");
        int  i = 1;
        if (data.isEmpty())
        {
            System.out.println(" Tidak ada Makanan");
        }
        else {
            System.out.println("No \tNama Barang \tKategori \tJumlah");
        }
        for (InventoryItem item : data) {
            if (item.getKategori().equals("Makanan") || item.getKategori().equals("Bahan Makanan")) {
                if (item.getKategori().equals("Bahan Makanan")) {
                    System.out.println(i + ". \t" + item.getNamaBarang() + "\t" + item.getKategori() + "\t" + item.getJumlah());
                }
                // jika inventory adalah makanan
                else if (item.getKategori().equals("Makanan")) {
                    System.out.println(i + ". \t" + item.getNamaBarang() + "\t" + item.getKategori() + "\t\t" + item.getJumlah());
                }
                i++;
            }
        }
    }

    // testing inventory
    public static void main(String[] args) {
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
        //ObjekBahanMakanan.printArray(daftar_bahan);

        
        // List objek non makanan
        ObjekNonMakanan[] daftar_barang = new ObjekNonMakanan[8];
        // Menambahkan elemen ke dalam array daftar_barang
        daftar_barang[0] = new ObjekNonMakanan("Kasur Single\t", 4, 1, 50, "Tidur");
        daftar_barang[1] = new ObjekNonMakanan("Kasur Queen Size", 4, 2, 100, "Tidur");
        daftar_barang[2] = new ObjekNonMakanan("Kasur King Size ", 5, 2, 150, "Tidur");
        daftar_barang[3] = new ObjekNonMakanan("Toilet\t\t", 1, 1, 50, "Buang air");
        daftar_barang[4] = new ObjekNonMakanan("Kompor Gas\t", 2, 1, 100, "Memasak");
        daftar_barang[5] = new ObjekNonMakanan("Kompor Listrik\t", 1, 1, 200, "Memasak");
        daftar_barang[6] = new ObjekNonMakanan("Meja dan Kursi\t", 3, 3, 50, "Makan");
        daftar_barang[7] = new ObjekNonMakanan("Jam\t\t", 1, 1, 10, "Melihat Waktu");

        // List objek makanan
        ObjekMakanan[] daftar_makanan = new ObjekMakanan[5];

        daftar_makanan[0] = new ObjekMakanan("Nasi Ayam", new ObjekBahanMakanan[]{nasi, ayam}, 16);
        daftar_makanan[1] = new ObjekMakanan("Nasi Kari", new ObjekBahanMakanan[]{nasi, kentang, wortel, sapi}, 30);
        daftar_makanan[2] = new ObjekMakanan("Susu Kacang", new ObjekBahanMakanan[]{susu, kacang}, 5);
        daftar_makanan[3] = new ObjekMakanan("Tumis Sayur", new ObjekBahanMakanan[]{wortel, bayam}, 5);
        daftar_makanan[4] = new ObjekMakanan("Bistik\t", new ObjekBahanMakanan[]{kentang, sapi}, 22);    
        
        // ArrayList<InventoryItem> Inventory = new ArrayList<InventoryItem>();

        // print inventory
        //Inventory.viewInventory();

        Inventory inventory = new Inventory();
        inventory.addItemPeralatan(daftar_barang[0], 2);
        inventory.addItemPeralatan(daftar_barang[1], 1);
        inventory.addItemPeralatan(daftar_barang[2], 1);
        inventory.addItemPeralatan(daftar_barang[3], 1);
        
        // print inventory 
        //inventory.viewInventory();

        // add item bahan makanan
        inventory.addItemBahanMakanan(daftar_bahan[0], 2);
        inventory.addItemBahanMakanan(daftar_bahan[1], 1);
        inventory.addItemBahanMakanan(daftar_bahan[2], 1);
        inventory.addItemBahanMakanan(daftar_bahan[3], 1);
        inventory.addItemBahanMakanan(daftar_bahan[4], 1);
        inventory.addItemBahanMakanan(daftar_bahan[5], 1);
        inventory.addItemBahanMakanan(daftar_bahan[6], 1);
        inventory.addItemBahanMakanan(daftar_bahan[7], 1);

         
        // print inventory
        //inventory.viewInventory();

        // add item makanan
        inventory.addItemMakanan(daftar_makanan[0], 2);
        inventory.addItemMakanan(daftar_makanan[1], 1);
        inventory.addItemMakanan(daftar_makanan[2], 1);
        inventory.addItemMakanan(daftar_makanan[3], 1);
        inventory.addItemMakanan(daftar_makanan[4], 1);


        // print inventory
        inventory.viewInventory();

        // print khusus makanan
        inventory.viewInventoryMakanan();

        // print khusus peralatan
        inventory.viewInventoryPeralatan();

        // ============= PRINT BERHASIL ==========

        
        // testing add item peralatan 
        
        Inventory inventory2 = new Inventory();
        inventory2.addItemPeralatan(daftar_barang[0], 2);
        
        // print inventory
        inventory2.viewInventory();

        // menambahkan jumlah
        inventory2.addItemPeralatan(daftar_barang[0], 2);

        // print inventory
        inventory2.viewInventory();

        // kurangi item sebanyak 2
        inventory2.kurangiItem(daftar_barang[0].getNamaObjek(), 2);

        // print inventory
        inventory2.viewInventory();

        // kurangi item sebanyak 2 (item menjadi 0 atau habis)
        inventory2.kurangiItem(daftar_barang[0].getNamaObjek(), 2);

        // print inventory
        inventory2.viewInventory();
        inventory2.viewInventoryPeralatan();

        daftar_bahan[0].printDaftarBahanMakanan(daftar_bahan);
        //printDaftarMakanan(daftar_makanan);
        //printDaftarPeralatan(daftar_barang);
        daftar_makanan[0].printDaftarMakanan(daftar_makanan);

    }
}
