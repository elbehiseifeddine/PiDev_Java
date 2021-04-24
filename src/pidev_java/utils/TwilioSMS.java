///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pidev_java.utils;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class TwilioSMS {
//    
//    public String ACCOUNT_SID = "ACb853270e5a4dad385925b874a26c382b";
//    public String AUTH_TOKEN = "5358f74afbbfcf0dbeb9396a7c8b6caf";
//
//    public TwilioSMS (){
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//    }
//    
//    public void  sendSMS (String msgText, String sendTo){
//        Message message = Message.creator(new PhoneNumber(sendTo),
//        new PhoneNumber("+16672550913"), 
//        msgText).create();
//        
//        System.out.println(message.getSid());
//    }
//    
//    
//    
//    
//    
//}
