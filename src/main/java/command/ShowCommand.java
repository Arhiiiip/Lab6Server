package command;

import data.Movie;
import utility.MovieFactory;
import utility.RRHandler;

import java.io.IOException;

public class ShowCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public ShowCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        String result = "";
        System.out.println("СРАОТАО");
        for (Movie movie : movieFactory.getCollectionForWork()) {
            result = result + "\n" + movie.toString();
        }

        try {
            RRHandler.res(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
