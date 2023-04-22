package com.simplicity.Objek;
import com.simplicity.Point;


public class ObjekNonMakanan extends Objek{
    int panjang;
    int lebar;
    int harga;
    String aksi;
    Point titik=null;
    String posisi=null;

    public ObjekNonMakanan(String nama_barang, int panjang, int lebar, int harga, String aksi){
        super(nama_barang);
        this.panjang = panjang;
        this.lebar = lebar;
        this.harga = harga;
        this.aksi = aksi;
    }
    
    // getter
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

    public Point getTitik() {
        return titik;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setTitik(Point point){
        titik = point;
    }

    public void setPosisi(String pos){
        posisi= pos;
    }

    // tidak ada setter karena barang hanya diinisialisasi sebelum game dimulai

    // print 
    public void print(){
        System.out.printf("\t%S \t%d \t%d \t%d \t%S\n", getNamaObjek(), panjang, lebar, harga, aksi);
    }

    // semua print array nanti dipindah dan nama nya jadi spesifik 
    public void printDaftarBarang(ObjekNonMakanan[] daftar_barang){
        System.out.println("Berikut adalah daftar barang yang tersedia.");
        System.out.println("No \tNama Barang \t\tPanjang Lebar \tHarga \tAksi");
        for (int i = 0; i < daftar_barang.length; i++){
            System.out.print(i+1);
            daftar_barang[i].print();
        }
    }

    public static ObjekNonMakanan returnObject(String nama){
        ObjekNonMakanan o = null;
        if(nama.equals("kasur single 4x1")){
            o = new ObjekNonMakanan(nama, 4, 1, 50, "Tidur");
        }
        else if(nama.equals("kasur queen size 4x2")){
            o = new ObjekNonMakanan(nama, 4, 2, 100, "Tidur");
        }
        else if(nama.equals("kasur king size 5x2")){
            o = new ObjekNonMakanan(nama, 5, 2, 150, "Tidur");
        }
        else if(nama.equals("toilet 1x1")){
            o = new ObjekNonMakanan(nama, 1, 1, 50, "Buang Air");
        }
        else if(nama.equals("kompor gas 2x1")){
            o = new ObjekNonMakanan(nama, 2, 1, 100, "Memasak");
        }
        else if(nama.equals("kompor listrik 1x1")){
            o = new ObjekNonMakanan(nama, 1, 1, 200, "Memasak");
        }
        else if(nama.equals("meja kursi 3x3")){
            o = new ObjekNonMakanan(nama, 3, 3, 50, "Makan");
        }
        else if(nama.equals("jam 1x1")){
            o = new ObjekNonMakanan(nama, 1, 1, 10, "Melihat Waktu");
        }
        return o;
    }

    // testing
    public static void main(String[] args){
        // inisialisasi di bawah ini di letakan di MAIN

        // Inisiasi array daftar barang dengan tipe objek_non_makanan
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

        // print array of daftar barang
        //daftar_barang[6].printArray(daftar_barang);
        daftar_barang[0].printDaftarBarang(daftar_barang);
    }
}
