package com.simplicity.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.simplicity.Rumah;
import com.simplicity.Sim;

public class ActionHandler implements ActionListener {

    GameManager gm;

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
                gm.routing.showScreen(3); 
                break;
            case "aksi" : 
                gm.ui.messagText.setText("HALOO JOS");
                break;
        }
    }
    
}
