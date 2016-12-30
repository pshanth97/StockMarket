import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager.*;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GUI extends Frame
{
    private StockManager stockManager;
   
    public GUI()//constructor
    {
        init();// call method
    }
    
    private void init() {
       Person p1= new Person(5000); // create an object of Person with Cash 5000...
       stockManager= new StockManager();// create a new StockManager to manage stocks..
        
       Stock[] stocks= stockManager.getStocks();// create an array with stocks..
       
       showStock st= new showStock();// create a new showStock for OwnedStock..
       
       // step 1a: set frame style
       setTitle("Stock Market"); // (show in title bar")
       setSize(800, 800); // (int width, int height)
        
        // step 1b: add frame listeners - i.e. frame behaviours
        addWindowListener(new WindowAdapter() 
        {
           public void windowClosing(WindowEvent e) {
               System.exit(0); // quit application, if X is pressed
              
           }
        });
        
        // step 2a: create elements
        
        TextArea details = new TextArea(30, 50);  // create text areas with size of area..
        TextArea details2 = new TextArea(30, 45); // create text areas with size of area..
        TextArea owned= new TextArea(10,60);      // create text areas with size of area..
        details.setEditable(false);      // make the text area non-editable
        details2.setEditable(false);     // make the text area non-editable
        owned.setEditable(false);        // make the text areas non-editable
        
        Label owned2= new Label("Owned Stock:");  // create a label and name it
        owned2.setFont(new Font("Serif", Font.PLAIN, 30));// set font and size of label
        
        
        Label cas= new Label();// create a label
        cas.setText("Cash: £"+ p1.getCash()); // set text in label to cash available..
        cas.setFont(new Font("Serif", Font.PLAIN, 30));// set label font and size
        add(cas); // add label
        
        
        TextField t1= new TextField(10); // create a textfield and set size
        Label b2= new Label("BUY");      // add a label and name it
        setLayout(new FlowLayout());
        add(b2); // addlabel 
        add(t1); // add textfield 
        
        Label lblAmount = new Label("Amount"); // new label
        TextField txtAmount = new TextField(10); // new textfield
        add(lblAmount); // add label
        add(txtAmount); // add textfield
        
      t1.addActionListener(new ActionListener() // add action listener for textfield
      {
          public void actionPerformed(ActionEvent e)
          {
              // first, we get name of stock and amount
              String symbol = t1.getText();
              String amountTxt = txtAmount.getText();
              
              // now convert them
              int amount = Integer.parseInt(amountTxt);
              
              // test amount
              if (amount > 0)
              {
                  // now try get stock
                  Stock s = stockManager.getStock(symbol);
                  Stock[] stocks = stockManager.getStocks();
                  if (s == null) 
                  {
                      details.append("Invalid symbol!!!\n");
                  } 
                  else 
                  {
                      // fine
                      double cost = s.getValue() * amount;
                      double cash = p1.getCash();
                      
                      if (cash >= cost) 
                      {
                         p1.setCash(cash - cost);
                         p1.addStock(s, amount);
                         details.append("You have bought " + amount + " shares of "+ s.getSymbol());
                         details.append("\n");
            
                         // update cash in label
                         cas.setText("Cash: £" + p1.getCash());
                         
                         String own=st.getOwned(p1,stocks);
                         owned.setText(own);
                         
                         
                      }
                      else 
                      {
                          details.append("You dont have enough funds!!!\n");
                      }
                  }
             } 
             else
             {
                  // bad amount
                  details.append("Invalid amount!!!\n");
             }
          }
      });
      
      txtAmount.addActionListener(new ActionListener()// add ActionListener for textfield...
      {
          public void actionPerformed(ActionEvent e)
          {
              // first, we get name of stock and amount
              String symbol = t1.getText();
              String amountTxt = txtAmount.getText();
              Stock[] stocks = stockManager.getStocks();
              // now convert them
              int amount = Integer.parseInt(amountTxt);
              
              // test amount
              if (amount > 0)
              {
                  // now try get stock
                  Stock s = stockManager.getStock(symbol);
                  
                  if (s == null) 
                  {
                      details.append("Invalid symbol!!!\n");
                  } else 
                  {
                      // fine
                      double cost = s.getValue() * amount;
                      double cash = p1.getCash();
                      
                      if (cash >= cost) 
                      {
                          p1.setCash(cash - cost);
                          p1.addStock(s, amount);
                          details.append("You have bought " + amount + " shares of "+ s.getSymbol());
                          details.append("\n");
            
                           // update cash
                           cas.setText("Cash: £" + p1.getCash());
                          
                         String own=st.getOwned(p1,stocks);
                         owned.setText(own);
                      }
                       else 
                      {
                          details.append("You dont have enough funds!!!\n");
                      }
                  }
              } 
              else 
              {
                  // bad amount
                  details.append("Invalid amount!!!\n");
              }
          }
      });
      
        TextField t2= new TextField(10);// create a textfield..
        Label b3= new Label("SELL");// create label
        setLayout(new FlowLayout());
        Label sAmount= new Label("Amount");// create label
        TextField sText= new TextField(10);// create textfield
        
        
      t2.addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              // first, we get name of stock and amount
             
              String symbol = t2.getText();
              String amountTxt = sText.getText();
              Stock[] stocks = stockManager.getStocks();
              OwnedStock stock = p1.getStock(symbol);
              
              // now convert them
              double amount = Integer.parseInt(amountTxt);
              
              
              Stock s = stockManager.getStock(symbol);
              
              // test amount
              
              if (s!=null)// check if symbol exists
              {
                  if(stock.getAmount()>=amount&&amount>0)// enough funds to sell
                  {
                      p1.removeStock(symbol,amount);// remove from OwnedStock
                      details.append("You have sold "+amountTxt+" shares of "+symbol+"\n");
                      double cost= s.getValue() * amount;
                      p1.setCash(p1.getCash()+cost);
                      cas.setText("Cash: £" + p1.getCash());
                      double a = stock.getAmount()-amount;
                      stock.setAmount(a);
                      
                      String own=st.getOwned(p1,stocks);//update OwnedStock
                      owned.setText(own);
           
             
                  }        
                  else
                  {
                      details.append("You do not have enough shares to sell\n");
                  }
                  
              }
            
               else 
              {
                  // bad amount
                  details.append("Invalid!!!\n");
              }
         } 
      });
      
      sText.addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              // first, we get name of stock and amount
             
              String symbol = t2.getText();
              String amountTxt = sText.getText();
              Stock[] stocks = stockManager.getStocks();
              OwnedStock stock = p1.getStock(symbol);
              
              // now convert them
              double amount = Integer.parseInt(amountTxt);
              Stock s = stockManager.getStock(symbol);
              
              // test amount
              
              if (s!=null)
              {
                  if(stock.getAmount()>=amount&&amount>0)
                  {
                      p1.removeStock(symbol,amount);
                      details.append("You have sold "+amountTxt+" shares of "+symbol+"\n");
                      double cost= s.getValue() * amount;
                      p1.setCash(p1.getCash()+cost);
                      cas.setText("Cash: £" + p1.getCash());
                      double a = stock.getAmount()-amount;
                      stock.setAmount(a);
                      
                      String own=st.getOwned(p1,stocks);
                      owned.setText(own);
                  }
                  else
                  {
                      details.append("You do not have enough shares to sell\n");
                  }
                  
              }
            
               else {
                  // bad amount
                  details.append("Invalid!!!\n");
              }
          }
      });
           
        Button NEXTDAY = new Button("NEXTDAY");
        NEXTDAY.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
              // now show new values
                details2.append("Stock:\n");
                Stock[] stocks = stockManager.getStocks();
                
                // go over all stocks
                for (int i = 0; i < stocks.length; i++) 
                {
                    // get name and value
                    String symbol = stocks[i].getSymbol();
                    stocks[i].updateValue();// update values of stocks...
                    double value=stocks[i].getValue();// get value of stocks...
                    
                    // now add to list
                    details2.append("Symbol: " + symbol + " at the price of £" + value + "\n");
                }
                details2.append("\n");// new line
                
                details.append("\n");// new line
                
                String own=st.getOwned(p1,stocks);// get OwnedStocks..
                owned.setText(own); 
            }
        });
        
      
        // step 2b: add elements to frame
        Panel p = new Panel(); // create a new Panel
        Panel p2 = new Panel();// create a new Panel
        Panel p3 = new Panel();// create a new Panel
        // add everything to panel
        p.add(b3);
        p.add(t2);
        p.add(sAmount);
        p.add(sText);
        p.add(NEXTDAY, BorderLayout.PAGE_END);
        
        p3.add(details2);
        p3.add(details);
        
 
        // add panel to the frame
        add(p);
        add(p2);
        add(p3);
        
        // put start stock values in box
        details2.append("Stock:\n");
        
        
        // go over all stocks
        for (int i = 0; i < stocks.length; i++) 
        {
            // get name and value
            String symbol = stocks[i].getSymbol();
            stocks[i].updateValue();
            double value=stocks[i].getValue();
            
            
            // now add to list
            details2.append("Symbol: " + symbol + " at the price of £" + value + "\n");
        }
        details2.append("\n");       
        // go over all stocks
        
        String own=st.getOwned(p1,stocks);
        owned.setText(own);
        
        add(owned2);// add label
        add(owned);// add textarea
        
        Button save= new Button("Save");// new button
        add(save);// add to frame
        
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e4)
            { 
                
                try 
                {
                    PrintWriter out = new PrintWriter("Ostock.txt", "UTF-8");// file of SavedFile.txt
                    PrintWriter out2 = new PrintWriter("Cstock.txt", "UTF-8");
                    PrintWriter out3 = new PrintWriter("Mstock.txt", "UTF-8");
                    
                    out.println(owned.getText());
                    out2.println(details.getText());
                    out3.println(Double.toString(p1.getCash()));// prints line of String to file
                    out.close();// close the PrintWriter...
                    out2.close();
                    out3.close();
                    
                }
                catch(IOException e1)
                {
                    e1.printStackTrace();
                }
                
            }
        });
        
        Button load= new Button("Load");// new button
        add(load);// add to frame
        
        load.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e3)
            {  
               try {
                
               
                String line=null;
                String line2=null;
                String line3=null;
                
                BufferedReader bufferedReader= new BufferedReader(new FileReader("Ostock.txt"));
                // bufferedReader to read from file...
                while((line= bufferedReader.readLine()) !=null)
                {
                   line= line+"\n";      
                   owned.append(line);
                   ArrayList<String[]> list= new ArrayList<String[]>();// new ArrayList to store lines.
                  try
                  {
                       
                    while((line=bufferedReader.readLine())!=null)
                    {
                       
                       list.add(line.split(" "));
                    }
                    String[]sa= list.toArray(new String[list.size()]); 
                    
                       
                    int[] ownedF=new int[sa.length/4];
                    for(int a=0; a<=ownedF.length;a++)
                    {
                       sa[a]=sa[a+4];
                       ownedF[a]=ownedF[a+1];
                       
                       
                    }
                    for(int a=1; a<ownedF.length;a++)
                    {
                       
                       
                       ownedF[a]=Integer.parseInt(sa[a*4]);
                       
                    }
                   
                    
                    String[]ba= list.toArray(new String[list.size()]);
                    String[] names= new String[ba.length/4];
                    for(int b=0; b<=names.length;b++)
                    {
                       ba[b]=ba[b+1];
                       names[b]= names[b+1];
                       
                       
                    }
                    for(int b=1; b<names.length;b++)
                    {
                       
                     names[b]= ba[4*b];
                       
                    }
                   
                    for (int c=0;c<names.length;c++)
                    {
                       Stock s = stockManager.getStock(names[c]);
                       p1.addStock(s,ownedF[c]);
                    } 
                  
                  }
            
                    catch(ArrayIndexOutOfBoundsException e)
                   {
                     e.printStackTrace();
                   }
                    catch(NumberFormatException e)
                   {
                     e.printStackTrace();
                   }
                    catch(NullPointerException e)
                   {
                     e.printStackTrace();
                   }
                    catch(ArrayStoreException e)
                   { e.printStackTrace();
                  }
               }
                
                bufferedReader.close();
                
                BufferedReader bufferedReader2= new BufferedReader(new FileReader("Cstock.txt"));
                while((line2= bufferedReader2.readLine()) !=null)
                {
                   line2= line2+"\n";      
                   details.append(line2);
                }
                
                bufferedReader2.close();
                
                BufferedReader bufferedReader3= new BufferedReader(new FileReader("Mstock.txt"));
                while((line3= bufferedReader3.readLine()) !=null)
                {
                    
                        
                   p1.setCash(Double.parseDouble(line3));
                   cas.setText("Cash: £"+ p1.getCash());
                   
                }
                
                bufferedReader3.close();
           }
            catch(ArrayIndexOutOfBoundsException e)
           {
              e.printStackTrace();
           }
            catch(NumberFormatException e)
           {
              e.printStackTrace();
           }
            catch(FileNotFoundException e)
           {
              e.printStackTrace();
           }
            catch(IOException e)
           {
              e.printStackTrace();
           }
            catch(ArrayStoreException e)
           {  
              e.printStackTrace();
           }
        }
       });
    }
    
    public static void main(String[]args)
    {
        GUI ui = new GUI(); 
        // we call the constructor, to set it up
        ui.setResizable(false);// set frame to to setSize...
        ui.setVisible(true);// set window visible
    }
}



        

    
    
   


