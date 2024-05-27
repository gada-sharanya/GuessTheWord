package com.sharanya.guesstheword.guesstheword.controllers;


import com.sharanya.guesstheword.guesstheword.services.GameService;
import com.sharanya.guesstheword.guesstheword.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameUtils gameUtils;



    @GetMapping("/game-home")
    public String showHomeGamePage(@RequestParam(value="guessedChar",required = false) String guessedChar, Model model){

        System.out.println(guessedChar);
        String randomWord = gameService.toString();



        if (guessedChar!=null){

            if(guessedChar.trim().isEmpty()){
                model.addAttribute("errorMessage","You cannot enter a blank value");
            }
            else{
                boolean isGuessed = gameService.addGuess(guessedChar.charAt(0));
                randomWord=gameService.toString();
                if(isGuessed == false){
                    gameUtils.reduceTrial();
                }
            }

        }


        model.addAttribute("wordToDisplay",randomWord);
        model.addAttribute("triesLeft",gameUtils.getTriesRemaining());

        return "game-home-page";
    }

    @GetMapping("/reload")
    public String reloadGame(){

        gameService = gameUtils.reload();
        gameUtils.resetTries();
        return "redirect:/game-home";

    }
}
