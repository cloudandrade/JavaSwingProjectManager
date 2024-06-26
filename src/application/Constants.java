package application;

import java.awt.Dimension;

public class Constants {
	public static final int FRAME_WIDTH = 1366;
    public static final int FRAME_HEIGHT = 720;
    public static final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    public static final int MENU_WIDTH = (int) (FRAME_WIDTH * 0.2);
    public static final int MAIN_WIDTH = (int) (FRAME_WIDTH * 0.8);
    public static final Dimension MENU_SIZE = new Dimension(MENU_WIDTH, FRAME_HEIGHT);
    public static final Dimension MAIN_SIZE = new Dimension(MAIN_WIDTH, FRAME_HEIGHT);
    
    public static final int SMALL_FRAME_WIDTH = 500;
    public static final int SMALL_FRAME_HEIGHT = 500;
    public static final Dimension SMALL_FRAME_SIZE = new Dimension(SMALL_FRAME_WIDTH, SMALL_FRAME_HEIGHT);
    public static final Dimension SMALL_FRAME__EXTENDED_SIZE = new Dimension(SMALL_FRAME_WIDTH, 600);
    
    public static final String PROJECT_STATUS_CREATED = "CREATED";
}
