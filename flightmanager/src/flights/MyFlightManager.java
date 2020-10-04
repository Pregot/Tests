package flights;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lt.itakademija.exam.FlightManager;
import lt.itakademija.exam.Passenger;
import lt.itakademija.exam.Plane;
import lt.itakademija.exam.SeatIsOccupiedException;

public class MyFlightManager implements FlightManager {
	private Collection<Plane> list = new ArrayList<>();
	private Collection<Passenger> listOofPassenger = new ArrayList<>();

	@Override
	public Passenger createPassenger(String name, String last, int age) {
		if (name == null || last == null || age <= 0) {
			throw new NullPointerException();
		}
		if (name.isEmpty() || last.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Passenger human = new Passenger(name, last, age);
		this.listOofPassenger.add(human);
		return human;
	}

	@Override
	public Plane createPlane(String id, int seats) {
		if (id.isEmpty()) {
			throw new IllegalArgumentException();
		}

		if (seats <= 0 || id == null) {
			throw new IllegalArgumentException();
		}
		Plane planes = new Plane(id, seats);
		this.list.add(planes);
		return planes;
	}

	@Override
	public double getAveragePassengerAge(String age) {
		double avg = 0;
		double sum = 0;

		int a = this.listOofPassenger.size();
		for (Passenger g : this.listOofPassenger) {
			sum = sum + g.getAge();
		}
		avg = sum / a;
		return avg;
	}

	@Override
	public List<Plane> getCreatedPlanes() {
		return (List<Plane>) this.list;
	}

	@Override
	public Passenger getOldestPassenger(String planeId) {
		return getPassengers(planeId).stream().max((g1, g2) -> Integer.compare(g1.getAge(), g2.getAge())).orElse(null);

	}

	@Override
	public List<Passenger> getPassengers(String planeId) {
		Plane planesId = list.stream().filter(plane -> plane.getId().contentEquals(planeId)).findAny()
				.orElseGet(null);
		if (planesId != null) {
			return planesId.getPassengers();
		}
		return null;
	}

	@Override
	public Plane getPlaneById(String id) {
		if (id == null) {
			throw new NullPointerException();
		}
		return this.list.stream().filter(plane -> plane.getId().equals(id)).findAny().get();

	}

	@Override
	public void registerPassenger(Plane plane, int seat, Passenger passengers) throws SeatIsOccupiedException {
		if (seat <= 0) {
			throw new IllegalArgumentException();
		}
		if (plane.getId().isEmpty()) {
			throw new NullPointerException();
		}
		if (plane.isSeatOccupied(seat)) {
			throw new SeatIsOccupiedException();
		}
		plane.registerPassenger(seat, passengers);

	}

}
