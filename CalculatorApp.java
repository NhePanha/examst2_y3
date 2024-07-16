package exam_st2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        display = new JTextField();
        display.setEditable(false);
        panel.add(display);

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        operationButtons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            operationButtons[i] = new JButton(buttonLabels[i]);
            operationButtons[i].addActionListener(this);
            panel.add(operationButtons[i]);
        }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            display.setText(String.valueOf(result));
        } else if (actionCommand.equals("C")) {
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = '\0';
            display.setText("");
        } else if (actionCommand.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (actionCommand.matches("[0-9]")) {
            display.setText(display.getText() + actionCommand);
        } else {
            num1 = Double.parseDouble(display.getText());
            operator = actionCommand.charAt(0);
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}
