/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.istc.icv.engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Luca
 */
public class ApplicationManager {

    public String SCREENSHOT_FOLDER = "screenshots";
    private static ApplicationManager _instance = null;

    public static ApplicationManager getInstance() {
        if (_instance == null) {
            _instance = new ApplicationManager();
            return _instance;
        } else {
            return _instance;
        }
    }

    private ApplicationManager() {
        super();
    }

    public void screenshotPanel(JComponent panel) {
        new File("./" + SCREENSHOT_FOLDER).mkdirs();
        BufferedImage bImg = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        panel.paintAll(cg);
        try {
            FileOutputStream fm = new FileOutputStream(new File("./" + SCREENSHOT_FOLDER + "/screenshot_" + (new Date().getTime()) + ".png"));
            if (ImageIO.write(bImg, "png", fm)) {
//                JOptionPane.showMessageDialog(null, "The screenshot has been saved!", "Message", JOptionPane.INFORMATION_MESSAGE);
//                System.out.println("-- saved");
                fm.close();
            }
        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "The screenshot cannot be saved", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    
     public void screenshotPanel(JComponent panel, String subFolder, String imageStartName) {
        new File("./" + SCREENSHOT_FOLDER+"/"+subFolder).mkdirs();
        BufferedImage bImg = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        panel.paintAll(cg);
        try {
            FileOutputStream fm = new FileOutputStream(new File("./" + SCREENSHOT_FOLDER + "/"+subFolder+"/"+imageStartName + (new Date().getTime()) + ".png"));
            if (ImageIO.write(bImg, "png", fm)) {
//                JOptionPane.showMessageDialog(null, "The screenshot has been saved!", "Message", JOptionPane.INFORMATION_MESSAGE);
//                System.out.println("-- saved");
                fm.close();
            }
        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "The screenshot cannot be saved", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
