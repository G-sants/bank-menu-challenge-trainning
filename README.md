# bank-menu-challenge-training
 Just a model of a Bank App menu for interaction and transactions

############################################################################################################################

How to Use it:

First:
Choose one of the options in between: 
 -creating an account;
 -viewing your account;
 -exiting the app;
 
Second:
 If you choose to create it, then just put a Personal ID (it got to be 11 numbers);
 Than type your Name
 and then select the type of account you want to create:
  -Checking;
  -Salary;
  -Saving;

 Third:
  Now you will be back to the main menu, if you want to create a new account (recommend if you want to make transference's in between them)
if you don't, them just select to enter the application.
 In here you can choose different option:
  1- Make a deposit to it;
   Can be made with any value.
   
  2- Cash out;
   Can only be made if the value being taken out is not greater than the value the user has.
   
  3- Transfer;
   Can only be made if the value being taken out is not greater than the value the user has.
   
  4- View/Change Limit;
   Limit changes are made due to history in transactions and will be implemented by asking but the values are not decided by the user,
  the application will evaluate how much can you expand your limit using your transaction history.
  
  5 - View/Export Transaction History;
   Shows every transaction made since the account was created or create an .CSV file with all of it.
   
  6 - Exit the User Menu;
  ############################################################################################################################

OBSERVATIONS
 The code does not preserve anything, so once closed it will delete every information created.

############################################################################################################################

DIAGRAM 

```mermaid
flowchart TB

    subgraph Bank Menu
        LogIn
        SignUp
        Exit
    end
    subgraph Bank Info
        User_List
        ID_Validation
    end
    subgraph LoggedIn Menu
        Deposit
        Cash_Out
        Transfer
        View/Change_Limit
        View/Export_History
        Exit_Menu
    end
    subgraph User Info
        ID
        Name
        Account
        Account_Type
    end

    subgraph User
        Balance
        Limit
        Transaction_History
        c
    end

    LogIn -.-> |Type ID| User_List
    User_List -.-> Logging_In

    Exit --> |Close Application| End

    SignUp -->|Type ID| ID_Validation
    ID_Validation --> |Type Name| User_List

    Logging_In --> Info(Display User Informations)


    Info --> Name
    Info --> ID
    Info --> Account
    Info --> Account_Type

    Account --> om(User Menu)
    Name --> om(User Menu)
    ID --> om(User Menu)
    Account_Type --> om(User Menu)

    om -->Deposit
    om -->Cash_Out
    om -->Transfer
    om --> View/Change_Limit
    om --> View/Export_History
    om --> Exit_Menu

    Deposit --> |Adds Amount to Balance| Balance
    Cash_Out --> |Subtracts Amount to Balance| Balance
    Transfer --> |Subtracts Amount to Balance| Balance

    View/Change_Limit --o vL(view)-->|Shows up| Limit
    View/Change_Limit-.-o cL(change)-.->|search history| Transaction_History
    Transaction_History -.-> |Looks for amount of money moved|c
    c{change} -.->|Update and Shows Up| Limit

    View/Export_History --> nL(view) --> |Shows Up| Transaction_History
    View/Export_History --> eH(Export) --> |Creat .CSV File| Download

    Exit_Menu -->|Close Users Menu|Return_Bank_Menu;
```
