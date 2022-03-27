# topup-service


There is following one API exposed for topup
1. http://localhost:8080/topup-service/topup

# Implemented Flow

API receives the request payload mentioned in this requirement https://github.com/Tap-Payments/JAVA-Technical-Assessment

System will validate the request payload and System will respond with validation messages if Mandatory fields are missing in request payload.

If All Mandatory data is present in request payload, System will save request payload in the DB for Audit/Investigation purpose what data was sent.
Now System will go to check if Customer exists in the DB(MongoDB) or not
If Customer doesn't exist then System will break the flow and respond with error message -> Customer "id" not found

If Customer exists then System will go to check if give Wallet Id exists in the System or not,
if Wallet doesn't exist then System will break the flow and respond with error message -> Wallet "Id" not found

If Customer and Wallet are found then System will perform basic topup transaction and Add the topup amount in customer given wallet with subtracting Fee amount 
as mentioned in the requirement https://github.com/Tap-Payments/JAVA-Technical-Assessment

Now System will prepare response data as per the requirement https://github.com/Tap-Payments/JAVA-Technical-Assessment

# Business Flow ended.

# Note:
We used MongoDB as a data storage.
We created two collections to perform above business flow.
1. topup_detail -> to save request Payload for Audit/Investigation purpose.
2. customer_wallet -> Having Customer's Wallet Information
