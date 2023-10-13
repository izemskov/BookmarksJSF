package ru.develgame.bookmarks.jsf.model;

import ru.develgame.bookmarks.jsf.ConsoleBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

//@FacesConverter("consoleConverter")
public class ConsoleConverter /*implements Converter*/ {
//    @Inject
//    private ConsoleBean consoleBean;
//
//    @Override
//    public Console getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
//        int value = Integer.parseInt(s);
//
//        return consoleBean.getOptions().stream()
//                .filter(t -> t.getId() == value)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Option not found"));
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
//        return Integer.toString(((Console) o).getId());
//    }
}
