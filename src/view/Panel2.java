package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.Constants;

public class Panel2 extends JPanel {
    public Panel2(MainFrame mainFrame) {
        setBackground(Color.GREEN);
        setPreferredSize(Constants.MAIN_SIZE);

        JButton button = new JButton("Go to Panel 1");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Panel1");
            }
        });
        add(button);
    }
}

