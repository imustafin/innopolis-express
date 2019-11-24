# Test Report


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#successful-order-request">TC-1: Successful order request</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| A1       | Meal list                 | Available   | Meal list is displayed | Meal list is displayed |
| A2       | Meal selection            | At least one meal | Meal is added to Cart | Meal is added to Cart |
| A3       | Cart                      | Non-empty | Order is created | Order is created |
| A4       | Status                    | Pending   | Invoice is displayed | Invoice is displayed |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#prevent-order-request">TC-2: Prevent order request</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| A1       | Meal list                 | Not available | Meal list is not displayed | Meal list is not displayed |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#successful-order-cancellation">TC-3: Successful order cancellation </a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order Status              | Pending   | Pending status displayed | Pending status displayed |
| A2       | Button                    | Cancel    | Cancel button displayed | Cancel button displayed |
| S3       | Email                     | Sent      | Email about cancellation sent to the user | Email about cancellation sent to the user|


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#cancellation-of-delivering-order">TC-5: Cancellation of "Delivering" order </a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order Status              | Delivering | Delivering status displayed | Delivering status displayed |
| A2       | Button                    | Cancel    | Cancel button not displayed | Cancel button not displayed |
| S3       | Email                     | Sent      | Email about cancellation not sent to the user | Email about cancellation not sent to the user |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#checking-the-status-of-an-existing-order">TC-6: Checking the status of an existing order</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order ID                  | Existing  | Navigate to Track order | Navigate to Track order |
| A2       | Input field               | Filled    | Order information   | Order information |
| S3       | Order Status              | Pending   | -                   | -            |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#checking-the-status-of-an-nonexisting-order">TC-7: Checking the status of a non-existing order</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order ID                  | Nonexisting | Navigate to Track order | Navigate to Track order |
| A2       | Input field               | Filled    | Error message       | Error message |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#login">TC-8: Login</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Login and password fields | Exist     | Login page          | Login page   |
| A2       | Login and password        | 'batyrz@mail.ru', 'zaq123' | Filled login and password fields | Filled login and password fields |
| A3       | Login Button              | Pressed   | Successfull Login   | Successfull Login |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#login-with-empty-or-incorrect-fields">TC-9: Login with empty or incorrect fields</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Login and password fields | Exist     | Display Login page  | Display Login page |
| A2       | Login and password        | empty, empty | Empty login and password fields | Empty login and password fields |
| A3       | Login Button              | Pressed   | Error message       | Error message |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#login-with-incorrect-password-or-nonexisiting-account">TC-10: Login with incorrect password or non-exisiting account</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Login and password fields | Exist     | Display Login page  | Display Login page |
| A2       | Login and password        | 'not@exitsting.email', '123' | Filled login and password fields | Filled login and password fields |
| A3       | Login Button              | Pressed   | Error message       | Error message |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#sign-up">TC-11: Sign up</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| A1       | Name                      | Kirill    | Error focus is not shown | Error focus is not shown |
| A2       | Email                     | test@test.ru | Error focus is not shown | Error focus is not shown |
| A3       | Password                  | Qwerty123   | Error focus is not shown | Error focus is not shown |
| A4       | Password                  | true   | Error focus is not shown | Error focus is not shown |
| A5       | Phone number              | 77777777777 | Error focus is not shown | Error focus is not shown |
| A6       | Address                   | 1/4 404 | Error focus is not shown | Error focus is not shown |
| A7       | Submit button             | Pressed | Redirect to next activity | Redirect to next activity |
| S8       | User account              | Created | Filled user is added to database | Filled user is added to database |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#signup-with-empty-fields">TC-12: Signup with empty fields</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| A1       | Name                      | Empty    | Error focus is shown | Error focus is shown |
| A2       | Email                     | Empty | Error focus is shown | Error focus is shown |
| A3       | Password                  | Qwerty123   | Error focus is not shown | Error focus is not shown |
| A4       | Password                  | true   | Error focus is not shown | Error focus is not shown |
| A5       | Phone number              | 77777777777 | Error focus is not shown | Error focus is not shown |
| A6       | Address                   | 1/4 404 | Error focus is not shown | Error focus is not shown |
| A7       | Submit button             | Pressed | Error focus is shown | Error focus is shown |
| S8       | User account              | Created | User is not created | User is not created |

## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#signup-with-not-similar-passwords">TC-13: Signup with not similar passwords</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| A1       | Name                      | Kirill    | Error focus is not shown | Error focus is not shown |
| A2       | Email                     | test@test.ru | Error focus is not shown | Error focus is not shown |
| A3       | Password                  | Qwerty123   | Error focus is not shown | Error focus is not shown |
| A4       | Password                  | fasle   | Error focus is shown | Error focus is shown |
| A5       | Phone number              | 77777777777 | Error focus is not shown | Error focus is not shown |
| A6       | Address                   | 1/4 404 | Error focus is not shown | Error focus is not shown |
| A7       | Submit button             | Pressed | Error focus is shown | Error focus is shown |
| S8       | User account              | Created | User is not created | User is not created |

## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#signup-with-existing-email">TC-14: Signup with existing email</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| A1       | Name                      | Kirill    | Error focus is not shown | Error focus is not shown |
| A2       | Email                     | test@test.ru (existing) | Error focus is not shown | Error focus is not shown |
| A3       | Password                  | Qwerty123   | Error focus is not shown | Error focus is not shown |
| A4       | Password                  | fasle   | Error focus is shown | Error focus is shown |
| A5       | Phone number              | 77777777777 | Error focus is not shown | Error focus is not shown |
| A6       | Address                   | 1/4 404 | Error focus is not shown | Error focus is not shown |
| A7       | Submit button             | Pressed | Error focus is shown | Error focus is shown |
| S8       | User account              | Created | User is not created | User is not created |

## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#show-invoice-after-order-is-made">TC-20: Show invoice after order is made</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order ID                  | PBYFC | Order Id displayed | Order Id displayed |
| S2       | Invoice information       | Displayed | Invoice information displayed | Invoice information displayed |

## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#client-sees-how-status-was-changed">TC-21: Client sees how status was changed</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order ID                  | PBYFC | Order Id displayed | Order Id displayed |
| S2       | Invoice information       | Status changed | Invoice status changed | Invoice status changed |
## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#check-out">TC-15: Check out</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Meals in cart             | Freekeh x2 | Non-empty cart     | Non-empty cart |
| S2       | Price                     | Calculated correctly | 600      | 600          |
| A3       | Address and phone number  | '2-222', '89265757832' | -      | -            |
| A4       | Submit button             | Pressed   | Successful checkout | Successful checkout |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#empty-address-orand-phone-number-fields-in-cart-check-out">TC-16: Empty address or/and phone number fields in cart check out</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Meals in cart             | Freekeh x2 | Non-empty cart     | Non-empty cart |
| S2       | Price                     | Calculated correctly | 600      | 600          |
| A3       | Address and phone number  | empty, empty | -                | -            |
| A4       | Submit button             | Pressed   | Error message       | Error message |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#the-same-meal-added-several-times-to-the-cart-before-check-out">TC-17: The same meal added several times to the cart before check out</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Meals in cart             | Freekeh x2, then Freekeh x3 | Freekeh x5 | Freekeh x5 |
| S2       | Price                     | Calculated correctly | 1500      | 1500          |
| A3       | Address and phone number  | '2-222', '89265757832' | -      | -            |
| A4       | Submit button             | Pressed   | Successful checkout | Successful checkout |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#client-deletes-meals-during-check-out">TC-18: Client deletes meals during check out</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Meals in cart             | Freekeh x2, Ozi x1 | Non-empty cart | Non-empty cart |
| S2       | Price                     | Calculated correctly | 850      | 850          |
| A3       | Address and phone number  | '2-222', '89265757832' | -      | -            |
| AE3      | Meal delete button        | Pressed on Ozi | Ozi is deleted from cart | Ozi is deleted from cart |
| A4       | Submit button             | Pressed   | Successful checkout | Successful checkout |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#empty-cart-check-out">TC-19: Empty cart check out</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Meals in cart             | Not Selected | Empty cart       | Empty cart   |
