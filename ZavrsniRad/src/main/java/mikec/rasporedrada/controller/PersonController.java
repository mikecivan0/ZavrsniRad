/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.rasporedrada.controller;

import java.util.List;
import mikec.rasporedrada.model.Osoba;
import mikec.rasporedrada.util.BaseException;

/**
 *
 * @author Ivan
 */
public class PersonController extends BaseController<Osoba>{

    @Override
    public List<Osoba> read() {
        return session.createQuery("from osobe").list();
    }

    @Override
    protected void createControll() throws BaseException {
        notEmptyControll("ime");
        notEmptyControll("prezime");
        lengthControll("ime",25);
        lengthControll("prezime",25);
        lengthControll("adresa",100);
        lengthControll("telefon",50);
        lengthControll("email",50);
    }

    @Override
    protected void updateControll() throws BaseException {
        this.createControll();
    }

    @Override
    protected void deleteControll() throws BaseException {

    }

    private void lengthControll(String variable, Integer length) throws BaseException{   
        if(getVariable(variable).length()>length){
            throw new BaseException("Unos '" + variable + "' ne može biti duži od " + length + " znakova");
        }    
    }
    
    private void notEmptyControll(String variable) throws BaseException{        
        if(getVariable(variable)==null || getVariable(variable).trim().length()==0){
           throw new BaseException("Unos '" + variable + "' ne smije biti prazan");
       }    
    }
    
    private String getVariable(String variable){         
        String text = "";        
        if(null != variable) switch (variable) {
            case "ime" -> text = entity.getIme();
            case "prezime" -> text = entity.getPrezime();
            case "adresa" -> text = entity.getAdresa();
            case "telefon" -> text = entity.getTelefon();
            case "email" -> text = entity.getEmail();
            default -> {}
        }
        return text;
    }
    
}
