/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.util;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.swing.ImageIcon;
import mikec.shedule.model.User;

/**
 *
 * @author Ivan
 */
public class Application {

    public static final String APP_TITLE = "Shedule App";
    public static User user = new User();
    public static final String LINK_GITHUB = "https://github.com/mikecivan0/ZavrsniRad";
    public static final String LINK_ER_DIAGRAM = "https://github.com/mikecivan0/ZavrsniRad/blob/main/Shedule/database.png";
    public static final String PATH_ICON = "src/main/resources/icon.png";
    public static final String PATH_TRAY_ICON = "src/main/resources/trayIcon.png";
    private static TrayIcon trayIcon;
    private static SystemTray tray;

    public static String getTitle(String title) {
        if (Application.user.getId()==null) {
            return Application.APP_TITLE + " | " + title;
        }
        return Application.APP_TITLE + " | " + title + " | "
                + Application.user.getPerson().getLastName();
    }

    public static Image getIcon() {
        ImageIcon icon = new ImageIcon(PATH_ICON);
        Image image = icon.getImage();
        return image;
    }

    public static User getUser() {
        return user;
    }

    public static void initializeTrayIcon() {
        ImageIcon icon = new ImageIcon(PATH_TRAY_ICON);
        Image image = icon.getImage();
        trayIcon = new TrayIcon(image, "Shedule");
        tray = SystemTray.getSystemTray();
        if (SystemTray.isSupported()) {

            trayIcon.setImageAutoSize(true);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        }
    }

    public static void removeTrayIcon() {
        tray.remove(trayIcon);
    }
    
    public static void setUser(User user){
        Application.user=user;
    }

}
