package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import application.Constants;
import controller.LoginController;
import model.UserModel;
import util.CacheUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MainHomePanel extends JPanel {
    public MainHomePanel(MainFrame mainFrame) {
    	
    	//UserModel loggedUser = LoginController.getLoggedUser();
        setBackground(new Color(255, 255, 240));
        setPreferredSize(Constants.MAIN_SIZE);
        setLayout(null);
        
        JLabel lblBemVindo = new JLabel("O que é o Project Manager");
        lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblBemVindo.setBounds(21, 11, 1045, 41);
        add(lblBemVindo);
        
        JLabel lblNewLabel = new JLabel("Seja bem vindo, ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(42, 104, 103, 30);
        add(lblNewLabel);
        
        
        UserModel loggerUser = CacheUtils.recoverUserOnLocalStorage();
        JLabel lblNewLabel_1 = new JLabel(loggerUser.getName());
        
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(148, 104, 425, 30);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("em diversos lugares diferentes. Cada projeto possui etapas a serem analisadas e");
        lblNewLabel_2.setToolTipText("");
        lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2.setBounds(42, 215, 740, 30);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Project Manager é uma iniciativa de controle de projetos para ajuda humanitária e mundial,");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_3.setBounds(42, 175, 516, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("os projetos possuem temas baseados nos  indicadores de ODS da ONU que ajudam a salvar muitas pessoas");
        lblNewLabel_4.setBounds(42, 195, 556, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("que caso aprovado serão executados pela equipe responsável em conjunto com o criador do projeto.");
        lblNewLabel_5.setBounds(42, 235, 498, 14);
        add(lblNewLabel_5);
        
        JLabel lblOdsImage = new JLabel("");
        lblOdsImage.setBounds(633, 67, 350, 350);
        
        try {
            Image originalImage = ImageIO.read(new File("src/resources/ods.png"));
            // Redimensionar a imagem para caber no JLabel
            Image scaledImage = originalImage.getScaledInstance(lblOdsImage.getWidth(), lblOdsImage.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            // Definir a imagem redimensionada como ícone do JLabel
            lblOdsImage.setIcon(scaledIcon);
            
        } catch (IOException e) {
            e.printStackTrace();
            lblOdsImage.setText("Imagem não encontrada");
        }
        
        add(lblOdsImage);
    }
}