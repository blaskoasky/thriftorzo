package com.binar.kelompok3.secondhand.service.offers;

import com.binar.kelompok3.secondhand.model.entity.Offers;
import com.binar.kelompok3.secondhand.model.entity.Products;
import com.binar.kelompok3.secondhand.model.entity.Users;
import com.binar.kelompok3.secondhand.repository.OffersRepository;
import com.binar.kelompok3.secondhand.service.products.IProductsService;
import com.binar.kelompok3.secondhand.service.users.IUsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OffersServiceImpl implements IOffersService {

    private OffersRepository offersRepository;
    private IUsersService iUsersService;
    private IProductsService iProductsService;

    @Override
    public List<Offers> getAllOffers() {
        return offersRepository.getAllOffers();
    }

    @Override
    public void saveOffers(Integer userId, String productId, Double offerPrice, Integer status) {
        Offers offers = new Offers();
        Users users = iUsersService.findUsersById(userId);
        Products products = iProductsService.findProductsById(productId);
        offers.setUserId(users);
        offers.setProductId(products);
        offers.setOfferPrice(offerPrice);
        offers.setStatus(status);
        offersRepository.save(offers);
    }

    //service untuk riwayat tawaran buyer
    @Override
    public List<Offers> getAllByUserId(Integer userId) {
        return offersRepository.findAllByUserId(userId);
    }

    //service untuk riwayat tawaran penjual

    @Override
    public List<Offers> getHistorySeller(Integer userId) {
        return offersRepository.getHistorySeller(userId);
    }


    //update status pada tawaran
    @Override
    public void updateOffers(Integer id, Integer status) {
        offersRepository.updateOffers(id, status);
    }

    @Override
    public void deleteOffersById(Integer id) {
        offersRepository.deleteOffersById(id);
    }

    @Override
    public Offers getOffersById(Integer id) {
        return offersRepository.getOffersById(id);
    }

}
