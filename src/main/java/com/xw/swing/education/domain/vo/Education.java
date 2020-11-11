/*
 * Created by JFormDesigner on Mon Oct 19 14:50:38 CST 2020
 */

package com.xw.swing.education.domain.vo;

import javax.swing.*;
import java.awt.*;
import com.xw.swing.education.domain.vo.call.*;
import com.xw.swing.education.domain.vo.table.*;

/**
 * @author Brainrain
 */

public class Education extends JFrame {
    public Education() {
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        initComponents();
    }

    public void start() {
        this.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane2 = new JTabbedPane();
        panel3 = new JPanel();
        studentTableView1 = new StudentTableView();
        studentCall1 = new StudentCall();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane2 ========
        {

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
                panel3.add(studentTableView1);
            }
            tabbedPane2.addTab("\u5b66\u751f\u7ba1\u7406", panel3);
            tabbedPane2.addTab("\u70b9\u540d", studentCall1);
        }
        contentPane.add(tabbedPane2, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane2;
    private JPanel panel3;
    private StudentTableView studentTableView1;
    private StudentCall studentCall1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
