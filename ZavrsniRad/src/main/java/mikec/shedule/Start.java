package mikec.shedule;

//import mikec.rasporedrada.util.StartData;
import mikec.shedule.util.BaseValues;
import mikec.shedule.util.HibernateUtil;
import mikec.shedule.view.SplashScreen;
public class Start {
    
    public static void main(String[] args) {
        BaseValues baseValues = new BaseValues(HibernateUtil.getSession());
        baseValues.load();
//        StartData.addPerson();  
//        new SplashScreen().setVisible(true);
    }
    
}
