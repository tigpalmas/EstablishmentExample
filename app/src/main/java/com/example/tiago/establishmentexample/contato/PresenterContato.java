package com.example.tiago.establishmentexample.contato;

import com.example.tiago.establishmentexample.domain.Contato;
import com.example.tiago.establishmentexample.product.MVP;

/**
 * Created by tiago on 02/11/2017.
 */

public class PresenterContato implements MVP.PresenterContato {
    private MVP.ModelContato model;
    private MVP.ViewContato view;

    public PresenterContato() {
        model = new ModelContato(this);
    }

    @Override
    public void postContato(Contato contato) {
        model.postContato(contato);
    }

    @Override
    public void setView(MVP.ViewContato view) {
        this.view = view;
    }



}
