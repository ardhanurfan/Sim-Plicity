package com.simplicity;
import java.util.ArrayList;
import com.simplicity.Objek.ObjekNonMakanan;



public class Ruangan {
    private String nama;
    private ArrayList<ObjekNonMakanan> daftarObjek;
    private String[][] ruang;
    private int luasSisa;

    public Ruangan(String nama) {
        this.nama = nama;
        this.luasSisa = 36;
        this.daftarObjek = new ArrayList<ObjekNonMakanan>();
        this.ruang = new String[6][6];
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getLuasSisa() {
        return luasSisa;
    }

    public void addLuasSisa(int kurang) {
            luasSisa+=kurang;
    }

    public String getRuangan(int x, int y) {
        return ruang[x][y];
    }

    public ObjekNonMakanan getObjek(int index) {
        return daftarObjek.get(index);
    }

    public void tambahObjek(ObjekNonMakanan objek, Point point,String posisi) {
        daftarObjek.add(objek);
        int luas = objek.getPanjang()*objek.getLebar();
        addLuasSisa(-luas);
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

    public void hapusObjek(ObjekNonMakanan objek,Point point, String posisi) {
        daftarObjek.remove(objek);
        int luas = objek.getPanjang()*objek.getLebar();
        addLuasSisa(luas);
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
                ruang[point.getX()+i][point.getY()+j]=null;
            }
        }
    }

    public void tampilkanRuangan() {
        System.out.println("Berikut adalah tata letak ruangan");
        for (int i = 0; i < ruang.length; i++) {
            for (int j = 0; j < ruang[i].length; j++) {
                if (ruang[i][j] == null) {
                    System.out.print("|\t\t");
                } else {
                    System.out.print("| " + ruang[i][j] + "\t\t");
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

