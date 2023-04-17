package com.simplicity.Util;

import com.simplicity.World;

public class GameManager {
	World world = new World();
	ActionHandler actionHandler = new ActionHandler(this);
	UI ui = new UI(this);
	Routing routing = new Routing(this);

	public GameManager() {
	}
	
}
