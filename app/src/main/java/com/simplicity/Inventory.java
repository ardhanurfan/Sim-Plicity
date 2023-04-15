package com.simplicity;

import java.util.ArrayList;

import com.simplicity.Objek.Objek;
import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekNonMakanan;

public class Inventory {
    private ArrayList<InventoryItem> data = new ArrayList<InventoryItem>();

    public class InventoryItem{
        private String namaBarang;
        private String kategori;
        private int jumlah;

        public InventoryItem(String namaBarang, String kategori, int jumlah) {
            this.namaBarang = namaBarang;
            this.kategori = kategori;
            this.jumlah = jumlah;
        }

        public String getNamaBarang() {
            return namaBarang;
        }

        public String getKategori() {
            return kategori;
        }

        public int getJumlah() {
            return jumlah;
        }

        public void setNamaBarang(String namaBarang) {
            this.namaBarang = namaBarang;
        }

        public void setKategori(String kategori) {
            this.kategori = kategori;
        }

        public void setJumlah(int jumlah) {
            this.jumlah = jumlah;
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
    public void addItemNonMakanan(ObjekNonMakanan objekNonMakanan, int banyak) {
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
                    data.remove(item);
                } else {
                    item.setJumlah(item.getJumlah() - banyak);
                }
            }
        }
    }

    // print inventory
    public void viewInventory() {
        System.out.println("========== Inventory ==========");
        int  i = 1;
        if (data.isEmpty())
        {
            System.out.println(" Inventory Kosong");
        }
        else {
            System.out.println("No \tNama Barang \tKategori \tJumlah");
        }
        for (InventoryItem item : data) {
            System.out.println(" " + i + ". " + item.getNamaBarang() + item.getKategori() +  item.getJumlah());
            i++;
        }
    }
}
