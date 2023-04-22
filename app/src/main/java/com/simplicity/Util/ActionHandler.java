package com.simplicity.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.simplicity.Sim;

public class ActionHandler implements ActionListener {

    GameManager gm;

    public ActionHandler(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            // Main menu
            case "start" : 
                gm.routing.showScreen(1); 
                break;
            case "new-sim" :
                String name = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "What is your sim name?", "Input Full Name", JOptionPane.PLAIN_MESSAGE);
                if (name!=null && name.replaceAll(" ", "").length() > 0) {
                    gm.world.addSim(name);
                    // set current sim terbaru
                    gm.setCurrentSim(gm.world.getDaftarSim().get(gm.world.getDaftarSim().size()-1));
                    gm.routing.showScreen(2);
                    gm.ui.nameText.setText(gm.getCurrentSim().getNamaLengkap());
                } else if (name!=null) {
                    JOptionPane.showMessageDialog(null,  "Name Required!");
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
                    gm.routing.showScreen(2);
                    gm.ui.nameText.setText(gm.getCurrentSim().getNamaLengkap());
                } 
                break;
            case "exit" :
                int result = JOptionPane.showConfirmDialog(gm.ui.bgPanel[1],
                "Do you want to Exit ?", "Exit Confirmation : ",
                JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } 
                break;

            
            case "ruangan" : 
                gm.routing.showScreen(3); 
                break;
            case "aksi" : 
                gm.ui.messagText.setText("HALOO JOS");
                break;
        }
    }
    
}
