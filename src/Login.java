import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;



public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private static final String ERROR_TEXT = "Invalid user name or password!";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        initializeUI();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);  // Center the window
        setIconImage(new ImageIcon(getClass().getResource("/path/to/logo.png")).getImage());
    }

    private void initializeUI() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(5, 5));

        contentPane.add(createFormPanel(), BorderLayout.CENTER);
        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
        setContentPane(contentPane);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel lblUsername = new JLabel("User name:");
        usernameField = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        passwordField = new JPasswordField();

        panel.add(lblUsername);
        panel.add(usernameField);
        panel.add(lblPassword);
        panel.add(passwordField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(this::attemptLogin);
        panel.add(btnLogin);

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);

        return panel;
    }

    private void attemptLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText(ERROR_TEXT);
        } else if (DB.verifyLogin(username, password)) {
            errorLabel.setText("");
            openUserPanel(username);
        } else {
            errorLabel.setText(ERROR_TEXT);
        }
    }

    private void openUserPanel(String username) {
        if ("admin".equals(username)) {
            new AdminPanel().setVisible(true);
        } else {
            new GenerateInvoice().setVisible(true);
        }
        dispose();
    }
}
