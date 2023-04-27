package com.simplicity.Util;

public class Routing {
    GameManager gm;

    public Routing(GameManager gm) {
        this.gm = gm;
    }

    public void showScreen(int screenIndex) {
        for (int i = 0; i < gm.ui.bgPanel.length; i++) {
            if (i == screenIndex) {
                gm.ui.bgPanel[i].setVisible(true);
                if (screenIndex > 1) {
                    gm.ui.attributePanel.setVisible(true);
                    gm.ui.textPanel.setVisible(true);
                } else {
                    gm.ui.attributePanel.setVisible(false);
                    gm.ui.textPanel.setVisible(false);
                }
            } else {
                if (gm.ui.bgPanel[i] != null) {
                    gm.ui.bgPanel[i].setVisible(false);
                }
            }
        }

    }
}
