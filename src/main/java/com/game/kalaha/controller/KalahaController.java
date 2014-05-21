package com.game.kalaha.controller;

import com.game.kalaha.service.KalahaService;
import com.game.kalaha.util.KalahaUtilEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.game.kalaha.model.KalahaGame;

import javax.annotation.Resource;

@Controller
public class KalahaController {

    @Resource(name = "kalahaService")
    private KalahaService kalahaService;

    KalahaGame game;
	
	@RequestMapping(value = "/game.do", method = RequestMethod.GET)
	public String startGame(Model model) {
		game = new KalahaGame();
		model.addAttribute("game", game);
        model.addAttribute("rowLength", KalahaUtilEnum.PITS_FOR_PLAYER.getValue());
        model.addAttribute("totalPits", KalahaUtilEnum.NUMBER_OF_PITS.getValue());
		return "game";
	}
	
	@RequestMapping(value = "/move.do", method = RequestMethod.GET)
	public String move(@RequestParam String pitId, Model model) {
        int clickedPitId = Integer.valueOf(pitId).intValue();
        game = kalahaService.startTheGame(clickedPitId, game);
        model.addAttribute("game", game);
        model.addAttribute("rowLength", KalahaUtilEnum.PITS_FOR_PLAYER.getValue());
        model.addAttribute("totalPits", KalahaUtilEnum.NUMBER_OF_PITS.getValue());
        model.addAttribute("message", game.getMessage());
        return "game";
	}

}