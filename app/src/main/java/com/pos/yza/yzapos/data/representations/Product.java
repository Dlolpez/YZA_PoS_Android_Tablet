package com.pos.yza.yzapos.data.representations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dlolpez on 26/12/17.
 */

public final class Product implements Item {
    int id;
    Double unitPrice;
    String unitMeasure;
    ArrayList<ProductProperty> properties;
    ProductCategory category;

    public Product(int id, Double unitPrice, String unitMeasure, ProductCategory category,
                   ArrayList<ProductProperty> properties){
        this.id = id;
        this.unitPrice = unitPrice;
        this.unitMeasure = unitMeasure;
        this.category = category;
        this.properties = properties;
    }

    public Product(Double unitPrice, String unitMeasure, ProductCategory category,
                   ArrayList<ProductProperty> properties){
        this.unitPrice = unitPrice;
        this.unitMeasure = unitMeasure;
        this.category = category;
        this.properties = properties;
    }

    public void setProperties(){}

    public int getId() {return id;}

    public Double getUnitPrice() {
        return unitPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public ArrayList<ProductProperty> getProperties() {
        return properties;
    }

    @Override
    public List<String> showEditableFields() {
        return null;
    }

    @Override
    public String getName() {
        return category + Double.toString(unitPrice) + unitMeasure;
    }

    @Override
    public String toString(){
        String toReturn = getName() + "\n properties: ";

        for (ProductProperty property: properties) {
            toReturn += property;
        }

        return toReturn;
    }
}

