package controller;

import model.Referral;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReferralManager {

    private static ReferralManager instance;
    private List<Referral> referralQueue = new ArrayList<>();

    private ReferralManager() {}

    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void addReferral(Referral referral) {
        referralQueue.add(referral);
        writeReferralToFile(referral);
    }

    private void writeReferralToFile(Referral referral) {
        try (FileWriter writer = new FileWriter("data/referrals_outbox.txt", true)) {
            writer.write(referral.toText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
