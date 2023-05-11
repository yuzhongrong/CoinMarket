package com.zksg.kudoud.beans;

import com.contrarywind.interfaces.IPickerViewData;

public class LanguageItem implements IPickerViewData {
    private String name;

    public LanguageItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
