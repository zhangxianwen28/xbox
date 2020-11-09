package com.xw.elastic.domain.vo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TestCard {



        JPanel mainPanel = new JPanel();
        JMenu menu = new JMenu("Menu");
        JMenuBar menuBar = new JMenuBar();
        CardLayout cardlayout = new CardLayout();

        public TestCard() {
            mainPanel.setLayout(cardlayout);
            mainPanel.setBackground(Color.white);
            addCard("A");
            addCard("B");
            addCard("C");
            addCard("D");
            menuBar.add(menu);
        }

        private void addCard(String name) {
            JLabel label = new JLabel(name);
            mainPanel.add(label, name);
            menu.add(new JMenuItem(new MenuAction(name)));
        }

        public JComponent getMainPanel() {
            return mainPanel;
        }

        public JMenuBar getMenuBar() {
            return menuBar;
        }

        class MenuAction extends AbstractAction {
            public MenuAction(String name) {
                super(name);
            }

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String name = getValue(NAME).toString();
                cardlayout.show(mainPanel, name);
            }
        }

        public static void main(String[] args) {
            TestCard swapper = new TestCard();
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(swapper.getMainPanel());
            frame.setJMenuBar(swapper.getMenuBar());
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        }


}
