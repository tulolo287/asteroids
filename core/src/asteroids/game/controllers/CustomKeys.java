package asteroids.game.controllers;

public class CustomKeys {
    private static boolean[] keys;
    private static boolean[] prevKey;
    private static final int NUM_KEYS = 5;
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int SPACE = 4;

    static {
        keys = new boolean[NUM_KEYS];
        prevKey = new boolean[NUM_KEYS];
    }

    public static boolean isKeyDown(int key) {
        return keys[key];
    }
    public static boolean isKeyPressed(int key) {
        return keys[key] && !prevKey[key];
    }
    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            prevKey[i] = keys[i];
        }
    }
    public static void setKey(int key, boolean flag) {
        keys[key] = flag;
    }
}
