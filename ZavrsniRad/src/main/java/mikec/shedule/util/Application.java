/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.util;

import java.awt.Image;
import javax.swing.ImageIcon;
import mikec.shedule.model.User;

/**
 *
 * @author Ivan
 */
public class Application {
    
    public static final String APP_TITLE = "Shedule App";
    public static User user;
    public static final String LINK_GITHUB = "https://github.com/mikecivan0/ZavrsniRad";
    public static final String LINK_ER_DIAGRAM = "https://github.com/mikecivan0/ZavrsniRad/blob/main/ZavrsniRad/database.png";
    public static final String PATH_ICON =  "src/main/resources/icon.png";
    
    public static String getTitle(String title){
        if(Application.user==null){
            return Application.APP_TITLE + " " + title;
        }
        return Application.APP_TITLE + " " + title +": " 
                + Application.user.getPerson().getLastName();
    }
    
    public static Image getIcon(){
        ImageIcon icon = new ImageIcon(PATH_ICON);
        Image image = icon.getImage();
        return image;
    }
    
    public static User getUser(){
      return user;
    }
    
}
