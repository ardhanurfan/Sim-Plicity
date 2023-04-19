package com.simplicity;

import java.util.Collection;
// import java.util.ArrayList;
// import java.util.List;
import java.util.Map;
// import java.util.HashMap;

public class World { 
    // private static List<Rumah> listrumah = new ArrayList<Rumah>();;
    // private static List<Sim> listsim = new ArrayList<Sim>();
    // private static List<Map<Sim, Rumah>> rumahsim;
    private static Map<Sim, Rumah> kepemilikan;
    private static int time;
    private final int panjangMap = 64;
    private final int lebarMap = 64;
   
    public World(){
        time = 0;
    }
    
    public int getTime(){
        return time;
    }
    
    public void setTime(int aksi){
        if(time + aksi < 720){
            time += aksi;
        } else {
            time = (time+aksi)%720; 
        }
    }
    
    // public List<Rumah> getDaftarRumah() {
    //     return listrumah;
    // }

    public boolean checkLahan(Point lokasi){
        boolean check = false;
        for(Rumah home : kepemilikan.values()){
            if(home.getLocRumah().getX()==lokasi.getX() && home.getLocRumah().getY()==lokasi.getY()){
                check = true;
            }
        }
        return check;
    }

    public boolean validPos(Point lokasi){
        boolean check = false;
        if(lokasi.getX()<=panjangMap && lokasi.getY()<=lebarMap){
            check = true;
        }
        return check;
    }

    // public void addRumah(Point lokasi, String nama){
    //     if(!checkLahan(lokasi) && validPos(lokasi)){
    //         Rumah rumah = new Rumah(lokasi, nama);
    //         listrumah.add(rumah);
    //     } else if(checkLahan(lokasi)){
    //         System.out.println("Lahan sudah ditempati");
    //     } else if(!validPos(lokasi)){
    //         System.out.println("Tidak bisa membangun rumah di luar map");
    //     }
    // }

    // public List<Sim> getDaftarSim() {
    //     return listsim;
    // }

    // public void addSim(Sim sim){
    //     Sim newsim = new Sim(sim);
    //     listsim.add(newsim);
    // }

    public Collection<Rumah> getDaftarRumah() {
        return kepemilikan.values();
    }
    public Collection<Sim> getDaftarSim() {
        return kepemilikan.keySet();
    }
    
    public boolean checkSim(Sim sim){
        boolean check = false;
        for(Sim sims : kepemilikan.keySet()){
            if(sim.getNamaLengkap().equals(sims.getNamaLengkap())){
                check = true;
            }
        }
        return check;
    }

    public void addSimAndRumah(Sim sim, Point lokasi, String nama){
        if(!checkSim(sim)){
            if(!checkLahan(lokasi) && validPos(lokasi)){
                Rumah rumah = new Rumah(lokasi, nama);
                Sim newsim = new Sim(sim);
                // kepemilikan = new HashMap<Sim, Rumah>();
                kepemilikan.put(newsim, rumah);
                // rumahsim.add(kepemilikan);
            } else if(checkLahan(lokasi)){
                System.out.println("Lahan sudah ditempati");
            } else if(!validPos(lokasi)){
                System.out.println("Tidak bisa membangun rumah di luar map");
            }
        } else{
            System.out.println("Nama sudah digunakan");
        }
    }
}
