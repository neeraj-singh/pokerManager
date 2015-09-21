package com.mobilesaaj.pokergame.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mobilesaaj.pokergame.Constants.GameConstant;
import com.mobilesaaj.pokergame.Fragments.BaseFragment;
import com.mobilesaaj.pokergame.Fragments.GameOverFragment;
import com.mobilesaaj.pokergame.Fragments.InitGame;
import com.mobilesaaj.pokergame.Fragments.RunnigGameFragment;
import com.mobilesaaj.pokergame.Fragments.StartGameFragment;
import com.mobilesaaj.pokergame.Interfaces.GameFlowInterface;
import com.mobilesaaj.pokergame.R;
import com.mobilesaaj.pokergame.States.GameState;

public class MainActivity extends AppCompatActivity implements GameFlowInterface {

    private FragmentManager fragmentManager ;
    private BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if(GameConstant.gameState == GameState.PreInit) {
            MoveToNextState(GameState.PreInit);
        }
    }

    @Override
    public void MoveToNextState(GameState state) {
        switch (state){
            case Init:
                currentFragment = StartGameFragment.instance();
                moveToNextFragment(currentFragment);
                break;
            case PreInit:
                currentFragment = InitGame.instance();
                moveToNextFragment(currentFragment);
                break;
            case Running:
                currentFragment = RunnigGameFragment.instance();
                moveToNextFragment(currentFragment);
                break;
            case Ended:
                currentFragment = GameOverFragment.instance();
                moveToNextFragment(currentFragment);
                break;
        }
    }

    private void moveToNextFragment(BaseFragment fragment){
        if(fragment!=null)
            fragmentManager.beginTransaction()
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .replace(R.id.fragmentContainer, fragment, fragment.getClass().getSimpleName())
                    .commit();
    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() > 0 ){
            if(currentFragment!=null && !currentFragment.handleBackPressed()){
                fragmentManager.popBackStack();
            }
            fragmentManager.popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}
