package ulukmyrzategin.jsonplaceholder.ui;

/**
 * Created by $TheSusanin on 15.08.2018 17:11.
 */
public interface Lifecycle<O> {

    void bind(O view);

    void unbind();
}
