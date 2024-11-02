package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class CardRepositoryImpl implements CardRepository{

    EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> allCards = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        return allCards.getResultList();
    }

    @Transactional
    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color", Card.class);

        List<Card> results = query.getResultList();
        if (results.isEmpty()){
            throw new CardException("Color not found: " + color, HttpStatus.NOT_FOUND);
        }
        return query.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value", Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type", Card.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Card update(Card card) {
        entityManager.merge(card);
        return card;
    }

    @Transactional
    @Override
    public Card remove(Long id) {
        Card card = entityManager.find(Card.class, id);
        entityManager.remove(card);
        return card;
    }
}