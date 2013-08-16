package com.avicit.framework.web.support.editor;

import java.beans.PropertyEditorSupport;

@SuppressWarnings({"rawtypes", "unchecked"})
public class EnumEditor extends PropertyEditorSupport {

    private Class enumClass;

    public EnumEditor(Class cls) {
        this.enumClass = cls;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && text.trim().length() > 0) {
            setValue(Enum.valueOf(enumClass, text));
        }
    }

    @Override
    public String getAsText() {
        return (getValue() == null) ? "" : getValue().toString();
    }
}
