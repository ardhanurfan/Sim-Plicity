package com.simplicity;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class UI {
    private GameManager gameManager;
    private JFrame window;
    public JTextArea messagText;
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];

    public UI(GameManager gameManager) {
        this.gameManager = gameManager;
        createMainField();
        generateScreen();
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

    public void createBackground(int bgNum, String bgPath) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(0, 0, 1000, 800);
        bgPanel[bgNum].setBackground(Color.black);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 1000, 800);
        
        ImageIcon bgIcon = new ImageIcon( new ImageIcon(getClass().getClassLoader().getResource(bgPath)).getImage().getScaledInstance(1000, 800, Image.SCALE_DEFAULT));
        bgLabel[bgNum].setIcon(bgIcon);
    }


    public void createObjek(int bgNum, int x, int y, int width, int height, String objPath, String[] actions) {
        // POP MENU
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[actions.length];
        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(actions[i]);
            popMenu.add(menuItem[i]);
        }

        JLabel obj = new JLabel();
        obj.setBounds(x, y, width, height);

        ImageIcon objImage = new ImageIcon(getClass().getClassLoader().getResource(objPath));
        obj.setIcon(objImage);

        obj.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popMenu.show(obj, e.getX(), e.getY());
                }
            }
          
            @Override
            public void mouseReleased(MouseEvent e) {}
 
            @Override
            public void mouseEntered(MouseEvent e) {}
             
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        bgPanel[bgNum].add(obj);
        bgPanel[bgNum].add(bgLabel[bgNum]);
    }

    public void startButton(int bgNum, int x, int y, int width, int height, String text) {
        JButton btn = new JButton();
        
        btn.setBounds(x, y, width, height);
        btn.setText(text);
        btn.setBorder(null);
        btn.setBackground(null);
        btn.setForeground(Color.white);
        btn.setFocusPainted(false);
        // btn.addActionListener(gm.);
        btn.setContentAreaFilled(false);
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
        bgPanel[bgNum].add(bgLabel[bgNum]);
    }



    public void generateScreen() {
        // Start
        createBackground(0, "start.png");
        startButton(0, 150, 500, 700, 40, "Click Here to Start");
    }
}
