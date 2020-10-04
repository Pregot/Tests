package Philate;

import philatelist.Philatelist;
import philatelist.PhilatelistBaseTest;

public class PhilatelistBaseTestPBT extends PhilatelistBaseTest  {

	@Override
	protected Philatelist getPhilatelist() {
		PostStampps pav = new PostStampps();
		return pav;
	}
	
}
