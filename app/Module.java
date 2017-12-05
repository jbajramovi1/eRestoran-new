import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    public void configure(){
        bind(InitialData.class).asEagerSingleton();
    }

}
