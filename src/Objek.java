public abstract class Objek {
    private String namaObjek;

    public Objek(String namaObjek) {
        this.namaObjek = namaObjek;
    }

    public String getNamaObjek(){
        return namaObjek;
    }

    public abstract void print();

}
