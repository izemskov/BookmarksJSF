package ru.develgame.bookmarks.jsf;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("userBean")
public class UserBean implements Serializable {
    public String getUsername() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }
}
