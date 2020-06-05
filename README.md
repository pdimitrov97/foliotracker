# Foliotracker
This is a design and implementation of a Folio Tracker - an application for tracking the value of stock portfolios with a GUI.

## Description
The program supports the following features:
- Manages multiple portfolios.
- Create and delete portfolios.
- See the positions (stock name, number of shares held, price per share, and value of the holding) in a portfolio.
- Add and remove stocks to the portfolio.
- Increase and decrease the number of shares of a stock.
- Automatic and manual refresh of the stock prices.
- See the total value of a portfolio.
- Saving portfolio information to disk and loading old portfolios.
- Indicates whether price is going up or down.
- Sorting positions by value, ticker symbol, etc.

The stock prices are obtained from http://www.tickertech.com/

## How to use it
The program has a GUI.  When you first launch the program you will see this screen:

<img src="https://user-images.githubusercontent.com/15669909/83022819-c0b43300-a034-11ea-8834-48acca07f7ac.png" width=40%>

Creating a portfolio:

<img src="https://user-images.githubusercontent.com/15669909/83023777-0fae9800-a036-11ea-873c-610cfa4cc34b.png" width=40%> <img src="https://user-images.githubusercontent.com/15669909/83023834-21903b00-a036-11ea-8a43-55e18846a2b7.png" width=40%>

<img src="https://user-images.githubusercontent.com/15669909/83023976-513f4300-a036-11ea-9b07-1c1376bc00d1.png" width=40%> <img src="https://user-images.githubusercontent.com/15669909/83024020-60be8c00-a036-11ea-9705-e9a557cff852.png" width=40%>

Adding a folio:

<img src="https://user-images.githubusercontent.com/15669909/83024336-ea6e5980-a036-11ea-9923-391d6c58044b.png" width=40%> <img src="https://user-images.githubusercontent.com/15669909/83024348-efcba400-a036-11ea-8640-5b6bc9c14012.png" width=40%>

<img src="https://user-images.githubusercontent.com/15669909/83024367-f5c18500-a036-11ea-8655-73aba5e75f61.png" width=40%>

Switching between folios:

<img src="https://user-images.githubusercontent.com/15669909/83024549-3b7e4d80-a037-11ea-9bd6-805325dd4a3f.png" width=40%>

Adding a stock:

<img src="https://user-images.githubusercontent.com/15669909/83024599-4e911d80-a037-11ea-94e7-ba6bab81f6d7.png" width=40%> <img src="https://user-images.githubusercontent.com/15669909/83024623-551f9500-a037-11ea-836f-4656697aa480.png" width=40%>

Save a portfolio:

<img src="https://user-images.githubusercontent.com/15669909/83024863-7c766200-a037-11ea-9570-d48b36c9093e.png" width=40%>

Rename a folio:

<img src="https://user-images.githubusercontent.com/15669909/83025198-944de600-a037-11ea-9962-e4c56690adab.png" width=40%> <img src="https://user-images.githubusercontent.com/15669909/83025300-9ca62100-a037-11ea-9597-ea222725d2f0.png" width=40%>

<img src="https://user-images.githubusercontent.com/15669909/83025419-a4fe5c00-a037-11ea-81a2-318f1a0011d5.png" width=40%>

Delete a folio:

<img src="https://user-images.githubusercontent.com/15669909/83026061-d7a85480-a037-11ea-8c47-7a3ff8f0deeb.png" width=40%> <img src="https://user-images.githubusercontent.com/15669909/83026181-e0992600-a037-11ea-9a9c-8f84ad3b6e97.png" width=40%>

<img src="https://user-images.githubusercontent.com/15669909/83026299-e989f780-a037-11ea-966e-ba2395c109d5.png" width=40%>

Close a folio:

<img src="https://user-images.githubusercontent.com/15669909/83026833-13431e80-a038-11ea-821e-637525216681.png" width=40%> <img src="https://user-images.githubusercontent.com/15669909/83026925-1b02c300-a038-11ea-84dd-e7d8d745eb1f.png" width=40%>

## Requirements:
- JDK 8
- Maven

## Build:
````
mvn clean install
````
