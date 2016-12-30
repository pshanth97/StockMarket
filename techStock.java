import javax.swing.*;
import java.util.Random;
public class techStock extends Stock
{
    
    public techStock(String S, double V)// initialise superclass instance variables..
    {
        super(S,V);
        
        
    }
    public void updateValue()// override superclass method..
     {
     super.updateValue();
     Random random=new Random();
     double randomNumber=(random.nextInt(50));
     double dy= randomNumber-35.0;
     
      if(getValue()+dy>0)
      {
      setValue(getValue()+dy);
      
      }
    }
}
