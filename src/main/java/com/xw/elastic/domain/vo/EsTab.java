/*
 * Created by JFormDesigner on Mon Nov 09 18:39:16 CST 2020
 */

package com.xw.elastic.domain.vo;

import java.awt.*;
import javax.swing.*;
import com.xw.elastic.domain.vo.component.*;

/**
 * @author Brainrain
 */
public class EsTab extends JPanel {
    public EsTab() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        esTreeComponent1 = new EsTreeComponent();
        esFormComponent1 = new EsFormComponent();

        //======== this ========
        setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setLayout(new BorderLayout());
                panel1.add(esTreeComponent1, BorderLayout.WEST);
                panel1.add(esFormComponent1, BorderLayout.CENTER);
            }
            tabbedPane1.addTab("text", panel1);
        }
        add(tabbedPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private EsTreeComponent esTreeComponent1;
    private EsFormComponent esFormComponent1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
