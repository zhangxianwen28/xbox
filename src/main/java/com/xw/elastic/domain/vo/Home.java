/*
 * Created by JFormDesigner on Fri Nov 06 13:09:51 CST 2020
 */

package com.xw.elastic.domain.vo;

import java.awt.event.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.xw.elastic.domain.StartLog;

import javax.swing.*;
import java.awt.*;
import com.xw.elastic.domain.vo.component.*;

/**
 * @author Brainrain
 */
public class Home extends JFrame {
    public Home() {
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        initComponents();
    }
    public static void createUI(){
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        Home home = new Home();
        home.setVisible(true);
        home.setEnabled(false);
        new Thread(() -> {
            for (;;) {
                try {
                    String take = StartLog.log.take();
                    home.label2.setText(take);
                    if("就绪".equals(take)){
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            home.label1.setText("");
            home.label2.setText("");
            home.setEnabled(true);
        }).start();

    }

    private void menuItem6ActionPerformed(ActionEvent e) {
        show(e);
        indexTablePanel1.getIndexTableBO().page();
    }

    private void show(ActionEvent e) {
        CardLayout cardLayout = (CardLayout)panel1.getLayout();
        String actionCommand = e.getActionCommand();
        cardLayout.show(panel1, actionCommand);
    }

    private void menuItem7ActionPerformed(ActionEvent e) {
        show(e);

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem7 = new JMenuItem();
        menuItem6 = new JMenuItem();
        panel1 = new JPanel();
        index = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        indexTablePanel1 = new IndexTablePanel();
        esTreeComponent1 = new EsTreeComponent();
        popupMenu1 = new JPopupMenu();
        menuItem1 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u7ba1\u7406");

                //---- menuItem7 ----
                menuItem7.setText("\u6811");
                menuItem7.setActionCommand("card3");
                menuItem7.addActionListener(e -> menuItem7ActionPerformed(e));
                menu1.add(menuItem7);

                //---- menuItem6 ----
                menuItem6.setText("\u7d22\u5f15");
                menuItem6.setActionCommand("card2");
                menuItem6.addActionListener(e -> menuItem6ActionPerformed(e));
                menu1.add(menuItem6);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(new CardLayout());

            //======== index ========
            {
                index.setPreferredSize(new Dimension(590, 320));
                index.setLayout(new FlowLayout());

                //---- label1 ----
                label1.setText("\u670d\u52a1\u542f\u52a8\u4e2d\uff0c\u8bf7\u7a0d\u540e...");
                index.add(label1);
                index.add(label2);
            }
            panel1.add(index, "index");
            panel1.add(indexTablePanel1, "card2");
            panel1.add(esTreeComponent1, "card3");
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== popupMenu1 ========
        {

            //---- menuItem1 ----
            menuItem1.setText("\u6dfb\u52a0\u5b50\u8282\u70b9");
            popupMenu1.add(menuItem1);

            //---- menuItem5 ----
            menuItem5.setText("\u5220\u9664\u5f53\u524d\u8282\u70b9");
            popupMenu1.add(menuItem5);

            //---- menuItem2 ----
            menuItem2.setText("text");
            popupMenu1.add(menuItem2);

            //---- menuItem3 ----
            menuItem3.setText("text");
            popupMenu1.add(menuItem3);

            //---- menuItem4 ----
            menuItem4.setText("text");
            popupMenu1.add(menuItem4);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem7;
    private JMenuItem menuItem6;
    private JPanel panel1;
    private JPanel index;
    private JLabel label1;
    private JLabel label2;
    private IndexTablePanel indexTablePanel1;
    private EsTreeComponent esTreeComponent1;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem5;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
