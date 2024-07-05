package Entity;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    String name;
    List<String> bookingList;

    public Patient(String name) {
        this.name = name;
        this.bookingList =new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<String> booking) {
        bookingList = booking;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", BookingList=" + bookingList +
                '}';
    }
}
