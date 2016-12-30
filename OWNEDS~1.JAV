public class OwnedStock 
{
    private Stock s;
    private double amount;
    
    public OwnedStock(Stock s1, int amount1) // stores stocks and amount of shares..
    {
        s = s1;
        amount = amount1;
    }
    
    public Stock getStock() 
    {
        return s;
    }
    
    public double getAmount() 
    {
        return amount;
    }
    
    public void setAmount(double a) 
    {
        amount = a;
    }
}
