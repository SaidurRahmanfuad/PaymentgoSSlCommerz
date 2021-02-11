package com.saidur.paymentosslcommerz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCEMITransactionInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class PaymentActivity extends AppCompatActivity implements SSLCTransactionResponseListener {
public static final String Store_ID="srtlt602536bc89c1d";
public static final String Store_Password="srtlt602536bc89c1d@ssl";
 double Amount=10;
//public static final String Tran_ID="srtlt602536bc89c1d";

TextView success,faild,error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initView();
        //initPay();
        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization(
                Store_ID,
                "hb",
                Amount,
                SSLCCurrencyType.BDT,
                "123456789098765",
                "yourProductType",
                SSLCSdkType.TESTBOX);
       // CustomerInitializer();
        final SSLCCustomerInfoInitializer customerInfoInitializer = new SSLCCustomerInfoInitializer(
                "Motaleb", "abc@gmail.com",
                "address", "dhaka", "1214",
                "Bangladesh",
                "0179587891");
        //ProductInitializer();
        final SSLCProductInitializer productInitializer = new SSLCProductInitializer ("food", "food",
                new SSLCProductInitializer.ProductProfile.TravelVertical(
                        "Travel", "10",
                        "A", "12", "Dhk-Syl"));
      //  ShipmentInitialzer();
        final SSLCShipmentInfoInitializer shipmentInfoInitializer = new SSLCShipmentInfoInitializer (
                "Courier",
                2, new SSLCShipmentInfoInitializer.ShipmentDetails(
                "AA","Address 1",
                "Dhaka","1000","BD"));
        //EMI Initializer
        final SSLCEMITransactionInitializer emiTransactionInitializer = new SSLCEMITransactionInitializer(1);
        //SubmitData();
        IntegrateSSLCommerz
                .getInstance(PaymentActivity.this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .addEMITransactionInitializer(emiTransactionInitializer)
                .addCustomerInfoInitializer(customerInfoInitializer)
                .addProductInitializer(productInitializer)
                .buildApiCall(this);
    }


    public void initPay() {
        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization(
                Store_ID,
                "hb",
                Amount,
                SSLCCurrencyType.BDT,
                "123456789098765",
                "yourProductType",
                SSLCSdkType.TESTBOX);
    }

    private void initView() {
   success=findViewById(R.id.success);
   faild=findViewById(R.id.faild);
   error=findViewById(R.id.error);
    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {
     success.setText(/*sslcTransactionInfoModel.getAPIConnect()+*/sslcTransactionInfoModel.getStatus());
    }

    @Override
    public void transactionFail(String s) {
   faild.setText(s);
    }

    @Override
    public void merchantValidationError(String s) {
       error.setText(s);
    }
}