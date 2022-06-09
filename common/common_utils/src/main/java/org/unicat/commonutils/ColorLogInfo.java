package org.unicat.commonutils;

/**
 * @author UNICAT
 */
public class ColorLogInfo {

    public static final int RED = 31;
    public static final int GREEN = 32;
    public static final int YELLOW = 33;
    public static final int BLUE = 34;
    public static final int PURPLE = 35;
    public static final int CYAN = 36;


    /**
     *
     * @param describe 对于内容的前面描述
     * @param color 内容的颜色(RED/GREEN/YELLOW/BLUE/PURPLE/CYAN)
     * @param pattern 模式(数字+m：1加粗；3斜体；4下划线)
     * @param content 内容描述
     */
    public static void colorLog(String describe, int color, int pattern, String content) {
        System.out.format("%s\33[%d;%dm%s\33[0m\n", describe, color, pattern, content);
    }
}
