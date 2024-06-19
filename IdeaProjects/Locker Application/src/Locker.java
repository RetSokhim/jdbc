import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Locker {
    private JFrame frame;
    private String storedPassword = null;
    private final StringBuilder password = new StringBuilder();
    private JTextField passwordField;
    private JLabel statusLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Locker::new);
    }

    public Locker() {
        setupFrame();
    }

    public void setupFrame() {
        frame = new JFrame("Locker Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel numberPanel = createNumberPanel();
        JPanel controlPanel = createControlPanel();

        statusLabel = new JLabel("", SwingConstants.CENTER);

        frame.add(numberPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private JPanel createNumberPanel() {
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(3, 3));
        JButton[] numberButtons = new JButton[9];
        for (int i = 1; i <= 9; i++) {
            numberButtons[i - 1] = new JButton(String.valueOf(i));
            numberButtons[i - 1].addActionListener(new NumberButtonListener());
            numberPanel.add(numberButtons[i - 1]);
        }
        return numberPanel;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        passwordField = new JPasswordField(10);
        passwordField.setEditable(false);

        JButton enterButton = new JButton("Enter");
        JButton clearButton = new JButton("Clear");

        enterButton.addActionListener(new EnterButtonListener());
        clearButton.addActionListener(new ClearButtonListener());

        controlPanel.add(clearButton);
        controlPanel.add(passwordField);
        controlPanel.add(enterButton);

        return controlPanel;
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            password.append(source.getText());
            passwordField.setText(password.toString());
        }
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (storedPassword == null) {
                storedPassword = password.toString();
                statusLabel.setText("Password Set");
            } else {
                if (storedPassword.contentEquals(password)) {
                    statusLabel.setText("Correct Password");
                    JOptionPane.showMessageDialog(frame, "Correct Password", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    statusLabel.setText("Incorrect Password");
                    JOptionPane.showMessageDialog(frame, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            password.setLength(0);
            passwordField.setText("");
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            password.setLength(0);
            passwordField.setText("");
            statusLabel.setText("");
            storedPassword = null;
        }
    }
}
