package ru.develgame.bookmarks.jsf;

import ru.develgame.bookmarks.jsf.model.Console;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("test")
@ViewScoped
public class TestBean implements Serializable, Converter {
    @Inject
    private transient ConsoleBean consoleBean;

    private Console selectedOption;

    public void testAction() {
        System.out.println(selectedOption);
    }

    public Console getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Console selectedOption) {
        this.selectedOption = selectedOption;
    }

    public List<Console> getOptions() {
        return consoleBean.getOptions();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        int value = Integer.parseInt(s);

        return consoleBean.getOptions().stream()
                .filter(t -> t.getId() == value)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Option not found"));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return Integer.toString(((Console) o).getId());
    }
}
