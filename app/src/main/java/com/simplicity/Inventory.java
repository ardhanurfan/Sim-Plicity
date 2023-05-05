package com.simplicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.simplicity.Objek.Objek;
import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekNonMakanan;
import com.simplicity.Objek.ThreeElementArray;

public class Inventory {
    private ArrayList<InventoryItem> data = new ArrayList<InventoryItem>();

    public Inventory() {
    }

    public Inventory(JSONObject jsonObject) {
        JSONArray jsonArrayData = (JSONArray) jsonObject.get("inventoriData");
        for (Object object : jsonArrayData) {
            data.add(new InventoryItem((JSONObject) object));
        }
    }

    public JSONObject toJson() {
        HashMap<String, Object> inventoriDataMap = new HashMap<String, Object>();
        List<JSONObject> listData = new ArrayList<JSONObject>();
        for (InventoryItem item : data) {
            listData.add(item.toJson());
        }

        inventoriDataMap.put("inventoriData", listData);

        JSONObject dataJSON = new JSONObject(inventoriDataMap);
        return dataJSON;
    }

    public ArrayList<InventoryItem> getData() {
        return data;
    }

    public class InventoryItem implements InterfaceInventory {

        private ThreeElementArray<String, String, Integer> item;

        public InventoryItem(String namaBarang, String kategori, int jumlah) {
            this.item = new ThreeElementArray<>(namaBarang, kategori, jumlah);
        }

        public InventoryItem(JSONObject jsonObject) {
            String namaBarang = jsonObject.get("namaBarang").toString();
            String kategori = jsonObject.get("kategori").toString();
            int jumlah = Integer.parseInt(jsonObject.get("jumlah").toString());
            this.item = new ThreeElementArray<String, String, Integer>(namaBarang, kategori, jumlah);
        }

        public JSONObject toJson() {
            HashMap<String, Object> inventoryItemMap = new HashMap<String, Object>();

            inventoryItemMap.put("namaBarang", getNamaBarang());
            inventoryItemMap.put("kategori", getKategori());
            inventoryItemMap.put("jumlah", getJumlah());

            JSONObject inventoryItemJSON = new JSONObject(inventoryItemMap);
            return inventoryItemJSON;
        }

        public String getNamaBarang() {
            return item.getFirst();
        }

        public void setNamaBarang(String namaBarang) {
            item.setFirst(namaBarang);
        }

        public String getKategori() {
            return item.getSecond();
        }

        public void setKategori(String kategori) {
            item.setSecond(kategori);
        }

        public int getJumlah() {
            return item.getThird();
        }

        public void setJumlah(int jumlah) {
            item.setThird(jumlah);
        }
    }

    public boolean isContains(Objek objek) {
        boolean isAda = false;
        int i = 0;
        while (isAda && i < data.size()) {
            if (data.get(i).getNamaBarang().equals(objek.getNamaObjek())) {
                isAda = true;
            }
            i++;
        }
        return isAda;
    }

    // Menambah item objek non makanan ke inventory
    public void addItemPeralatan(ObjekNonMakanan objekNonMakanan, int banyak) {
        boolean isAda = false; // cek barang udah ada di inventory ato belum. kalo ada tinggal tambahin jumlah
        for (InventoryItem item : data) {
            if (item.getNamaBarang().equals(objekNonMakanan.getNamaObjek())) {
                item.setJumlah(item.getJumlah() + banyak);
                isAda = true;
            }
        }
        if (!isAda) { // kalo barangnya blum ada di inventory
            data.add(new InventoryItem(objekNonMakanan.getNamaObjek(), "Peralatan", banyak));
        }
    }

    // menambah item objek bahan makanan ke inventory
    public void addItemBahanMakanan(ObjekBahanMakanan objekBahanMakanan, int banyak) {
        boolean isAda = false; // cek barang udah ada di inventory ato belum. kalo ada tinggal tambahin jumlah
        for (InventoryItem item : data) {
            if (item.getNamaBarang().equals(objekBahanMakanan.getNamaObjek())) {
                item.setJumlah(item.getJumlah() + banyak);
                isAda = true;
            }
        }
        if (!isAda) { // kalo barangnya blum ada di inventory
            data.add(new InventoryItem(objekBahanMakanan.getNamaObjek(), "Bahan Makanan", banyak));
            ;
        }
    }

    // menambah objek makanan jadi ke inventory
    public void addItemMakanan(ObjekMakanan objekMakanan, int banyak) {
        boolean isAda = false; // cek barang udah ada di inventory ato belum. kalo ada tinggal tambahin jumlah
        for (InventoryItem item : data) {
            if (item.getNamaBarang().equals(objekMakanan.getNamaObjek())) {
                item.setJumlah(item.getJumlah() + banyak);
                isAda = true;
            }
        }
        if (!isAda) { // kalo barangnya blum ada di inventory
            data.add(new InventoryItem(objekMakanan.getNamaObjek(), "Makanan", banyak));
            ;
        }
    }

    // Mengurangi jumlah objek dalam inventory
    public void kurangiItem(String namaItem, int banyak) {
        for (InventoryItem item : data) {
            if (item.getNamaBarang().equals(namaItem)) {
                if (item.getJumlah() - banyak <= 0) { // menghapus item
                    data.removeIf(element -> element.equals(item));
                    break;
                } else {
                    item.setJumlah(item.getJumlah() - banyak);
                }
            }
        }
    }

    public String nameConverter(String nama) {
        String namaConv = null;
        if (nama.equals("Kasur Single Size")) {
            namaConv = "Kasur Single Size 4x1";
        } else if (nama.equals("Kasur Queen Size")) {
            namaConv = "Kasur Queen Size 4x2";
        } else if (nama.equals("Kasur King Size")) {
            namaConv = "Kasur King Size 5x2";
        } else if (nama.equals("Jam")) {
            namaConv = "Jam 1x1";
        } else if (nama.equals("Meja dan Kursi")) {
            namaConv = "Meja dan Kursi 3x3";
        } else if (nama.equals("Toilet")) {
            namaConv = "Toilet 1x1";
        } else if (nama.equals("Kompor Gas")) {
            namaConv = "Kompor Gas 2x1";
        } else if (nama.equals("Kompor Listrik")) {
            namaConv = "Kompor Listrik 1x1";
        } else if (nama.equals("Laptop")) {
            namaConv = "Laptop 1x1";
        } else if (nama.equals("Tv")) {
            namaConv = "Tv 1x1";
        } else if (nama.equals("Matras")) {
            namaConv = "Matras 2x1";
        } else if (nama.equals("Sofa")) {
            namaConv = "Sofa 2x1";
        }
        return namaConv;
    }

    public String nameConverterReverse(String nama) {
        String namaConv = null;
        if (nama.equals("Kasur Single Size 4x1")) {
            namaConv = "Kasur Single Size";
        } else if (nama.equals("Kasur Queen Size 4x2")) {
            namaConv = "Kasur Queen Size";
        } else if (nama.equals("Kasur King Size 5x2")) {
            namaConv = "Kasur King Size";
        } else if (nama.equals("Jam 1x1")) {
            namaConv = "Jam";
        } else if (nama.equals("Meja dan Kursi 3x3")) {
            namaConv = "Meja dan Kursi";
        } else if (nama.equals("Toilet 1x1")) {
            namaConv = "Toilet";
        } else if (nama.equals("Kompor Gas 2x1")) {
            namaConv = "Kompor Gas";
        } else if (nama.equals("Kompor Listrik 1x1")) {
            namaConv = "Kompor Listrik";
        } else if (nama.equals("Laptop 1x1")) {
            namaConv = "Laptop";
        } else if (nama.equals("Tv 1x1")) {
            namaConv = "Tv";
        } else if (nama.equals("Matras 2x1")) {
            namaConv = "Matras";
        } else if (nama.equals("Sofa 2x1")) {
            namaConv = "Sofa";
        }
        return namaConv;
    }

    public List<String> getIventoryString() {
        List<String> objekNonMakananList = Arrays.asList("Kasur Single Size 4x1",
                "Kasur Queen Size 4x2",
                "Kasur King Size 5x2", "Jam 1x1", "Meja dan Kursi 3x3", "Toilet 1x1", "Kompor Gas 2x1",
                "Kompor Listrik 1x1", "Laptop 1x1", "Tv 1x1", "Matras 2x1", "Sofa 2x1");
        List<String> inventoryString = new ArrayList<String>();
        for (InventoryItem item : data) {
            // if (item.getKategori().equals("Peralatan")) {
            // inventoryString.add(nameConverter(item.getNamaBarang()));
            // }
            if (objekNonMakananList.contains(nameConverter(item.getNamaBarang()))) {
                inventoryString.add(nameConverter(item.getNamaBarang()));
            } else {
                System.out.println(item.getNamaBarang());

            }
        }
        return inventoryString;
    }

}
