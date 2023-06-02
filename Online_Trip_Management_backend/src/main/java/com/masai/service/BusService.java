package com.masai.service;

import com.masai.entity.Bus;
import com.masai.exception.BusException;
import com.masai.exception.LoginException;

public interface BusService {
	public Bus addBus(String uuid, Bus bus) throws LoginException, BusException;
	public Bus deleteBus(String uuid, int busid) throws LoginException, BusException;
	public Bus updateBus(String uuid, Bus bus) throws LoginException, BusException;
}
