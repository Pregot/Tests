package lengvas;

import lt.vtvpmc.java.postoffice.AbstractPostOfficeTest;
import lt.vtvpmc.java.postoffice.PostOffice;

public class MyAbstractPostOfficeTest extends AbstractPostOfficeTest {

	@Override
	protected PostOffice getPostOffice() {
		PostOffice pav = new MyPostOffice();
		return pav;
	}

}
