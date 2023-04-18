package com.simplicity.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    GameManager gm;

    public ActionHandler(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "start" : gm.routing.showScreen(1); break;
            case "halo" : gm.routing.showScreen(0); break;
            case "ruangan" : gm.routing.showScreen(2); break;
            case "aksi" : gm.ui.messagText.setText("HALOO JOS");; break;
        }
    }
    
}
