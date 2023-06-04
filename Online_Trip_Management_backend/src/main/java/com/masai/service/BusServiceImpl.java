package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Bus;
import com.masai.entity.CurrentUserSession;
import com.masai.exception.BusException;
import com.masai.exception.LoginException;
import com.masai.repository.BusRepository;
import com.masai.repository.SessionRepository;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusRepository busrepo;
	@Autowired
	private SessionRepository sessionrepo;
	
	@Override
	public Bus addBus(String uuid, Bus bus) throws LoginException, BusException {
		// TODO Auto-generated method stub
		CurrentUserSession cus = sessionrepo.findBySessionId(uuid);
		if (cus == null) throw new LoginException("Login please");
		if(bus == null) throw new BusException("Please enter Bus details properly");
		return busrepo.save(bus);
	}

	@Override
	public Bus deleteBus(String uuid, int busid) throws LoginException, BusException {
		// TODO Auto-generated method stub
		CurrentUserSession cus = sessionrepo.findBySessionId(uuid);
		if (cus == null) throw new LoginException("Login please");
		Optional<Bus> ba = busrepo.findById(busid);
		if(ba.isEmpty()) throw new BusException("Please enter Bus details properly");
		Bus bus = ba.get();
		busrepo.delete(bus);
		return bus;
	}

	@Override
	public Bus updateBus(String uuid, Bus bus) throws LoginException, BusException {
		// TODO Auto-generated method stub
		CurrentUserSession cus = sessionrepo.findBySessionId(uuid);
		if (cus == null) throw new LoginException("Login please");
		Optional<Bus> ba = busrepo.findById(bus.getBusId());
		if(ba.isEmpty()) throw new BusException("Please enter Bus details properly");
		return busrepo.save(bus);
	}

}
