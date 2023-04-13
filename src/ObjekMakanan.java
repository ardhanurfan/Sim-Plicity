import java.util.Arrays;

public class ObjekMakanan extends Objek {
    String[] bahan;
    int kekenyangan;

    public ObjekMakanan(String nama_makanan, String[] bahan, int kekenyangan){
        super(nama_makanan);
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;
    }

    // getter
    public String[] getBahan(){
        
        return bahan;
    }

    public int getKekenyangan(){
        return kekenyangan;
    }

    // tidak ada setter karena tidak diperlukan

    // print
    public void printBahan(){
        System.out.print(bahan[0]);
        for (int i = 1; i < bahan.length; i++){
            System.out.print("+" + bahan[i]);
        }
    }

    public void print(){
        System.out.print("\t"+ getNamaObjek() + "\t");
        printBahan();
        if (kekenyangan < 30){
            System.out.printf("\t\t\t%d\n", kekenyangan);
        } else {
            System.out.printf("\t%d\n", kekenyangan);
        }
    }

    // print array
    public static void printArray(ObjekMakanan[] daftar_makanan){
        System.out.println("Berikut adalah daftar masakan yang tersedia.");
        System.out.println("No \tNama Masakan \tBahan yang Diperlukan \t\tKekenyangan");
        for (int i = 0; i < daftar_makanan.length; i++) {
            System.out.print(i+1);
            daftar_makanan[i].print();
        }
    }

    // testing
    public static void main(String[] args){
        // inisialisasi di bawah ini di letakan di MAIN

        // Inisiasi array daftar makanan dengan tipe objek_makanan
        ObjekMakanan[] daftar_makanan = new ObjekMakanan[5];
        daftar_makanan[0] = new ObjekMakanan("Nasi Ayam", new String[]{"Nasi", "Ayam"}, 16);
        daftar_makanan[1] = new ObjekMakanan("Nasi Kari", new String[]{"Nasi", "Kentang", "Wortel", "Sapi"}, 30);
        daftar_makanan[2] = new ObjekMakanan("Susu Kacang", new String[]{"Susu", "Kacang"}, 5);
        daftar_makanan[3] = new ObjekMakanan("Tumis Sayur", new String[]{"Wortel", "Bayam"}, 5);
        daftar_makanan[4] = new ObjekMakanan("Bistik\t", new String[]{"Kentang","Sapi"}, 22);
        // print daftar makanan
        //printArray(daftar_makanan); 

        // print daftar bahan dari salah satu menu
        String[] bahan1 = daftar_makanan[0].getBahan();
        System.out.println(Arrays.toString(bahan1));

        // print salah satu bahan dari salah satu menu
        System.out.println(daftar_makanan[0].getBahan()[1]);
        
    }    
}
