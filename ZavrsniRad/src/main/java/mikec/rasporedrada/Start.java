package mikec.rasporedrada;

import mikec.rasporedrada.util.HibernateUtil;
import mikec.rasporedrada.util.NewPersonTry;
import mikec.rasporedrada.util.OsnovniUnosi;
public class Start {
    
    public static void main(String[] args) {
        HibernateUtil.createSession();     
        OsnovniUnosi unosi = new OsnovniUnosi(HibernateUtil.getSession());
        unosi.ucitaj();
        NewPersonTry.addPerson();       
    }
    
}
