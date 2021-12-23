package com.krymlov.benchmark.scene;

import com.krymlov.benchmark.tests.DividingTest;
import com.krymlov.benchmark.tests.MinusTest;
import com.krymlov.benchmark.tests.MultiplicationTest;
import com.krymlov.benchmark.tests.PlusTest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Scene extends JFrame {
    private String[] opArray = new String[]{"+", "-", "*", "/"};
    private String[] typeArray = new String[]{"byte", "short", "int", "long", "char", "float", "double"};

    private PlusTest plusTest = new PlusTest();
    private MinusTest minusTest = new MinusTest();
    private MultiplicationTest multiplicationTest = new MultiplicationTest();
    private DividingTest dividingTest = new DividingTest();
    private final int NUMOFTYPES = 7;
    private String[] columnNames = new String[]{"Op", "Type", "OpPerSecond", "Percent"};
    private String[] columns = new String[]{};
    private Object[][] data = new Object[][]{{}};
    private DefaultTableModel model;
    private JTable table;
    private JButton jbtStart;
    private JPanel jPanel;

    public Scene(){
        this.setTitle("Benchmark");
        this.setSize(800, 700);
        this.setLocationRelativeTo((Component) null);
        this.setDefaultCloseOperation(3);
        this.model = new DefaultTableModel(this.data, this.columns);
        this.table = new JTable(this.model);
        this.table.setShowGrid(true);
        this.table.setGridColor(Color.BLACK);
        this.add(new JScrollPane(this.table), "Center");
        this.table.setColumnSelectionAllowed(false);
        this.jPanel = new JPanel(new GridLayout(1, 1));
        this.jPanel.setPreferredSize(new Dimension(800, 50));
        jbtStart = new JButton("Start");
        this.jPanel.add(jbtStart);
        this.add(jPanel, "North");
        this.setResizable(true);
        this.setVisible(true);

        this.jbtStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                addColumns(columnNames);

                List<Long> operationsList = new ArrayList<>();
                for (int i = 0; i < NUMOFTYPES; i++) {
                    operationsList.add(plusTest.getOperations(typeArray[i]));
                }
                for (int i = 0; i < NUMOFTYPES; i++) {
                    operationsList.add(minusTest.getOperations(typeArray[i]));
                }
                for (int i = 0; i < NUMOFTYPES; i++) {
                    operationsList.add(multiplicationTest.getOperations(typeArray[i]));
                }
                for (int i = 0; i < NUMOFTYPES; i++) {
                    operationsList.add(dividingTest.getOperations(typeArray[i]));
                }
                List<Long> percentList = calcPercent(operationsList);
                System.out.println(operationsList.size());
                System.out.println(percentList.size());
                for (int i = 0; i < operationsList.size(); i++) {
                    System.out.println(operationsList.get(i));
                }

                for (int i = 0; i < NUMOFTYPES; i++) {
                    addRow(opArray[0], typeArray[i], operationsList.get(i), percentList.get(i));
                }
                for (int i = 0; i < NUMOFTYPES; i++) {
                    addRow(opArray[1], typeArray[i], operationsList.get(i+7), percentList.get(i+7));
                }
                for (int i = 0; i < NUMOFTYPES; i++) {
                    addRow(opArray[2], typeArray[i], operationsList.get(i+14), percentList.get(i+14));
                }
                for (int i = 0; i < NUMOFTYPES; i++) {
                    addRow(opArray[3], typeArray[i], operationsList.get(i+21), percentList.get(i+21));
                }

            }
        });
    }

    private void addColumns(String[] array){
        for (int i = 0; i < array.length; i++) {
            String name = array[i];
            Scene.this.model.addColumn(name);
        }
    }

    private void addRow(String op, String type, long opPerSec, long percent){
        model.addRow(new Object[]{ op, type, opPerSec, percent});
    }

    private void clearTable(){
        model.setColumnCount(0);
        model.setRowCount(0);
    }

    private List<Long> calcPercent(List<Long> list){
        List<Long> result = new ArrayList<>();

        long max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max){
                max = list.get(i);
            }
        }

        System.out.println(max);
        for (int i = 0; i < list.size(); i++) {
                result.add((list.get(i)*100)/max);
        }
        return result;
    }

}
