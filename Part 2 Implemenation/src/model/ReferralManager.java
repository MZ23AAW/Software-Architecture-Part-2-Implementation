package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReferralManager {

    private static ReferralManager instance;

    private final Queue<Referral> referralQueue = new LinkedList<>();
    private final Set<String> processedReferralIds = new HashSet<>(); // ✅ FIXED

    private ReferralManager() {
    }

    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void processReferral(Referral referral) {
        if (processedReferralIds.contains(referral.getReferralId())) {
            System.out.println("Duplicate referral ignored: " + referral.getReferralId());
            return;
        }

        referralQueue.add(referral);
        processedReferralIds.add(referral.getReferralId());

        writeEmailToFile(referral);
        writeEhrUpdate(referral);

        System.out.println("Referral queued and processed: " + referral.getReferralId());
    }

    public Referral nextReferral() {
        return referralQueue.poll();
    }

    public int queueSize() {
        return referralQueue.size();
    }

    private void writeEmailToFile(Referral referral) {
        String fileName = "data/referral_email_" + referral.getReferralId() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(referral.sendReferralNotification());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeEhrUpdate(Referral referral) {
        try (FileWriter writer = new FileWriter("data/ehr_updates.txt", true)) {
            writer.write("EHR UPDATE:\n" + referral.toText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

