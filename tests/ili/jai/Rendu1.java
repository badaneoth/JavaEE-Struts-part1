package ili.jai;

public class Rendu1 extends TestJDBC1 {

	@Override
	protected ServicePersistance getServicePersistance() {
		// TODO Auto-generated method stub
		return new ServicePersistanceImp();
	}

}
