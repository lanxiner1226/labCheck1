package edu.hdu.lab.checkIn.serviceImpls;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hdu.lab.checkIn.mapper.AddressMapper;
import edu.hdu.lab.checkIn.model.Address;
import edu.hdu.lab.checkIn.model.AddressExample;
import edu.hdu.lab.checkIn.services.AddressService;

@Service("AddressService")
public class AddressServerImpl implements AddressService{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public int addAddress(Address address) {
		
		return addressMapper.insert(address);
	}

	@Override
	public int deleteAddress(int id) {
		return addressMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateAddress(Address address) {
		return addressMapper.updateByPrimaryKeySelective(address);
	}

	@Override
	public Address getAddress(int id) {
		return addressMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Address> getAddress(Address address) {
		AddressExample exampl= new AddressExample();
		return addressMapper.selectByExample(exampl);
	}

}
