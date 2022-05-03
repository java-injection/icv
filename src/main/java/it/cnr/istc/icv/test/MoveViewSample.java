/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cnr.istc.icv.test;
/*
Definitive Guide to Swing for Java 2, Second Edition
By John Zukowski     
ISBN: 1-893115-78-X
Publisher: APress
*/

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JViewport;
import javax.swing.KeyStroke;

public class MoveViewSample {

  public static final int INCREASE = 0; // direction

  public static final int DECREASE = 1; // direction

  public static final int X_AXIS = 0; // axis

  public static final int Y_AXIS = 1; // axis

  public static final int UNIT = 0; // type

  public static final int BLOCK = 1; // type

  static class MoveAction extends AbstractAction {
    JViewport viewport;

    int direction;

    int axis;

    int type;

    public MoveAction(JViewport viewport, int direction, int axis, int type) {
      if (viewport == null) {
        throw new IllegalArgumentException(
            "null viewport not permitted");
      }
      this.viewport = viewport;
      this.direction = direction;
      this.axis = axis;
      this.type = type;
    }

    public void actionPerformed(ActionEvent actionEvent) {
      Dimension extentSize = viewport.getExtentSize();
      int horizontalMoveSize = 0;
      int verticalMoveSize = 0;
      if (axis == X_AXIS) {
        if (type == UNIT) {
          horizontalMoveSize = 1;
        } else { // type == BLOCK
          horizontalMoveSize = extentSize.width;
        }
      } else { // axis == Y_AXIS
        if (type == UNIT) {
          verticalMoveSize = 1;
        } else { // type == BLOCK
          verticalMoveSize = extentSize.height;
        }
      }
      if (direction == DECREASE) {
        horizontalMoveSize = -horizontalMoveSize;
        verticalMoveSize = -verticalMoveSize;
      }
      // Translate origin by some amount
      Point origin = viewport.getViewPosition();
      origin.x += horizontalMoveSize;
      origin.y += verticalMoveSize;
      // set new viewing origin
      viewport.setViewPosition(origin);
    }
  }

  public static void main(String args[]) {
      try {
          JFrame frame = new JFrame("JViewport Sample");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
          URL url = new URL("http://www.serenitymoments.com/screensavers/big_sur_forest_lime_kiln_creek.jpg");
          Image image = ImageIO.read(url);
          
          Icon icon = new ImageIcon(image);
          JLabel dogLabel = new JLabel(icon);
          JViewport viewport = new JViewport();
          viewport.setView(dogLabel);
          
          InputMap inputMap = viewport
                  .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
          ActionMap actionMap = viewport.getActionMap();
          
          // Up key moves view up unit
          Action upKeyAction = new MoveAction(viewport, DECREASE, Y_AXIS, UNIT);
          KeyStroke upKey = KeyStroke.getKeyStroke("UP");
          inputMap.put(upKey, "up");
          actionMap.put("up", upKeyAction);
          
          // Down key moves view down unit
          Action downKeyAction = new MoveAction(viewport, INCREASE, Y_AXIS, UNIT);
          KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
          inputMap.put(downKey, "down");
          actionMap.put("down", downKeyAction);
          
          // Left key moves view left unit
          Action leftKeyAction = new MoveAction(viewport, DECREASE, X_AXIS, UNIT);
          KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
          inputMap.put(leftKey, "left");
          actionMap.put("left", leftKeyAction);
          
          // Right key moves view right unit
          Action rightKeyAction = new MoveAction(viewport, INCREASE, X_AXIS, UNIT);
          KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
          inputMap.put(rightKey, "right");
          actionMap.put("right", rightKeyAction);
          
          // PgUp key moves view up block
          Action pgUpKeyAction = new MoveAction(viewport, DECREASE, Y_AXIS, BLOCK);
          KeyStroke pgUpKey = KeyStroke.getKeyStroke("PAGE_UP");
          inputMap.put(pgUpKey, "pgUp");
          actionMap.put("pgUp", pgUpKeyAction);
          
          // PgDn key moves view down block
          Action pgDnKeyAction = new MoveAction(viewport, INCREASE, Y_AXIS, BLOCK);
          KeyStroke pgDnKey = KeyStroke.getKeyStroke("PAGE_DOWN");
          inputMap.put(pgDnKey, "pgDn");
          actionMap.put("pgDn", pgDnKeyAction);
          
          // Shift-PgUp key moves view left block
          Action shiftPgUpKeyAction = new MoveAction(viewport, DECREASE, X_AXIS,
                  BLOCK);
          KeyStroke shiftPgUpKey = KeyStroke.getKeyStroke("shift PAGE_UP");
          inputMap.put(shiftPgUpKey, "shiftPgUp");
          actionMap.put("shiftPgUp", shiftPgUpKeyAction);
          
          // Shift-PgDn key moves view right block
          Action shiftPgDnKeyAction = new MoveAction(viewport, INCREASE, X_AXIS,
                  BLOCK);
          KeyStroke shiftPgDnKey = KeyStroke.getKeyStroke("shift PAGE_DOWN");
          inputMap.put(shiftPgDnKey, "shiftPgDn");
          actionMap.put("shiftPgDn", shiftPgDnKeyAction);
          
          Container contentPane = frame.getContentPane();
          contentPane.add(viewport, BorderLayout.CENTER);
          frame.setSize(300, 200);
          frame.setVisible(true);
      } catch (MalformedURLException ex) {
          Logger.getLogger(MoveViewSample.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(MoveViewSample.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
}

