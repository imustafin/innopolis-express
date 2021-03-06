# Test Cases


## Successful order request

| **Test case name** | Successful order request                                      |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-1 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#order-process">Order Process</a> |
| **Set up**         | Login into mobile application and make sure the cart is empty |
| **Teardown**       | Cancel order and empty the cart | 
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-1-A1</td>
            <td>Browse menu of the mobile application</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-1-A2</td>
            <td>Add meals to the cart</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-1-A3</td>
            <td>Client check-outs the cart</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-1-S4</td>
            <td>System creates order with status "Pending"</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


## Prevent order request

| **Test case name** | Prevent order request                                         |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-2 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#order-process">Order Process</a> |
| **Set up**         | Login into mobile application with existing order in status "Pending" or "Delivering"|
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-2-SE1</td>
            <td>Mobile application navigates client to invoice information about existing order</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


## Successful order cancellation

| **Test case name** | Successful order cancellation                                 |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-3 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#order-cancellation">Order Cancellation</a> | 
| **Set up**         | Login into mobile application with a created order |
| **Teardown**       | Create another order | 
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-3-S1</td>
            <td>Mobile application displays cancel-button and current status of the order</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-3-A2</td>
            <td>Client clicks on the cancel-button</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-3-S3</td>
            <td>Administrator recieves alert about cancellation in Admin Panel</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


## Cancellation of "Delivering" order

| **Test case name** | Cancellation of "Delivering" order |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-5 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#order-cancellation">Order Cancellation</a> | 
| **Set up**         | Login into mobile application with a created order in status "Delivering" |
| **Teardown**       | Close mobile application | 
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-5-S1</td>
            <td>Mobile application displays current status and information about the order</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-5-SE1</td>
            <td>Mobile application hides cancel-button</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


## Checking the status of an existing order

| **Test case name** | Checking the status of an existing order |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-6 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#checking-order-status">Checking order status</a> | 
| **Set up**         | Order must be created |
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 |

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-6-S1</td>
            <td>Systems sends email to the client with information about odrder and invoice</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-6-A2</td>
            <td>Client enters ID of the order</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-6-S3</td>
            <td>System shows the status and information abouth the order</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


## Login

| **Test case name** | Login                                                         |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-8 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#login">Login</a> | 
| **Set up**         | Existing account |
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 |

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-8-S1</td>
            <td>System shows inputs for email and password</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-8-A1</td>
            <td>Clients enters correctly email and password</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-8-A2</td>
            <td>Client presses login button</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


## Login with empty or incorrect fields 

| **Test case name** | Login with empty or incorrect fields |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-9 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#login">Login</a> | 
| **Set up**         | Inappropriate login data |
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 |

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-9-S1</td>
            <td>System shows inputs for email and password</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-9-A1</td>
            <td>Choose one of the options:
            	<ul>
            		<li>Client leaves one of the fields empty</li>
            		<li>Client leaves both fields empty</li>
            		<li>Client enters incorrect email (without '@' or domain)</li>
            	</ul>
            </td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-9-A2</td>
            <td>Client presses login button</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-9-SE1</td>
            <td>System shows the message on which field a mistake was made</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>

## Sign up

| **Test case name** | Sign up |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-11 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#sign-up">Sign up</a> | 
| **Set up**         | Email which was not signed up before |
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 |

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
            <th>Error</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-11-A1 ... TC-11-A6</td>
            <td>Client enters name, email, phone number, password and address </td>
            <td>Warning</td>
            <td>The password confiramtion field does not exist</td>
        </tr>
        <tr>
            <td>TC-11-A7</td>
            <td>Clients clicks on submit button</td>
            <td>Passed</td>
            <td>-</td>
        </tr>
        <tr>
            <td>TC-11-SE1</td>
            <td>System creates an account with given email and password</td>
            <td>Passed</td>
            <td>-</td>
        </tr>
    </tbody>
</table>


## Signup with empty fields

| **Test case name** | Signup with empty fields |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-12 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#sign-up">Sign up</a> | 
| **Set up**         | Inappropriate signup data |
| **Status**         | Passed | 
| **Date complete**  | 24-11-19 |

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
            <th>Error</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-12-A1 ... TC-12-A6</td>
            <td>Client leaves one/some/all fields empty </td>
            <td>Warning</td>
            <td>The password confiramtion field does not exist</td>
        </tr>
        <tr>
            <td>TC-12-A7</td>
            <td>Clients clicks on submit button</td>
            <td>Passed</td>
            <td>-</td>
        </tr>
        <tr>
            <td>TC-12-SE3</td>
            <td>System shows error message and which fields should be completed</td>
            <td>Passed</td>
            <td>-</td>
        </tr>
    </tbody>
</table>

## Signup with not similar passwords

| **Test case name** | Signup with not similar passwords |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-13 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#sign-up">Sign up</a> | 
| **Set up**         | Different passwords |
| **Status**         | Failed | 
| **Date complete**  | 24-11-19 |

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Warning</th>
            <th>Error</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-13-A1 ... TC-13-A6</td>
            <td>Client enters everything correctly except second password </td>
            <td>Failed</td>
            <td>The password confiramtion field does not exist</td>
        </tr>
        <tr>
            <td>TC-13-A7</td>
            <td>Clients clicks on submit button</td>
            <td>Passed</td>
            <td>-</td>
        </tr>
        <tr>
            <td>TC-13-SE2</td>
            <td>System shows that password must be similar</td>
            <td>Blocked</td>
            <td>-</td>
        </tr>
    </tbody>
</table>

## Signup with existing email

| **Test case name** | Signup with existing email |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-14 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#sign-up">Sign up</a> | 
| **Set up**         | Exisiting email of an acocunt |
| **Status** | Warning | 
| **Date complete** | 24-11-19 |

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
            <th>Error</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-14-A1</td>
            <td>Client enters name correctly </td>
            <td>Passed</td>
            <td></td>
        </tr>
        <tr>
            <td>TC-14-A2</td>
            <td>Client enters existing email correctly </td>
            <td>Passed</td>
            <td></td>
        </tr>
        <tr>
            <td>TC-14-A3</td>
            <td>Client enters password correctly </td>
            <td>Passed</td>
            <td></td>
        </tr>
         <tr>
            <td>TC-14-A4</td>
            <td>Client enters password confirmation correctly </td>
            <td>Failed</td>
            <td>The password confiramtion field does not exist</td>
        </tr>
        <tr>
            <td>TC-14-A5</td>
            <td>Client enters phone number correctly </td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-14-A6</td>
            <td>Client enters address correctly </td>
            <td>Passed</td>
            <td></td>
        </tr>
        <tr>
            <td>TC-14-A7</td>
            <td>Clients clicks on submit button</td>
            <td>Passed</td>
            <td></td>
        </tr>
        <tr>
            <td>TC-14-SE1</td>
            <td>System shows that enterred email exists</td>
            <td>Warning</td>
            <td>The system shows message "Sign Up failed"</td>
        </tr>
    </tbody>
</table>

## Check out

| **Test case name** | Check out                                                         |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-15 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#check-out">Check out</a> | 
| **Set up**         | Non-empty cart |
| **Status** | Passed | 
| **Date complete** | 24-11-19 |

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-15-S1</td>
            <td>Mobile application shows meals that were added to the cart</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-15-S2</td>
            <td>Mobile application calculates total price and dislpays it </td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-15-A3</td>
            <td>Client enters address and phone number</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-15-A4</td>
            <td>Client clicks on submit button</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>

## Empty address or/and phone number fields in cart check out

| **Test case name** | Empty cart check out                                          |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-16 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#check-out">Check out</a> | 
| **Set up**         | Non-empty cart |
| **Status** | Passed | 
| **Date complete** | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-16-S1</td>
            <td>Mobile application shows meals that were added to the cart</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-16-S2</td>
            <td>Mobile application calculates total price and dislpays it </td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-16-A3</td>
            <td>Client leaves empty one or both of the fields (address/phone number)</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-16-A4</td>
            <td>Client clicks on submit button</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-16-SE4</td>
            <td>Mobile application shows message that some of the fields should be completed</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>

## The same meal added several times to the cart before check out

| **Test case name** | The same meal added several times to the cart before check out|
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-17 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#check-out">Check out</a> | 
| **Set up**         | Non-empty cart where client added the same meal several times |
| **Status** | Passed | 
| **Date complete** | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-17-SE2</td>
            <td>Mobile application shows meals that were added to the cart and sums the quantity of the meal which was added several times</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-17-S2</td>
            <td>Mobile application calculates total price and dislpays it </td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-17-A3</td>
            <td>Client enters address and phone number</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-17-A4</td>
            <td>Client clicks on submit button</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>

## Client deletes meals during check out
| **Test case name** | Client deletes meals during check out |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-18 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#check-out">Check out</a> | 
| **Set up**         | Non-empty cart where client added the same meal several times |
| **Status** | Passed | 
| **Date complete** | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-18-S1</td>
            <td>Mobile application shows meals that were added to the cart</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-18-AE3</td>
            <td>Client deletes meal by clicking on button</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-18-S2</td>
            <td>Mobile application calculates total price and dislpays it </td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-18-A3</td>
            <td>Client enters address and phone number</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-18-A4</td>
            <td>Client clicks on submit button</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


## Empty cart check out

| **Test case name** | Empty cart check out                                          |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-19 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#check-out">Check out</a> | 
| **Set up**         | Empty cart |
| **Status** | Passed | 
| **Date complete** | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-19-SE1</td>
            <td>Mobile application shows client that the cart is empty</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>

## Show invoice after order is made
| **Test case name** | Show invoice after order is made|
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-20 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#order-detail-invoice">Order Detail invoice</a> |
| **Set up**         | Submitted order |
| **Status** | Passed | 
| **Date complete** | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-20-S1</td>
            <td>System generates unique ID of the order and displays it to the client</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-20-S2</td>
            <td>System shows all the information about the order: meal list, total price, qunatity and status</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>

## Change order status
| **Test case name** | Change order status |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-22 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#change-order-status">Change order status</a> | 
| **Set up**         | Login to the Admin Panel and exisiting orders with different statuses |
| **Status** | Passed | 
| **Date complete** | 24-11-19 | 

<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Description</th>
            <th>Result</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>TC-22-S1</td>
            <td>System shows the list of orders with information abouth them: client info, status, date, meals</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-22-A2</td>
            <td>Administrator finds order which status he/she wants to change</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-22-A3</td>
            <td>Administrator clicks on the button to change status</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-22-S4</td>
            <td>System sends email to the client about changes</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>

