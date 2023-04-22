package com.simplicity.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;
import javax.swing.JComboBox;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.simplicity.Rumah;
import com.simplicity.Sim;
import com.simplicity.Ruangan;
import com.simplicity.Objek.ObjekNonMakanan;
import com.simplicity.Point;

public class ActionHandler implements ActionListener {

    GameManager gm;
    Ruangan currRuangan=null;

    public ActionHandler(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int indexObj = -1;

        if (command.contains("%")) {
            String[] listCmd = command.split("%");
            command = listCmd[1];
            indexObj = Integer.parseInt(listCmd[0]);
        }

        switch (command) {
            // Main menu
            case "start" : 
                gm.routing.showScreen(1); 
                break;
            case "new-sim" :
                String name = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "What is your sim name?", "Input Full Name", JOptionPane.PLAIN_MESSAGE);
                if (name!=null && name.replaceAll(" ", "").length() > 0) {
                    if (gm.world.addSim(name)) {
                        // set current sim terbaru
                        gm.setCurrentSim(gm.world.getDaftarSim().get(gm.world.getDaftarSim().size()-1));
                        for (int i = 0; i < gm.world.getDaftarRumah().size(); i++) {
                            gm.ui.createObjek(2, gm.world.getDaftarRumah().get(i).getLocRumah().getX()*10+30, gm.world.getDaftarRumah().get(i).getLocRumah().getY()*10+30, 20, 20, "rumah.png", new String[]{"View Home%" + gm.world.getDaftarRumah().get(i).getNama()}, i);
                        }
                        gm.routing.showScreen(2);
                        gm.ui.messagText.setText("Hello Welcome, " + gm.getCurrentSim().getNamaLengkap() + "! Select your house location!");
                        gm.ui.nameText.setText(gm.getCurrentSim().getNamaLengkap());
                    } else {
                        JOptionPane.showMessageDialog(null,  "Nama sudah digunakan!");
                    }
                } else if (name!=null) {
                    JOptionPane.showMessageDialog(null,  "Nama tidak boleh kosong");
                }
                break;
            case "choose-sim" :
                List<String> options = gm.world.getDaftarSim().stream()
                                   .map(Sim::getNamaLengkap)
                                   .collect(Collectors.toList());
                Object selected = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Your Sim", "Choose Sim", JOptionPane.PLAIN_MESSAGE, null, options.toArray(new String[gm.world.getDaftarSim().size()]), options.get(0));
                if (selected!=null) {
                    int index = options.indexOf(selected);
                    // set current sim by index choosen
                    gm.setCurrentSim(gm.world.getDaftarSim().get(index));
                    for (int i = 0; i < gm.world.getDaftarRumah().size(); i++) {
                        if (gm.getCurrentSim().getRumah() != null && gm.getCurrentSim().getRumah().getNama().equals(gm.world.getDaftarRumah().get(i).getNama())) {
                            gm.ui.createObjek(2, gm.getCurrentSim().getRumah().getLocRumah().getX()*10+30, gm.getCurrentSim().getRumah().getLocRumah().getY()*10+30, 20, 20, "rumahku.png", new String[]{"View Home%" + gm.world.getDaftarRumah().get(i).getNama()}, i);
                        } else {
                            gm.ui.createObjek(2, gm.world.getDaftarRumah().get(i).getLocRumah().getX()*10+30, gm.world.getDaftarRumah().get(i).getLocRumah().getY()*10+30, 20, 20, "rumah.png", new String[]{"View Home%" + gm.world.getDaftarRumah().get(i).getNama()}, i);
                        }
                    }
                    gm.routing.showScreen(2);
                    gm.ui.messagText.setText("Hello Welcome Back, " + gm.getCurrentSim().getNamaLengkap());
                    gm.ui.nameText.setText(gm.getCurrentSim().getNamaLengkap());
                } 
                break;
            case "exit" :
                int result = JOptionPane.showConfirmDialog(gm.ui.bgPanel[1],
                "Do you want to Exit ?", "Exit Confirmation",
                JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } 
                break;

            case "Back to World" :
                gm.routing.showScreen(2);
                gm.ui.messagText.setText("Silakan berkunjung");
                break;
            case "ruangan" : 
                gm.routing.showScreen(3); 
                break;
            case "View Home":
                Rumah currRumah = gm.world.getDaftarRumah().get(indexObj);
                if (currRumah.getNama().equals(gm.getCurrentSim().getRumah().getNama())) {
                    gm.ui.messagText.setText("Berada di rumah milik Anda, " + currRumah.getNama());
                } else {
                    gm.ui.messagText.setText("Saat ini Anda berkunjung ke " + currRumah.getNama());
                }
                currRuangan = currRumah.getDaftarRuangan().get(0);
                gm.routing.showScreen(3); 
                gm.ui.generateRoom(currRuangan,3);
                gm.ui.bgPanel[3].add(gm.ui.bgLabel[3]);

                break;
            case "aksi" : 
                gm.ui.messagText.setText("HALOO JOS");
                break;



            case "Edit Room":
                // Opsi tombol edit room
                List<String> option = Arrays.asList("Tambah Barang", "Hapus Barang","Pindah Barang");
                Object selectedPane = JOptionPane.showInputDialog(gm.ui.bgPanel[3], "Your Action", "Edit Room", JOptionPane.PLAIN_MESSAGE, null, option.toArray(), option.get(0));
                int index = option.indexOf(selectedPane);
    
                // Ceritanya ini Inventory
                List<String> inventory = Arrays.asList("kasur single 4x1","kasur queen size 4x2","kasur king size 5x2","jam 1x1","meja kursi 3x3","toilet 1x1","kompor gas 2x1","kompor listrik 1x1");
                
                // Tambah Barang
                if(index ==0){
                    // Opsi inventory
                    Object selectedInventory = JOptionPane.showInputDialog(gm.ui.bgPanel[3], "Inventory", "Edit Room", JOptionPane.PLAIN_MESSAGE, null, inventory.toArray(), inventory.get(0));
                    if (selectedInventory!=null){
                        int indexInventory = inventory.indexOf(selectedInventory);
    
                        // Bikin 3 dropdown buat milih titik dan posisi barang
                        String[] coordOptions = {"0","1","2","3","4","5"};
                        String[] positionOptions = {"v","h"};
                        
                        JComboBox<String> selectionX = new JComboBox<>(coordOptions);
                        JComboBox<String> selectionY = new JComboBox<>(coordOptions);
                        JComboBox<String> selectionPosisi = new JComboBox<>(positionOptions);
    
                        Object[] message = {"Dropdown 1:", selectionX, "Dropdown 2:", selectionY, "Dropdown 3:", selectionPosisi};
                        int optionPosisi1 = JOptionPane.showConfirmDialog(null, message, "Posisi Barang", JOptionPane.OK_CANCEL_OPTION);
    
                        if (optionPosisi1 == JOptionPane.OK_OPTION) {
                            String stringX = (String) selectionX.getSelectedItem();
                            String stringY = (String) selectionY.getSelectedItem();
                            String posisi = (String) selectionPosisi.getSelectedItem();
    
                            // Konversi ke integer
                            int x = Integer.parseInt(stringX);
                            int y = Integer.parseInt(stringY);
    
                            // Bikin point dan objek sesuai pilihan
                            Point point = new Point(x, y);
                            ObjekNonMakanan o = ObjekNonMakanan.returnObject(inventory.get(indexInventory));
    
                            // Ngecek nabrak ato ga
                            if(currRuangan.nabrakGa(o, point, posisi)){
                                currRuangan.tambahObjek(o,point,posisi);
                                gm.ui.messagText.setText("Aman");
                                gm.ui.generateRoom(currRuangan, 3);
                                gm.ui.bgPanel[3].add(gm.ui.bgLabel[3]);
                                gm.ui.bgPanel[3].revalidate();
                                gm.ui.bgPanel[3].repaint();
                            }  
                            else{
                                gm.ui.messagText.setText("Nabrak euyyy");
                            }  
                        } else {
                            gm.ui.messagText.setText("Dialog ditutup.");
                        }        
                    }
                }
                 // Hapus Barang
                else if (index==1){
                    // Opsi barang yang ada di currRuangan
                    List<String> optionObjek = currRuangan.getDaftarObjekString();
                    Object selectedObjek = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Choose Object you want to delete", "Delete Object", JOptionPane.PLAIN_MESSAGE, null, optionObjek.toArray(), optionObjek.get(0));
                    if(selectedObjek!=null){
                        // Hapus barang sesuai pilihan
                        int indexOptionObjek = optionObjek.indexOf(selectedObjek);
                        ObjekNonMakanan deleteObject = currRuangan.getObjek(indexOptionObjek);
                        currRuangan.hapusObjek(deleteObject);
    
                        // Refresh panel
                        gm.ui.bgPanel[3].removeAll();
                        gm.ui.generateRoom(currRuangan, 3);
                        gm.ui.createObjek(3, 650, 600, 40, 40, "back.png", new String[]{"Edit Room"}, -1);
                        gm.ui.createObjek(3, 650, 650, 40, 40, "back.png", new String[]{"Back to Home", "Back to World"}, -1);
                        gm.ui.bgPanel[3].add(gm.ui.bgLabel[3]);
                        gm.ui.bgPanel[3].revalidate();
                        gm.ui.bgPanel[3].repaint();
                        gm.ui.messagText.setText("Objek berhasil dihapus");
                    }
                }
                // Pindah barang
                else{
                    // Opsi barang yang ada di currRuangan
                    List<String> optionObjek2 = currRuangan.getDaftarObjekString();
                    Object selectedObjek = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Choose Object you want to delete", "Delete Object", JOptionPane.PLAIN_MESSAGE, null, optionObjek2.toArray(), optionObjek2.get(0));
                    if(selectedObjek!=null){
                        // Hapus objek sesuai pilihan
                        int indexOptionObjek = optionObjek2.indexOf(selectedObjek);
                        ObjekNonMakanan moveObject = currRuangan.getObjek(indexOptionObjek);
                        currRuangan.hapusObjek(moveObject);
                        // Bikin 3 dropdown buat milih titik dan posisi barang
                        String[] coord = {"0","1","2","3","4","5"};
                        String[] position = {"v","h"};
                        
                        JComboBox<String> selectionX = new JComboBox<>(coord);
                        JComboBox<String> selectionY = new JComboBox<>(coord);
                        JComboBox<String> selectionPosisi = new JComboBox<>(position);
    
                        Object[] message = {"Dropdown 1:", selectionX, "Dropdown 2:", selectionY, "Dropdown 3:", selectionPosisi};
                        int optionPosisi2 = JOptionPane.showConfirmDialog(null, message, "Posisi Barang", JOptionPane.OK_CANCEL_OPTION);
    
                        if (optionPosisi2 == JOptionPane.OK_OPTION) {
                            String stringX = (String) selectionX.getSelectedItem();
                            String stringY = (String) selectionY.getSelectedItem();
                            String posisi = (String) selectionPosisi.getSelectedItem();
    
                            // Konversi ke Integer
                            int x = Integer.parseInt(stringX);
                            int y = Integer.parseInt(stringY);
                            
                            // Bikin point dan ngecek nabrak ato ga
                            Point point = new Point(x, y);
                            if(currRuangan.nabrakGa(moveObject, point, posisi)){
                                currRuangan.tambahObjek(moveObject,point,posisi);
                                gm.ui.messagText.setText("Aman");
                            }  
                            else{
                                currRuangan.tambahObjek(moveObject,moveObject.getTitik(),moveObject.getPosisi());
                                gm.ui.messagText.setText("Nabrak euyyy");
                            }  
                            // Refresh panel
                            gm.ui.bgPanel[3].removeAll();
                            gm.ui.generateRoom(currRuangan, 3);
                            gm.ui.createObjek(3, 650, 600, 40, 40, "back.png", new String[]{"Edit Room"}, -1);
                            gm.ui.createObjek(3, 650, 650, 40, 40, "back.png", new String[]{"Back to Home", "Back to World"}, -1);
                            gm.ui.bgPanel[3].add(gm.ui.bgLabel[3]);
                            gm.ui.bgPanel[3].revalidate();
                            gm.ui.bgPanel[3].repaint();
                        } else {
                            gm.ui.messagText.setText("Dialog ditutup");
                        }        
                    }
    
                }
                break;
        }
    }
    
}
