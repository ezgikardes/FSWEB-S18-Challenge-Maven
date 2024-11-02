package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cards")
public class CardController {
    CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    @PostMapping
    public Card addCard(@RequestBody Card card){
        return cardRepository.save(card);
    }

    @PutMapping
    public Card updateCard(@RequestBody Card card){
        CardValidation.validateCard(card);
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public Card deleteCard(@PathVariable Long id){
        return cardRepository.remove(id);
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getCardsByValue(@PathVariable Integer value){
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getCardsByColor(@PathVariable String color){
        CardValidation.validateColor(color);
        return cardRepository.findByColor(color.toUpperCase());
    }

    @GetMapping("/byType/{type}")
    public List<Card> getCardsByType(@PathVariable String type){
        return cardRepository.findByType(type);
    }
}
