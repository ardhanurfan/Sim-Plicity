package com.simplicity;

import java.util.ArrayList;
import java.util.List;
    
import com.simplicity.Rumah;
import com.simplicity.Point;

public class World { 
    private static List<Rumah> listrumah; 
    private static int time;
    private final int panjangMap = 64;
    private final int lebarMap = 64;
   
    public World(List<Rumah> listrumah){
        World.listrumah = new ArrayList<Rumah>();
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
    
    public List<Rumah> getDaftarRumah() {
        return listrumah;
    }

    public boolean checklahan(Point lokasi){
        boolean check = false;
        for(Rumah home : listrumah){
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

    public void addRumah(Point lokasi, String nama){
        if(!checklahan(lokasi) && validPos(lokasi)){
            Rumah rumah = new Rumah(lokasi, nama);
            listrumah.add(rumah);
        } else if(checklahan(lokasi)){
            System.out.println("Lahan sudah ditempati");
        } else if(!validPos(lokasi)){
            System.out.println("Tidak bisa membangun rumah di luar map");
        }
    }
}
