package mikec.rasporedrada;

import mikec.rasporedrada.util.HibernateUtil;
import mikec.rasporedrada.util.StartData;
import mikec.rasporedrada.util.OsnovniUnosi;
public class Start {
    
    public static void main(String[] args) {
//        OsnovniUnosi unosi = new OsnovniUnosi(HibernateUtil.getSession());
//        unosi.ucitaj();
        StartData.addPerson();    
//        PersonTry.changePerson();
    }
    
}
