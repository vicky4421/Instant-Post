package com.vismijatech.instantpost

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ConsumeResponseListener
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vismijatech.instantpost.components.HollowButton
import com.vismijatech.instantpost.data.NAVIGATE_TO_DASHBOARD
import com.vismijatech.instantpost.ui.theme.InstantPostTheme
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily

class CreditScreenActivity : ComponentActivity() {

    private lateinit var billingClient: BillingClient
    private lateinit var productIds: List<String>
    lateinit var credits: List<Int>
    private lateinit var productDetailLists: List<ProductDetails>
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseDatabase
    lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstantPostTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreditScreen(
                        toDashboard = {
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra(NAVIGATE_TO_DASHBOARD, true)
                            startActivity(intent)
                        }
                    ) {
                        launchPurchaseFlow(productDetailLists[0])
                    }
                }
            }
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()
        val currentUser = auth.currentUser
        val uid = currentUser?.uid
        dbRef = db.getReference("users").child(uid.toString())

        /*    Buy Credits    */
        productIds = listOf("100credits")
        credits = listOf(100)

        //Initialize a BillingClient with PurchasesUpdatedListener onCreate method
        billingClient = BillingClient.newBuilder(this)
            .enablePendingPurchases()
            .setListener { billingResult, list ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && list != null) {
                    list.forEach { purchase -> verifyPurchase(purchase) }
                }
            }
            .build()

        //start the connection after initializing the billing client
        establishConnection()
        // buyCreditsView.setOnClickListener(v -> launchPurchaseFlow(productDetailsList.get(0)));
    }

    private fun establishConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                establishConnection()
            }

            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) showProducts()
            }
        })
    }

    private fun showProducts() {
        val productList = listOf(
            // product
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("100credits")
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(productList)
            .build()

        billingClient.queryProductDetailsAsync(params) { _, list ->
            productDetailLists = ArrayList()
            (productDetailLists as ArrayList<ProductDetails>).clear()
            (productDetailLists as ArrayList<ProductDetails>).addAll(list)
        }
    }

    private fun launchPurchaseFlow(productDetails: ProductDetails) {
        val productDetailsParamsList = listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                .setProductDetails(productDetails)
                .build()
        )
        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()
        val billingResult = billingClient.launchBillingFlow(this, billingFlowParams)
    }

private fun verifyPurchase(purchase: Purchase?) {
    val consumeParams: ConsumeParams = ConsumeParams.newBuilder()
        .setPurchaseToken(purchase!!.purchaseToken)
        .build()
    val listener = ConsumeResponseListener { billingResult, _ ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) giveUserCoins(
            purchase
        )
    }
    billingClient.consumeAsync(consumeParams, listener)
}

private fun giveUserCoins(purchase: Purchase) {

    for (element in productIds) {
        if (purchase.products[0] == element) {
            dbRef.child("credit").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val currentCredits = snapshot.getValue(Int::class.java) ?: 0
                    val updatedCredits = currentCredits + 100
                    dbRef.child("credit").setValue(updatedCredits)
                    Toast.makeText(
                        this@CreditScreenActivity,
                        "Thank you for your purchase! Your credits are now ready to use. Enjoy the benefits!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onCancelled(error: DatabaseError) {
//                        Completed("Not yet implemented")
                }
            })
        }
    }
}

override fun onResume() {
    super.onResume()
    billingClient.queryPurchasesAsync(
        QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP)
            .build()
    ) { billingResult, list ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            list.forEach { purchase ->
                if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
                    verifyPurchase(purchase)
                }
            }
        }
    }
}
}


@Composable
fun CreditScreen(toDashboard: () -> Unit, launchPurchaseFlow: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { launchPurchaseFlow() }
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.buy_credits),
            contentDescription = "buy credits"
        )
        Text(
            text = stringResource(id = R.string.buyCredit_imageAttribute),
            style = TextStyle(fontWeight = FontWeight.Light),
            fontFamily = PoppinsFontFamily
        )

        Text(
            text = stringResource(R.string.buyCredit_tapToBuy),
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontFamily = PoppinsFontFamily
        )
        HollowButton(label = stringResource(id = R.string.buyCredit_goToDashboard)) {
            toDashboard()
        }
    }
}

@Preview
@Composable
fun CredScreen() {
    CreditScreen(toDashboard = {}) {

    }
}

