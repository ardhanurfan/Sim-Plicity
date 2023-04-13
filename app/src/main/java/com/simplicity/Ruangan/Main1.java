public class Main1 {
    public static void main (String[] args){
    
        ObjekNonMakanan o1 = new ObjekNonMakanan("Ayam", 1, 4, 50, null);
        ObjekNonMakanan o2 = new ObjekNonMakanan("Kucing", 3, 4, 70, null);
        Ruangan r1 = new Ruangan("Haha");
        r1.tambahObjek(o1);
        System.out.println(r1.getLuasSisa());
        r1.tambahObjek(o2);
        System.out.println(r1.getLuasSisa());
        r1.tampilkanDaftarObjek();
        System.out.println(r1.getNama());
        r1.setNama("Blog");
        System.out.println(r1.getNama());
        System.out.println(r1.getObjek(1).getNama_barang());
    }
}
