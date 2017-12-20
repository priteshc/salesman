package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.Product;
import com.example.dec.mylocation.pojo.ProductDetailsPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class ProductPresenter extends BasePresentre implements Observer<ProductDetailsPojo> {


    private ProductInterface visitInterface;

    public ProductPresenter(ProductInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onProdComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onProdError(e.getMessage());

    }

    @Override
    public void onNext(ProductDetailsPojo aquLoginPojo) {

        visitInterface.onProduct(aquLoginPojo);

    }


    public void getData(String cate){

        subscribe(visitInterface.getProduct(cate),ProductPresenter.this);

    }


}
