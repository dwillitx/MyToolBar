package darin.com.mytoolbar;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Toolbar actionBarToolBar;



    private int currentPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // action bar toolbar
        actionBarToolBar = (Toolbar) findViewById(R.id.my_action_toolbar);

        //set first
        setSupportActionBar(actionBarToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set it to navigtion
//        actionBarToolBar.setNavigationIcon(R.drawable.ic_action_back_arrow);

//        actionBarToolBar.setNavigationContentDescription(getResources().getString(R.string.navigation_icon_description));

        //set the logo
        actionBarToolBar.setLogo(R.mipmap.ic_fitness);
        actionBarToolBar.setLogoDescription(getResources().getString(R.string.logo_description));



        //setup the fragament
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout),actionBarToolBar);


        //This will get the intent than check the action

        Intent searchIntent = getIntent();
        if (Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {

            //Get the text typed in for the Search
            String query = searchIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
        }


//        mainDisplay = (ImageView) findViewById(R.id.my_main_images);
        ImageView dumbBellImage = (ImageView) findViewById(R.id.imageDumbbells);

        dumbBellImage.setOnClickListener(this);


//        //Get the drawer_layout's list items from strings.xml
//        drawerListViewItems = getResources().getStringArray(R.array.titles);
//
//
//        //Get the ListView defineed in drawer_layout_layout.xml
//        drawerListView = (ListView) findViewById(R.id.left_drawer);
//
//        drawerListView.setAdapter(new ArrayAdapter<String>(this,
//                R.l, drawerListViewItems));
//
//        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
//
//        //Set the list's click listener
//        drawerListView.setOnItemClickListener( new DrawerItemClickKistener());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu this is how items are added to the action bar
        getMenuInflater().inflate(R.menu.main_menu, menu);

        //Create the searchview
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        //Get the search manager from the system
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);

        //bind the searchView object with the searchable component
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Handle actions from menu
        switch (item.getItemId()) {
            //Run code when setting is clicked
            case R.id.fitness_settings:

                Toast.makeText(MainActivity.this, "Fitness Option clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_hide_toolbar:

                Toast.makeText(MainActivity.this, "Hide Option clicked", Toast.LENGTH_SHORT).show();

                ActionBar ab = getSupportActionBar();
                ab.hide();

                break;
            case R.id.menu_search:
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {
            case R.id.imageDumbbells:
                intent = new Intent(v.getContext(), DetailActivity.class);
                startActivity(intent);


        }

    }


}
