package com.sharanya.guesstheword.guesstheword.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
@Scope("prototype")
public class GameService {

    private String randomlyChosenWord = null;
    private char[] allCharactersofTheWord;
    private String[] randomWords={"father","sister","hello","candlelights","dinner","java","tutorial"};
    Random random = new Random();

    public GameService(){

        randomlyChosenWord = randomWords[random.nextInt(randomWords.length)];
        System.out.println(randomlyChosenWord);
        allCharactersofTheWord = new char[randomlyChosenWord.length()];

    }


    @Override
    public String toString() {

        String ret="";

        for(char c:allCharactersofTheWord){
            if(c=='\u0000'){
                ret=ret+"_";
            }
            else {
                ret = ret+ c;
            }
            ret=ret+' ';
        }

        return ret;

    }

    public boolean addGuess(char guessedChar) {
        boolean isGuessed = false;

        for(int i=0;i<randomlyChosenWord.length();i++){
            if(guessedChar == randomlyChosenWord.charAt(i)){
                allCharactersofTheWord[i]=guessedChar;
                isGuessed=true;
            }
        }

        return isGuessed;
    }

}
