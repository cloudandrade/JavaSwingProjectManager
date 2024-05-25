package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import application.Constants;
import controller.LoginController;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
        panelMenu.setBackground(new Color(135, 206, 250));

        JButton btnHome = new JButton("Inicio");
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnHome.setFocusPainted(false); // Remove o foco pintado padrão
        btnHome.setBorderPainted(false); // Remove a borda pintada
        btnHome.setContentAreaFilled(false); // Remove a área de conteúdo preenchida
        btnHome.setOpaque(true);
        btnHome.setBounds(0, 95, 273, 42);
        btnHome.setBackground(new Color(135, 206, 250));
        btnHome.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	btnHome.setBackground(new Color(153, 50, 204)); // Cor quando o botão está focado
            }

            @Override
            public void focusLost(FocusEvent e) {
            	btnHome.setBackground(new Color(135, 206, 250)); // Cor quando o botão perde o foco
            }
        });
        
       
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("MainHomePanel");
            }
        });
        panelMenu.setLayout(null);

        panelMenu.add(btnHome);
        
        JLabel lblMenuLabel = new JLabel("Project\r\nManager");
        lblMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenuLabel.setFont(new Font("Impact", Font.BOLD, 32));
        lblMenuLabel.setBounds(0, 11, 273, 73);
        panelMenu.add(lblMenuLabel);
        
        JButton btnMainUser = new JButton("Usuários");
        btnMainUser.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnMainUser.setFocusPainted(false); // Remove o foco pintado padrão
        btnMainUser.setBorderPainted(false); // Remove a borda pintada
        btnMainUser.setContentAreaFilled(false); // Remove a área de conteúdo preenchida
        btnMainUser.setOpaque(true);
        btnMainUser.setBackground(new Color(135, 206, 250));
        btnMainUser.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	btnMainUser.setBackground(new Color(153, 50, 204)); // Cor quando o botão está focado
            }

            @Override
            public void focusLost(FocusEvent e) {
            	btnMainUser.setBackground(new Color(135, 206, 250)); // Cor quando o botão perde o foco
            }
        });
        btnMainUser.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showPanel("MainUserPanel");
        	}
        });
        btnMainUser.setBounds(0, 137, 273, 42);
        panelMenu.add(btnMainUser);
        
        JButton btnLogout = new JButton("Sair");
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogout.setFocusPainted(false); // Remove o foco pintado padrão
        btnLogout.setBorderPainted(false); // Remove a borda pintada
        btnLogout.setContentAreaFilled(false); // Remove a área de conteúdo preenchida
        btnLogout.setOpaque(true);
        btnLogout.setBackground(new Color(135, 206, 250));
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoginController.onLogout();
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
        btnMainProject.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnMainProject.setFocusPainted(false); // Remove o foco pintado padrão
        btnMainProject.setBorderPainted(false); // Remove a borda pintada
        btnMainProject.setContentAreaFilled(false); // Remove a área de conteúdo preenchida
        btnMainProject.setOpaque(true);
        btnMainProject.setBackground(new Color(135, 206, 250));
        btnMainProject.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	btnMainProject.setBackground(new Color(153, 50, 204)); // Cor quando o botão está focado
            }

            @Override
            public void focusLost(FocusEvent e) {
            	btnMainProject.setBackground(new Color(135, 206, 250)); // Cor quando o botão perde o foco
            }
        });
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