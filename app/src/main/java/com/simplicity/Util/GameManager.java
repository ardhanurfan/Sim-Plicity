package com.simplicity.Util;
public class GameManager {
	ActionHandler actionHandler = new ActionHandler(this);
	UI ui = new UI(this);
	Routing routing = new Routing(this);

	public GameManager() {
	}
	
}
