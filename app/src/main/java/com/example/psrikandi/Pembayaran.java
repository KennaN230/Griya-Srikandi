//package com.example.psrikandi;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
//import com.midtrans.sdk.corekit.core.MidtransSDK;
//import com.midtrans.sdk.corekit.core.TransactionRequest;
//import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
//import com.midtrans.sdk.corekit.models.ItemDetails;
//import com.midtrans.sdk.corekit.models.SnapTransactionDetails;
//import com.midtrans.sdk.corekit.models.TransactionResponse;
//import com.midtrans.sdk.corekit.models.UserDetail;
//import com.midtrans.sdk.corekit.models.snap.CreditCardPaymentModel;
//import com.midtrans.sdk.corekit.models.snap.Transaction;
//import com.midtrans.sdk.corekit.models.snap.TransactionResult;
//
//import java.util.ArrayList;
//
//public class Pembayaran extends AppCompatActivity implements TransactionFinishedCallback {
//    private Button payButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pembayaran);
//
//        payButton = findViewById(R.id.payButton);
//        payButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startPayment();
//            }
//        });
//    }
//
//    private void startPayment() {
//        // Set up the details for the transaction
//        TransactionRequest transactionRequest = new TransactionRequest(System.currentTimeMillis() + "", 10000);
//
//        // Set up item details (for example, you're selling a product)
//        ArrayList<ItemDetails> itemDetailsArrayList = new ArrayList<>();
//        itemDetailsArrayList.add(new ItemDetails("1", 10000, 1, "T-Shirt"));
//
//        // Set up user details
//        UserDetail userDetail = new UserDetail();
//        userDetail.setUserFullName("John Doe");
//        userDetail.setEmail("john@example.com");
//        userDetail.setPhoneNumber("08123456789");
//
//        // Set up Snap Transaction Details
//        SnapTransactionDetails snapTransactionDetails = new SnapTransactionDetails();
//        snapTransactionDetails.setOrderId("ORDER123");
//        snapTransactionDetails.setGrossAmount(10000);
//
//        // Set up custom color theme
//        CustomColorTheme customColorTheme = new CustomColorTheme("#FF0000", "#00FF00", "#0000FF");
//
//        // Set up the transaction
//        Transaction transaction = new Transaction(transactionRequest, itemDetailsArrayList, userDetail, snapTransactionDetails, customColorTheme);
//
//        // Request Snap Token from Midtrans
//        MidtransSDK.getInstance().getCardToken(transaction, new TokenCallback() {
//            @Override
//            public void onSuccess(SnapToken snapToken) {
//                // Got Snap token, now start payment using Snap
//                startPaymentUsingSnap(snapToken);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Toast.makeText(Pembayaran.this, "Error getting Snap token", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void startPaymentUsingSnap(SnapToken snapToken) {
//        // Initialize MidtransSDK
//        MidtransSDK.getInstance().setTransactionFinishedCallback(this);
//
//        // Set up credit card payment model (you may need to collect card details from user)
//        CreditCardPaymentModel creditCardPaymentModel = new CreditCardPaymentModel();
//        creditCardPaymentModel.setSaveCard(false);
//        creditCardPaymentModel.setAuthentication(CreditCardPaymentModel.AUTH_3DS);
//
//        // Set up MidtransPaymentDetails
//        MidtransPaymentDetails paymentDetails = new MidtransPaymentDetails("BCA", creditCardPaymentModel);
//
//        // Start payment using Snap
//        MidtransSDK.getInstance().startPaymentUiFlow(this, snapToken, paymentDetails);
//    }
//
//    @Override
//    public void onTransactionFinished(TransactionResult result) {
//        if (result.getResponse() != null) {
//            TransactionResponse response = result.getResponse();
//            String message = response.getStatusMessage();
//
//            if (response.getStatusCode().equals("200")) {
//                // Payment success
//                Toast.makeText(this, "Payment Success: " + message, Toast.LENGTH_SHORT).show();
//            } else {
//                // Payment failed
//                Toast.makeText(this, "Payment Failed: " + message, Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            // Payment canceled
//            Toast.makeText(this, "Payment Canceled", Toast.LENGTH_SHORT).show();
//        }
//    }
//}