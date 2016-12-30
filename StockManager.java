public class StockManager // Controls all stocks and values...
{
    private Stock[] stocks;
    
    public StockManager() // Store all stock symbols and values..
    {
        stocks = new Stock[] // create new Objects for stocks...
        {
            new otherStock("BAC",13.65),
            new energyStock("CHK",5.10),
            new techStock("ACAL",255.00),
            new techStock("BANGO",45.50),
            new techStock("EMIS",1069.00),
            new otherStock("FASTJET",40.75),
            new otherStock("NEXT",6650.00),
            new energyStock("LEKOIL",14.75),
            new techStock("KROMEK",32.75),
            new Stock("TASTY",160.00)
        };
    }
    public Stock getStock(String symbol) // checks if symbol is a Stock..
    {
        for (int i = 0; i < stocks.length; i++) {
            Stock s = stocks[i];
            
            if (s.getSymbol().equalsIgnoreCase(symbol)) {
                return s;
            }
        }
        return null; // not found
    }
    
    public Stock[] getStocks() 
    {
        return stocks;
    }
}
