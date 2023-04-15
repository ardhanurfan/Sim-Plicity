package com.simplicity;

import java .util.List;
    
import com.simplicity.Rumah;

public class World { 
    private static List<Rumah> listrumah; 
    private static int time;
    private final int panjangMap = 64;
    private final int lebarMap = 64;
   
    public World(List<Rumah> listrumah){
        this.listrumah = listrumah;
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
    
}
