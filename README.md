## _Online Shop Simulation_
The data of this store were missing or erroring, so we have to handle all errors.


___
 
 
 
## _Creator_
- PuttiLos (https://github.com/PuttiLos)
- nc-mannequin (https://github.com/nc-mannequin)
 
 
___
 
 
 
## _Requirements_
1) The data in file products.txt, Each line contains product name, price, and weight. Implement class Product, with at least the following
- Variables name, price, weight : values are read from each line of products.txt
- Variables totalSalesInCash and totalSalesInUnits : keep the total sales of each product
- Add more variables and methods as needed
2) The data in file postages.txt. Each line contains type (“E” for EMS, “R” for registered), min weight (exclusive), max weight (inclusive), and rate. Implement class Postage, with at least the following
- Variables type, minWeight, maxWeight, rate : values are read from each line of postages.txt
- Add more variables and methods as needed
3) The data in file customers.txt containing customer names, postage type, and number of each product he/she orders. There must be 10 lines in this file. Implement class Customer, with at least the following
- Variables name, orders (array or ArrayList), type : values are read from each line of customers.txt
- Variables totalBill and totalWeight : keep total bill and total weight of the products he/she orders
- Add more variables and methods as needed
4) Implement class OnlineShop as the main class. When the program starts
- 4.1 Read data from products.txt into array or ArrayList of Products
- 4.2 Read data from postages.txt into array or ArrayList of Postages
- 4.3 Read data from customers.txt into array or ArrayList of Customers
- 4.4 Process each customer’s order
  - Get postage price from total weight and type
  - Calculate total bill from product prices and postage price
  - Print output with at least : total product price & weight, postage price, and total bill
- 4.5 Print summary reports
  - Total bills of all customers sorted in either increasing or decreasing order of the bills
  - Total sales of all products sorted in either increasing or decreasing order of the sales (either in cash or in unit)
5) The program must be able to handle the following errors/exceptions
- 5.1 Missing files
- 5.2 File customers.txt may contain input errors. You may use 2 separate files, one with clean input and another with errors. You can handle these errors by: skipping the whole line (values from this transaction are not added to customer’s points & menu’s total dishes), replacing them with some default values, etc.
  - Missing values, e.g. only 4 products instead of 5 products
  - Format errors, e.g. “O (oh)” instead of “0 (zero)”
  - Invalid numbers, e.g. -5 for number of products
- 5.3 Handling the above errors/exceptions means your program must be able to continue and give correct output. Printing messages and ending the program doesn’t count as proper exception handling.
6. In summary, your program must have
- Files : products.txt, postages.txt, customers.txt with your own data (10 lines)
- Classes : Product, Postage, Customer, OnlineShop (main class)
___
You can make two files of customers.txt, one is correctly (do not have to handle) and the other one is wrong (have to handle).
## ** Don’t hard code !!! ** ##
