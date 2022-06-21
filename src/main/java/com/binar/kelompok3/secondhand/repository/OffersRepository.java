package com.binar.kelompok3.secondhand.repository;

import com.binar.kelompok3.secondhand.model.entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OffersRepository extends JpaRepository<Offers, Integer> {

    @Query(value = "select * from offers", nativeQuery = true)
    List<Offers> getAllOffers();

    List<Offers> getAllByUserId(Integer userId);

    @Query(value = "select * from offers where offers.product_id = (select id from products where prodcuts.user_id = users.id)", nativeQuery = true)
    List<Offers> getHistorySeller(Integer productId, Integer userId);

    Integer deleteOffersById(Integer id);

    @Modifying
    @Query(value = "update offers set status=?2 where id=?1", nativeQuery = true)
    Integer updateOffers(Integer id, Integer status);

    Offers getOffersById(Integer id);

}