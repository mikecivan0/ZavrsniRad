package mikec.rasporedrada.main;

//import mikec.rasporedrada.util.HibernateUtil;
//import org.hibernate.Session;
import mikec.rasporedrada.util.NewPersonTry;

/**
 *
 * @author dell
 */
public class Start {
    
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSession();
//       
//        OsnovniUnosi unosi = new OsnovniUnosi(session);
//        unosi.ucitaj();

        NewPersonTry.addPerson();
       
    }
    
}
