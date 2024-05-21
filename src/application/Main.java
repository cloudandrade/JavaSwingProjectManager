package application;

import javax.swing.SwingUtilities;

import view.LoginFrame;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	LoginFrame frame = new LoginFrame();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
            }
        });
    }

}
