import javax.swing.*;
import java.util.ArrayList;

public class Person
{
   private ArrayList<OwnedStock> stocks = new ArrayList<>();// Create an ArrayList of type OwnedStock...
   private double cash;

    public Person(double C)
    {
        cash=C;
    }
    public double getCash()
    {
        return cash;
    }
    public void setCash(double mon){
        cash=mon;
    }
    
    public void addStock(Stock s, int amount) // method to check and add stocks to OwnedStock ArrayList..
    {
        OwnedStock find = getStock(s.getSymbol()); //check if Stock s exists...
        
        if (find != null) { // update amount
            find.setAmount(find.getAmount() + amount);
        } else { // add to stocks..
            stocks.add(new OwnedStock(s, amount));
        }
    }
    
    public void removeStock(String s, double amount) // method to removeStock when sold..
    {
        for (int i = 0; i < stocks.size(); i++) {
            OwnedStock stock = stocks.get(i);// get names of stocks using a for loop...
            if(s.equals(stock))//check if stock is owned....
            {
                stocks.remove(i);// remove from stocks..
                
                
            }
        }
        
    }
    
    public OwnedStock getStock(String symbol) // method check whether symbol is a stock..
    {
        for (int i = 0; i < stocks.size(); i++) {
            OwnedStock stock = stocks.get(i);// get names of stocks from a for loop...
            
            if (stock.getStock().getSymbol().equalsIgnoreCase(symbol))
                return stock;// if exist then return stock..
        }
        return null;// if it does not exist return null..
    }
    
    public ArrayList getstock()
    {
        return stocks;
    }
}