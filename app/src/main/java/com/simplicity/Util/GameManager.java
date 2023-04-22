package com.simplicity.Util;

import com.simplicity.Sim;
import com.simplicity.World;

public class GameManager {
	World world = new World();
	private Sim currentSim;
	ActionHandler actionHandler = new ActionHandler(this);
	UI ui = new UI(this);
	Routing routing = new Routing(this);

	public GameManager() {
		routing.showScreen(0);
	}
	
	public Sim getCurrentSim() {
		return currentSim;
	}

	public void setCurrentSim(Sim currentSim) {
		this.currentSim = currentSim;
	}
}
