import javax.swing.*;
import java.util.ArrayList;
public class showStock
{
    public  String getOwned(Person p1,Stock []stocks)// method to get all OwnedStocks..
    {
         ArrayList<OwnedStock> stock2= p1.getstock();// get OwnedStock ArrayLisr
         String own="";
         StockManager m= new StockManager();// create new StockManager to check for stocks..
         for (int i = 0; i < stock2.size(); i++)
         {
             // get name and value
              OwnedStock stock = stock2.get(i);
              Stock symbol= stock.getStock();
              String symbol1=symbol.getSymbol();
              
              if(stock!=null){
              double value = stock.getAmount();
              // get amount of shares owned
              if(value>0){
               own=own+(value+" shares of "+symbol1+"\n");
               // create a builder for all Owned Stock
            }
            }
            else{}
         }
         
         return own;
    }
    }
   
    
   


    