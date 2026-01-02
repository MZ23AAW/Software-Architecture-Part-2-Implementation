import controller.ReferralController;

public class Main {
    public static void main(String[] args) {

        ReferralController rc = new ReferralController();
        rc.createReferral(
                1, 1001,
                200, 300,
                10, 20,
                "Urgent",
                "Cardiology referral",
                "Chest pain on exertion for 2 weeks.",
                "ECG, Bloods",
                5001,
                "Patient anxious."
        );
    }
}
