package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import finalproject.ski2rent.R;
import finalproject.ski2rent.callbacks.CallBack_GetShoppingCartData;
import finalproject.ski2rent.objects.ShoppingCart;
import finalproject.ski2rent.utils.FireBaseManager;

public class Activity_Base extends AppCompatActivity {
    private boolean isShoppingCartReturned = false;
    private ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__base);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FireBaseManager fireBaseManager = FireBaseManager.getInstance();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            fireBaseManager.readShoppingCartDataFromServer(uid, new CallBack_GetShoppingCartData() {
                @Override
                public void retrieveShoppingCart(ShoppingCart sc) {
                    shoppingCart = sc;
                    isShoppingCartReturned = true;
                }
            });
        }

    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.my_menu is a reference to an xml file named my_menu.xml which should be inside your res/menu directory.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

            if (id == R.id.cart && isShoppingCartReturned) {
                openShoppingCartActivity(this);
            } else if (id == R.id.login) {
                openLoginActivity(this);
            }

        return super.onOptionsItemSelected(item);
    }

    private void openLoginActivity(Activity activity) {
        Intent myIntent = new Intent(this, Activity_Login.class);
        startActivity(myIntent);
        if (!(this instanceof Activity_MainMenu)) {
            finish();
        }
    }

    private void openShoppingCartActivity(Activity activity) {
        String shoppingCartJson;
        shoppingCartJson = new Gson().toJson(shoppingCart);

        Intent myIntent = new Intent(this, Activity_ShoppingCart.class);
        myIntent.putExtra(Activity_ShoppingCart.EXTRA_KEY_SHOPPING_CART, shoppingCartJson);
        startActivity(myIntent);
        if (!(this instanceof Activity_MainMenu)) {
            finish();
        }

    }

} // Activity_Base