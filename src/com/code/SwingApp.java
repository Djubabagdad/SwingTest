package com.code;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingApp {
    private JFrame frame;

    public static void main(String[] args) throws InterruptedException {
        SwingApp swingApp = new SwingApp();
        swingApp.go();
    }

    public void go() {
        frame = new JFrame();
        Intro intro = new Intro();

        frame.setContentPane(intro.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private class Intro extends JComponent {
        private JButton enter_button;
        private JPanel panel;
        private JTextArea textArea;

        public Intro() {
            setup();
            events();
        }

        private void setup() {
            panel = new JPanel();
            enter_button = new JButton();
            textArea = new JTextArea();

            final JLabel label1 = new JLabel();
            final Spacer spacer1 = new Spacer();
            final Spacer spacer2 = new Spacer();

            panel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
            panel.setEnabled(false);
            enter_button.setBackground(new Color(-12830242));
            enter_button.setEnabled(true);
            enter_button.setForeground(new Color(-1));
            enter_button.setHideActionText(false);
            enter_button.setText("Enter");

            label1.setText("How many numbers to display?");
            textArea.setEnabled(true);

            panel.add(enter_button, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 30), null, 0, false));
            panel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
            panel.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 70), null, 0, false));
            panel.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 70), null, 0, false));
            panel.add(textArea, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 30), null, 0, false));
        }

        private void events() {
            enter_button.addActionListener(e -> {
                Sort sort = new Sort(new Integer(textArea.getText()));
                frame.setContentPane(sort.panel);
                frame.validate();
                frame.repaint();
            });
        }
    }

    private class Sort extends JComponent {
        private JButton sortButton;
        private JButton resetButton;
        private JPanel panel;
        private JPanel buttonsPanel;
        private int number;
        private JButton[] buttons;
        private boolean isSorted = true;

        public Sort(int number) {
            this.number = number;
            initialize();
            setup();
            events();
        }

        private void initialize() {
            buttons = new JButton[number];
            for (int i = 0; i < number; i++) {
                buttons[i] = new JButton();
            }
            for (int i = 0; i < number; i++) {
                if (i == number - 1) {
                    int lastNumb = (int) (Math.random() * 30);
                    buttons[i].setText(String.valueOf(lastNumb));
                    break;
                }
                int nameNumb = (int) (Math.random() * 1000);
                buttons[i].setText(String.valueOf(nameNumb));
            }
        }

        private void setup() {
            panel = new JPanel();
            final JPanel panel3 = new JPanel();
            final Spacer spacer2 = new Spacer();
            final Spacer spacer1 = new Spacer();

            panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
            panel.add(panel3, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

            sortButton = new JButton();
            sortButton.setText("Sort");
            resetButton = new JButton();
            resetButton.setText("Reset");

            panel3.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
            panel3.add(sortButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 30), null, 0, false));
            panel3.add(resetButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 30), null, 0, false));
            panel3.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
            panel3.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
            setButtons();
        }

        private void setButtons() {
            if(buttonsPanel != null) {
                panel.remove(buttonsPanel);
            }
            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayoutManager(10, (number / 10) + 1, new Insets(0, 0, 0, 0), -1, -1));
            panel.add(buttonsPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
            int column = 0;
            int row = 0;

            for (int i = 0; i < buttons.length; i++) {
                if (row == 10) {
                    row = 0;
                    column = i / 10;
                }
                buttons[i].addActionListener(new ButtonsListener());
                buttons[i].setBackground(new Color(-12830242));
                buttons[i].setForeground(new Color(-1));
                buttonsPanel.add(buttons[i], new GridConstraints(row++, column, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(100, 30), null, 0, false));
            }
        }

        public void events() {
            resetButton.addActionListener(e -> {
                Intro intro = new Intro();

                frame.setContentPane(intro.panel);
                frame.validate();
                frame.repaint();
            });
            sortButton.addActionListener(new SortButton());
        }

        class ButtonsListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                Integer buttonNumb = new Integer(b.getText());
                if (buttonNumb <= 30) {
                    Sort comp = new Sort(number);
                    comp.initialize();
                    comp.setup();
                    comp.events();
                    frame.setContentPane(comp.panel);
                    frame.validate();
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a value smaller or equal to 30");
                }
            }
        }

        class SortButton implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int startIndex = 0;
                int endIndex = buttons.length - 1;

                new Thread(()->{
                    SwingUtilities.invokeLater(()->{frame.invalidate(); frame.repaint();});
                    if(isSorted) {
                        descendingOrder(buttons, startIndex, endIndex);
                    }else{
                        increasingOrder(buttons, startIndex, endIndex);
                    }
                }).start();


            }

            private void descendingOrder(JButton[] array, int start, int end) {
                if (start >= end) {
                    isSorted = false;
                    return;
                }
                int i = start, j = end;
                int cur = i - (i - j) / 2;

                while (i < j) {
                    int value1 = new Integer(array[i].getText());
                    int value2 = new Integer(array[cur].getText());
                    int value3 = new Integer(array[j].getText());
                    while (i < cur && (value1 >= value2)) {
                        i++;
                        value1 = new Integer(array[i].getText());

                    }
                    while (j > cur && (value2 >= value3)) {
                        j--;
                        value3 = new Integer(array[j].getText());

                    }
                    if (i < j) {
                        Integer temp = new Integer(array[i].getText());
                        array[i].setText(array[j].getText());
                        array[j].setText(temp.toString());

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ignored) {
                        }

                        setButtons();

                        if (i == cur)
                            cur = j;
                        else if (j == cur)
                            cur = i;
                    }
                }
                descendingOrder(buttons, start, cur);
                descendingOrder(buttons, cur+1, end);
            }

            private void increasingOrder(JButton[] array, int start, int end) {
                if (start >= end) {
                    isSorted = true;
                    return;
                }
                int i = start, j = end;
                int cur = i - (i - j) / 2;

                while (i < j) {
                    int value1 = new Integer(array[i].getText());
                    int value2 = new Integer(array[cur].getText());
                    int value3 = new Integer(array[j].getText());
                    while (i < cur && (value1 <= value2)) {
                        i++;
                        value1 = new Integer(array[i].getText());
                    }
                    while (j > cur && (value2 <= value3)) {
                        j--;
                        value3 = new Integer(array[j].getText());
                    }
                    if (i < j) {
                        Integer temp = new Integer(array[i].getText());
                        array[i].setText(array[j].getText());
                        array[j].setText(temp.toString());

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (i == cur)
                            cur = j;
                        else if (j == cur)
                            cur = i;
                    }
                }
                increasingOrder(buttons, start, cur);
                increasingOrder(buttons, cur+1, end);
            }
        }
    }
}
