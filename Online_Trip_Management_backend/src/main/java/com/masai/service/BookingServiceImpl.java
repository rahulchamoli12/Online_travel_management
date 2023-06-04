package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.BookingDTO;
import com.masai.entity.Booking;
import com.masai.entity.Bus;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.CurrentUserSession.Role;
import com.masai.entity.Customer;
import com.masai.entity.Hotel;
import com.masai.entity.Package;
import com.masai.entity.PaymentDetails;
import com.masai.entity.PaymentDetails.PaymentType;
import com.masai.entity.Route;
import com.masai.entity.TicketDetails;
import com.masai.entity.TicketDetails.Status;
import com.masai.exception.AdminException;
import com.masai.exception.BookingException;
import com.masai.exception.BusException;
import com.masai.exception.CustomerException;
import com.masai.exception.HotelException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;
import com.masai.exception.RouteException;
import com.masai.exception.UserException;
import com.masai.repository.AdminRepository;
import com.masai.repository.BookingRepository;
import com.masai.repository.BusRepository;
import com.masai.repository.CustomerRepository;
import com.masai.repository.HotelRepository;
import com.masai.repository.PackageRepository;
import com.masai.repository.PaymentRepository;
import com.masai.repository.RouteRepository;
import com.masai.repository.SessionRepository;
import com.masai.repository.TicketRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookRepo;

	@Autowired
	private SessionRepository sessRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private BusRepository busRepo;

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private PackageRepository packageRepo;

	@Autowired
	private EmailsenderService emailService;

	@Override
	public Booking makeBooking(String sessionId, BookingDTO bookingdto) throws BookingException, LoginException,
			UserException, RouteException, PackageException, CustomerException, BusException, HotelException {

		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");
		Optional<Customer> customer = customerRepo.findById(cus.getUserId());
		if (customer.isEmpty())
			throw new CustomerException("customer not found");
		Customer us = customer.get();

		Booking newBooking = new Booking();
		newBooking.setBookingDate(LocalDateTime.now());
		newBooking.setBookingTitle(bookingdto.getBookingTitle());
		newBooking.setBookingType(bookingdto.getBookingType());
		newBooking.setDescription(bookingdto.getDescription());
		newBooking.setNumberOfPeople(bookingdto.getNumberOfPeople());

		Optional<Bus> busopt = busRepo.findById(bookingdto.getBus_id());
		if (busopt.isEmpty())
			throw new BusException("bus not available");
		Bus bus = busopt.get();

		us.getBookings().add(newBooking);
		newBooking.setCustomer(us);

		Optional<Hotel> hotelopt = hotelRepo.findById(bookingdto.getHotel_id());
		if (hotelopt.isEmpty())
			throw new HotelException("hotel not available");
		Hotel hotel = hotelopt.get();
		hotel.getBookings().add(newBooking);
		newBooking.setHotel(hotel);

		Optional<Package> pac = packageRepo.findById(bookingdto.getPackage_id());
		if (pac.isEmpty())
			throw new PackageException("package not found");
		Package packages = pac.get();
		packages.getBookings().add(newBooking);
		newBooking.setTourPackage(packages);

		PaymentDetails pm = new PaymentDetails();
		PaymentType payType = PaymentType.valueOf(bookingdto.getPaymentType());
		pm.setPaymentType(payType);
		pm.setPaymentDate(LocalDateTime.now());
		pm.setAmount(bookingdto.getAmount());
		pm.setBooking(newBooking);
		newBooking.setPaymentDetails(pm);

		TicketDetails ticketdetails = new TicketDetails();
		ticketdetails.setTicketDate(bookingdto.getStartDateJourney());
		ticketdetails.setExpireDate(bookingdto.getEndDateJourney());
		ticketdetails.setTicketStatus(Status.CONFIRMED);
		ticketdetails.setBooking(newBooking);
		newBooking.setTicketDetails(ticketdetails);

		System.out.println("booking confirmed.....!");

		return bookRepo.save(newBooking);
	}

	@Override
	public Booking cancelBooking(String sessionId, Integer bookingId) throws LoginException, BookingException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");
		Optional<Booking> booking = bookRepo.findById(bookingId);
		if (booking.isEmpty())
			throw new BookingException("booking not found");
		Booking cancelBook = booking.get();
		cancelBook.getTicketDetails().setTicketStatus(Status.CANCELED);

		// Send an email .
		String emailSubject = "Booking Cancelled Successfully";
		String interviewerBody = "Dear " + "Rahul your booking cancelled successfully of "
				+ cancelBook.getBookingTitle();

		String email = "rahulchamoli518@gmail.com";
		emailService.sendEmail(email, emailSubject, interviewerBody);

		return bookRepo.save(cancelBook);
	}

	@Override
	public Booking viewBooking(String sessionId, Integer bookingId) throws BookingException, LoginException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");
		Optional<Booking> booking = bookRepo.findById(bookingId);
		if (booking.isEmpty())
			throw new BookingException("booking not found");
		return booking.get();
	}

	@Override
	public List<Booking> viewAllBooking(String sessionId) throws BookingException, LoginException, AdminException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("you're not logged in");
		if (cus.getRole() == Role.ADMIN) {
			List<Booking> bookings = bookRepo.findAll();
			if (bookings.isEmpty())
				throw new BookingException("no bookings");
			return bookings;
		}
		throw new AdminException("User not Authorized!");
	}

}
