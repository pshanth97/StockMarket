import javax.swing.*;
import java.util.Random;
 public class Stock
 {
     private double value;
     private String symbol;
     public Stock(String S,double V)
     {
       value=V;
       symbol=S;
     }
     public double getValue()
     {
         return value;
     }
     public void setValue(double a)
     {
         value=a;
     }
     public String getSymbol()
     {
        return symbol;
     }
     public void updateValue()// method to update values of the stocks which is done randomly..
     {
     
     Random random=new Random();
     double randomNumber=(random.nextInt(10));// get a randomNumber
     double dy= randomNumber-5.5;// and subtract from the number
     if(getValue()+dy>0)// check the value of the stock will not be negative..
     {
      setValue(getValue()+dy);// if positive then update value of stock..
      
     }
     
     }
 }