import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TestStock extends StockManager
{
    public static void main(String[]args)
    {
        Person p1= new Person(5000);
        
        Stock stock1= new Stock("BAC",13.65);
        Stock stock2= new Stock("CHK",5.10);
        Stock stock3= new Stock("FCX",10.20);
        
        
        
    }
    public Stock[] getStocks()
    {
        super.getStocks();
        StockManager m= new StockManager();
        return m.getStocks();
    }
}
    