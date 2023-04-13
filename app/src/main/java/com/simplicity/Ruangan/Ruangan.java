import java.util.ArrayList;


public class Ruangan {
    private String nama;
    private ArrayList<ObjekNonMakanan> daftarObjek;
    private int luasSisa;

    public Ruangan(String nama) {
        this.nama = nama;
        this.luasSisa = 36;
        this.daftarObjek = new ArrayList<ObjekNonMakanan>();
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

    public ObjekNonMakanan getObjek(int index) {
        return daftarObjek.get(index);
    }

    // public void setObjek(int index, ObjekNonMakanan objek) {
    //     daftarObjek.set(index, objek);
    // }

    public void tambahObjek(ObjekNonMakanan objek) {
        daftarObjek.add(objek);
        int luas = objek.getPanjang()*objek.getLebar();
        addLuasSisa(-luas);
    }

    public void hapusObjek(ObjekNonMakanan objek) {
        daftarObjek.remove(objek);
        int luas = objek.getPanjang()*objek.getLebar();
        addLuasSisa(luas);
    }

    public void tampilkanDaftarObjek() {
        System.out.println("Berikut adalah daftar objek dalam ruangan");
        System.out.println("No \tNama Barang \t");
        int i =1;
        for (ObjekNonMakanan objek : daftarObjek) {
            System.out.print(i+"\t");
            System.out.println(objek.getNama_barang());
            i++;
        }
    }
}
