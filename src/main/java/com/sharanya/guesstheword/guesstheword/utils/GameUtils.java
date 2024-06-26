package com.sharanya.guesstheword.guesstheword.utils;

import com.sharanya.guesstheword.guesstheword.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GameUtils {


    @Autowired
    ConfigurableApplicationContext applicationContext;
    private int MAX_TRIES = 5;

    public int reduceTrial(){
        MAX_TRIES = MAX_TRIES-1;
        return  MAX_TRIES;
    }

    public void resetTries(){
        MAX_TRIES = 5;
    }

    public int getTriesRemaining(){
        return MAX_TRIES;
    }


    public boolean checkSuccess(String randomWord,int triesleft){

        for(char c: randomWord.toCharArray()){
            if (c == '\u005F') {
                return false;
            }
        }
        if(triesleft<=MAX_TRIES && triesleft>0){
            return true;
        }
        return false;
    }

    public GameService reload(){


        GameService gameService= applicationContext.getBean(GameService.class);
        return gameService;

    }
}
