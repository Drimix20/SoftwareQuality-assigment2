package trongame;

import java.awt.*;

public class Core {

    private static Core gameCore = new Core();
    public static final int MOVE_AMOUNT = 10;

    private Core() {
    }

    public static Core getInstance() {
        return gameCore;
    }

    private final DisplayMode[] supportedDisplayModes
            = {
                //new DisplayMode(1920,1080,32,0),
                new DisplayMode(1680, 1050, 32, 0),
                //new DisplayMode(1280,1024,32,0),
                new DisplayMode(800, 600, 32, 0),
                new DisplayMode(800, 600, 24, 0),
                new DisplayMode(800, 600, 16, 0),
                new DisplayMode(640, 480, 32, 0),
                new DisplayMode(640, 480, 24, 0),
                new DisplayMode(640, 480, 16, 0),};

    public DisplayMode[] getSupportedDisplayModes() {
        return supportedDisplayModes;
    }

}
