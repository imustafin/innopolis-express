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



## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#checking-the-status-of-an-existing-order">TC-6: Checking the status of an existing order</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order ID                  | Existing  | Navigate to Track order | Navigate to Track order |
| A2       | Input field               | Filled    | Display order information | Display order information |
| S3       | Order Status              | Pending   | -                   | -            |


## <a href="https://github.com/BatyrSeven/innopolis-express/blob/firebase/Requirements/Test%20Cases.md#checking-the-status-of-an-nonexisting-order">TC-7: Checking the status of a non-existing order</a>

| **Step** | **Variable or selection** | **Value** | **Expected result** | **Obtained** |
|----------|---------------------------|-----------|---------------------|--------------|
| S1       | Order ID                  | Nonexisting | Navigate to Track order | Navigate to Track order |
| A2       | Input field               | Filled    | Display error message | Display error message |