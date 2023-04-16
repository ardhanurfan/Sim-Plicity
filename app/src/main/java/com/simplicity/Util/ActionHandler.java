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
            case "halo" : System.out.println("ppp"); break;
        }
    }
    
}
