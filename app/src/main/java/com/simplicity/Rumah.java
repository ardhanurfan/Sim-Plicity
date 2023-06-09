package com.simplicity;

import com.simplicity.Objek.ObjekNonMakanan;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

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

    public Rumah(JSONObject object2) {
        id = Integer.parseInt(object2.get("id").toString());
        nama = object2.get("nama").toString();
        loc = new Point((JSONObject) object2.get("loc"));

        JSONArray jsonArrayRumah = (JSONArray) object2.get("daftarRuangan");
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

        ObjekNonMakanan o1 = new ObjekNonMakanan("Kasur Single Size 4x1");
        ObjekNonMakanan o2 = new ObjekNonMakanan("Toilet 1x1");
        ObjekNonMakanan o3 = new ObjekNonMakanan("Kompor Gas 2x1");
        ObjekNonMakanan o4 = new ObjekNonMakanan("Meja dan Kursi 3x3");
        ObjekNonMakanan o5 = new ObjekNonMakanan("Jam 1x1");

        daftarRuangan.get(0).tambahObjek(o1, p1, "h");
        daftarRuangan.get(0).tambahObjek(o2, p2, "h");
        daftarRuangan.get(0).tambahObjek(o3, p3, "v");
        daftarRuangan.get(0).tambahObjek(o4, p4, "v");
        daftarRuangan.get(0).tambahObjek(o5, p5, "v");
    }

    // Getters
    public int getId() {
        return id;
    }

    public Point getLocRumah() {
        return loc;
    }

    public String getNama() {
        return nama;
    }

    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    // Methods
    public Ruangan upgradeRumah(Ruangan ruanganacuan, String arah, String namaruangan) {
        Ruangan newRuangan = null;
        if (arah.equals("Atas")) {
            if (ruanganacuan.getAtas() == null) {
                newRuangan = new Ruangan(namaruangan, daftarRuangan.size());
                ruanganacuan.setAtas(newRuangan);
                newRuangan.setBawah(ruanganacuan);
            } else {
                JOptionPane.showMessageDialog(null, "Ruangan tidak tersedia karena sudah terisi.");
            }
        } else if (arah.equals("Bawah")) {
            if (ruanganacuan.getBawah() == null) {
                newRuangan = new Ruangan(namaruangan, daftarRuangan.size());
                ruanganacuan.setBawah(newRuangan);
                newRuangan.setAtas(ruanganacuan);
            } else {
                JOptionPane.showMessageDialog(null, "Ruangan tidak tersedia karena sudah terisi.");
            }
        } else if (arah.equals("Kanan")) {
            if (ruanganacuan.getKanan() == null) {
                newRuangan = new Ruangan(namaruangan, daftarRuangan.size());
                ruanganacuan.setKanan(newRuangan);
                newRuangan.setKiri(ruanganacuan);
            } else {
                JOptionPane.showMessageDialog(null, "Ruangan tidak tersedia karena sudah terisi.");
            }
        } else if (arah.equals("Kiri")) {
            if (ruanganacuan.getKiri() == null) {
                newRuangan = new Ruangan(namaruangan, daftarRuangan.size());
                ruanganacuan.setKiri(newRuangan);
                newRuangan.setKanan(ruanganacuan);
            } else {
                JOptionPane.showMessageDialog(null, "Ruangan tidak tersedia karena sudah terisi.");
            }
        }
        return newRuangan;
    }

}
