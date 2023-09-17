package submission.project.core.util;

import submission.project.core.controllers.CabangController;
import submission.project.core.controllers.CustomerController;
import submission.project.core.controllers.ProdukController;
import submission.project.core.controllers.TransaksiController;
import submission.project.core.models.repositories.repositoryImpl.CabangRepositoryImpl;
import submission.project.core.models.repositories.repositoryImpl.ProdukRepositoryImpl;
import submission.project.core.models.repositories.repositoryImpl.TransaksiRepositoryImpl;
import submission.project.core.services.serviceImpl.CabangServiceImpl;
import submission.project.core.services.serviceImpl.ProdukServiceImpl;
import submission.project.core.services.serviceImpl.TransaksiServiceImpl;

public class CustomerUtil {

    private static final CustomerController customerController;

    static{
        ProdukRepositoryImpl produkRepository = new ProdukRepositoryImpl();
        CabangRepositoryImpl cabangRepository = new CabangRepositoryImpl();
        TransaksiRepositoryImpl transaksiRepository = new TransaksiRepositoryImpl();

        ProdukServiceImpl produkService = new ProdukServiceImpl(produkRepository);
        CabangServiceImpl cabangService = new CabangServiceImpl(cabangRepository);
        TransaksiServiceImpl transaksiService = new TransaksiServiceImpl(transaksiRepository, produkRepository, cabangRepository);

        ProdukController produkController = new ProdukController(produkService);
        CabangController cabangController = new CabangController(cabangService);
        TransaksiController transaksiController = new TransaksiController(transaksiService);

        customerController = new CustomerController(produkController, cabangController, transaksiController);
    }

    public static CustomerController getCustomerController() {
        return customerController;
    }
}
