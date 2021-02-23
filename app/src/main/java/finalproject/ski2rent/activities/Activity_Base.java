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
import finalproject.ski2rent.objects.ShoppingCart;
import finalproject.ski2rent.utils.FireBaseManager;

public class Activity_Base extends AppCompatActivity {

    protected Menu mymMenu;
    protected FireBaseManager fireBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("baseLifeCycle", "onCreate: Activity_Base");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__base);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fireBaseManager = FireBaseManager.getInstance();
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mymMenu = menu;
        getMenuInflater().inflate(R.menu.my_menu, menu);
        if (FireBaseManager.getInstance().isCurrentUserLoggedIn()) {
            menu.getItem(0).setIcon(R.drawable.ic_user_logout);
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_user_login);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        invalidateOptionsMenu();
        int id = item.getItemId();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

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
                fireBaseManager.setShoppingCartReturned(false);
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

} // Activity_Base