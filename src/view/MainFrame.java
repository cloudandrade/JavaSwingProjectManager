package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.JSeparator;

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
        JPanel homePanel = new MainHomePanel(this);
        JPanel projectPanel = new MainProjectPanel(this);
        JPanel userPanel = new MainUserPanel(this);

        // Adicione os JPanels ao CardLayout
        //nome da variavel do painel - nome da classe
        mainPanel.add(homePanel, "MainHomePanel");
        mainPanel.add(projectPanel, "MainProjectPanel");
        mainPanel.add(userPanel, "MainUserPanel");

        // Crie o menu lateral
        JPanel menuPanel = createMenuPanel();

        // Configurar o layout do JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(menuPanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Exiba o primeiro painel
        cardLayout.show(mainPanel, "MainHomePanel");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    private JPanel createMenuPanel() {
        JPanel panelMenu = new JPanel();
        panelMenu.setPreferredSize(Constants.MENU_SIZE);
        panelMenu.setBackground(new Color(0, 128, 64));

        JButton btnHome = new JButton("Inicio");
        btnHome.setBounds(0, 95, 273, 42);
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("MainHomePanel");
            }
        });
        panelMenu.setLayout(null);

        panelMenu.add(btnHome);
        
        JLabel lblMenuLabel = new JLabel("ProjectManager");
        lblMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenuLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        lblMenuLabel.setBounds(0, 24, 273, 42);
        panelMenu.add(lblMenuLabel);
        
        JButton btnMainUser = new JButton("Usuários");
        btnMainUser.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showPanel("MainUserPanel");
        	}
        });
        btnMainUser.setBounds(0, 137, 273, 42);
        panelMenu.add(btnMainUser);
        
        JButton btnLogout = new JButton("Sair");
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		LoginFrame frame = new LoginFrame();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
        	}
        });
        btnLogout.setBounds(0, 650, 273, 33);
        panelMenu.add(btnLogout);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 84, 273, 2);
        panelMenu.add(separator);
        
        JButton btnMainProject = new JButton("Projetos");
        btnMainProject.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showPanel("MainProjectPanel");
        	}
        });
        btnMainProject.setBounds(0, 179, 273, 42);
        panelMenu.add(btnMainProject);

        return panelMenu;
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