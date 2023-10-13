package ru.develgame.bookmarks.jsf;

import ru.develgame.bookmarks.jsf.model.Console;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@ApplicationScoped
public class ConsoleBean {
    private List<Console> options = Arrays.asList(new Console(1, "PS3", 1000),
            new Console(2, "PS4", 2000),
            new Console(3, "PS5", 3000));

    public List<Console> getOptions() {
        return options;
    }
}
