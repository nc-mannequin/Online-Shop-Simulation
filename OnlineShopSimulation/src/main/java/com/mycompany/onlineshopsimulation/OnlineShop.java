
/*
    PUTTIMAIT   VIWATTHARA      6213130
    NAPAT       CHEEPMUANGMAN   6213200
 */

import java.io.*;
import java.util.*;

class Product implements Comparable<Product>{
    private Scanner fileFinder;
    private String filename;
    private String name;
    private double price;
    private double weight;
    private double totalSalesInCash;
    private double totalSalesInUnits;
    public int compareTo(Product other){
    if(this.totalSalesInCash>other.totalSalesInCash){return -1;}
    else{return 1;}
    }
    public void reserve(int quantity){totalSalesInCash+=(price*quantity); totalSalesInUnits+=quantity; } 
    public double gettotalSalesInCash(){return totalSalesInCash;}
    public double gettotalSalesInUnits(){return totalSalesInUnits;}
    public String getName(){return name;}
    public double getPrice(){return price;}
    public double getWeight(){return weight;}
    public Product(String n,double p,double w,double tsc,double tsu){name=n;price=p;weight=w;totalSalesInCash=tsc;totalSalesInUnits=tsu;}
    public Product(String fn){filename=fn; fileFinder= new Scanner(System.in);}
    public void LoadTo(ArrayList<Product> ALP){
        boolean Loaded=false;
        while(!Loaded){
        try(Scanner input = new Scanner(new File(filename)))
        {
            while(input.hasNext())
            {
                String line = input.nextLine();
                String[] buf = line.split(",");
                ALP.add(new Product(buf[0],Double.parseDouble(buf[1].trim()),Double.parseDouble(buf[2].trim()),0,0) );
            }
            Loaded=true;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
            System.out.println("New Product file name : ");
            filename = fileFinder.next();
        }
        }
    }
}

class Postages{
    private String filename;
    private Scanner fileFinder;
    private String type;
    private double min_weight;
    private double max_weight;
    private double rate;
    public double getMin(){return min_weight;}
    public double getMax(){return max_weight;}
    public double getRate(){return rate;}
    public Postages(String t,double minw,double maxw,double r){type=t; min_weight=minw; max_weight=maxw; rate=r;}
    public Postages(String fn){filename =fn; fileFinder = new Scanner(System.in);}
    public void LoadTo(ArrayList<Postages> ALP){
        boolean Loaded=false;
        while(!Loaded){
        try(Scanner input = new Scanner(new File(filename)))
        {
            while(input.hasNext())
            {
                String line = input.nextLine();
                String[] buf = line.split(",");
                if(buf[2].compareToIgnoreCase(" inf")!=0) {ALP.add(new Postages(buf[0],Double.parseDouble(buf[1].trim()),Double.parseDouble(buf[2].trim()),Double.parseDouble(buf[3].trim())) ); }//case number
                else {ALP.add(new Postages(buf[0],Double.parseDouble(buf[1].trim()), Double.POSITIVE_INFINITY ,Double.parseDouble(buf[3].trim())) ); }
            }
            Loaded=true;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
            System.out.println("New Postages file name : ");
            filename = fileFinder.next();
        }
        }
    }
}

class Customers implements Comparable<Customers>{
    private String filename;
    private Scanner fileFinder;
    private String name;
    private String type;
    private int[] orders={0,0,0,0,0};
    private double totalBill;
    private double totalWeight;
    private double freight;
    public int compareTo(Customers other)
    {
        if(this.totalBill>other.totalBill){return -1;}
        else {return 1;}
    }
    public void transport_fee(ArrayList<Postages> ALP){
        int i;
        if(this.type.equals(" E")){i=0;}
        else{i=5;}// R
        for(;i<10;++i)
            {
                if(this.totalWeight>ALP.get(i).getMin()&&this.totalWeight<=ALP.get(i).getMax())
                {
                    freight=ALP.get(i).getRate();
                    break;
                }
            }
    }
    public void buy(Product x,int quantity){totalBill+=(x.getPrice()*quantity); totalWeight+=(x.getWeight()*quantity);}
    public double gettotalBill(){return totalBill;}
    public double gettotalWeight(){return totalWeight;}
    public int getOrders(int o){return orders[o];}
    public String getName(){return name;}
    public double getfreight(){return freight;}
    //public Customers(){}
    public Customers(String n,String t,int O0,int O1,int O2,int O3,int O4,double tb,double tw,double f)
    {
        name=n; type=t; orders[0]=O0; orders[1]=O1; orders[2]=O2; orders[3]=O3; orders[4]=O4; totalBill=tb; totalWeight=tw; freight=f;
    }
    public Customers(String fn){filename=fn; fileFinder=new Scanner(System.in);}
    
    public void processLine(String line,ArrayList<Customers> ALC){
        System.out.println("\n" + line);
        String[] buf = line.split(",");
        String[] Tbuf= {"Default_Name"," R"," 0"," 0"," 0"," 0"," 0"};
        for(int i=0;i<buf.length;++i){Tbuf[i]=buf[i];}
        if(buf.length != Tbuf.length) System.out.println("\tThis order isn't complete.");
        boolean Finished = false;
        int check = 0;
        while (!Finished)
        {
        try {
            String name = Tbuf[0];
            String type = Tbuf[1];
            if (!(type.equals(" E") || type.equals(" R"))) {
                Tbuf[1] = " R";
                throw new Exception("WrongType");
            }
            int Order1 = Integer.parseInt(Tbuf[2].trim()); check ++;
            int Order2 = Integer.parseInt(Tbuf[3].trim()); check ++;
            int Order3 = Integer.parseInt(Tbuf[4].trim()); check ++;
            int Order4 = Integer.parseInt(Tbuf[5].trim()); check ++;
            int Order5 = Integer.parseInt(Tbuf[6].trim()); check ++;
            if (!(Order1 >= 0 && Order2 >= 0 && Order3 >= 0 && Order4 >= 0 && Order5 >= 0)) {
                if (Order1 < 0) Order1 *= -1;
                if (Order2 < 0) Order2 *= -1;
                if (Order3 < 0) Order3 *= -1;
                if (Order4 < 0) Order4 *= -1;
                if (Order5 < 0) Order5 *= -1;
                System.out.println("\tNegative Quantity Not Allow, But We've Changed It For you. <3");
            }
            Finished = true;
            System.out.println("\n Input      : " + line);
            System.out.println(" Correction : " + name + " " + type + "  " + Order1 + "  " + Order2 + "  " + Order3 + "  " + Order4 + "  " + Order5);
            ALC.add(new Customers(name,type,Order1,Order2,Order3,Order4,Order5, 0, 0, 0));
        } catch (RuntimeException e) {
            
            System.out.println("\t" + e);
            System.out.println("\tThis line has been edited.");
            Tbuf[2+check] = "0";
        } catch (Exception x) {

            System.out.println("\t" + x);
            System.out.println("\tThis postage has been edited To Regular.");
        }
        }
    }
    
    public void LoadTo(ArrayList<Customers> ALC){
    boolean Loaded=false;
    while(!Loaded){
        try(Scanner input = new Scanner(new File(filename)))
    {
        while(input.hasNext())
        {
            processLine(input.nextLine(),ALC);
        }
        Loaded=true;
    }
    catch(FileNotFoundException e)
    {
        System.out.println(e);
        System.out.println("New Customer file name : ");
        filename = fileFinder.next();
    }
    }
    }
    
}

public class OnlineShop {
    public static void main(String[] args)
    {
        ArrayList<Product> ProductArray = new ArrayList<Product>();
        ArrayList<Postages> PostagesArray = new ArrayList<Postages>();
        ArrayList<Customers> CustomersArray = new ArrayList<Customers>();
        Product P = new Product("products.txt");
        Postages Po = new Postages("postages.txt");
        Customers C = new Customers("Nofile.txt");
        P.LoadTo(ProductArray);
        Po.LoadTo(PostagesArray);
        C.LoadTo(CustomersArray);

        //Begin Process
        System.out.printf("-----Process order-----\n");
        
        for(Customers c : CustomersArray)
        {
            
            System.out.printf("%-20s >> ",c.getName());
            for(int i=0;i<5;++i)
            {
                System.out.printf("%-20s : %3d   ", ProductArray.get(i).getName(),c.getOrders(i));//Normal Case
                c.buy(ProductArray.get(i), c.getOrders(i));
                ProductArray.get(i).reserve(c.getOrders(i));
                
            }//Bought All Orders
            c.transport_fee(PostagesArray);
            System.out.printf("\n                        product price = %7.0f   total weight = %7.0f\n",c.gettotalBill(),c.gettotalWeight());
            System.out.printf("                        postage (E)   = %7.0f   total bill   = %7.0f\n\n",c.getfreight(),c.gettotalBill()+c.getfreight());
        }
        //End Process
        
        //Begin Sorting
        System.out.printf("----- Sort customers by total bill -----\n");
        Collections.sort(CustomersArray);
        for(Customers c : CustomersArray)
        {
            System.out.printf("%-20s   bill = %6.0f\n",c.getName(),c.gettotalBill());
            
        }
        
        System.out.printf("\n----- Sort products by total sales in cash -----\n");
        Collections.sort(ProductArray);
        for(Product p : ProductArray)
        {
            System.out.printf("%-30s   total sales = %6.0f B,   %3.0f units\n",p.getName(),p.gettotalSalesInCash(),p.gettotalSalesInUnits());
        }
        //End Sorting
    }
}
