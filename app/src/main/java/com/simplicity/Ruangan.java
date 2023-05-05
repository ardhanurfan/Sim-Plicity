package com.simplicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.simplicity.Objek.ObjekNonMakanan;

public class Ruangan {
    private int id;
    private String nama;
    private List<ObjekNonMakanan> daftarObjek;
    private String[][] ruang;
    private Ruangan atas = null;
    private Ruangan bawah = null;
    private Ruangan kiri = null;
    private Ruangan kanan = null;

    public Ruangan(String nama, int id) {
        this.nama = nama;
        this.daftarObjek = new ArrayList<ObjekNonMakanan>();
        this.ruang = new String[6][6];
        this.id = id;
    }

    public Ruangan(JSONObject jsonObject) {
        id = Integer.parseInt(jsonObject.get("id").toString());
        nama = jsonObject.get("nama").toString();

        JSONArray jsonArrayObjekNonMakanan = (JSONArray) jsonObject.get("daftarObjek");
        List<ObjekNonMakanan> daftarObjek = new ArrayList<ObjekNonMakanan>();
        for (Object object : jsonArrayObjekNonMakanan) {
            daftarObjek.add(new ObjekNonMakanan((JSONObject) object));
        }
        this.daftarObjek = daftarObjek;
        this.ruang = new String[6][6];
        JSONArray jsonArrayRuang = (JSONArray) jsonObject.get("ruang");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if ((((JSONArray) jsonArrayRuang.get(i)).get(j)) != null) {
                    ruang[i][j] = (((JSONArray) jsonArrayRuang.get(i)).get(j)).toString();
                }
            }
        }
    }

    public JSONObject toJson() {
        HashMap<String, Object> ruanganMap = new HashMap<String, Object>();
        List<JSONObject> daftarObjekJSON = new ArrayList<JSONObject>();
        for (ObjekNonMakanan objek : daftarObjek) {
            daftarObjekJSON.add(objek.toJson());
        }

        List<List<String>> ruangList = new ArrayList<List<String>>();
        for (String[] r1 : ruang) {
            ruangList.add(Arrays.asList(r1));
        }

        ruanganMap.put("id", id);
        ruanganMap.put("nama", nama);
        ruanganMap.put("daftarObjek", daftarObjekJSON);
        ruanganMap.put("ruang", ruangList);
        ruanganMap.put("atas", atas == null ? null : atas.getId());
        ruanganMap.put("bawah", bawah == null ? null : bawah.getId());
        ruanganMap.put("kiri", kiri == null ? null : kiri.getId());
        ruanganMap.put("kanan", kanan == null ? null : kanan.getId());

        JSONObject ruangJSON = new JSONObject(ruanganMap);
        return ruangJSON;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Ruangan getAtas() {
        return atas;
    }

    public void setAtas(Ruangan ruangan) {
        this.atas = ruangan;
    }

    public Ruangan getBawah() {
        return bawah;
    }

    public void setBawah(Ruangan ruangan) {
        this.bawah = ruangan;
    }

    public Ruangan getKanan() {
        return kanan;
    }

    public void setKanan(Ruangan ruangan) {
        this.kanan = ruangan;
    }

    public Ruangan getKiri() {
        return kiri;
    }

    public void setKiri(Ruangan ruangan) {
        this.kiri = ruangan;
    }

    public String getRuangan(Point point) {
        return ruang[point.getX()][point.getY()];
    }

    public ObjekNonMakanan getObjek(int index) {
        return daftarObjek.get(index);
    }

    public ObjekNonMakanan getObjek(String nama) {
        ObjekNonMakanan objek = null;
        for (ObjekNonMakanan o : daftarObjek) {
            if (o.getNamaObjek().equals(nama)) {
                objek = o;
            }
        }
        return objek;
    }

    public List<ObjekNonMakanan> getDaftarObjek() {
        return daftarObjek;
    }

    public List<String> getDaftarObjekString() {
        List<String> list = new ArrayList<>();
        for (ObjekNonMakanan o : daftarObjek) {
            list.add(o.getNamaObjek() + " (" + o.getTitik().getX() + "," + o.getTitik().getY() + ") " + o.getPosisi());
        }
        return list;
    }

    // Return true kalau ga nabrak
    public boolean nabrakGa(ObjekNonMakanan objek, Point point, String posisi) {
        int i, j, jmax, imax;
        boolean nabrak = false;

        // posisi vertikal
        if (posisi.equals("v")) {
            imax = objek.getLebar();
            jmax = objek.getPanjang();
        }
        // posisi horizontal
        else {
            jmax = objek.getLebar();
            imax = objek.getPanjang();
        }
        if (point.getX() + imax > 6 || point.getY() + jmax > 6) {
            nabrak = true;
            return !nabrak;
        }
        for (i = 0; i < imax; i++) {
            for (j = 0; j < jmax; j++) {
                if (ruang[point.getX() + i][point.getY() + j] != null) {
                    nabrak = true;
                }
            }
        }
        return !nabrak;
    }

    public void tambahObjek(ObjekNonMakanan objek, Point point, String posisi) {
        objek.setPosisi(posisi);
        objek.setTitik(point);
        daftarObjek.add(objek);
        int i, j, jmax, imax;

        // posisi vertikal
        if (posisi.equals("v")) {
            imax = objek.getLebar();
            jmax = objek.getPanjang();
        }
        // posisi horizontal
        else {
            jmax = objek.getLebar();
            imax = objek.getPanjang();
        }
        for (i = 0; i < imax; i++) {
            for (j = 0; j < jmax; j++) {
                ruang[point.getX() + i][point.getY() + j] = objek.getNamaObjek();
            }
        }
    }

    public void hapusObjek(ObjekNonMakanan objek) {
        int i, j, jmax, imax;

        // posisi vertikal
        if (objek.getPosisi().equals("v")) {
            imax = objek.getLebar();
            jmax = objek.getPanjang();
        }
        // posisi horizontal
        else {
            jmax = objek.getLebar();
            imax = objek.getPanjang();
        }
        for (i = 0; i < imax; i++) {
            for (j = 0; j < jmax; j++) {
                ruang[objek.getTitik().getX() + i][objek.getTitik().getY() + j] = null;
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
}
