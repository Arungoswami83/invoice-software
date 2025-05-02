package com.amstech.invoice.service.response.model;

public class BusinessTypesResponseModel {
	
	private int id;
    private String typeName;

    public  BusinessTypesResponseModel(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}



