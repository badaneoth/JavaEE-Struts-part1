package ili.jai;

public class Rendu3 extends TestJDBC3 {
	@Override
	protected ServicePersistance getServicePersistance() {
		// TODO Auto-generated method stub
		return new ServicePersistanceImp();
	}

}
