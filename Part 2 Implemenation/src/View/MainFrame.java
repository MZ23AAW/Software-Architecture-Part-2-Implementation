package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private final PatientController patientController = new PatientController();
    private final AppointmentController appointmentController = new AppointmentController();
    private final FacilityController facilityController = new FacilityController();
    private final StaffController staffController = new StaffController();
    private final PrescriptionController prescriptionController = new PrescriptionController();
    private final ReferralController referralController = new ReferralController();

    private final DefaultListModel<String> patientListModel = new DefaultListModel<>();
    private final JList<String> patientList = new JList<>(patientListModel);

    private final DefaultTableModel apptTableModel = new DefaultTableModel(
            new String[]{"Appointment ID", "Date", "Time", "Type", "Status"}, 0
    );
    private final JTable apptTable = new JTable(apptTableModel);

    private final JTextArea logArea = new JTextArea();

    public MainFrame() {
        setTitle("Healthcare Management System (Swing)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        add(buildLeftPanel(), BorderLayout.WEST);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        loadAllData();
        wireEvents();
    }

    private JPanel buildLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setPreferredSize(new Dimension(250, 0));

        JLabel label = new JLabel("Patients");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(patientList), BorderLayout.CENTER);

        return panel;
    }

    private JPanel buildCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JLabel label = new JLabel("Appointments (selected patient)");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(apptTable), BorderLayout.CENTER);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton prescribeBtn = new JButton("Create Prescription");
        prescribeBtn.setActionCommand("PRESCRIBE");

        JButton referBtn = new JButton("Create Referral");
        referBtn.setActionCommand("REFER");

        buttonRow.add(prescribeBtn);
        buttonRow.add(referBtn);

        // store buttons in client properties so we can find later
        panel.putClientProperty("prescribeBtn", prescribeBtn);
        panel.putClientProperty("referBtn", referBtn);

        panel.add(buttonRow, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        logArea.setRows(6);
        logArea.setEditable(false);
        panel.add(new JScrollPane(logArea), BorderLayout.CENTER);
        return panel;
    }

    private void loadAllData() {
        try {
            patientController.loadPatients("data/patients.csv");
            appointmentController.loadAppointments("data/appointments.csv");
            facilityController.loadFacilities("data/facilities.csv");
            staffController.loadStaff("data/staff.csv");

            patientListModel.clear();
            for (Patient p : patientController.getPatients()) {
                patientListModel.addElement(p.getPatientId());
            }

            log("Loaded: " + patientController.getPatients().size() + " patients");
            log("Loaded: " + appointmentController.getAppointments().size() + " appointments");
            log("Loaded: " + facilityController.getFacilities().size() + " facilities");
            log("Loaded: " + staffController.getAllStaff().size() + " staff");

            if (!patientListModel.isEmpty()) {
                patientList.setSelectedIndex(0);
            }

        } catch (Exception e) {
            log("ERROR loading data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void wireEvents() {
        patientList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String patientId = patientList.getSelectedValue();
                if (patientId != null) {
                    refreshAppointments(patientId);
                }
            }
        });

        JPanel center = (JPanel) getContentPane().getComponent(1);
        JButton prescribeBtn = (JButton) center.getClientProperty("prescribeBtn");
        JButton referBtn = (JButton) center.getClientProperty("referBtn");

        prescribeBtn.addActionListener(e -> createPrescriptionForSelectedPatient());
        referBtn.addActionListener(e -> createReferralForSelectedPatient());
    }

    private void refreshAppointments(String patientId) {
        apptTableModel.setRowCount(0);

        List<Appointment> list = appointmentController.getAppointmentsForPatient(patientId);
        for (Appointment a : list) {
            apptTableModel.addRow(new Object[]{
                    a.getAppointmentId(),
                    a.getAppointmentDate(),
                    a.getAppointmentTime(),
                    a.getAppointmentType(),
                    a.getStatus()
            });
        }

        log("Selected patient: " + patientId + " | appointments: " + list.size());
    }

    private void createPrescriptionForSelectedPatient() {
        String patientId = patientList.getSelectedValue();
        if (patientId == null) return;

        // Minimal dialog inputs
        String clinicianId = JOptionPane.showInputDialog(this, "Clinician ID (e.g. C001):", "C001");
        if (clinicianId == null) return;

        String appointmentId = JOptionPane.showInputDialog(this, "Appointment ID (e.g. A001):", "A001");
        if (appointmentId == null) return;

        String medication = JOptionPane.showInputDialog(this, "Medication Name:", "Paracetamol");
        if (medication == null) return;

        String dosage = JOptionPane.showInputDialog(this, "Dosage:", "500mg");
        if (dosage == null) return;

        String frequency = JOptionPane.showInputDialog(this, "Frequency:", "Twice daily");
        if (frequency == null) return;

        String durationStr = JOptionPane.showInputDialog(this, "Duration days:", "5");
        if (durationStr == null) return;

        String quantityStr = JOptionPane.showInputDialog(this, "Quantity:", "10");
        if (quantityStr == null) return;

        String instructions = JOptionPane.showInputDialog(this, "Instructions:", "After food");
        if (instructions == null) return;

        String pharmacy = JOptionPane.showInputDialog(this, "Pharmacy Name:", "Boots");
        if (pharmacy == null) return;

        String rxId = "RX" + System.currentTimeMillis(); // quick unique id

        try {
            prescriptionController.createPrescription(
                    rxId, patientId, clinicianId, appointmentId,
                    medication, dosage, frequency,
                    Integer.parseInt(durationStr.trim()),
                    Integer.parseInt(quantityStr.trim()),
                    instructions, pharmacy
            );
            log("Prescription created: " + rxId + " (saved to data/prescriptions.csv)");
        } catch (Exception ex) {
            log("ERROR creating prescription: " + ex.getMessage());
        }
    }

    private void createReferralForSelectedPatient() {
        String patientId = patientList.getSelectedValue();
        if (patientId == null) return;

        String referralId = "R" + System.currentTimeMillis();
        String referringClinicianId = JOptionPane.showInputDialog(this, "Referring Clinician ID:", "C001");
        if (referringClinicianId == null) return;

        String referredToClinicianId = JOptionPane.showInputDialog(this, "Referred-to Clinician ID:", "C010");
        if (referredToClinicianId == null) return;

        String referringFacilityId = JOptionPane.showInputDialog(this, "Referring Facility ID:", "F001");
        if (referringFacilityId == null) return;

        String referredToFacilityId = JOptionPane.showInputDialog(this, "Referred-to Facility ID:", "F010");
        if (referredToFacilityId == null) return;

        String urgency = JOptionPane.showInputDialog(this, "Urgency Level:", "Urgent");
        if (urgency == null) return;

        String reason = JOptionPane.showInputDialog(this, "Referral Reason:", "Specialist review");
        if (reason == null) return;

        String summary = JOptionPane.showInputDialog(this, "Clinical Summary:", "Symptoms described...");
        if (summary == null) return;

        String investigations = JOptionPane.showInputDialog(this, "Requested Investigations:", "Bloods");
        if (investigations == null) return;

        String appointmentId = JOptionPane.showInputDialog(this, "Appointment ID:", "A001");
        if (appointmentId == null) return;

        String notes = JOptionPane.showInputDialog(this, "Notes:", "N/A");
        if (notes == null) return;

        try {
            referralController.createReferral(
                    referralId,
                    patientId,
                    referringClinicianId,
                    referredToClinicianId,
                    referringFacilityId,
                    referredToFacilityId,
                    urgency,
                    reason,
                    summary,
                    investigations,
                    appointmentId,
                    notes
            );
            log("Referral created: " + referralId + " (email+EHR files written in /data)");
        } catch (Exception ex) {
            log("ERROR creating referral: " + ex.getMessage());
        }
    }

    private void log(String msg) {
        logArea.append(msg + "\n");
    }
}
