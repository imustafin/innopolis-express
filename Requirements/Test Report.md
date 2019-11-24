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


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#successful-cancellation">TC-3: Successful order cancellation </a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order Status              | Pending   | Pending status displayed | Pending status displayed |
| A2       | Button                    | Cancel    | Cancel button displayed | Cancel button displayed |
| S3       | Email                     | Sent      | Email about cancellation sent to the user | Email about cancellation sent to the user|

## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#prevent-cancellation-cancelled">TC-5: Cancellation of "Delivering" order </a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order Status              | Delivering | Delivering status displayed | Delivering status displayed |
| A2       | Button                    | Cancel    | Cancel button not displayed | Cancel button not displayed |
| S3       | Email                     | Sent      | Email about cancellation not sent to the user | Email about cancellation not sent to the user |
