package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.ProductDetailsPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface ProductInterface {


    void onProdComplete();
    void onProdError(String s);
    void onProduct(ProductDetailsPojo aquLoginPojo);
    Observable<ProductDetailsPojo> getProduct(String cate);


}
