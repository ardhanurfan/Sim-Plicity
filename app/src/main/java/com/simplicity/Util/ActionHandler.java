package com.simplicity.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JComboBox;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.simplicity.Rumah;
import com.simplicity.Sim;
import com.simplicity.Inventory.InventoryItem;
import com.simplicity.Ruangan;
import com.simplicity.Objek.ObjekBahanMakanan;
import com.simplicity.Objek.ObjekMakanan;
import com.simplicity.Objek.ObjekNonMakanan;
import com.simplicity.Objek.ObjekPekerjaan;
import com.simplicity.Point;

public class ActionHandler implements ActionListener {

    GameManager gm;
    Ruangan currRuangan = null;

    private Thread threadBerkunjung;
    private String textBerkunjung;

    public ActionHandler(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int indexObj = -1;

        if (command.contains("%")) {
            String[] listCmd = command.split("%");
            command = listCmd[1];
            indexObj = Integer.parseInt(listCmd[0]);
        }

        // click sound
        gm.playSE(gm.clickMusic);

        boolean cekSimHarusPunyaRumah = (gm.getCurrentSim() == null || gm.getCurrentSim().getRumah() != null
                || command.equals("start")
                || command.equals("exit") || command.equals("choose-sim") || command.equals("help") || command
                        .equals("new-sim"));

        if ((gm.threadAksi == null || !gm.threadAksi.isAlive()) && (threadBerkunjung == null
                || !threadBerkunjung.isAlive())
                && (cekSimHarusPunyaRumah)) {
            switch (command) {
                // Main menu
                case "start":
                    gm.routing.showScreen(1);
                    gm.stopMusic(gm.gameMusic);
                    gm.playMusic(gm.menuMusic);
                    break;
                case "new-sim":
                    if (!gm.world.getIsSudahBikinSim()) {
                        String name = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "What is your sim name?",
                                "Input Full Name",
                                JOptionPane.PLAIN_MESSAGE);
                        if (name != null && name.replaceAll(" ", "").length() > 0) {
                            if (gm.world.addSim(name)) {
                                // set sudah bikin sim hari ini
                                gm.world.setIsSudahBikinSim(true);
                                // set current sim terbaru
                                gm.setCurrentSim(gm.world.getDaftarSim().get(gm.world.getDaftarSim().size() - 1));
                                gm.updateAttribute();
                                gm.ui.bgPanel[2].removeAll();
                                gm.ui.bgPanel[2].add(gm.ui.bgLabel[2]);
                                for (int i = 0; i < gm.world.getDaftarRumah().size(); i++) {
                                    gm.ui.createObjek(gm.ui.bgPanel[2],
                                            gm.world.getDaftarRumah().get(i).getLocRumah().getX() * 10 + 30,
                                            gm.world.getDaftarRumah().get(i).getLocRumah().getY() * 10 + 30, 20, 20,
                                            "rumah.png",
                                            new String[] { "View Home%" + gm.world.getDaftarRumah().get(i).getNama() },
                                            i);
                                }
                                gm.routing.showScreen(2);
                                gm.ui.messagText.setText("Hello Welcome, " + gm.getCurrentSim().getNamaLengkap()
                                        + "! Select your house location!");
                                gm.ui.nameText.setText(gm.getCurrentSim().getNamaLengkap());
                                gm.stopMusic(gm.menuMusic);
                                gm.playMusic(gm.gameMusic);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nama sudah digunakan!");
                            }
                        } else if (name != null) {
                            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Anda hari ini sudah bikin sim!");
                    }
                    break;
                case "choose-sim":
                    List<String> options = gm.world.getDaftarSim().stream()
                            .map(Sim::getNamaLengkap)
                            .collect(Collectors.toList());
                    Object selected = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Your Sim", "Choose Sim",
                            JOptionPane.PLAIN_MESSAGE, null, options.toArray(), options.get(0));
                    if (selected != null) {
                        int index = options.indexOf(selected);
                        // set current sim by index choosen
                        gm.setCurrentSim(gm.world.getDaftarSim().get(index));
                        gm.updateAttribute();
                        gm.ui.bgPanel[2].removeAll();
                        gm.ui.bgPanel[2].add(gm.ui.bgLabel[2]);
                        for (int i = 0; i < gm.world.getDaftarRumah().size(); i++) {
                            if (gm.getCurrentSim().getRumah() != null && gm.getCurrentSim().getRumah().getNama()
                                    .equals(gm.world.getDaftarRumah().get(i).getNama())) {
                                gm.ui.createObjek(gm.ui.bgPanel[2],
                                        gm.getCurrentSim().getRumah().getLocRumah().getX() * 10 + 30,
                                        gm.getCurrentSim().getRumah().getLocRumah().getY() * 10 + 30, 20, 20,
                                        "rumahku.png",
                                        new String[] { "View Home%" + gm.world.getDaftarRumah().get(i).getNama() }, i);
                            } else {
                                gm.ui.createObjek(gm.ui.bgPanel[2],
                                        gm.world.getDaftarRumah().get(i).getLocRumah().getX() * 10 + 30,
                                        gm.world.getDaftarRumah().get(i).getLocRumah().getY() * 10 + 30, 20, 20,
                                        "rumah.png",
                                        new String[] { "View Home%" + gm.world.getDaftarRumah().get(i).getNama() }, i);
                            }
                        }
                        gm.ui.bgPanel[2].repaint();
                        gm.routing.showScreen(2);
                        if (gm.getCurrentSim().getRumah() == null) {
                            gm.ui.messagText.setText("Hello Welcome Back, " + gm.getCurrentSim().getNamaLengkap()
                                    + "! Select your house location!");
                        } else {
                            gm.ui.messagText.setText("Hello Welcome Back, " + gm.getCurrentSim().getNamaLengkap());
                        }
                        gm.ui.nameText.setText(gm.getCurrentSim().getNamaLengkap());
                        gm.stopMusic(gm.menuMusic);
                        gm.playMusic(gm.gameMusic);
                    }
                    break;
                case "help":
                    JOptionPane.showMessageDialog(gm.ui.bgPanel[1],
                            "Berikut adalah panduan untuk bermain Sim-Plicity : \n" +
                                    "1. Objective Game ini untuk menjaga kesejahteraan SIM agar tidak depresi dan mati.\n"
                                    +
                                    "2. Pemain dapat melanjutkan SIM yang telah dimainkan atau membuat SIM baru.\n" +
                                    "3. Pastikan untuk save SIM kalian sebelum meninggalkan permainan.\n" +
                                    "4. Pemain menunggu lama-nya aktivitas sesuai dengan waktu yang telah ditentukan.\n"
                                    +
                                    "5. Pemain dapat berimajinasi sesuai dengan keinginannya di Game ini.\n" +
                                    "Selamat Bermain!");
                    break;
                case "exit":
                    int result = JOptionPane.showConfirmDialog(gm.ui.bgPanel[1],
                            "Do you want to Exit ?", "Exit Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        int save = JOptionPane.showConfirmDialog(gm.ui.bgPanel[1],
                                "Do you want to Save Game ?", "Save Confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (save == JOptionPane.YES_OPTION) {
                            gm.save();
                        }
                        System.exit(0);
                    }
                    break;

                case "Back to World":
                    gm.routing.showScreen(2);
                    if (gm.getCurrentSim().getRumah() == null) {
                        gm.ui.messagText.setText("Hello, " + gm.getCurrentSim().getNamaLengkap()
                                + "! Select your house location!");
                    } else {
                        gm.ui.messagText.setText("Silakan berkunjung, lokasi saat ini " + gm.getCurrentSim()
                                .getCurrLokasi().getRumah().getNama());
                    }
                    // gm.getCurrentSim().getCurrLokasi().setRuangan(null);
                    // gm.getCurrentSim().getCurrLokasi().setRumah(null);
                    break;
                case "Back to Home":
                    Rumah currHome = gm.getCurrentSim().getCurrLokasi().getRumah();
                    gm.routing.showScreen(4);
                    if (currHome.getNama().equals(gm.getCurrentSim().getRumah().getNama())) {
                        gm.ui.messagText.setText("Berada di rumah milik Anda, " + currHome.getNama());
                    } else {
                        gm.ui.messagText.setText("Saat ini Anda berkunjung ke " + currHome.getNama());
                    }
                    gm.getCurrentSim().getCurrLokasi().setRuangan(null);
                    gm.ui.refreshHome(currHome);
                    break;

                case "View Room":
                    currRuangan = gm.getCurrentSim().getCurrLokasi().getRumah().getDaftarRuangan().get(indexObj);
                    gm.getCurrentSim().getCurrLokasi().setRuangan(currRuangan);
                    gm.ui.messagText.setText("Berada di ruang " + currRuangan.getNama() + " di dalam "
                            + gm.getCurrentSim().getCurrLokasi().getRumah().getNama());
                    gm.ui.refreshRoom(currRuangan);
                    break;
                case "View Home":
                    Rumah otwRumah = gm.world.getDaftarRumah().get(indexObj);
                    int waktuOtw = gm.getCurrentSim().berkunjung(otwRumah);
                    if (gm.getCurrentSim().getCurrLokasi().getRumah() == null) {
                        textBerkunjung = "Anda baru lahir, Otw dari (0, 0) ke " + otwRumah.getNama() + " selama "
                                + waktuOtw + " detik";
                    } else {
                        textBerkunjung = "Otw dari " + gm.getCurrentSim().getCurrLokasi().getRumah().getNama()
                                + " ke " + otwRumah.getNama() + " selama " + waktuOtw + " detik";
                    }
                    gm.getCurrentSim().setStatus("Sedang berkunjung");
                    threadBerkunjung = new Thread(() -> {
                        for (int i = 0; i < waktuOtw; i++) {
                            gm.ui.messagText
                                    .setText(textBerkunjung);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            gm.onUpdateThreadAksi();
                        }
                        gm.ui.refreshHome(otwRumah);
                        gm.updateAttribute();
                        gm.getCurrentSim().getCurrLokasi().setRumah(otwRumah);
                        if (gm.getCurrentSim().getRumah() != null
                                && otwRumah.getNama().equals(gm.getCurrentSim().getRumah().getNama())) {
                            gm.ui.messagText.setText("Berada di rumah milik Anda, " + otwRumah.getNama());
                        } else {
                            gm.ui.messagText.setText("Saat ini Anda berkunjung ke " + otwRumah.getNama());
                        }
                    });
                    threadBerkunjung.start();
                    break;

                // Upgrade Rumah
                case "Upgrade House":
                    if (gm.getCurrentSim().getRuangUpgrade() == null) {
                        // Buat Pilihan Acuan Kamar
                        // Boolean status;
                        if (gm.getCurrentSim().getUangReal() >= 1800){
                            Rumah currHouse = gm.getCurrentSim().getCurrLokasi().getRumah();
                            List<Ruangan> ruangan = currHouse.getDaftarRuangan();
                            List<String> listRuangans = new ArrayList<String>();
                            for (Ruangan ruang : gm.getCurrentSim().getCurrLokasi().getRumah().getDaftarRuangan()) {
                                listRuangans.add(ruang.getNama());
                            }
                            // int jumlahKamarAwal = currHouse.getDaftarRuangan().size();
                            Object acuanRuang = JOptionPane.showInputDialog(gm.ui.bgPanel[3], "Pilih ruangan acuan",
                                    "Upgrade House",
                                    JOptionPane.PLAIN_MESSAGE, null, listRuangans.toArray(), listRuangans.get(0));
                            Ruangan RuanganAcuan = ruangan.get(listRuangans.indexOf(acuanRuang));
                            // Buat pilihan posisi
                            List<String> acuanposisi = Arrays.asList("Kanan", "Kiri", "Atas", "Bawah");
                            Object posisiacuan = JOptionPane.showInputDialog(gm.ui.bgPanel[3],
                                    "Silakan pilih posisi kamar baru dibanding kamar acuan",
                                    "Upgrade House", JOptionPane.PLAIN_MESSAGE, null, acuanposisi.toArray(),
                                    acuanposisi.get(0));
                            String acuan = acuanposisi.get(acuanposisi.indexOf(posisiacuan));
    
                            // Melakukan upgrade rumah
                            // Meminta input namaruangan
                            String namaruangan = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                    "Silakan masukkan nama ruangan yang baru", "Input Nama Ruangan Baru",
                                    JOptionPane.PLAIN_MESSAGE);
    
                            gm.getCurrentSim().setRuangUpgrade(currHouse.upgradeRumah(RuanganAcuan, acuan, namaruangan));
                            if(gm.getCurrentSim().getRuangUpgrade() != null){
                                gm.getCurrentSim().setwaktuUpgradeRumah(1080);
                                gm.getCurrentSim().setUang(gm.getCurrentSim().getUangReal()-1500);
                                gm.ui.uangText.setText(gm.getCurrentSim().getUang());
                                gm.ui.messagText.setText("Sedang upgrade rumah");
                            } else {
                                gm.ui.messagText.setText("Upgrade rumah dibatalkan");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "uang Anda tidak cukup untuk melakukan upgrade rumah, diperlukan 1500!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Tidak bisa melakukan dua upgrade sekaligus");
                    }
                    break;
                case "Edit Room":
                    // Opsi tombol edit room
                    List<String> option = Arrays.asList("Add Object", "Delete Object", "Move Object");
                    Object selectedPane = JOptionPane.showInputDialog(gm.ui.bgPanel[3], "Your Action", "Edit Room",
                            JOptionPane.PLAIN_MESSAGE, null, option.toArray(), option.get(0));
                    int index = option.indexOf(selectedPane);

                    // Ceritanya ini Inventory

                    List<String> inventory = gm.getCurrentSim().getInventory().getIventoryString();

                    // Tambah Barang
                    if (index == 0) {
                        // Inventory ObjekNonMakanan kosong
                        if (inventory.size() == 0) {
                            JOptionPane.showMessageDialog(gm.ui.bgPanel[3], "Tidak ada barang");
                        } else {
                            // Opsi inventory
                            Object selectedInventory = JOptionPane.showInputDialog(gm.ui.bgPanel[3],
                                    "Choose Object you want to add", "Edit Room", JOptionPane.PLAIN_MESSAGE, null,
                                    inventory.toArray(), inventory.get(0));
                            if (selectedInventory != null) {
                                int indexInventory = inventory.indexOf(selectedInventory);

                                // Bikin 3 dropdown buat milih titik dan posisi barang
                                String[] coordOptions = { "0", "1", "2", "3", "4", "5" };
                                String[] positionOptions = { "v", "h" };

                                JComboBox<String> selectionX = new JComboBox<>(coordOptions);
                                JComboBox<String> selectionY = new JComboBox<>(coordOptions);
                                JComboBox<String> selectionPosisi = new JComboBox<>(positionOptions);

                                Object[] message = { "Posisi X:", selectionX, "Posisi Y:", selectionY,
                                        "Horizontal/Vertikal (h/v):", selectionPosisi };
                                int optionPosisi1 = JOptionPane.showConfirmDialog(null, message, "Posisi Barang",
                                        JOptionPane.OK_CANCEL_OPTION);

                                if (optionPosisi1 == JOptionPane.OK_OPTION) {
                                    String stringX = (String) selectionX.getSelectedItem();
                                    String stringY = (String) selectionY.getSelectedItem();
                                    String posisi = (String) selectionPosisi.getSelectedItem();

                                    // Konversi ke integer
                                    int x = Integer.parseInt(stringX);
                                    int y = Integer.parseInt(stringY);

                                    // Bikin point dan objek sesuai pilihan
                                    Point point = new Point(x, y);

                                    // Object to add
                                    ObjekNonMakanan o = new ObjekNonMakanan(inventory.get(indexInventory));

                                    // Ngecek nabrak ato ga
                                    if (currRuangan.nabrakGa(o, point, posisi)) {
                                        currRuangan.tambahObjek(o, point, posisi);
                                        gm.getCurrentSim().getInventory()
                                                .kurangiItem(gm.getCurrentSim().getInventory().nameConverterReverse(
                                                        o.getNamaObjek()), 1);

                                        gm.ui.refreshRoom(currRuangan);
                                        gm.ui.messagText.setText("Barang berhasil ditambahkan ke ruangan");
                                    } else {
                                        gm.ui.messagText.setText("Barang tidak bisa ditambahkan karena nabrak");
                                    }
                                } else {
                                    gm.ui.messagText.setText("Dialog ditutup.");
                                }
                            }
                        }
                    }
                    // Hapus Barang
                    else if (index == 1) {
                        // Ga ada barang
                        if (currRuangan.getDaftarObjekString().size() == 0) {
                            JOptionPane.showMessageDialog(gm.ui.bgPanel[3], "Tidak ada barang");
                        } else {
                            // Opsi barang yang ada di currRuangan
                            List<String> optionObjek = currRuangan.getDaftarObjekString();
                            Object selectedObjek = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                    "Choose Object you want to delete", "Delete Object", JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    optionObjek.toArray(), optionObjek.get(0));
                            if (selectedObjek != null) {
                                // Hapus barang sesuai pilihan
                                int indexOptionObjek = optionObjek.indexOf(selectedObjek);
                                ObjekNonMakanan deleteObject = currRuangan.getObjek(indexOptionObjek);
                                currRuangan.hapusObjek(deleteObject);

                                deleteObject.setNamaObjek(gm.getCurrentSim().getInventory()
                                        .nameConverterReverse(deleteObject.getNamaObjek()));
                                gm.getCurrentSim().getInventory().addItemPeralatan(deleteObject, 1);

                                // Refresh panel
                                gm.ui.refreshRoom(currRuangan);
                                gm.ui.messagText.setText("Barang berhasil dihapus dari ruangan");
                            }
                        }
                    }
                    // Pindah barang
                    else if (index == 2) {
                        if (currRuangan.getDaftarObjekString().size() == 0) {
                            JOptionPane.showMessageDialog(gm.ui.bgPanel[3], "Tidak ada barang");
                        } else {
                            // Opsi barang yang ada di currRuangan
                            List<String> optionObjek2 = currRuangan.getDaftarObjekString();
                            Object selectedObjek = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                    "Choose Object you want to move", "Move Object", JOptionPane.PLAIN_MESSAGE, null,
                                    optionObjek2.toArray(), optionObjek2.get(0));
                            if (selectedObjek != null) {
                                // Hapus objek sesuai pilihan
                                int indexOptionObjek = optionObjek2.indexOf(selectedObjek);
                                ObjekNonMakanan moveObject = currRuangan.getObjek(indexOptionObjek);
                                Point pointAwal = moveObject.getTitik();
                                String posisiAwal = moveObject.getPosisi();
                                currRuangan.hapusObjek(moveObject);
                                // Bikin 3 dropdown buat milih titik dan posisi barang
                                String[] coord = { "0", "1", "2", "3", "4", "5" };
                                String[] position = { "v", "h" };

                                JComboBox<String> selectionX = new JComboBox<>(coord);
                                JComboBox<String> selectionY = new JComboBox<>(coord);
                                JComboBox<String> selectionPosisi = new JComboBox<>(position);

                                Object[] message = { "Posisi X:", selectionX, "Posisi Y:", selectionY,
                                        "Horizontal/Vertikal (h/v):", selectionPosisi };
                                int optionPosisi2 = JOptionPane.showConfirmDialog(null, message, "Posisi Barang",
                                        JOptionPane.OK_CANCEL_OPTION);

                                if (optionPosisi2 == JOptionPane.OK_OPTION) {
                                    String stringX = (String) selectionX.getSelectedItem();
                                    String stringY = (String) selectionY.getSelectedItem();
                                    String posisi = (String) selectionPosisi.getSelectedItem();

                                    // Konversi ke Integer
                                    int x = Integer.parseInt(stringX);
                                    int y = Integer.parseInt(stringY);

                                    // Bikin point dan ngecek nabrak ato ga
                                    Point point = new Point(x, y);
                                    if (currRuangan.nabrakGa(moveObject, point, posisi)) {
                                        currRuangan.tambahObjek(moveObject, point, posisi);
                                        gm.ui.messagText.setText("Barang berhasil dipindah");
                                    } else {
                                        currRuangan.tambahObjek(moveObject, pointAwal, posisiAwal);
                                        gm.ui.messagText.setText("Barang tidak bisa dipindah karena nabrak");
                                    }
                                    // Refresh panel
                                    gm.ui.refreshRoom(currRuangan);
                                } else {
                                    gm.ui.messagText.setText("Dialog ditutup");
                                }
                            }
                        }
                    }
                    break;

                case "save":
                    int save = JOptionPane.showConfirmDialog(gm.ui.bgPanel[1],
                            "Do you want to Save Game ?", "Save Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (save == JOptionPane.YES_OPTION) {
                        gm.save();
                    }
                    break;

                case "View Inventory":
                    if (gm.getCurrentSim().getInventory().getData().size() == 0) {
                        JOptionPane.showMessageDialog(null, "Inventory Kosong");
                    } else {
                        gm.ui.inventoryPopUp();
                        gm.ui.popInventory.setVisible(true);
                    }
                    break;

                // ACTION METHOD
                case "Tidur":
                    String waktuTidurString = null;
                    int waktuTidur = 0;
                    boolean cek = false;
                    while (!cek) {
                        waktuTidurString = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                "Berapa lama kamu ingin tidur (dalam detik)?", "Input Waktu Tidur",
                                JOptionPane.PLAIN_MESSAGE);
                        if (waktuTidurString == null) {
                            break;
                        } else {
                            try {
                                waktuTidur = Integer.parseInt(waktuTidurString);
                                if (waktuTidur <= 0) {
                                    JOptionPane.showMessageDialog(null, "Waktu harus lebih dari 0!");
                                } else {
                                    cek = true;
                                }
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null, "Input tidak valid!");
                            }
                        }
                    }
                    if (waktuTidurString != null) {
                        gm.getCurrentSim().setStatus("Sedang tidur");
                        gm.threadAksi(waktuTidur, "Tidur", null, null, null);
                        gm.ui.messagText.setText("Tidur dulu " + waktuTidur + " detik ah, biar ga capek");
                    }
                    break;

                case "Olahraga":
                    String waktuOlahragaString = null;
                    int waktuOlahraga = 0;
                    cek = false;
                    while (!cek) {
                        waktuOlahragaString = JOptionPane.showInputDialog(gm.ui.bgPanel[1],
                                "Berapa lama kamu ingin berolahraga (dalam detik)?", "Input Waktu Olahraga",
                                JOptionPane.PLAIN_MESSAGE);
                        if (waktuOlahragaString == null) {
                            break;
                        } else {
                            try {
                                waktuOlahraga = Integer.parseInt(waktuOlahragaString);
                                if (waktuOlahraga % 20 != 0 || waktuOlahraga <= 0) {
                                    JOptionPane.showMessageDialog(null, "Waktu harus kelipatan 20!");
                                } else if (waktuOlahraga <= 0) {
                                    JOptionPane.showMessageDialog(null, "Waktu harus lebih dari 0!");
                                } else {
                                    cek = true;
                                }
                            } catch (Exception e2) {
                                JOptionPane.showMessageDialog(null, "Input tidak valid!");
                            }
                        }
                    }
                    if (waktuOlahragaString != null) {
                        gm.getCurrentSim().setStatus("Sedang olahraga");
                        gm.threadAksi(waktuOlahraga, "Olahraga", null, null, null);
                        gm.ui.messagText.setText("Olahraga dulu cuy biar sehat...");
                    }

                    break;

                case "Duduk":
                    gm.getCurrentSim().setStatus("Sedang duduk");
                    gm.threadAksi(30, "Duduk", null, null, null);
                    gm.ui.messagText.setText("Enaknya... Santai sekali");
                    break;

                case "Ngudud":
                    gm.getCurrentSim().setStatus("Sedang ngudud");
                    gm.threadAksi(30, "Ngudud", null, null, null);
                    gm.ui.messagText.setText("Fiuhh.. Dunhill emang mantep cuy..");
                    break;

                case "Menonton":
                    gm.getCurrentSim().setStatus("Sedang nonton tv");
                    gm.threadAksi(30, "Menonton", null, null, null);
                    gm.ui.messagText.setText("Horee... Seru sekali acaranya");
                    break;

                case "Ngoding":
                    gm.getCurrentSim().setStatus("Sedang ngoding");
                    gm.threadAksi(30, "Ngoding", null, null, null);
                    gm.ui.messagText.setText("Ngoding dulu biar jago...");
                    break;

                case "Meditasi":
                    gm.getCurrentSim().setStatus("Sedang meditasi");
                    gm.threadAksi(30, "Meditasi", null, null, null);
                    gm.ui.messagText.setText("Meditasi itu membuat lebih tenang..");
                    break;

                case "Main PS":
                    gm.getCurrentSim().setStatus("Sedang main ps");
                    gm.threadAksi(30, "Main PS", null, null, null);
                    gm.ui.messagText.setText("Game PS itu seru euyy.. T-tapi mataku kok rasanya agak sakit ya..");
                    break;

                case "Main Game":
                    gm.getCurrentSim().setStatus("Sedang main game");
                    gm.threadAksi(30, "Main Game", null, null, null);
                    gm.ui.messagText.setText("Horee... Seru sekali gamenya");
                    break;

                case "Buang Air":
                    gm.getCurrentSim().setStatus("Sedang buang air");
                    gm.threadAksi(10, "Buang Air", null, null, null);
                    gm.ui.messagText.setText("Uhhh lega... udah buang air");
                    break;

                case "Makan":
                    List<String> inventoryMakanan = new ArrayList<String>();
                    for (InventoryItem item : gm.getCurrentSim().getInventory().getData()) {
                        if (item.getKategori().equals("Makanan") || item.getKategori().equals("Bahan Makanan")) {
                            inventoryMakanan.add(item.getNamaBarang());
                        }
                    }
                    if (!inventoryMakanan.isEmpty()) {
                        Object selectMakanan = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Choose Food", "Eat",
                                JOptionPane.PLAIN_MESSAGE, null, inventoryMakanan.toArray(), inventoryMakanan.get(0));
                        if (selectMakanan != null) {
                            String namaMakanan = (String) selectMakanan;
                            // cari kekenyangannya
                            int kekenyangan = 0;
                            for (ObjekMakanan makanan : gm.world.getDaftar_makanan()) {
                                if (namaMakanan.equals(makanan.getNamaObjek())) {
                                    kekenyangan = makanan.getKekenyangan();
                                }
                            }
                            for (ObjekBahanMakanan bahan : gm.world.getDaftar_bahan()) {
                                if (namaMakanan.equals(bahan.getNamaObjek())) {
                                    kekenyangan = bahan.getKekenyangan();
                                }
                            }
                            gm.getCurrentSim().setStatus("Sedang makan");
                            gm.threadAksi(30, "Makan", namaMakanan, kekenyangan, null);
                            gm.ui.messagText.setText("Yaammyy... makan " + namaMakanan + " dulu yaa... Enakkk..");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Anda tidak memiliki makanan!");
                    }
                    break;

                case "Bekerja":
                    if (gm.getCurrentSim().getJedaGantiKerja() >= 720 || !gm.getCurrentSim().getSudahGantiKerja()) {
                        String waktuBekerjaString = null;
                        int waktukerja = 0;
                        cek = false;
                        while (!cek) {
                            waktuBekerjaString = JOptionPane.showInputDialog(null,
                                    "Berapa lama kamu ingin bekerja (dalam detik)?", "Input Waktu Kerja",
                                    JOptionPane.PLAIN_MESSAGE);
                            if (waktuBekerjaString == null) {
                                break;
                            } else {
                                try {
                                    waktukerja = Integer.parseInt(waktuBekerjaString);
                                    if (waktukerja % 120 != 0) {
                                        JOptionPane.showMessageDialog(null, "Waktu harus kelipatan 120!");
                                    } else if (waktukerja <= 0) {
                                        JOptionPane.showMessageDialog(null, "Waktu harus lebih dari 0!");
                                    } else {
                                        cek = true;
                                    }
                                } catch (Exception e1) {
                                    JOptionPane.showMessageDialog(null, "Input tidak valid!");
                                }
                            }
                        }
                        if (waktuBekerjaString != null) {
                            gm.getCurrentSim().setStatus("Sedang bekerja");
                            gm.threadAksi(waktukerja, "Bekerja", null, null, null);
                            gm.ui.messagText.setText("Kerja dulu boss, selama " + waktukerja + " detik");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Anda dapat mulai bekerja 1 hari setelah ganti kerja!");
                    }
                    break;

                case "Ganti Pekerjaan":
                    if (gm.getCurrentSim().getTotalWaktuKerja() >= 720) {
                        List<String> listPekerjaan = new ArrayList<String>();
                        for (ObjekPekerjaan pekerjaan : gm.world.getDaftarPekerjaan()) {
                            if (!pekerjaan.getNamaObjek().equals(gm.getCurrentSim().getPekerjaan())) {
                                listPekerjaan.add(pekerjaan.getNamaObjek());
                            }
                        }
                        Object selectPekerjaan = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "Choose New Work",
                                "Change Work",
                                JOptionPane.PLAIN_MESSAGE, null, listPekerjaan.toArray(), listPekerjaan.get(0));
                        if (selectPekerjaan != null) {
                            ObjekPekerjaan newPekerjaan = null;
                            for (ObjekPekerjaan pekerjaan : gm.world.getDaftarPekerjaan()) {
                                if (pekerjaan.getNamaObjek().equals((String) selectPekerjaan)) {
                                    newPekerjaan = pekerjaan;
                                }
                            }
                            gm.getCurrentSim().setStatus("Ganti pekerjaan");
                            gm.getCurrentSim().setPekerjaan(newPekerjaan);
                            gm.updateAttribute();
                            gm.ui.messagText.setText("Selamat anda dapat pekerjaan baru, yeayyy");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Belum dapat ganti pekerjaan, waktu bekerja belum 1 hari!");
                    }
                    break;

                case "Melihat Waktu":
                    List<String> opsiLihat = Arrays.asList("Beli Barang", "Upgrade Rumah");
                    Object selectLihat = JOptionPane.showInputDialog(gm.ui.bgPanel[3], "Ingin memantau apa?",
                            "Melihat Waktu",
                            JOptionPane.PLAIN_MESSAGE, null, opsiLihat.toArray(), opsiLihat.get(0));
                    int idx = opsiLihat.indexOf(selectLihat);
                    if (idx == 0) {
                        if (gm.getCurrentSim().getPembelian().size() == 0) {
                            JOptionPane.showMessageDialog(null, "Belum ada barang yang dibeli");
                        } else {
                            gm.ui.beliBarangPopUp();
                            gm.ui.popBeliBarang.setVisible(true);
                        }
                    } else {
                        if (gm.getCurrentSim().getWaktuUpgradeRumah() > 0) {
                            int time = gm.getCurrentSim().getWaktuUpgradeRumah();
                            int menit = time / 60;
                            int detik = time % 60;
                            String stringmenit = String.valueOf(menit < 10 ? "0" + menit : menit);
                            String stringdetik = String.valueOf(detik < 10 ? "0" + detik : detik);
                            JOptionPane.showMessageDialog(null,
                                    "Waktu upgrade rumah tersisa " + stringmenit + " : " + stringdetik);
                        } else {
                            JOptionPane.showMessageDialog(null, "Rumah sedang tidak di upgrade");
                        }
                    }
                    break;

                case "Go to Store":
                    List<String> opsi = Arrays.asList("Furnitur", "Bahan Makanan");
                    Object selectBeli = JOptionPane.showInputDialog(gm.ui.bgPanel[3], "Ingin membeli barang apa?",
                            "Buy Item",
                            JOptionPane.PLAIN_MESSAGE, null, opsi.toArray(), opsi.get(0));
                    int indeks = opsi.indexOf(selectBeli);
                    if (indeks == 0) {
                        gm.ui.peralatanPopUp();
                        gm.ui.popPeralatan.setVisible(true);
                    } else {
                        gm.ui.bahanPopUp();
                        gm.ui.popBahan.setVisible(true);
                    }
                    break;
                case "Beli Barang":
                    // Dapatkan Objek Barang dari Pilihan
                    String namaBarang = (String) gm.ui.selectBarang.getSelectedItem();
                    ObjekNonMakanan objekBarang = null;
                    for (ObjekNonMakanan barang : gm.world.getDaftar_barang()) {
                        if (barang.getNamaObjek().equals(namaBarang)) {
                            objekBarang = barang;
                        }
                    }
                    if (objekBarang != null) {
                        if (gm.getCurrentSim().getUangReal() >= objekBarang.getHarga()) {
                            gm.getCurrentSim().setUang(gm.getCurrentSim().getUangReal() - objekBarang.getHarga());
                            gm.getCurrentSim().addPembelian(namaBarang);
                            gm.getCurrentSim().addDeliveryTime(((int) (Math.random() * 5 * 1) + 1) * 30);
                            gm.ui.uangText.setText(gm.getCurrentSim().getUang());
                        } else {
                            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk membeli pilihan anda");
                        }
                    }
                    break;

                case "Beli Bahan":
                    // Dapatkan Objek Bahan dari Pilihan
                    String namaBahan = (String) gm.ui.selectBahan.getSelectedItem();
                    ObjekBahanMakanan objekBahan = null;
                    for (ObjekBahanMakanan bahan : gm.world.getDaftar_bahan()) {
                        if (bahan.getNamaObjek().equals(namaBahan)) {
                            objekBahan = bahan;
                        }
                    }
                    if (objekBahan != null) {
                        if (gm.getCurrentSim().getUangReal() >= objekBahan.getHarga()) {
                            gm.getCurrentSim().setUang(gm.getCurrentSim().getUangReal() - objekBahan.getHarga());
                            gm.getCurrentSim().addPembelian(namaBahan);
                            gm.getCurrentSim().addDeliveryTime(((int) (Math.random() * 5 * 1) + 1) * 30);
                            gm.ui.uangText.setText(gm.getCurrentSim().getUang());
                        } else {
                            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk membeli pilihan anda");
                        }
                    }
                    break;

                case "Memasak":
                    gm.ui.memasakPopUp();
                    gm.ui.popMemasak.setVisible(true);
                    break;

                case "masak":
                    // Dapatkan Objek Makanan dari Pilihan
                    String namaMasakan = (String) gm.ui.selectMasakan.getSelectedItem();
                    ObjekMakanan objekMasakan = null;
                    for (ObjekMakanan makanan : gm.world.getDaftar_makanan()) {
                        if (makanan.getNamaObjek().equals(namaMasakan)) {
                            objekMasakan = makanan;
                        }
                    }

                    if (objekMasakan != null) {
                        // int waktuMasak = gm.getCurrentSim().masak(objekMasakan);
                        int waktuMasak = (int) Math.round(objekMasakan.getKekenyangan() * 1.5);
                        // validasi bahan-bahan dari inventory
                        List<String> inventoryBahan = new ArrayList<String>();
                        for (InventoryItem item : gm.getCurrentSim().getInventory().getData()) {
                            if (item.getKategori().equals("Bahan Makanan")) {
                                inventoryBahan.add(item.getNamaBarang());
                            }
                        }
                        boolean isBahanAda = true;
                        for (ObjekBahanMakanan bahan : objekMasakan.getBahan()) {
                            boolean cekAdaBahan = inventoryBahan.contains(bahan.getNamaObjek());
                            if (!cekAdaBahan) {
                                isBahanAda = false;
                                JOptionPane.showMessageDialog(null,
                                        "Bahan " + bahan.getNamaObjek() + " tidak tersedia!");
                            }
                        }
                        if (isBahanAda) {
                            gm.getCurrentSim().setStatus("Sedang memasak");
                            gm.threadAksi(waktuMasak, "masak", null, null, objekMasakan);
                            gm.ui.messagText
                                    .setText("Srengggg... sedang masak " + namaMasakan + ", " + waktuMasak + " detik");
                        } else {
                            JOptionPane.showMessageDialog(null, "Bahan makanan tidak lengkap pilih menu lain!");
                        }
                    }
                    break;
            }
        } else if (!cekSimHarusPunyaRumah) {
            JOptionPane.showMessageDialog(null, "Silakan bangun rumah sebelum melakukan aksi!");
        } else {
            JOptionPane.showMessageDialog(null, "Aksi lain belum selesai!");
        }
    }
}
