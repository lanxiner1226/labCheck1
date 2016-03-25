package edu.hdu.lab.checkIn.services;

import java.util.List;

import edu.hdu.lab.checkIn.model.Address;


public interface AddressService {

	public int addAddress(Address address);
	
	public int deleteAddress(int id);

	public int updateAddress(Address address);

	public Address getAddress(int id);

	public List<Address> getAddress(Address address);
}
