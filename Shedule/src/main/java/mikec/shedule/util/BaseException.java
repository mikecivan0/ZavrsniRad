/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.util;

/**
 *
 * @author Ivan
 */
public class BaseException extends Exception{
    
    private String message;

    public BaseException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }   
    
}
