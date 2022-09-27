import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable {

    //region private attributes
    private List<IObserver> _observers = new ArrayList<IObserver>();
    private List<String> _twits = new ArrayList<String>();
    //endregion private attributes

    public Twitter(){
        _observers = new ArrayList<>();
        _twits = new ArrayList<>();
    }

    public Twitter(List<IObserver> observers){
        this._observers = observers;
        _twits = new ArrayList<>();
    }

    public List<IObserver> getObservers(){
        return _observers;    }

    public List<String> getTwits(){
        return _twits;
    }

    public String lastTwit(){
        throw new UnsupportedOperationException();
    }

    public void post(String twit){
        throw new RuntimeException();
    }

    @Override
    public void subscribe(List<IObserver> observer) {
        for(IObserver o : observer){
            if(_observers.contains(o)){
                throw new SubscriberAlreadyExistsException();
            }else {
                _observers.add(o);
            }
        }
    }

    @Override
    public void unsubscribe(IObserver observer) {
        if(_observers.isEmpty()) {
            throw new EmptyListOfSubscribersException();
        }

        if(!_observers.remove(observer)) {
            throw new SubscriberNotFoundException();
        }
    }

    @Override
    public void notifyObservers() {
        throw new EmptyListOfSubscribersException();
    }

    public class TwitterException extends RuntimeException { }
    public class EmptyListOfSubscribersException extends TwitterException { }
    public class SubscriberAlreadyExistsException extends TwitterException { }
    public class SubscriberNotFoundException extends TwitterException { }
}
