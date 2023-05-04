package com.simplicity.Objek;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class ObjekPekerjaan extends Objek {
    double gaji;

    public ObjekPekerjaan(String nama_pekerjaan, double gaji) {
        super(nama_pekerjaan);
        this.gaji = gaji;
    }

    public ObjekPekerjaan(JSONObject jsonObject) {
        super(jsonObject.get("nama").toString());
        gaji = Double.parseDouble(jsonObject.get("gaji").toString());
    }

    public JSONObject toJson() {
        HashMap<String, Object> objekPekerjaanMap = new HashMap<String, Object>();

        objekPekerjaanMap.put("nama", getNamaObjek());
        objekPekerjaanMap.put("gaji", gaji);

        JSONObject objekPekerjaanJSON = new JSONObject(objekPekerjaanMap);
        return objekPekerjaanJSON;
    }

    public double getGaji() {
        return gaji;
    }

    // tidak perlu setter karena pekerjaan hanya diinisialisasi saat game dimulai
}
