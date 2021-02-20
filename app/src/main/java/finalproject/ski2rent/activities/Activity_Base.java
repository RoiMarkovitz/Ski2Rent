package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("baseLifeCycle", "onCreate: Activity_Base");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__base);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        // R.menu.my_menu is a reference to an xml file named my_menu.xml which should be inside your res/menu directory.
        getMenuInflater().inflate(R.menu.my_menu, menu);
//        int id = menu.getItem(0).getItemId();
        if (firebaseUser == null) {
            menu.getItem(0).setIcon(R.drawable.ic_user_login);
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_user_logout);
        }

        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        invalidateOptionsMenu();
        int id = item.getItemId();
        FireBaseManager fireBaseManager = FireBaseManager.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (id == R.id.cart) {
            if (fireBaseManager.isShoppingCartReturned()) {
                openShoppingCartActivity(this, fireBaseManager.getShoppingCart());
            }
            if (firebaseUser == null) {
                openLoginActivity(this);
            }
        }

        if (id == R.id.sign) {
            if (firebaseUser == null) {
                openLoginActivity(this);
            } else {
                FirebaseAuth.getInstance().signOut();
                // TODO fix here, if logout not in main menu the icon wont be refreshed
                if (!(this instanceof Activity_MainMenu)) {
                    finish();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }


    protected void openLoginActivity(Activity activity) {
        Intent myIntent = new Intent(this, Activity_Login.class);
        startActivity(myIntent);
        if (!(this instanceof Activity_MainMenu)) {
            activity.finish();
        }
    }

    protected void openShoppingCartActivity(Activity activity, ShoppingCart shoppingCart) {

        String shoppingCartJson;
        shoppingCartJson = new Gson().toJson(shoppingCart);

        Intent myIntent = new Intent(this, Activity_ShoppingCart.class);
        myIntent.putExtra(Activity_ShoppingCart.EXTRA_KEY_SHOPPING_CART, shoppingCartJson);
        startActivity(myIntent);
        if (!(this instanceof Activity_MainMenu)) {
            activity.finish();
        }

    }

    @Override
    protected void onStart() {
        Log.d("baseLifeCycle", "onStart: Activity_Base");
        super.onStart();
    }

} // Activity_Base