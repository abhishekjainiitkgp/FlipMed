package Repository;

import Entity.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    Map<String, Booking> bookingAccepted;
    Map<String, Booking> bookingCompletedOrCancelled;
    private static BookingRepository bookingRepository= null;
    private BookingRepository(){
        this.bookingAccepted= new HashMap<>();
        this.bookingCompletedOrCancelled=new HashMap<>();
    }

    public static BookingRepository getBookingRepoInstance(){
        if(bookingRepository==null)
            bookingRepository=new BookingRepository();
        return bookingRepository;
    }

    //returns the booking id after booking is done
    public void bookAppointment(Booking booking){
        bookingAccepted.put(booking.getId(), booking);
    }
    //cancels the bookings on request
    public void cancelBooking(String id){
        bookingCompletedOrCancelled.put(id, bookingAccepted.get(id));
        bookingAccepted.remove(id);
    }

    public Map<String, Booking> getBookingAccepted() {
        return bookingAccepted;
    }

    public Map<String, Booking> getBookingCompletedOrCancelled() {
        return bookingCompletedOrCancelled;
    }

    public void setBookingCompletedOrCancelled(Map<String, Booking> bookingCompletedOrCancelled) {
        this.bookingCompletedOrCancelled = bookingCompletedOrCancelled;
    }

    public void setBookingAccepted(Map<String, Booking> bookingAccepted) {
        this.bookingAccepted = bookingAccepted;
    }
}
