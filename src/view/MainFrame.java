package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import application.Constants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("CardLayout with Sidebar Example");
        setSize(Constants.FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialize o CardLayout para o painel principal
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setPreferredSize(Constants.MAIN_SIZE);

        // Crie os JPanels
        JPanel panel1 = new Panel1(this);
        JPanel panel2 = new Panel2(this);

        // Adicione os JPanels ao CardLayout
        mainPanel.add(panel1, "Panel1");
        mainPanel.add(panel2, "Panel2");

        // Crie o menu lateral
        JPanel menuPanel = createMenuPanel();

        // Configurar o layout do JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(menuPanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Exiba o primeiro painel
        cardLayout.show(mainPanel, "Panel1");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(Constants.MENU_SIZE);
        panel.setBackground(Color.LIGHT_GRAY);

        JButton button1 = new JButton("Panel 1");
        button1.setBounds(0, 65, 160, 42);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Panel1");
            }
        });

        JButton button2 = new JButton("Panel 2");
        button2.setBounds(0, 107, 160, 42);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Panel2");
            }
        });
        panel.setLayout(null);

        panel.add(button1);
        panel.add(button2);
        
        JLabel lblNewLabel = new JLabel("MENU");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        lblNewLabel.setBounds(0, 12, 160, 42);
        panel.add(lblNewLabel);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}