package com.gsg.codegenerator;

import org.springframework.util.StringUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * TODO
 *
 * @author shuaigang
 * @date 2023/6/14 16:52
 */
public class Operation {
    public static void main(String[] args) throws AWTException {
        //轰炸内容
        String str = "[炸弹],[烟花],[庆祝],[爆竹]";
        Robot robot = new Robot();
        robot.delay(5000);
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String[] authors = str.split("[,]");
        //轰炸次数
        for (int j = 0; j < 1000; j++){
            for (String str1 : authors) {
                Transferable text = new StringSelection(str1);
                clip.setContents(text, null);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.delay(100);
                robot.keyPress(KeyEvent.VK_ENTER);
            }
        }
    }
}
