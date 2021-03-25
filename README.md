###Target Code Challenge: MyRetail API

This api exclusively focuses/allows for the update and retrieval of product price information via endpoints.

####Set up


| Tool      | Version |
| ----------- | ----------- |
| Java      |12.0.2       |
| SpringBoot   | 2.4.4        |
| MongoDB   | 4.4.4        |
| Maven   | 4.0.0        |
| Postman   |         |

In order to run this application:

* Please ensure that you have at the minimum the technologies mentioned in the table installed.

* Next, clone or download the zip file containing the project's source code.

* Update the ```application.properties``` file located at this location: ```pricechecker\src\main\resources``` with you MongoDB login information and/or your server port.

* Run the ```Pricecheckerapplication```.

####Endpoints

Using Postman, you'll be able to access the different Endpoints outlined below and test MyRetail PriceChecker's API. 
  
* GET: ```{localhost:server.port}/products```  gives you a list of all products present in the database. It's worth mentioning that it(the database) is populated with basic price information linked to the ID's present in the assignment file. That information is available in the ```price-data.json``` file present at this path ```pricechecker\src\main\resources```. 

* GET: ```{localhost:server.port}/products/{id}``` gives you in JSON format information pertaining to the ```{id}``` variable passed in. A sample result would be: 
```
{
    "productId": 54456119,
    "name": "Creamy Peanut Butter 40oz - Good &#38; Gather&#8482;",
    "current_price": {
        "value": 700,
        "currency": "USD"
        }
}
 ```
    

  
* PUT: ```{localhost:server.port}/products/{id}``` along with a JSON input 
``` 
{
       "productId": 54456119,
       "name": "Creamy Peanut Butter 40oz - Good &#38; Gather&#8482;",
       "current_price": {
           "value": 300,
           "currency": "USD"
       }
   }
```
Would update the ```current_price``` value of the previous JSON output from ```700``` to ```300```. 