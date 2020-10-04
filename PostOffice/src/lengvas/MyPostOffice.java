package lengvas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import lt.vtvpmc.java.postoffice.IllegalPackageException;
import lt.vtvpmc.java.postoffice.Package;
import lt.vtvpmc.java.postoffice.PostOffice;

public class MyPostOffice implements PostOffice {
	private Collection<Package> post = new ArrayList<>();

	@Override
	public long numberOfPackagesForOwner(String owner) {
		long numberOfPackagesForOwner = this.post.stream().filter(pk -> pk.getOwner().equals(owner))
				.collect(Collectors.toList()).size();

		return numberOfPackagesForOwner;
	}

	@Override
	public void postPackage(Package pkg) throws IllegalPackageException {
		if ((pkg.getOwner() == null) || pkg.getOwner().isEmpty() || pkg.getPackageCode() == null
				|| pkg.getPackageCode().isEmpty()) {
			throw new IllegalPackageException();
		}
		if (!this.post.contains(pkg)) {
			this.post.add(pkg);
		}
	}

	@Override
	public Package retrievePackage(String packageCode) {
		Package g =  this.post.stream().filter(pkc -> pkc.getPackageCode()
				.equals(packageCode)).findAny().orElse(null);
		if(this.post.stream().filter(pkc -> pkc.getPackageCode().contains(packageCode)) != null) {
			this.post.remove(g);
		}
		return g;
	}

}
