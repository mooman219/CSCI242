import java.util.HashMap;

/**
 * A simple class to simulate the operation of a doctor's office for tracking
 * patients and medication.
 *
 * This class assigns patient identification numbers. ID numbers start at 1000
 * and increment by one for each new Patient object added to the database.
 *
 * This class maintains two databases: One for the current Patients (the master
 * database) and one for the inactive patients.
 *
 * @author Trudy Howles tmh@cs.rit.edu
 *
 *
 */
public class DoctorsOffice {

    private int nextPaitentNumber = 1000;
    private final String name;
    private final HashMap<Integer, Patient> active = new HashMap<Integer, Patient>();
    private final HashMap<Integer, Patient> inactive = new HashMap<Integer, Patient>();

    /**
     * Constructor for a DoctorsOffice object.
     *
     * @param name Name of this Dr's Office
     */
    public DoctorsOffice(String name) {
        this.name = name;
    }

    /**
     * Add a new patient to the office. The identification number is uniquely
     * generated and is returned when the Patient object is added to the
     * database. ID numbers start at 1000 and increment by one for each new
     * Patient added.
     *
     * @param firstName first name of this patient
     * @param lastName last name of this patient
     * @param age age of this patient
     * @return the ID number assigned to this Patient
     *
     */
    public int addPatient(String firstName, String lastName, int age) {
        Patient patient = new Patient(lastName, firstName, age);
        int number = nextPaitentNumber++;
        active.put(number, patient);
        return number;

    }

    /**
     * Remove this patient from the master database. Removed patients are
     * archived in an "inactive" database which maintains Patron objects in the
     * order in which they were removed from the master database.
     *
     * If throwing an exception, use "removePatient()" as the message.
     *
     * @param patientNo	Patient number assigned
     * @exception throws a NoSuchPatientException if this patient does not exist
     *
     */
    public void removePatient(int patientNo) throws NoSuchPatientException {
        // DO NOT IMPLEMENT
    }

    /**
     * Add a new medication for this patient.
     *
     * If throwing an exception, use "addMedication()" as the message.
     *
     * @param patientNo	Patient number
     * @param medicationName	Name of this medication
     * @param isGeneric	True if a generic drug
     * @exception throws NoSuchPatientException if this patient ID does not
     * exist.
     *
     */
    public void addMedication(int patientNo, String medicationName, boolean isGeneric) throws NoSuchPatientException {
        Patient patient = active.get(patientNo);
        if (patient != null) {
            patient.recordNewMed(medicationName, isGeneric);
        } else {
            throw new NoSuchPatientException("addMedication()");
        }
    }

    /**
     * Print the medication detail for this patient. Print the patient's full
     * name (lastname COMMA SPACE firstName) then each medication (each one on a
     * new line). To print the medications, simply call your toString() method
     * in the Medication class.
     *
     * If this patient has no medication history, print "No Medications
     * Prescribed".
     *
     * If throwing an exception, use "printMedicationDetail()" as the message.
     *
     * @param patientNo	Patient number
     * @exception throws NoSuchPatientException if patient does not exist.
     *
     */
    public void printMedicationDetail(int patientNo) throws NoSuchPatientException {
        Patient patient = active.get(patientNo);
        if (patient != null) {
            if (patient.getNumberOfMeds() > 0) {
                System.out.println(patient.toString());
            } else {
                System.out.println("No Medications Prescribed");
            }
        } else {
            throw new NoSuchPatientException("printMedicationDetail()");
        }
    }

    /**
     * Print all patients ordered by last name, then first name if you encounter
     * two patients with the same last name.
     *
     * To print the Patient objects, simply call your toString() method in the
     * Patient class.
     *
     *
     */
    public void listByName() {
        // DO NOT IMPLEMENT
    }

    /**
     * Print all patients ordered by age.
     *
     * To print the Patient objects, simply call your toString() method in the
     * Patient class.
     *
     *
     */
    public void listByAge() {
        // DO NOT IMPLEMENT
    }

    /**
     * Print all inactive patients in the order in which they were added to the
     * inactive archive.
     *
     *
     * To print the Patient objects, simply call your toString() method in the
     * Patient class.
     *
     *
     */
    public void listInactive() {
        // DO NOT IMPLEMENT
    }

}
