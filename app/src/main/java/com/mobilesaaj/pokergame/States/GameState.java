package com.mobilesaaj.pokergame.States;

/**
 * Created by neeraj.singh on 06/09/15.
 */
public enum GameState {
    Init,
    Running,
    Ended,
    Unkown, PreInit;

    public GameState lookupForValue(String type){
        for(GameState state : GameState.values()){
            if(type.equalsIgnoreCase(state.toString())){
                return state;
            }
        }
        return Unkown;
    }
}
