import javax.swing.*;
import java.util.Random;
public class otherStock extends Stock
{
     
    public otherStock(String S, double V)// initialise superclass instance variables..
    {
        super(S,V);
        
        
    }
    public void updateValue()// override superclass method..
     {
     super.updateValue();
     Random random=new Random();
     double randomNumber=(random.nextInt(10));
     double dy= randomNumber-6.0;
     
      if(getValue()+dy>0)
      {
        setValue(getValue()+dy);
     
      }
    }
}
