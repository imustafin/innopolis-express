# Test Matrices

## Order Process matrix
<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Variable or selection</th>
            <th>TC-1</th>
            <th>TC-2</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>A1</td>
            <td>Meal list</td>
            <td>Available</td>
            <td>Not available</td>
        </tr>
        <tr>
            <td>A2</td>
            <td>Meal selection</td>
            <td>At least one meal</td>
            <td>-</td>
        </tr>
        <tr>
            <td>A3</td>
            <td>Cart</td>
            <td>Non-empty</td>
            <td>-</td>
        </tr>
        <tr>
            <td>S4</td>
            <td>Status</td>
            <td>Pending</td>
            <td>-</td>
        </tr>
    </tbody>
</table>

## Order Cancellation matrix 
<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Variable or selection</th>
            <th>TC-3</th>
            <th>TC-5</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>S1</td>
            <td>Order status</td>
            <td>Pending</td>
            <td>Delivering</td>
        </tr>
        <tr>
            <td>A2</td>
            <td>Button</td>
            <td>Cancel</td>
            <td>-</td>
        </tr>
        <tr>
            <td>S3</td>
            <td>Email notification</td>
            <td>Sent</td>
            <td>-</td>
        </tr>
    </tbody>
</table>

## Checking order status
<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Variable or selection</th>
            <th>TC-6</th>
            <th>TC-7</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>S1</td>
            <td>Order ID</td>
            <td>Existing</td>
            <td>Nonexisting</td>
        </tr>
        <tr>
            <td>A2</td>
            <td>Input field</td>
            <td>Filled</td>
            <td>Filled</td>
        </tr>
        <tr>
            <td>S3</td>
            <td>Order Status</td>
            <td>Regular</td>
            <td>-</td>
        </tr>
    </tbody>
</table>

## Login
<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Variable or selection</th>
            <th>TC-8</th>
            <th>TC-9</th>
            <th>TC-10</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>S1</td>
            <td>Login and password fields</td>
            <td>Exist</td>
            <td>Exist</td>
            <td>Exist</td>
        </tr>
        <tr>
            <td>A2</td>
            <td>Login and password</td>
            <td>Regular</td>
            <td>Empty (one/both)</td>
            <td>Incorrect</td>
        </tr>
        <tr>
            <td>A3</td>
            <td>Login Button</td>
            <td>Pressed</td>
            <td>Pressed</td>
            <td>Pressed</td>
        </tr>
    </tbody>
</table>

## Sign up
<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Variable or selection</th>
            <th>TC-11</th>
            <th>TC-12</th>
            <th>TC-13</th>
            <th>TC-14</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>A1</td>
            <td>Name</td>
            <td>Regular</td>
            <td>Empty/Filled</td>
            <td>Regular</td>
            <td>Regular</td>
        </tr>
        <tr>
            <td>A2</td>
            <td>Email</td>
            <td>Regular</td>
            <td>Empty/Filled</td>
            <td>Regular</td>
            <td>Existing</td>
        </tr>
        <tr>
            <td>A3</td>
            <td>Phone number</td>
            <td>Regular</td>
            <td>Empty/Filled</td>
            <td>Regular</td>
            <td>Regular</td>
        </tr>
        <tr>
            <td>A4</td>
            <td>Phone number</td>
            <td>Regular</td>
            <td>Empty/Filled</td>
            <td>Regular</td>
            <td>Regular</td>
        </tr>
        <tr>
            <td>A5</td>
            <td>Password</td>
            <td>Regular</td>
            <td>Empty/Filled</td>
            <td>Regular</td>
            <td>Regular</td>
        </tr>
		<tr>
            <td>A6</td>
            <td>Password (repeated)</td>
            <td>Matches</td>
            <td>Empty/Filled</td>
            <td>Doesn't match</td>
            <td>Matches</td>
        </tr>
        <tr>
            <td>A7</td>
            <td>Submit button</td>
            <td>Pressed</td>
            <td>Pressed</td>
            <td>Pressed</td>
            <td>Pressed</td>
        </tr>
        <tr>
            <td>S8</td>
            <td>User account</td>
            <td>Created</td>
            <td>Not created</td>
            <td>Not created</td>
            <td>Not created</td>
        </tr>
    </tbody>
</table>

## Check out
<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Variable or selection</th>
            <th>TC-15</th>
            <th>TC-16</th>
            <th>TC-17</th>
            <th>TC-18</th>
            <th>TC-19</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>S1</td>
            <td>Meals in cart</td>
            <td>Added at least one</td>
            <td>Added at least one</td>
            <td>The same meal added several times</td>
            <td>Added at least one</td>
            <td>Empty</td>
        </tr>
        <tr>
        	<td>S2</td>
        	<td>Price</td>
        	<td>Calculated correctly</td>
        	<td>Calculated correctly</td>
        	<td>Calculated correctly</td>
        	<td>Calculated correctly</td>
        	<td>-</td>
        </tr>
        <tr>
        	<td>A3</td>
        	<td>Address and phone number</td>
        	<td>Regular</td>
        	<td>One/both empty</td>
        	<td>Regular</td>
        	<td>Regular</td>
        	<td>-</td>
        </tr>
        <tr>
        	<td>A4</td>
        	<td>Submit button</td>
        	<td>Pressed</td>
        	<td>Pressed</td>
        	<td>Pressed</td>
        	<td>Pressed</td>
        	<td>-</td>
        </tr>
        <tr>
        	<td>AE3</td>
        	<td>Meal delete button</td>
        	<td>-</td>
        	<td>-</td>
        	<td>-</td>
        	<td>Pressed</td>
        	<td>-</td>
        </tr>
    </tbody>
</table>

## Order Detail invoice
<table>
    <thead>
        <tr>
            <th>Step</th>
            <th>Variable or selection</th>
            <th>TC-20</th>
            <th>TC-21</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>S1</td>
            <td>Order ID</td>
            <td>Displayed</td>
            <td>Displayed</td>
        </tr>
        <tr>
        	<td>S2</td>
        	<td>Invoice information</td>
        	<td>Displayed and static</td>
        	<td>Disaplyed but status changed</td>
        </tr>
    </tbody>
</table>

