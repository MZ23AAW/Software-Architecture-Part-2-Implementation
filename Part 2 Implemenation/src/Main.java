import controller.ReferralManager;
import model.Referral;

public class Main {

    public static void main(String[] args) {

        ReferralManager rm = ReferralManager.getInstance();

        Referral r = new Referral(
                "R001",
                "P001",
                "C001",
                "C010",
                "Neurology referral",
                "Patient reports persistent headaches",
                "Pending"
        );

        rm.addReferral(r);

        System.out.println("Referral created and recorded.");
    }
}
