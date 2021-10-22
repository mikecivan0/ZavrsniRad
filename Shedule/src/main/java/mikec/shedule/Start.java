package mikec.shedule;

//import javax.swing.JOptionPane;
//import mikec.shedule.util.BaseException;
//import mikec.shedule.util.BaseValues;
//import mikec.shedule.util.HibernateUtil;

import mikec.shedule.view.SplashScreen;
public class Start {
    
    public static void main(String[] args) {
//        try {
//            BaseValues baseValues = new BaseValues(HibernateUtil.getSession()); 
//            baseValues.load();
//
//        } catch (BaseException ex) {
//           JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
                new SplashScreen().setVisible(true);
    }    
}
