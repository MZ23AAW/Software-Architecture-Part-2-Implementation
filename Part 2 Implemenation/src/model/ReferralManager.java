package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReferralManager {
    private static ReferralManager instance;
    private List<Referral> referralQueue;

    private ReferralManager() {
        referralQueue = new ArrayList<>();
    }
    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void processReferral(Referral referral) {
        referralQueue.add(referral);
        saveReferral(referral);
        generateReferralEmail(referral);
    }
    private void saveReferral(Referral referral) {
        try(FileWriter fw = new FileWriter("data/referral.csv", true )) {
            fw.write(referral.toText() + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generateReferralEmail(Referral referral) {
        try (FileWriter fw =
                new FileWriter("data/referral_email_" + referral.hashCode() + ".txt")){
            fw.write(referral.toText());
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
}
