package finalproject.ski2rent.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import finalproject.ski2rent.R;

public class Activity_Base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__base);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

        if (id == R.id.cart) {
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
        Intent myIntent = new Intent(this, Activity_ShoppingCart.class);
        startActivity(myIntent);
        if (!(this instanceof Activity_MainMenu)) {
            finish();
        }

    }
    
} // Activity_Base