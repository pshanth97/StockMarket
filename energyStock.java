import javax.swing.*;
import java.util.Random;
public class energyStock extends Stock
{
   
    public energyStock(String S, double V)// initialise  superclass instance variables
    {
        super(S,V);
       
    }
    public void updateValue()// override method in superclass
    {
     super.updateValue();
     Random random=new Random();
     double randomNumber=(random.nextInt(20));
     double dy= randomNumber-11.0;
     
      
      if(getValue()+dy>0)
      {
        setValue(getValue()+dy);
      
      }
   
    }
}
