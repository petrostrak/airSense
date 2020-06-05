/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.dao;

import com.oop.entities.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Walter
 */
@Repository
public interface ISubscriptionDao extends CrudRepository<Subscription, Long> {
    Subscription findByUserId(long userId);
    boolean existsByUserId(long userId);
}
