package ili.jai;

public class Rendu2 extends TestJDBC2 {

	@Override
	protected ServicePersistance getServicePersistance() {
		// TODO Auto-generated method stub
		return new ServicePersistanceImp();
	}

}
