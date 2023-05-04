package com.simplicity.Objek;

import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.simplicity.Point;

public class ObjekNonMakanan extends Objek {
    int panjang;
    int lebar;
    int harga;
    String[] aksi;
    Point titik = null;
    String posisi = null;

    public ObjekNonMakanan(String nama_barang, int panjang, int lebar, int harga, String[] aksi) {
        super(nama_barang);
        this.panjang = panjang;
        this.lebar = lebar;
        this.harga = harga;
        this.aksi = aksi;
    }

    public ObjekNonMakanan(String nama) {
        super(nama);
        if (nama.equals("Kasur Single 4x1")) {
            panjang = 4;
            lebar = 1;
            harga = 50;
            aksi = new String[] { "Tidur" };
        } else if (nama.equals("Kasur Queen Size 4x2")) {
            panjang = 4;
            lebar = 2;
            harga = 100;
            aksi = new String[] { "Tidur" };
        } else if (nama.equals("Kasur King Size 5x2")) {
            panjang = 5;
            lebar = 2;
            harga = 150;
            aksi = new String[] { "Tidur" };
        } else if (nama.equals("Toilet 1x1")) {
            panjang = 1;
            lebar = 1;
            harga = 50;
            aksi = new String[] { "Buang Air" };
        } else if (nama.equals("Kompor Gas 2x1")) {
            panjang = 2;
            lebar = 1;
            harga = 100;
            aksi = new String[] { "Memasak" };
        } else if (nama.equals("Kompor Listrik 1x1")) {
            panjang = 1;
            lebar = 1;
            harga = 200;
            aksi = new String[] { "Memasak" };
        } else if (nama.equals("Meja dan Kursi 3x3")) {
            panjang = 3;
            lebar = 3;
            harga = 50;
            aksi = new String[] { "Makan" };
        } else if (nama.equals("Jam 1x1")) {
            panjang = 1;
            lebar = 1;
            harga = 10;
            aksi = new String[] { "Melihat Waktu" };
        } else if (nama.equals("Sofa 2x1")) {
            panjang = 2;
            lebar = 1;
            harga = 30;
            aksi = new String[] { "Duduk", "Ngudud" };
        } else if (nama.equals("Tv 1x1")) {
            panjang = 1;
            lebar = 1;
            harga = 20;
            aksi = new String[] { "Menonton", "Main PS" };
        } else if (nama.equals("Matras 2x1")) {
            panjang = 2;
            lebar = 1;
            harga = 8;
            aksi = new String[] { "Olahraga", "Meditasi" };
        } else if (nama.equals("Laptop 1x1")) {
            panjang = 1;
            lebar = 1;
            harga = 50;
            aksi = new String[] { "Main game", "Ngoding" };
        }
    }

    public ObjekNonMakanan(JSONObject jsonObject) {
        super(jsonObject.get("nama").toString());
        panjang = Integer.parseInt(jsonObject.get("panjang").toString());
        lebar = Integer.parseInt(jsonObject.get("lebar").toString());
        harga = Integer.parseInt(jsonObject.get("harga").toString());
        // membuat array list of string
        ArrayList<String> aksiTemp = new ArrayList<String>();
        for (Object object : (JSONArray) jsonObject.get("aksi")) {
            aksiTemp.add(object.toString());
        }

        // memasukkan elemen pada aksiTemp ke this.aksi
        aksi = new String[aksiTemp.size()];
        for (int i = 0; i < aksiTemp.size(); i++) {
            aksi[i] = aksiTemp.get(i);
        }
        titik = new Point((JSONObject) jsonObject.get("titik"));
        posisi = jsonObject.get("posisi").toString();
    }

    public JSONObject toJson() {
        HashMap<String, Object> ObjekNonMakananMap = new HashMap<String, Object>();

        ObjekNonMakananMap.put("nama", getNamaObjek());
        ObjekNonMakananMap.put("panjang", panjang);
        ObjekNonMakananMap.put("lebar", lebar);
        ObjekNonMakananMap.put("harga", harga);
        List<String> aksi = new ArrayList<String>();
        for (String r1 : this.aksi) {
            aksi.add(r1);
        }
        ObjekNonMakananMap.put("aksi", aksi);
        ObjekNonMakananMap.put("posisi", posisi);
        ObjekNonMakananMap.put("titik", titik.toJson());

        JSONObject objekNonMakananJSON = new JSONObject(ObjekNonMakananMap);
        return objekNonMakananJSON;
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

    public String[] getAksi() {
        return aksi;
    }

    public Point getTitik() {
        return titik;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setTitik(Point point) {
        titik = point;
    }

    public void setPosisi(String pos) {
        posisi = pos;
    }

    // tidak ada setter karena barang hanya diinisialisasi sebelum game dimulai
}
