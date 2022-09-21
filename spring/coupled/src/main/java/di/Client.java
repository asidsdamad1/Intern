package di;

import di.repository.AbstractRepo;
import di.repository.FactoryRepo;

class Client {
    AbstractRepo abstractRepository;

    public Client() {
        abstractRepository = FactoryRepo.getRepository();
    }

    public AbstractRepo getAbstractRepository() {
        return abstractRepository;
    }

    public void setAbstractRepository(AbstractRepo abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    public void execute() {
        abstractRepository.insert();
        abstractRepository.update();
        abstractRepository.delete();
    }
}
