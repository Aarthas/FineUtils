package com.arthas.finefragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.arthas.finefragment.R;
import com.arthas.finefragment.anno.IBackPressedActivity;
import com.arthas.finefragment.fragment.BackpressedFragment;


/**
 * Created by Administrator on 2016/8/16.
 */
public class FineBaseFragActivity extends AppCompatActivity implements IBackPressedActivity
{
    public static final String FRAG_CLASS = "FRAG_CLASS";
    public static final String MAIN_TAG = "mainView";
    public FineBaseFragActivity context;

    public Fragment currentFragment;

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        context = this;
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setId(R.id.mainView);
        setContentView(frameLayout);

        Intent intent = getIntent();
        Class clazz = (Class) intent.getSerializableExtra(FRAG_CLASS);
        try
        {
            currentFragment = (Fragment) clazz.newInstance();
            Bundle bundle = context.getIntent().getBundleExtra("bundle");
            currentFragment.setArguments(bundle);


            FragmentManager fManager = getSupportFragmentManager();
            FragmentTransaction transaction = fManager.beginTransaction();
            transaction.add(R.id.mainView, currentFragment, MAIN_TAG);
            transaction.commitAllowingStateLoss();

        } catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (currentFragment!=null)
            currentFragment.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed()
    {
        if (currentFragment != null && currentFragment instanceof BackpressedFragment)
        {
            if (!((BackpressedFragment) currentFragment).onBackPressed())
            {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0)
                {
                    super.onBackPressed();
                } else
                {
                    getSupportFragmentManager().popBackStack();
                }
            }
        } else
        {
            super.onBackPressed();
        }

    }

    @Override
    public void setSelectedFragment(BackpressedFragment selectedFragment)
    {
        currentFragment = selectedFragment;
    }
}
