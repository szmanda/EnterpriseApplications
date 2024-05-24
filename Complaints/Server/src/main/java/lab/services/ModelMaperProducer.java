package lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ModelMaperProducer {
    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
