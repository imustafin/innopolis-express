# Interaction Test Cases

## Use Case Interaction Matrix

| Use case name         | Order Process | Order Cancellation | Checking order status | Login | Sign up           | Check out | Order detail Invoice | Change order status |
| --------------------- | ------------- | ------------------ | --------------------- | ----- | ----------------- | --------- | -------------------- | ------------------- |
| Order Process         |               |                    |                       |       |                   |           |                      |                     |
| Order Cancellation    |               |                    |                       |       |                   |           |                      |                     |
| Checking order status | RC - order    |                    |                       |       |                   |           |                      |                     |
| Login                 |               |                    |                       |       | RC - user account |           |                      |                     |
| Sign up               |               |                    |                       |       |                   |           |                      |                     |
| Check out             | RC - cart     |                    |                       |       |                   |           |                      |                     |
| Order detail Invoice  |               |                    |                       |       |                   |           |                      | RC - order status   |
| Change order status   | RC - order    |                    |                       |       |                   |           |                      |                     |


## Checking the status of an nonexisting order (Checking order status + Order process)

| **Test case name** | Checking the status of a nonexisting order |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-7 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#checking-order-status">Checking order status</a> | 
| **Set up**         | Random ID which doesn't match to any ID of exixting orders |
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
            <td>TC-7-A2</td>
            <td>Client enters nonexisting ID</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-7-S3</td>
            <td>System informs client that ID is incorrect and there is no such order</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


## Empty cart check out (Check out + Order Process)

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

## Login with incorrect password or nonexisiting account (Sign up + Login)

| **Test case name** | Login with incorrect password or nonexisiting account |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-10 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#login">Login</a> |
| **Set up**         | Inappropriate login data |
| **Status** | Passed |
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
            <td>TC-10-S1</td>
            <td>System shows inputs for email and password</td>
            <td>Passed</td>
            <td>-</td>
        </tr>
        <tr>
            <td>TC-10-A1</td>
            <td>Choose one of the options:
            	<ul>
            		<li>Client enters nonexisting email</li>
            		<li>Client enters incorrect password for existing account</li>
            	</ul>
            </td>
            <td>Passed</td>
            <td>-</td>
        </tr>
        <tr>
            <td>TC-10-A2</td>
            <td>Client presses login button</td>
            <td>Passed</td>
            <td>-</td>
        </tr>
        <tr>
            <td>TC-10-SE2</td>
            <td>System shows the message with description of the error</td>
            <td>Warning</td>
            <td>Systems always shows error only with invalid password but not with nonexisiting account</td>
        </tr>
    </tbody>
</table>

## Client sees how status was changed (Order detail Invoice + Change order status)
| **Test case name** | Client sees how status was changed |
|--------------------|---------------------------------------------------------------|
| **Test ID**        | TC-21 |
| **Test suite**     | <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/UseCases.md#order-detail-invoice">Order Detail invoice</a> |
| **Set up**         | Submitted order and changing status |
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
            <td>TC-21-S1</td>
            <td>System generates unique ID of the order and displays it to the client</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-21-S2</td>
            <td>System shows all the information about the order: meal list, total price, qunatity and status</td>
            <td>Passed</td>
        </tr>
        <tr>
            <td>TC-21-SE1</td>
            <td>If the status was changed - status on the mobile screen is changed</td>
            <td>Passed</td>
        </tr>
    </tbody>
</table>


