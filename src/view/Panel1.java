package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.Constants;

public class Panel1 extends JPanel {
    public Panel1(MainFrame mainFrame) {
        setBackground(Color.BLUE);
        setPreferredSize(Constants.MAIN_SIZE);

        JButton button = new JButton("Go to Panel 2");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Panel2");
            }
        });
        add(button);
    }
}
