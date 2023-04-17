package com.simplicity;
import java.util.ArrayList;
import com.simplicity.Objek.ObjekNonMakanan;



public class Ruangan {
    private String nama;
    private ArrayList<ObjekNonMakanan> daftarObjek;
    private String[][] ruang;
    private Ruangan atas = null;
    private Ruangan bawah = null;
    private Ruangan kiri = null;
    private Ruangan kanan = null;

    public Ruangan(String nama) {
        this.nama = nama;
        this.daftarObjek = new ArrayList<ObjekNonMakanan>();
        this.ruang = new String[6][6];
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Ruangan getAtas(){
        return atas;
    }

    public void setAtas(Ruangan ruangan){
        this.atas = ruangan;
    }

    public Ruangan getBawah(){
        return bawah;
    }

    public void setBawah(Ruangan ruangan){
        this.bawah = ruangan;
    }

    public Ruangan getKanan(){
        return kanan;
    }

    public void setKanan(Ruangan ruangan){
        this.kanan = ruangan;
    }

    public Ruangan getKiri(){
        return kiri;
    }

    public void setKiri(Ruangan ruangan){
        this.kiri = ruangan;
    }

    public String getRuangan(Point point) {
        return ruang[point.getX()][point.getY()];
    }

    public ObjekNonMakanan getObjek(int index) {
        return daftarObjek.get(index);
    }

    public boolean nabrakGa(ObjekNonMakanan objek, Point point, String posisi){
        int i,j, jmax, imax;
        boolean nabrak=false;

        // posisi vertikal
        if(posisi.equals("v")){
            imax = objek.getLebar();
            jmax = objek.getPanjang(); 
        }
        // posisi horizontal
        else{
            jmax = objek.getLebar();
            imax = objek.getPanjang(); 
        }
        if(imax>5 || jmax>5){
            nabrak=true;
        }
        for(i =0;i<imax;i++){
            for(j=0;j<jmax;j++){
                if(ruang[point.getX()+i][point.getY()+j]!=null){
                    nabrak=true;
                }
            }
        }
        return !nabrak;
    }

    public void tambahObjek(ObjekNonMakanan objek, Point point, String posisi) {
        objek.setPosisi(posisi);
        objek.setTitik(point);
        daftarObjek.add(objek);
        int i,j, jmax, imax;

        // posisi vertikal
        if(posisi.equals("v")){
            imax = objek.getLebar();
            jmax = objek.getPanjang(); 
        }
        // posisi horizontal
        else{
            jmax = objek.getLebar();
            imax = objek.getPanjang(); 
        }
        for(i =0;i<imax;i++){
            for(j=0;j<jmax;j++){
                ruang[point.getX()+i][point.getY()+j]=objek.getNamaObjek();
            }
        }
    }

    public void hapusObjek(ObjekNonMakanan objek) {
        int i,j, jmax, imax;

        // posisi vertikal
        if(objek.getPosisi().equals("v")){
            imax = objek.getLebar();
            jmax = objek.getPanjang(); 
        }
        // posisi horizontal
        else{
            jmax = objek.getLebar();
            imax = objek.getPanjang(); 
        }
        for(i =0;i<imax;i++){
            for(j=0;j<jmax;j++){
                ruang[objek.getTitik().getX()+i][objek.getTitik().getY()+j]=null;
            }
        }
        objek.setPosisi(null);
        objek.setTitik(null);
        daftarObjek.remove(objek);
    }

    public void tampilkanRuangan() {
        System.out.println("Berikut adalah tata letak ruangan");
        for (int i = 0; i < ruang.length; i++) {
            for (int j = 0; j < ruang[i].length; j++) {
                if (ruang[i][j] == null) {
                    System.out.print("| \t\t");
                } else {
                    System.out.print("| " + ruang[i][j] + "  \t");
                }
            }
            System.out.println("|");
        }
    }
    

    public void tampilkanDaftarObjek() {
        System.out.println("Berikut adalah daftar objek dalam ruangan");
        System.out.println("No \tNama Barang \t");
        int i =1;
        for (ObjekNonMakanan objek : daftarObjek) {
            System.out.print(i+"\t");
            System.out.println(objek.getNamaObjek());
            i++;
        }
    }
}

