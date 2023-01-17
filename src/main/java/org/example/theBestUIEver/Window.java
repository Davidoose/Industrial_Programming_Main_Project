
package org.example.theBestUIEver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Window extends JFrame {


    private final JTextArea inputArea;
    private final JTextArea outputArea;

    ArrayList<String> listExp = new ArrayList<>();

    private Strategy method;

    Window(String name) {
        super(name);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        inputArea = new JTextArea();
        add(inputArea);
        JButton addExp = new JButton("Enter an expression");
        add(addExp);
        addExp.setPreferredSize(new Dimension(400, 60));
        JButton addSourceFile = new JButton("Select the source file");
        add(addSourceFile);
        addSourceFile.setPreferredSize(new Dimension(400, 60));
        JButton proccesExp = new JButton("procces expression");
        add(proccesExp);
        proccesExp.setPreferredSize(new Dimension(395, 60));

        JButton proccesExpByLib = new JButton("procces expression by exp4j lib");
        add(proccesExpByLib);
        proccesExpByLib.setPreferredSize(new Dimension(395, 60));

        JButton parseXML = new JButton("parse xml file");
        add(parseXML);
        parseXML.setPreferredSize(new Dimension(395, 60));

        JButton parseJSON = new JButton("parse json file");
        add(parseJSON);
        parseJSON.setPreferredSize(new Dimension(395, 60));

        JButton archive = new JButton("Archive");
        add(archive);
        archive.setPreferredSize(new Dimension(390, 60));

        JButton encrypt = new JButton("encrypt");
        add(encrypt);
        encrypt.setPreferredSize(new Dimension(390, 60));

        outputArea = new JTextArea();
        add(outputArea);

        addExp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Scanner scanner = new Scanner(JOptionPane.showInputDialog(null, "Enter expression"));
                try {
                    listExp.add(scanner.next());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                showInput();
            }
        });

        addSourceFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        Scanner scanner = new Scanner(fileChooser.getSelectedFile());
                        listExp.clear();
                        while (scanner.hasNext()) {
                            listExp.add(scanner.next());
                        }
                        showInput();
                    } catch (IllegalArgumentException | FileNotFoundException | InputMismatchException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        proccesExp.addActionListener(e -> {
            processExpStrategy(new ProccesExpStrategy());
        });

        proccesExpByLib.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                processExpStrategy(new ProccesExpStrategy());
            }
        });
    }

    private void processExpStrategy(Strategy s) {
        Scanner scanner = new Scanner(JOptionPane.showInputDialog(null, "Enter ageFrom and ageTo using space."));
        try {
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setStrategy(s);
        showOutput(method.getProcessedArray(listExp));
    }

    private void setStrategy(Strategy strategy) {
        method = strategy;
    }

    public static void main(String[] args) {
        Window chooser = new Window("By far the best UI ever");
        chooser.pack();
        chooser.getContentPane().setBackground(Color.CYAN);
        chooser.setSize(1000, 500);
        chooser.setLocation(300, 150);
        chooser.setVisible(true);
    }

    private void showOutput(ArrayList<String> output) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String exp : output) {
            stringBuilder.append(exp).append("\n");
        }
        outputArea.setText(stringBuilder.toString());
    }

    private void showInput() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String exp : listExp) {
            stringBuilder.append(exp.toString()).append("\n");
        }
        inputArea.setText(stringBuilder.toString());
    }

}


