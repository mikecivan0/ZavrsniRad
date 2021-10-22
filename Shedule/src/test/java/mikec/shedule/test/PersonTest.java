///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mikec.shedule.test;
//
//import mikec.shedule.controller.PersonController;
//import mikec.shedule.model.Person;
//import mikec.shedule.util.BaseException;
//import org.junit.Assert;
//import org.junit.Test;
//
///**
// *
// * @author Ivan
// */
//public class PersonTest {
//    
//    private PersonController personController;
//    private Person person;
//    public PersonTest() throws BaseException {
//        personController = new PersonController();
//        person = new Person();
//        prepareData();
//    }
//
//    private void prepareData() {
//        
//        person.setFirstName("f");
//        person.setLastName("l");
//        person.setPhoneNr("ph");
//        person.setAddress("a");
//        person.setEmail("e");
//        personController.setEntity(person);
//    }
//    
//    @Test
//    public void create() throws BaseException{        
//        person = personController.create();
//        Assert.assertEquals(person.getFirstName(), personController.getEntity().getFirstName());
//    }
//    
//    
//    
//}
