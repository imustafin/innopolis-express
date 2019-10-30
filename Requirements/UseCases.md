
Food Delivery Use Cases
=======================

Order Process
-------------
 
 | **Use Case Name**                | **Order Process**                                                 |
 |:--------------------------------:|:------------------------------------------------------------------|
 | Actors                           | Client, Food point administrator                                  |
 | Pre-conditions                   | Meal plans are shown in the system                                |
 | Flow of events                   | 1\. Client browses menu                                           |
 |                                  | 2\. Client adds meals to delivery cart                            |
 |                                  | 3\. Client adds info about contacts, place and time delivery      |
 |                                  | 4\. Client makes checkout for all items in the cart               |
 |                                  | 5\. New order created and will be shown in the cafe dashboard     |
 |                                  | 6\. Food point administrator confirms the order                   |
 |                                  | 7\. Client gets confirmation about the order                      |
 | Post-conditions                  | Order will be processed by the food point                         |
 | Alternative flows and exceptions | The food point administrator rejects the order of the client. The |
 |                                  | client will be notified about rejection                           |
  

Order Cancellation
------------------

  
 | **Use Case Name**                  | **Order Cancellation**                                               |
 |:----------------------------------:|:---------------------------------------------------------------------|
 | Actors                             | Client Food point Administrator                                      |
 | Pre-conditions                     | Order is created by client                                           |
 | Flow of events                     | 1\. Client receives unique identification № of order                 |
 |                                    | 2\. Client enters № of order to check-order web pages                |
 |                                    | 3\. Current status of order and cancel-button is displayed on page   |
 |                                    | 4\. Client cancels order by cancel-button                            |
 |                                    | 5\. Food point Administrator gets notification about cancelling      |
 | Post-conditions                    | Order is cancelled                                                   |
 | Alternative flows and exceptions   | Order can't be cancelled if it already has status "delivering" ot    |
 |                                    | if it is than 1h before delivery time                                |
 

Checking order status
---------------------

 
 | **Use Case Name**                | **Checking order status**                                        |
 |:--------------------------------:|:-----------------------------------------------------------------|
 | Actors                           | Client                                                           |
 | Pre-conditions                   | Order is created by client                                       |
 | Flow of events                   | 1\. Client receives unique identification № of order             |
 |                                  | 2\. Client enters № of order to check-order web pages            |
 |                                  | 3\. Current status of the order is displayed on page             |
 | Post-conditions                  | Client gets to know about current order status                   |
 | Alternative flows and exceptions | Unique code of order was not generated -- order can't be checked |
 

New menu meals uploading
------------------------

 
 | **Use Case Name**                | **New menu meals uploading**                                     |
 |:--------------------------------:|:-----------------------------------------------------------------|
 | Actors                           | Food point administrator, System administrator                   |
 | Pre-conditions                   | Food point is registered in the system                           |
 | Flow of events                   | 1\. Food point administrator fill the menu meals form            |
 |                                  | 2\. Food point administrator submits the meals form              |
 |                                  | 3\. System administrator approve the meals form                  |
 | Post-conditions                  | New food point meals uploaded in the system                      |
 | Alternative flows and exceptions | The system administrator rejects submitted meal plan. The food   |
 |                                  | point administrator receives the notification.                   |
 

Current menu meals choosing
---------------------------

 
 | **Use Case Name**                | **Current menu meals choosing**                                            |
 |:--------------------------------:|:---------------------------------------------------------------------------|
 | Actors                           | Food point administrator                                                   |
 | Pre-conditions                   | Food point is registered in the system, menu meals uploaded by food        |
 |                                  | point adminisrator and approved by the system administrator                |
 | Flow of events                   | 1\. Food point administrator combines the menu from the approved meals     |
 |                                  | list                                                                       |
 |                                  | 2\. Food point administrator submits the menu form                         |
 | Post-conditions                  | Current food point menu updated                                            |
 | Alternative flows and exceptions | There are no already uploaded and approved meals in the system - nothing   |
 |                                  | can be chosen for current menu.                                            |
 

Client feedback processing
--------------------------

 
 | **Use Case Name**                | **Client feedback processing**                                             |
 |:--------------------------------:|:---------------------------------------------------------------------------|
 | Actors                           | Client                                                                     |
 | Pre-conditions                   | Order in the "delivered" status, the user on the "Check Order" page        |
 | Flow of events                   | 1\. The system asks the client about the quality of food (like/dislike)    |
 |                                  | 2\. Client makes a choice (like/dislike)                                   |
 |                                  | 3\. Client leaves a comment in the textbox                                 |
 |                                  | 4\. Client submits review-form                                             |
 | Post-conditions                  | Review information appears on "Order Monitor" page of the system next to   |
 | Alternative flows and exceptions | the client's order. User will not provide any review about its quality     |
 
Use Case Diagram
----------------

![](media/image1.png)

Glossary
========

1.  **Customer** - an entity that uses the Food Delivery system for
    their business. Food points are customers in this case.

2.  **Client** - a person who orders food from the system.

3.  **Food point** - any food court which uses Food Delivery system for
    their business and makes delivery to the clients.

4.  **Courier** - employee of food point who delivers food to the
    clients.

5.  **Food point Administrator** - an employee of food points who is
    responsible for maintaining the system for their use

6.  **System Administrator** - a person who adds new food point entities
    to the system and approves food points' meal plans in the system


