package com.simplicity.Util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.simplicity.Rumah;
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

	public void save() {
		try (FileWriter file = new FileWriter("save.json")) {
			file.write(world.toJson().toJSONString());
			file.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Save gagal");
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader("save.json"));
			JSONObject jsonObject = (JSONObject) obj;

			World.setTime(Integer.parseInt(jsonObject.get("time").toString()));

			JSONArray jsonArrayRumah = (JSONArray) jsonObject.get("listRumah");
			List<Rumah> listRumah = new ArrayList<Rumah>();
			for (Object object : jsonArrayRumah) {
				listRumah.add(new Rumah((JSONObject) object));
			}
			World.setListRumah(listRumah);

			JSONArray jsonArraySim = (JSONArray) jsonObject.get("listSim");
			List<Sim> listSim = new ArrayList<Sim>();
			for (Object object : jsonArraySim) {
				listSim.add(new Sim((JSONObject) object, world.getDaftarRumah()));
			}
			World.setListSim(listSim);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Load gagal");
			e.printStackTrace();
		}
	}

<<<<<<< Updated upstream
=======
	// public void threadTime() {
	// threadTime = new Thread(new Runnable() {
	// @Override
	// public void run() {
	// boolean stop = false;
	// while (threadTime.isAlive() && !stop) {
	// try {
	// Thread.sleep(1000);
	// world.setTime(1);
	// currentSim.addOnTimeWorld(1); // untuk aksi yang perlu kondisi
	// ui.jamText.setText(world.getTime());
	// ui.hariText.setText("Hari ke-" + world.getHari());
	// } catch (InterruptedException e) {
	// stop = true;
	// }
	// }
	// }
	// });
	// }

	public void threadAksi(int waktuAksi, String cmd) {
		threadAksi = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < waktuAksi; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					onUpdateThreadAksi();
				}
				method(waktuAksi, cmd);
				updateAttribute();
				//fungsi();
			}
		});

		threadAksi.start();
	}

	public void onUpdateThreadAksi() {
		world.setTime(1);
		ui.jamText.setText(world.getTime());
		ui.hariText.setText("Hari ke-" + world.getHari());
		world.efekTiapSim(1);

		if (getCurrentSim().getWaktuTidakTidur() == 0 || currentSim.getWaktuTidakBuangAir() == 0 ) {
		ui.kesehatanText.setText(getCurrentSim().getKesehatan());
		ui.moodText.setText(getCurrentSim().getMood());
		}
	}

	public void updateAttribute() {
		ui.pekerjaanText.setText(getCurrentSim().getPekerjaan());
		ui.kesehatanText.setText(getCurrentSim().getKesehatan());
		ui.moodText.setText(getCurrentSim().getMood());
		ui.kekenyanganText.setText(getCurrentSim().getKekenyangan());
		ui.uangText.setText(getCurrentSim().getUang());
	}

	public void method(int waktuAksi, String cmd) {
		switch (cmd) {
			case "Bekerja":
				getCurrentSim().kerja(waktuAksi);
				break;
			case "Tidur":
				getCurrentSim().tidur(waktuAksi);
				break;
			case "Olahraga" :
				getCurrentSim().olahraga(waktuAksi);
				break;
			case "Duduk":
				getCurrentSim().duduk();
				break;
			case "Buang Air":
				getCurrentSim().buangAir();
				break;
			case "Ngudud":
				getCurrentSim().ngudud();	
				break;
			case "Menonton" :
				getCurrentSim().nontonTv();
				break;
			case "Ngoding":
				getCurrentSim().ngoding();
				break;
			case "Meditasi" :
				getCurrentSim().meditasi();
				break;
			case "Main PS" :
				getCurrentSim().mainPS();
				break;
			case "Main Game" :
				getCurrentSim().bermain();
				break;
			case ""

		
		
			default:
				break;
		}
	}
>>>>>>> Stashed changes
}
