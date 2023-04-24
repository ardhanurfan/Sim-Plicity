package com.simplicity.Util;

import java.io.FileWriter;
import java.io.IOException;

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
		save();
	}

	public Sim getCurrentSim() {
		return currentSim;
	}

	public void setCurrentSim(Sim currentSim) {
		this.currentSim = currentSim;
	}

	public void save() {
		try (FileWriter file = new FileWriter("save.json")) {
			file.write(world.toJson().toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
