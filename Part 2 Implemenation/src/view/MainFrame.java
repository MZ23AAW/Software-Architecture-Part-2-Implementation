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
    private final ClinicianController clinicianController = new ClinicianController();

    private final DefaultListModel<String> patientListModel = new DefaultListModel<>();
    private final JList<String> patientList = new JList<>(patientListModel);

    private final DefaultTableModel apptTableModel = new DefaultTableModel(
            new String[]{"Appointment ID", "Date", "Time", "Type", "Status"}, 0
    );
    private final JTable apptTable = new JTable(apptTableModel);

    private final DefaultTableModel rxTableModel = new DefaultTableModel(
            new String[]{"Rx ID", "Issue Date", "Drug", "Dosage", "Freq", "Days", "Qty", "Clinician", "Appt", "Pharmacy"}, 0
    );
    private final JTable rxTable = new JTable(rxTableModel);

    private final DefaultTableModel clinicianTableModel = new DefaultTableModel(
            new String[]{"Clinician ID", "Name", "Title", "Speciality", "Workplace", "Type", "Status"}, 0
    );
    private final JTable clinicianTable = new JTable(clinicianTableModel);

    private final JTextArea logArea = new JTextArea();
    private JTabbedPane tabs;

    public MainFrame() {
        setTitle("Healthcare Management System (Swing)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        add(buildLeftPanel(), BorderLayout.WEST);
        add(buildTabs(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        loadAllData();
        wireEvents();
    }


    private JPanel buildLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setPreferredSize(new Dimension(260, 0));

        JLabel label = new JLabel("Patients");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(patientList), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridLayout(3, 1, 6, 6));
        JButton addPatientBtn = new JButton("Add Patient");
        JButton editPatientBtn = new JButton("Edit Patient");
        JButton deletePatientBtn = new JButton("Delete Patient");

        btnPanel.add(addPatientBtn);
        btnPanel.add(editPatientBtn);
        btnPanel.add(deletePatientBtn);

        panel.add(btnPanel, BorderLayout.SOUTH);

        panel.putClientProperty("addPatientBtn", addPatientBtn);
        panel.putClientProperty("editPatientBtn", editPatientBtn);
        panel.putClientProperty("deletePatientBtn", deletePatientBtn);

        return panel;
    }

    private JTabbedPane buildTabs() {
        tabs = new JTabbedPane();
        tabs.addTab("Appointments", buildAppointmentsPanel());
        tabs.addTab("Prescriptions", buildPrescriptionsPanel());
        tabs.addTab("Clinicians", buildCliniciansPanel());
        return tabs;
    }

    private JPanel buildAppointmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JLabel label = new JLabel("Appointments (selected patient)");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(apptTable), BorderLayout.CENTER);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addApptBtn = new JButton("Add Appointment");
        JButton editApptBtn = new JButton("Edit Appointment");
        JButton deleteApptBtn = new JButton("Delete Appointment");

        JButton referBtn = new JButton("Create Referral");

        buttonRow.add(addApptBtn);
        buttonRow.add(editApptBtn);
        buttonRow.add(deleteApptBtn);

        buttonRow.add(referBtn);

        panel.putClientProperty("addApptBtn", addApptBtn);
        panel.putClientProperty("editApptBtn", editApptBtn);
        panel.putClientProperty("deleteApptBtn", deleteApptBtn);

        panel.putClientProperty("referBtn", referBtn);

        panel.add(buttonRow, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildPrescriptionsPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JLabel label = new JLabel("Prescriptions (selected patient)");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(rxTable), BorderLayout.CENTER);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addRxBtn = new JButton("Add Prescription");
        JButton deleteRxBtn = new JButton("Delete Prescription");

        buttonRow.add(addRxBtn);
        buttonRow.add(deleteRxBtn);

        panel.putClientProperty("addRxBtn", addRxBtn);
        panel.putClientProperty("deleteRxBtn", deleteRxBtn);

        panel.add(buttonRow, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buildCliniciansPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JLabel label = new JLabel("Clinicians");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(clinicianTable), BorderLayout.CENTER);

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

            prescriptionController.loadPrescriptions("data/prescriptions.csv");
            clinicianController.loadClinicians("data/clinicians.csv");

            refreshPatientList();
            refreshCliniciansTable();

            log("Loaded: " + patientController.getPatients().size() + " patients");
            log("Loaded: " + appointmentController.getAppointments().size() + " appointments");
            log("Loaded: " + facilityController.getFacilities().size() + " facilities");
            log("Loaded: " + staffController.getAllStaff().size() + " staff");
            log("Loaded: " + prescriptionController.getPrescriptions().size() + " prescriptions");
            log("Loaded: " + clinicianController.getClinicians().size() + " clinicians");

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
                    refreshPrescriptions(patientId);
                }
            }
        });

        JPanel left = (JPanel) getContentPane().getComponent(0);
        JButton addPatientBtn = (JButton) left.getClientProperty("addPatientBtn");
        JButton editPatientBtn = (JButton) left.getClientProperty("editPatientBtn");
        JButton deletePatientBtn = (JButton) left.getClientProperty("deletePatientBtn");

        addPatientBtn.addActionListener(e -> addPatientDialog());
        editPatientBtn.addActionListener(e -> editPatientDialog());
        deletePatientBtn.addActionListener(e -> deletePatientAction());

        JPanel appointmentsPanel = (JPanel) tabs.getComponentAt(0);
        JButton referBtn = (JButton) appointmentsPanel.getClientProperty("referBtn");


        JButton addApptBtn = (JButton) appointmentsPanel.getClientProperty("addApptBtn");
        JButton editApptBtn = (JButton) appointmentsPanel.getClientProperty("editApptBtn");
        JButton deleteApptBtn = (JButton) appointmentsPanel.getClientProperty("deleteApptBtn");

        addApptBtn.addActionListener(e -> addAppointmentDialog());
        editApptBtn.addActionListener(e -> editSelectedAppointment());
        deleteApptBtn.addActionListener(e -> deleteSelectedAppointment());


        referBtn.addActionListener(e -> createReferralForSelectedPatient());

        JPanel prescriptionsPanel = (JPanel) tabs.getComponentAt(1);
        JButton addRxBtn = (JButton) prescriptionsPanel.getClientProperty("addRxBtn");
        JButton deleteRxBtn = (JButton) prescriptionsPanel.getClientProperty("deleteRxBtn");

        addRxBtn.addActionListener(e -> {
            createPrescriptionForSelectedPatient();
            prescriptionController.loadPrescriptions("data/prescriptions.csv");
            String pid = patientList.getSelectedValue();
            if (pid != null) refreshPrescriptions(pid);
        });

        deleteRxBtn.addActionListener(e -> deleteSelectedPrescription());
    }


    private void refreshPatientList() {
        patientListModel.clear();
        for (Patient p : patientController.getPatients()) {
            patientListModel.addElement(p.getPatientId());
        }
        if (!patientListModel.isEmpty()) {
            patientList.setSelectedIndex(0);
        }
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

    private void refreshPrescriptions(String patientId) {
        rxTableModel.setRowCount(0);

        List<Prescription> list = prescriptionController.getPrescriptionsForPatient(patientId);
        for (Prescription p : list) {
            rxTableModel.addRow(new Object[]{
                    p.getPrescriptionId(),
                    p.getIssueDate(),
                    p.getMedication(),
                    p.getDosage(),
                    p.getFrequency(),
                    p.getDurationDays(),
                    p.getQuantity(),
                    p.getClinicianId(),
                    p.getAppointmentId(),
                    p.getPharmacy()
            });
        }
        log("Prescriptions for " + patientId + ": " + list.size());
    }

    private void refreshCliniciansTable() {
        clinicianTableModel.setRowCount(0);

        for (Clinician c : clinicianController.getClinicians()) {
            clinicianTableModel.addRow(new Object[]{
                    c.getClinicianId(),
                    c.getFirstName() + " " + c.getLastName(),
                    c.getTitle(),
                    c.getSpeciality(),
                    c.getWorkplaceId(),
                    c.getWorkplaceType(),
                    c.getEmploymentStatus()
            });
        }
    }


    private void addPatientDialog() {
        try {
            String id = JOptionPane.showInputDialog(this, "Patient ID (e.g. P011):");
            if (id == null || id.isBlank()) return;

            String fn = JOptionPane.showInputDialog(this, "First name:");
            if (fn == null) return;

            String ln = JOptionPane.showInputDialog(this, "Last name:");
            if (ln == null) return;

            String dob = JOptionPane.showInputDialog(this, "DOB (yyyy-mm-dd or dd/mm/yyyy):");
            if (dob == null) return;

            String nhs = JOptionPane.showInputDialog(this, "NHS Number:");
            if (nhs == null) return;

            String gender = JOptionPane.showInputDialog(this, "Gender:");
            if (gender == null) return;

            String phone = JOptionPane.showInputDialog(this, "Phone:");
            if (phone == null) return;

            String email = JOptionPane.showInputDialog(this, "Email:");
            if (email == null) return;

            String address = JOptionPane.showInputDialog(this, "Address:");
            if (address == null) return;

            String postcode = JOptionPane.showInputDialog(this, "Postcode:");
            if (postcode == null) return;

            String ecName = JOptionPane.showInputDialog(this, "Emergency contact name:");
            if (ecName == null) return;

            String ecPhone = JOptionPane.showInputDialog(this, "Emergency contact phone:");
            if (ecPhone == null) return;

            String regDate = JOptionPane.showInputDialog(this, "Registration date:");
            if (regDate == null) return;

            String gpId = JOptionPane.showInputDialog(this, "GP surgery ID:");
            if (gpId == null) return;

            Patient newP = new Patient(
                    id.trim(), fn.trim(), ln.trim(),
                    util.DateParser.parse(dob),
                    nhs.trim(), gender.trim(),
                    phone.trim(), email.trim(),
                    address.trim(), postcode.trim(),
                    ecName.trim(), ecPhone.trim(),
                    util.DateParser.parse(regDate),
                    gpId.trim()
            );

            boolean ok = patientController.addPatient("data/patients.csv", newP);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Patient ID already exists!");
                return;
            }

            refreshPatientList();
            log("Patient added: " + id);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding patient: " + ex.getMessage());
        }
    }

    private void editPatientDialog() {
        String selectedId = patientList.getSelectedValue();
        if (selectedId == null) return;

        Patient p = patientController.findPatientById(selectedId);
        if (p == null) return;

        try {
            String fn = JOptionPane.showInputDialog(this, "First name:", p.getFirstName());
            if (fn == null) return;

            String ln = JOptionPane.showInputDialog(this, "Last name:", p.getLastName());
            if (ln == null) return;

            String phone = JOptionPane.showInputDialog(this, "Phone:", p.getPhoneNumber());
            if (phone == null) return;

            String email = JOptionPane.showInputDialog(this, "Email:", p.getEmail());
            if (email == null) return;

            String address = JOptionPane.showInputDialog(this, "Address:", p.getAddress());
            if (address == null) return;

            String postcode = JOptionPane.showInputDialog(this, "Postcode:", p.getPostcode());
            if (postcode == null) return;

            Patient updated = new Patient(
                    p.getPatientId(),
                    fn.trim(),
                    ln.trim(),
                    p.getDateOfBirth(),
                    p.getNhsNumber(),
                    p.getGender(),
                    phone.trim(),
                    email.trim(),
                    address.trim(),
                    postcode.trim(),
                    p.getEmergencyContactName(),
                    p.getEmergencyContactPhone(),
                    p.getRegistrationDate(),
                    p.getGpSurgeryId()
            );

            boolean ok = patientController.updatePatient("data/patients.csv", updated);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Update failed (patient not found).");
                return;
            }

            refreshPatientList();
            log("Patient updated: " + selectedId);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error editing patient: " + ex.getMessage());
        }
    }

    private void deletePatientAction() {
        String selectedId = patientList.getSelectedValue();
        if (selectedId == null) return;

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete patient " + selectedId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) return;

        boolean ok = patientController.deletePatient("data/patients.csv", selectedId);
        if (!ok) {
            JOptionPane.showMessageDialog(this, "Delete failed (patient not found).");
            return;
        }

        refreshPatientList();
        log("Patient deleted: " + selectedId);
    }


    private void deleteSelectedPrescription() {
        int row = rxTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a prescription row first.");
            return;
        }



        String rxId = String.valueOf(rxTableModel.getValueAt(row, 0));

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete prescription " + rxId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) return;

        boolean ok = prescriptionController.deletePrescription("data/prescriptions.csv", rxId);
        if (!ok) {
            JOptionPane.showMessageDialog(this, "Delete failed (not found).");
            return;
        }

        prescriptionController.loadPrescriptions("data/prescriptions.csv");
        String pid = patientList.getSelectedValue();
        if (pid != null) refreshPrescriptions(pid);

        log("Prescription deleted: " + rxId);
    }

    private void addAppointmentDialog() {
        String patientId = patientList.getSelectedValue();
        if (patientId == null) {
            JOptionPane.showMessageDialog(this, "Select a patient first.");
            return;
        }

        String appointmentId = "A" + System.currentTimeMillis();

        String clinicianId = JOptionPane.showInputDialog(this, "Clinician ID (e.g. C001):", "C001");
        if (clinicianId == null) return;

        String facilityId = JOptionPane.showInputDialog(this, "Facility ID (e.g. F001):", "F001");
        if (facilityId == null) return;

        String dateStr = JOptionPane.showInputDialog(this, "Appointment Date (yyyy-mm-dd):", "2025-01-10");
        if (dateStr == null) return;

        String timeStr = JOptionPane.showInputDialog(this, "Appointment Time (HH:mm or HH:mm:ss):", "09:00");
        if (timeStr == null) return;

        String durationStr = JOptionPane.showInputDialog(this, "Duration minutes:", "30");
        if (durationStr == null) return;

        String type = JOptionPane.showInputDialog(this, "Appointment Type:", "Check-up");
        if (type == null) return;

        String status = JOptionPane.showInputDialog(this, "Status:", "Booked");
        if (status == null) return;

        String reason = JOptionPane.showInputDialog(this, "Reason for visit:", "N/A");
        if (reason == null) return;

        String notes = JOptionPane.showInputDialog(this, "Notes:", "");
        if (notes == null) return;

        try {
            java.sql.Date apptDate = util.DateParser.parse(dateStr);
            java.sql.Time apptTime = java.sql.Time.valueOf(normaliseTime(timeStr));
            int duration = Integer.parseInt(durationStr.trim());

            java.sql.Date now = new java.sql.Date(System.currentTimeMillis());

            Appointment a = new Appointment(
                    appointmentId,
                    patientId,
                    clinicianId.trim(),
                    facilityId.trim(),
                    apptDate,
                    apptTime,
                    duration,
                    type.trim(),
                    status.trim(),
                    reason.trim(),
                    notes.trim(),
                    now,
                    now
            );

            appointmentController.addAppointment(a);
            refreshAppointments(patientId);
            log("Appointment added: " + appointmentId);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding appointment: " + ex.getMessage());
        }
    }

    private void deleteSelectedAppointment() {
        String patientId = patientList.getSelectedValue();
        if (patientId == null) return;

        int row = apptTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an appointment row first.");
            return;
        }

        String appointmentId = String.valueOf(apptTableModel.getValueAt(row, 0));

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete appointment " + appointmentId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) return;

        appointmentController.deleteAppointment(appointmentId);
        refreshAppointments(patientId);
        log("Appointment deleted: " + appointmentId);
    }

    private void editSelectedAppointment() {
        String patientId = patientList.getSelectedValue();
        if (patientId == null) {
            JOptionPane.showMessageDialog(this, "Select a patient first.");
            return;
        }

        int row = apptTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an appointment row first.");
            return;
        }

        String appointmentId = String.valueOf(apptTableModel.getValueAt(row, 0));

        Appointment target = null;
        for (Appointment a : appointmentController.getAppointments()) {
            if (a.getAppointmentId().equals(appointmentId)) {
                target = a;
                break;
            }
        }

        if (target == null) {
            JOptionPane.showMessageDialog(this, "Appointment not found.");
            return;
        }

        String dateStr = JOptionPane.showInputDialog(
                this,
                "Appointment Date (yyyy-mm-dd):",
                target.getAppointmentDate().toString()
        );
        if (dateStr == null) return;

        String timeStr = JOptionPane.showInputDialog(
                this,
                "Appointment Time (HH:mm or HH:mm:ss):",
                target.getAppointmentTime().toString()
        );
        if (timeStr == null) return;

        String type = JOptionPane.showInputDialog(this, "Appointment Type:", target.getAppointmentType());
        if (type == null) return;

        String status = JOptionPane.showInputDialog(this, "Status:", target.getStatus());
        if (status == null) return;

        try {
            target.setAppointmentDate(util.DateParser.parse(dateStr));
            target.setAppointmentTime(java.sql.Time.valueOf(normaliseTime(timeStr)));
            target.setAppointmentType(type.trim());
            target.setStatus(status.trim());
            target.setLastModified(new java.sql.Date(System.currentTimeMillis()));

            refreshAppointments(patientId);
            log("Appointment updated: " + appointmentId);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error editing appointment: " + ex.getMessage());
        }
    }


    private void createPrescriptionForSelectedPatient() {
        String patientId = patientList.getSelectedValue();
        if (patientId == null) return;

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

        String rxId = "RX" + System.currentTimeMillis();

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

    private String normaliseTime(String raw) {
        raw = raw.trim();
        if (raw.matches("^\\d{2}:\\d{2}$")) {
            return raw + ":00";
        }
        return raw;
    }



    private void log(String msg) {
        logArea.append(msg + "\n");
    }
}
