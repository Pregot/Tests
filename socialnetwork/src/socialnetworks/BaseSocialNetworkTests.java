package socialnetworks;

import lt.infobalt.itakademija.javalang.exam.socialnetwork.BaseSocialNetworkTest;
import lt.infobalt.itakademija.javalang.exam.socialnetwork.SocialNetwork;

public class BaseSocialNetworkTests extends BaseSocialNetworkTest  {

	@Override
	protected SocialNetwork getSocialNetwork() {
		SocialNetwork pav = new SocialNetworks();
		return pav;
	}

}
