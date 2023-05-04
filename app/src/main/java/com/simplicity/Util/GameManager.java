package com.simplicity.Util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.simplicity.Rumah;
import com.simplicity.Sim;
import com.simplicity.World;
import com.simplicity.Objek.ObjekMakanan;

public class GameManager {
	World world = new World();
	private Sim currentSim;
	ActionHandler actionHandler = new ActionHandler(this);
	UI ui = new UI(this);
	Routing routing = new Routing(this);

	// Thread threadTime;
	Thread threadAksi;

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

			world.setTime(Integer.parseInt(jsonObject.get("time").toString()));
			world.setHari(Integer.parseInt(jsonObject.get("hari").toString()));

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

	public void threadAksi(Integer waktuAksi, String cmd, String nama, Integer jumlah, ObjekMakanan makanan) {
		threadAksi = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < waktuAksi; i++) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						onUpdateThreadAksi();
					}
					method(waktuAksi, cmd, nama, jumlah, makanan);
					updateAttribute();
				} catch (Exception e) {
				}
			}
		});

		threadAksi.start();
	}

	public void onUpdateThreadAksi() {
		world.setTime(1);
		ui.jamText.setText(world.getTime());
		ui.hariText.setText("Hari ke-" + world.getHari());
		world.efekTiapSim(1);

		if (getCurrentSim().getWaktuTidakTidur() == 0 || getCurrentSim().getWaktuTidakBuangAir() == 0) {
			ui.kesehatanText.setText(getCurrentSim().getKesehatan());
			ui.moodText.setText(getCurrentSim().getMood());
		}

		efekMati();
	}

	public void efekMati() {
		Integer indeks = world.getDaftarSim().size();
		for (int i = 0; i < indeks; i++) {
			Sim sim = world.getDaftarSim().get(i);
			if (sim.getKekenyanganReal() <= 0 || sim.getKesehatanReal() <= 0 || sim.getMoodReal() <= 0) {
				if (getCurrentSim() != null && sim.getNamaLengkap().equals(getCurrentSim().getNamaLengkap())) {
					world.hapusRumah(sim.getRumah());
					world.hapusSim(sim);
					i--;
					indeks--;
					threadAksi.interrupt();
					currentSim = null;
				} else {
					world.hapusRumah(sim.getRumah());
					world.hapusSim(sim);
					i--;
					indeks--;
				}

				// kalo mati, balikin ke main menu kalo yg mati itu sim yg dimainin
			}
		}
		if (getCurrentSim() == null) {
			JOptionPane.showMessageDialog(null,
					"Anda akan dikembalikan ke main menu karena sim yang anda mainkan mati");
			routing.showScreen(1);
		}
	}

	public void updateAttribute() {
		ui.pekerjaanText.setText(getCurrentSim().getPekerjaan());
		ui.kesehatanText.setText(getCurrentSim().getKesehatan());
		ui.moodText.setText(getCurrentSim().getMood());
		ui.kekenyanganText.setText(getCurrentSim().getKekenyangan());
		ui.uangText.setText(getCurrentSim().getUang());
	}

	public void method(Integer waktuAksi, String cmd, String nama, Integer jumlah, ObjekMakanan makanan) {
		switch (cmd) {
			case "Bekerja":
				getCurrentSim().kerja(waktuAksi);
				break;
			case "Tidur":
				getCurrentSim().tidur(waktuAksi);
				break;
			case "Olahraga":
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
			case "Menonton":
				getCurrentSim().nontonTv();
				break;
			case "Ngoding":
				getCurrentSim().ngoding();
				break;
			case "Meditasi":
				getCurrentSim().meditasi();
				break;
			case "Main PS":
				getCurrentSim().mainPS();
				break;
			case "Main Game":
				getCurrentSim().bermain();
				break;
			case "Makan":
				getCurrentSim().makan(nama, jumlah);
				break;
			case "masak":
				getCurrentSim().masak(makanan);
				break;

		}
	}
}
