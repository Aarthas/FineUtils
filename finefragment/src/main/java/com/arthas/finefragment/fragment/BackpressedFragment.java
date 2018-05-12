package com.arthas.finefragment.fragment;

import com.arthas.finefragment.anno.IBackPressedActivity;

/**
 * Created by Administrator on 2016/8/18.
 */
public abstract class BackpressedFragment extends BaseFragment
{

    @Override
    public void onResume()
    {
        super.onResume();

        if (getActivity() instanceof IBackPressedActivity)
        {
            ((IBackPressedActivity) getActivity()).setSelectedFragment(this);
        }
    }

    public boolean onBackPressed()
    {
        return false;
    }

}
