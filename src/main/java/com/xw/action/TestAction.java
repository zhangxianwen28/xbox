package com.xw.action;

import com.xw.Application;
import com.xw.bean.Table;
import com.xw.bean.TableInfo;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class TestAction extends AbstractAction {


    @Override
    public void actionPerformed(ActionEvent e) {
        List<Table> tables = new ArrayList<>();
        tables.add(new Table("11","22","33"));
        tables.add(new Table("22","22","33"));
        tables.add(new Table("33","22","33"));
        tables.add(new Table("11","22","33"));

        System.out.println("Set list 1111");
    }
}
