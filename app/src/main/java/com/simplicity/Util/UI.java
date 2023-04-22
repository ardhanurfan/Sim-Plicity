package com.simplicity.Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class UI {
    private GameManager gm;
    public JFrame window;
    public JTextArea messagText;
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];
    public JPanel attributePanel;

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
    }

    public void createBackground(int bgNum, String bgPath) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(0, 0, 700, 700);
        bgPanel[bgNum].setBackground(Color.blue);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 700, 700);
        ImageIcon bgIcon = new ImageIcon( new ImageIcon(getClass().getClassLoader().getResource(bgPath)).getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH));
        bgLabel[bgNum].setIcon(bgIcon);
    }

    public void attributeField() {
        messagText = new JTextArea();
        messagText.setBounds(10, 710, 630, 90);
        messagText.setBackground(null);
        messagText.setForeground(Color.white);
        messagText.setEditable(false);
        messagText.setLineWrap(true);
        messagText.setWrapStyleWord(true);
        messagText.setFont(new Font("Book Antique", Font.PLAIN, 24));
        window.add(messagText);

        attributePanel = new JPanel();
        attributePanel.setBounds(700, 0, 300, 750);
        attributePanel.setBackground(null);
        attributePanel.setLayout(null);
        window.add(attributePanel);
        
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

    public void createObjek(int bgNum, int x, int y, int width, int height, String objPath, String[] actions) {
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
        obj.setIcon(objImage);

        obj.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popMenu.show(obj, e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });

        bgPanel[bgNum].add(obj);
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

        bgPanel[bgNum].add(btn);
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

        bgPanel[bgNum].add(btn);
    }
    
    public void generateScreen() {
        // Start
        createBackgroundFull(0, "start.png");
        startButton(0, 150, 500, 700, 40, "Click Here to Start");
        bgPanel[0].add(bgLabel[0]);
        
        // Main Menu
        createBackgroundFull(1, "main_menu.png");
        if (gm.world.getDaftarSim().size() == 0) {
            customButton(1, 300, 300, 400, 50, "Create New Sim", 32, "new-sim");
            customButton(1, 300, 400, 400, 50, "Exit Game", 32, "exit");
        } else {
            customButton(1, 300, 250, 400, 50, "Choose Sim", 32, "choose-sim");
            customButton(1, 300, 350, 400, 50, "Create New Sim", 32, "new-sim");
            customButton(1, 300, 450, 400, 50, "Exit Game", 32, "exit");
        }
        bgPanel[1].add(bgLabel[1]);
        bgPanel[1].setVisible(false);
        
        // World
        createBackground(2, "");
        customButton(2, 300, 400, 400, 50, "Coba", 32, "aksi");
        bgPanel[2].add(bgLabel[2]);
        bgPanel[2].setVisible(false);;
        
        // Ruangan
        createBackground(3, "ruangan fix.png");
        customButton(3, 300, 400, 400, 50, "Coba", 32, "aksi");
        bgPanel[3].add(bgLabel[3]);
        bgPanel[3].setVisible(false);;
    }

}
