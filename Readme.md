# Software Requirements
* Java 11 (https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* Visual Studio Code IDE (https://code.visualstudio.com/)
* Postman API client (https://www.postman.com/downloads/)

# Setup Process
After installing the Visual Studio Code IDE, open it and clone the project by clicking on the **clone repository...** link on the welcome page of the IDE.

By clicking on the **clone repository...** link a search box will pop on the top. In that search box, you need to enter **https://github.com/bvkpardhu/fetch-rewards** and then press Enter. It will ask you to select a destination directory to store the repository (select a directory in your system).

It will pop a notification on the bottom right-hand corner prompting **Would you like to open the cloned repository?** then select **open**. 
### If you have no Java Extension Pack extension installed
* Install it by clicking on the **Extensions** icon on the left tab, then you can see a text box, type **Java Extension Pack** and install the first one with Microsoft as the author.

It prompts a notification **Maven Wrapper is found in this workspace, do you trust it?** on the bottom right-hand corner then select **yes**.

It prompts another notification saying **The workspace contains Java projects. Would you like to import them?** then click on yes. 

That's it now your project setup is completed.

(Note: If the notification dialog doesn't pop for you in any case, click on the **notification bell icon** tab on the bottom right-hand corner of the IDE.)

# Running the project
In the IDE navigate to **src/main/java/com/bvkpardhu/fetchrewards/FetchRewardsApplication.java** file and click on the **play icon button** on the right-hand top corner. The project will start on port **8080**.

Now open the Postman API client and paste **http://localhost:8080/fetch-rewards/** in the URL.

### API calls
* Add transactions

You can upload the transactions JSON data by choosing **POST** and entering **http://localhost:8080/fetch-rewards/upload** in the URL. Click on the **Body** tab below the URL and enter JSON data and click on the **Send** button now.

* Balance check

You can check the user balances by choosing **GET** and enter **http://localhost:8080/fetch-rewards/balances** in the URL and click on the **Send** button now. Now you can see the balances of the respected users.

* Spend points

You can spend points by choosing **GET** and enter **http://localhost:8080/fetch-rewards/points/INTEGER** (in the place of **INTEGER** you need to type a number) in the URL and click on the **Send** button now. You can see the spending transaction details as the response.



# Format
* JSON format

place the transactions in the following format.

{
"users":
[
{
"payer": "DANNON",
"points": 1000,
"timeStamp": "2020-11-02T14:00:00Z"
},
{
"payer": "UNILEVER",
"points": 200,
"timeStamp": "2020-10-31T11:00:00Z"
},
{
"payer": "DANNON",
"points": -200,
"timeStamp": "2020-10-31T15:00:00Z"
},
{
"payer": "MILLER COORS",
"points": 10000,
"timeStamp": "2020-11-01T14:00:00Z"
},
{
"payer": "DANNON",
"points": 300,
"timeStamp": "2020-10-31T10:00:00Z"
}
]
}

Place your transactions inside the **users** array. You can enter one or more transactions at a time.

Place the payer name in the **payer** attribute (do not enter an empty string or null as payer name).

Place the payer's points in the **points** attribute (do not enter floating-point values as the points. I took int datatype for simplicity, so enter an integer).

Place the transaction timestamp in the **timeStamp** attribute (enter the timestamp in the yyyy-MM-ddTHH:mm:ssZ format).

#### Note: Do not enter the transactions having a negative balance for a user. Do not remove an amount that is more than the current balance of a user.

* Spend points API call

Do not enter an integer with a negative value or a number that is greater than the total balances of the users in the URL (http://localhost:8080/fetch-rewards/points/INTEGER).
