package com.xtronlabs.koochooloo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.fragment.CountryFragment;

public class CountryDetailsActivity extends BaseActivity {


    public static final String COUNTRY_FLAG_LINK = "link";
    public static final String COUNTRY_NAME ="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        Intent callingIntent = getIntent();
        if(callingIntent == null)
            throw  new NullPointerException("No intent or data found to select country");

        String link = callingIntent.getStringExtra(COUNTRY_FLAG_LINK);
        int id = callingIntent.getIntExtra(RecipeActivity.COUNTRY_ID,0);
        String countryName = callingIntent.getStringExtra(COUNTRY_NAME);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainContentHolder, CountryFragment.newInstance(id, link,countryName), CountryFragment.TAG)
                .commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
