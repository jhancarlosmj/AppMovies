package com.jhancarlos.appmovies.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.adapters.AdapterNavigationDrawer;
import com.jhancarlos.appmovies.interfaces.RecycleViewItemInterface;
import com.jhancarlos.appmovies.models.ItemNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAM on 21/4/15.
 */
public class NavigationDrawerFragment extends Fragment {

    /*Remember the position of the selected item.*/
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private int mCurrentSelectedPosition = 0;
    public static final String PREF_FILE_NAME = "app_preferences";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawe";
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String name = "JhanCarlos MJ";
    private String email = "jhancarlos.mj@gmail.com";
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    AdapterNavigationDrawer adapterNavigationDrawer;
    int PROFILE = R.drawable.ll;
    private List<RecycleViewItemInterface> listViewItems;
    private boolean mUserLearnedDrawer;
    private boolean mFromSaveInstanceState;
    private View mNavigationDrawerView;


    public NavigationDrawerFragment(){

    }

    public static void saveToPreferences(Context context,String preferenceName,String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(preferenceName,preferenceValue);

        //Faster than commit
        editor.apply();
    }

    public static String readFromPreferences(Context context,String preferenceName, String defaulValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,context.MODE_PRIVATE);

        return sharedPreferences.getString(preferenceName,defaulValue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listViewItems = new ArrayList<>();

        listViewItems.add(new ItemNavigationDrawer(0,getResources().getColor(R.color.accent),R.string.app_name));
        listViewItems.add(new ItemNavigationDrawer(R.drawable.ic_home,getResources().getColor(R.color.primary_dark),R.string.home));
        listViewItems.add(new ItemNavigationDrawer(R.drawable.ic_movies,getResources().getColor(R.color.primary_dark),R.string.movies));

        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
        if (savedInstanceState !=null){
            mFromSaveInstanceState =  true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        adapterNavigationDrawer = new AdapterNavigationDrawer(listViewItems,name,email,PROFILE);
        mRecyclerView.setAdapter(adapterNavigationDrawer);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void setUp(int fragmentId,final DrawerLayout drawerLayout, final Toolbar toolbar,AdapterNavigationDrawer.ItemRecycleClickListener itemRecycleClickListener){
        //Conseguir la vista de la id pasada
        mNavigationDrawerView = getActivity().findViewById(fragmentId);

        //guardar el drawerlayout
        mDrawerLayout = drawerLayout;

        //Establecemos una sombra al drawer layout
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        //Listener del menu
        adapterNavigationDrawer.setItemRecycleClickListener(itemRecycleClickListener);

        //Instanciar el ActionDrawerToogle (Hamburgesa)
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){

            //En el dispositivo pre-Lollipop el navigation drawer se abrira automaticamente cada vez que se abra la aplicacion por primera vez


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                //si es la primera vez que el usuario abre el navigation drawer
                if (!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,String.valueOf(mUserLearnedDrawer));
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerSlide(View drawerView,float slideOffset){
                super.onDrawerSlide(drawerView,slideOffset);
                //we fade the toolbar
                if (slideOffset < 0.5)
                    toolbar.setAlpha(1 - slideOffset);
            }
        };

        if (!mUserLearnedDrawer && !mFromSaveInstanceState){
            mDrawerLayout.openDrawer(mNavigationDrawerView);
        }

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        //sincronizar el estado de la hamburguesa con el del navigation drawer
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });


    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfiguration){
        super.onConfigurationChanged(newConfiguration);
        mActionBarDrawerToggle.onConfigurationChanged(newConfiguration);
    }
}
