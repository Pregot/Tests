package flights;

import lt.itakademija.exam.FlightManager;
import lt.itakademija.exam.test.BaseTest;

public class MyBaseTest extends BaseTest {

	@Override
	protected FlightManager createFlightManager() {
		FlightManager pav = new MyFlightManager();
		return pav;
	}

}
