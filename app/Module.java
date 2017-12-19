import com.google.inject.AbstractModule;

/**
 * The type Module.
 */
public class Module extends AbstractModule {
    @Override
    public void configure() {
        bind(InitialData.class).asEagerSingleton();
    }

}
