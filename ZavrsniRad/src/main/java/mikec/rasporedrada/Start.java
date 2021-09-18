package mikec.rasporedrada;

//import mikec.rasporedrada.util.StartData;
import mikec.rasporedrada.util.BaseValues;
import mikec.rasporedrada.util.HibernateUtil;
import mikec.rasporedrada.view.SplashScreen;
public class Start {
    
    public static void main(String[] args) {
        BaseValues baseValues = new BaseValues(HibernateUtil.getSession());
        baseValues.load();
//        StartData.addPerson();  
//        new SplashScreen().setVisible(true);
    }
    
}
