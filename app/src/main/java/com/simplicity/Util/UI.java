package com.simplicity.Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.simplicity.Point;
import com.simplicity.Ruangan;
import com.simplicity.Rumah;
import com.simplicity.Objek.ObjekNonMakanan;

public class UI {
    private GameManager gm;
    public JFrame window;
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];
    
    public JTextArea messagText;
    public JLabel nameText;
    public JLabel jamText;

    public UI(GameManager gm) {
        this.gm = gm;
        createMainField();
        generateScreen();
        attributeField();
        window.setVisible(true);
    }

    public void createMainField() {
        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setResizable(false);
    }

    public void createBackgroundFull(int bgNum, String bgPath) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(0, 0, 1000, 800);
        bgPanel[bgNum].setBackground(Color.black);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 1000, 800);
        
        ImageIcon bgIcon = new ImageIcon( new ImageIcon(getClass().getClassLoader().getResource(bgPath)).getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH));
        bgLabel[bgNum].setIcon(bgIcon);
        bgPanel[bgNum].add(bgLabel[bgNum]);
        bgPanel[bgNum].setVisible(false);
    }

    public void createBackground(int bgNum, String bgPath, MouseListener mouse) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(0, 0, 700, 700);
        bgPanel[bgNum].setBackground(null);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);
        
        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 700, 700);
        ImageIcon bgIcon = new ImageIcon( new ImageIcon(getClass().getClassLoader().getResource(bgPath)).getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH));
        bgLabel[bgNum].setIcon(bgIcon);
        bgLabel[bgNum].addMouseListener(mouse);
        bgPanel[bgNum].add(bgLabel[bgNum]);
        bgPanel[bgNum].setVisible(false);
    }

    public void attributeField() {
        JPanel textPanel = new JPanel();
        textPanel.setBounds(0, 700, 700, 100);
        textPanel.setBackground(Color.white);
        textPanel.setLayout(null);
        window.add(textPanel);

        messagText = new JTextArea();
        messagText.setBounds(10, 10, 680, 90);
        messagText.setBackground(null);
        messagText.setWrapStyleWord(true);
        messagText.setLineWrap(true);
        messagText.setEditable(false);
        messagText.setForeground(Color.black);
        messagText.setFont(new Font("Book Antique", Font.PLAIN, 24));
        textPanel.add(messagText);

        JPanel attributePanel = new JPanel();
        attributePanel.setBounds(700, 0, 300, 800);
        attributePanel.setBackground(new Color(163, 115, 42, 255));
        attributePanel.setLayout(null);
        window.add(attributePanel);
        
        // add nama
        nameText = new JLabel();
        nameText.setBounds(50, 50, 270, 50);
        nameText.setBackground(Color.blue);
        nameText.setForeground(Color.white);
        nameText.setFont(new Font("Book Antique", Font.PLAIN, 24));
        attributePanel.add(nameText);

        // add jam
        ImageIcon jamImage = new ImageIcon( new ImageIcon(getClass().getClassLoader().getResource("jam.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel jamIcon = new JLabel();
        jamIcon.setBounds(50, 100, 50, 50);
        jamIcon.setIcon(jamImage);
        jamIcon.setBackground(null);
        attributePanel.add(jamIcon);
        jamText = new JLabel(String.format("%d : %d", (720-gm.world.getTime())/60,  (720-gm.world.getTime())%60));
        jamText.setBounds(120, 100, 200, 50);
        jamText.setBackground(null);
        jamText.setForeground(Color.white);
        jamText.setFont(new Font("Book Antique", Font.PLAIN, 24));
        attributePanel.add(jamText);
    }

      public ImageIcon rotate(ImageIcon icon, double degrees) {
        // Get the buffered image from the ImageIcon
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
    
        // Calculate the new size of the image based on the angle of rotation
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(icon.getIconWidth() * cos + icon.getIconHeight() * sin);
        int newHeight = (int) Math.round(icon.getIconWidth() * sin + icon.getIconHeight() * cos);
    
        // Create a new image
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = rotatedImage.createGraphics();
        // Calculate the "anchor" point around which the image will be rotated
        int x = (newWidth - icon.getIconWidth()) / 2;
        int y = (newHeight - icon.getIconHeight()) / 2;
        // Transform the origin point around the anchor point
        AffineTransform at = new AffineTransform();
        at.setToRotation(radians, x + (icon.getIconWidth() / 2), y + (icon.getIconHeight() / 2));
        at.translate(x, y);
        g2d.setTransform(at);
        // Paint the original image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
    
        // Create a new ImageIcon from the rotated image
        return new ImageIcon(rotatedImage);
    }

    public void createObjek(int bgNum, int x, int y, int width, int height, String objPath, String[] actions, String posisi) {
        // POP MENU
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[actions.length];
        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(actions[i]);
            menuItem[i].addActionListener(gm.actionHandler);
            menuItem[i].setActionCommand(actions[i]);
            popMenu.add(menuItem[i]);
        }

        JLabel obj = new JLabel();
        obj.setBounds(x, y, width, height);
        
        ImageIcon objImage = new ImageIcon(getClass().getClassLoader().getResource(objPath));
        if (posisi.equals("h")){
            objImage = rotate(objImage, 90);
        }
        obj.setIcon(objImage);

        obj.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    popMenu.show(obj, e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });

        bgPanel[bgNum].add(obj);
    }

    public void createObjek(int bgNum, int x, int y, int width, int height, String objPath, String[] actions, int index) {
        // POP MENU
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[actions.length];
        for (int i = 0; i < menuItem.length; i++) {
            if (actions[i].contains("View Home")) { // khusus rumah
                String[] command = actions[i].split("%");
                menuItem[i] = new JMenuItem("View " + command[1]);
                menuItem[i].addActionListener(gm.actionHandler);
                menuItem[i].setActionCommand(index+"%"+command[0]);
            } else {
                menuItem[i] = new JMenuItem(actions[i]);
                menuItem[i].addActionListener(gm.actionHandler);
                menuItem[i].setActionCommand(index+"%"+actions[i]);
            }
            popMenu.add(menuItem[i]);
        }

        JLabel obj = new JLabel();
        obj.setBounds(x, y, width, height);
        ImageIcon objImage = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource(objPath)).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        obj.setIcon(objImage);
        obj.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    popMenu.show(obj, e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        bgPanel[bgNum].add(obj, 0);
    }


    public void startButton(int bgNum, int x, int y, int width, int height, String text) {
        JButton btn = new JButton();
        
        btn.setBounds(x, y, width, height);
        btn.setText(text);
        btn.setBorder(null);
        btn.setBackground(null);
        btn.setForeground(Color.white);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.addActionListener(gm.actionHandler);
        btn.setActionCommand("start");
        btn.setFont(new Font("Helvetica", Font.BOLD, 30));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setContentAreaFilled(true);
                btn.setBackground(new Color(0, 0, 0, 200));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setContentAreaFilled(false);
            }
        });

        bgPanel[bgNum].add(btn, 0);
    }

    public void customButton(int bgNum, int x, int y, int width, int height, String text, int fontSize, String command) {
        JButton btn = new JButton();
        btn.setBounds(x, y, width, height);
        btn.setText(text);
        btn.setForeground(new Color(7, 168, 104));
        btn.setFocusPainted(false);
        btn.addActionListener(gm.actionHandler);
        btn.setActionCommand(command);
        btn.setFont(new Font("Helvetica", Font.BOLD, fontSize));
        bgPanel[bgNum].add(btn, 0);
    }

    public void generateRoom(Ruangan r, int bgNum){
        for(int i =1;i<=r.getDaftarObjek().size();i++){
            ObjekNonMakanan o = r.getObjek(i-1);
            int x = (int)Math.round(o.getTitik().getX()*116.67);
            int y = (int)Math.round(o.getTitik().getY()*116.67);
            int width,height;
            if("v".equals(o.getPosisi())){
                height = (int)Math.round(o.getPanjang()*116.67);
                width = (int)Math.round(o.getLebar()*116.67);
            }
            else{
                width = (int)Math.round(o.getPanjang()*116.67);
                height = (int)Math.round(o.getLebar()*116.67);
            }
            String filename = o.getNamaObjek()+".png";
            String [] action = new String[1]; 
            action[0] = o.getAksi();

            createObjek(bgNum,  x,  y,  width,  height, filename, action,o.getPosisi());
        }
    }

    public void createRoom(int bgNum, int x, int y, Ruangan ruangan, int index) {
        // POP MENU
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[2];
        menuItem[1] = new JMenuItem("View Room");
        menuItem[1].addActionListener(gm.actionHandler);
        menuItem[1].setActionCommand(index+"%"+"View Room");
        popMenu.add(menuItem[1]);

        JLabel obj = new JLabel();
        obj.setBounds(x, y,80, 80);
        obj.setForeground(Color.white);
        obj.setBackground(new Color(13, 161, 136)); 
        obj.setOpaque(true);
        obj.setBorder(BorderFactory.createLineBorder(Color.black));
        obj.setText("<html><div style='text-align: center;'>Ruang " + ruangan.getNama() + "</div></html>");
        obj.setFont(new Font("Helvetica", Font.BOLD, 16));
        obj.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    popMenu.show(obj, e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        bgPanel[bgNum].add(obj, 0);
    }

    public void generateHome(Rumah rumah, int bgNum) {
        
    }
    
    public void generateScreen() {
        // Start
        createBackgroundFull(0, "start.png");
        startButton(0, 150, 500, 700, 40, "Click Here to Start");
        
        // Main Menu
        createBackgroundFull(1, "main_menu.png");
        if (gm.world.getDaftarSim().isEmpty()) {
            customButton(1, 300, 300, 400, 50, "Create New Sim", 32, "new-sim");
            customButton(1, 300, 400, 400, 50, "Exit Game", 32, "exit");
        } else {
            customButton(1, 300, 250, 400, 50, "Choose Sim", 32, "choose-sim");
            customButton(1, 300, 350, 400, 50, "Create New Sim", 32, "new-sim");
            customButton(1, 300, 450, 400, 50, "Exit Game", 32, "exit");
        }
        
        // World
        createBackground(2, "world.png", new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getX() > 30 && e.getX() < 670 && e.getY() > 30 && e.getY() < 670) {
                    if (gm.getCurrentSim().getRumah()==null) {
                        createObjek(2, e.getX(), e.getY(), 20, 20, "rumahku.png", new String[]{""}, -1);
                        bgPanel[2].repaint();
                        String homeName = JOptionPane.showInputDialog(gm.ui.bgPanel[1], "What is your home name?", "Input Home homeName", JOptionPane.PLAIN_MESSAGE);
                        if (homeName!=null && homeName.replaceAll(" ", "").length() > 0) {
                            if (gm.world.addRumah(new Point((e.getX()-30)/10, (e.getY()-30)/10), homeName)) {
                                gm.getCurrentSim().setRumah(gm.world.getDaftarRumah().get(gm.world.getDaftarRumah().size()-1));
                                bgPanel[2].remove(0);
                                createObjek(2, e.getX(), e.getY(), 20, 20, "rumahku.png", new String[]{"View Home%" + homeName}, gm.world.getDaftarRumah().size()-1);
                                gm.ui.messagText.setText("Rumah berhasil di bangun, Selamat bermain");
                            } else {
                                JOptionPane.showMessageDialog(null,  "Nama sudah digunakan!");
                                bgPanel[2].remove(0);
                            }
                        } else {
                            bgPanel[2].remove(0);
                        }
                        bgPanel[2].repaint();
                    }    
                }
            }
        });

        // Rumah
        createBackground(4, "home.jpg", null);

        // Ruangan
        createBackground(3, "ruangan fix.png", null);
    }

}
