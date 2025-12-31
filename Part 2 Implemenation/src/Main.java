import controller.AppointmentController;

public class Main {
    public static void main(String[] args) {

        AppointmentController ac = new AppointmentController();
        ac.loadAppointments("data/appointments.csv");

        System.out.println("Appointments loaded: " + ac.getAppointments().size());
    }
}
