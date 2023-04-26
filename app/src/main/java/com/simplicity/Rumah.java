package com.simplicity;

import com.simplicity.Objek.ObjekNonMakanan;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Rumah {
    // Atribute
    private int id;
    private Point loc;
    private List<Ruangan> daftarRuangan;
    private String nama;

    // Constructor
    public Rumah(Point loc, String nama, int id) {
        this.id = id;
        this.loc = loc;
        this.nama = nama;
        this.daftarRuangan = new ArrayList<Ruangan>();
        // Inisialisasi ruangan pertama saat rumah pertama kali dibuat
        this.daftarRuangan.add(new Ruangan("Kamar", 0));
        inisialisasi();
    }

    public Rumah(JSONObject jsonObject) {
        id = Integer.parseInt(jsonObject.get("id").toString());
        nama = jsonObject.get("nama").toString();
        loc = new Point((JSONObject) jsonObject.get("loc"));

        JSONArray jsonArrayRumah = (JSONArray) jsonObject.get("daftarRuangan");
        List<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
        for (Object object : jsonArrayRumah) {
            JSONObject obj = (JSONObject) object;
            Ruangan ruangan = new Ruangan(obj);
            // Untuk cek acuan ruangan
            if (ruangan.getId() != 0) {
                if (obj.get("atas") != null) {
                    Ruangan ruanganacuan = daftarRuangan.get(Integer.parseInt(obj.get("atas").toString()));
                    ruangan.setAtas(ruanganacuan);
                    ruanganacuan.setBawah(ruangan);
                }
                if (obj.get("bawah") != null) {
                    Ruangan ruanganacuan = daftarRuangan.get(Integer.parseInt(obj.get("bawah").toString()));
                    ruangan.setBawah(ruanganacuan);
                    ruanganacuan.setAtas(ruangan);
                }
                if (obj.get("kiri") != null) {
                    Ruangan ruanganacuan = daftarRuangan.get(Integer.parseInt(obj.get("kiri").toString()));
                    ruangan.setKiri(ruanganacuan);
                    ruanganacuan.setKanan(ruangan);
                }
                if (obj.get("kanan") != null) {
                    Ruangan ruanganacuan = daftarRuangan.get(Integer.parseInt(obj.get("kanan").toString()));
                    ruangan.setKanan(ruanganacuan);
                    ruanganacuan.setKiri(ruangan);
                }
            }
            daftarRuangan.add(ruangan);
        }
        this.daftarRuangan = daftarRuangan;
    }

    public JSONObject toJson() {
        HashMap<String, Object> rumahMap = new HashMap<String, Object>();
        List<JSONObject> daftarRuanganJSON = new ArrayList<JSONObject>();
        for (Ruangan ruangan : daftarRuangan) {
            daftarRuanganJSON.add(ruangan.toJson());
        }

        rumahMap.put("id", id);
        rumahMap.put("nama", nama);
        rumahMap.put("loc", loc.toJson());
        rumahMap.put("daftarRuangan", daftarRuanganJSON);

        JSONObject rumahJSON = new JSONObject(rumahMap);
        return rumahJSON;
    }

    public void inisialisasi() {
        Point p1 = new Point(0, 5);
        Point p2 = new Point(5, 5);
        Point p3 = new Point(5, 1);
        Point p4 = new Point(2, 2);
        Point p5 = new Point(1, 1);

        ObjekNonMakanan o1 = ObjekNonMakanan.returnObject("kasur single 4x1");
        ObjekNonMakanan o2 = ObjekNonMakanan.returnObject("toilet 1x1");
        ObjekNonMakanan o3 = ObjekNonMakanan.returnObject("kompor gas 2x1");
        ObjekNonMakanan o4 = ObjekNonMakanan.returnObject("meja kursi 3x3");
        ObjekNonMakanan o5 = ObjekNonMakanan.returnObject("jam 1x1");

        daftarRuangan.get(0).tambahObjek(o1, p1, "h");
        daftarRuangan.get(0).tambahObjek(o2, p2, "h");
        daftarRuangan.get(0).tambahObjek(o3, p3, "v");
        daftarRuangan.get(0).tambahObjek(o4, p4, "v");
        daftarRuangan.get(0).tambahObjek(o5, p5, "v");
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public Point getLocRumah() {
        return loc;
    }

    public void setLocRumah(Point loc) {
        this.loc = loc;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    public void setDaftarRuangan(List<Ruangan> daftarRuangan) {
        this.daftarRuangan = daftarRuangan;
    }

    // Methods
    public void upgradeRumah(Ruangan ruanganacuan, String arah, String namaruangan) {
        if (arah.equals("atas")) {
            if (ruanganacuan.getAtas() == null) {
                Ruangan newRuangan = new Ruangan(namaruangan, daftarRuangan.size());
                daftarRuangan.add(newRuangan);
                ruanganacuan.setAtas(newRuangan);
                newRuangan.setBawah(ruanganacuan);
            } else {
                System.out.println("Ruangan tidak tersedia karena sudah terisi.");
            }
        } else if (arah.equals("bawah")) {
            if (ruanganacuan.getBawah() == null) {
                Ruangan newRuangan = new Ruangan(namaruangan, daftarRuangan.size());
                daftarRuangan.add(newRuangan);
                ruanganacuan.setBawah(newRuangan);
                newRuangan.setAtas(ruanganacuan);
            } else {
                System.out.println("Ruangan tidak tersedia karena sudah terisi.");
            }
        } else if (arah.equals("kanan")) {
            if (ruanganacuan.getKanan() == null) {
                Ruangan newRuangan = new Ruangan(namaruangan, daftarRuangan.size());
                daftarRuangan.add(newRuangan);
                ruanganacuan.setKanan(newRuangan);
                newRuangan.setKiri(ruanganacuan);
            } else {
                System.out.println("Ruangan tidak tersedia karena sudah terisi.");
            }
        } else if (arah.equals("kiri")) {
            if (ruanganacuan.getKiri() == null) {
                Ruangan newRuangan = new Ruangan(namaruangan, daftarRuangan.size());
                daftarRuangan.add(newRuangan);
                ruanganacuan.setKiri(newRuangan);
                newRuangan.setKanan(ruanganacuan);
            } else {
                System.out.println("Ruangan tidak tersedia karena sudah terisi.");
            }
        }
    }

}
