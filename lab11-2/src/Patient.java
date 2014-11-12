import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class to simulate a patient in a doctor's office. Objects of this type know
 * the first and last name and age of the patient, and will contain a medication
 * history.
 *
 * The medication history will contain Medication objects and must be maintained
 * in the same order the medication is prescribed.
 *
 * @author Lois Rixner
 * @author Trudy Howles tmh@cs.rit.edu
 */
public class Patient {

    // List of medications
    private LinkedList<Medication> medications = new LinkedList<Medication>();
    private final String lastname;
    private final String firstname;
    private final int age;

    /**
     * Constructor for this object.
     *
     * @param myLast the last name of this patient
     * @param myFirst the first name of this patient
     * @age age - the age of this patient
     */
    public Patient(String myLast, String myFirst, int age) {
        this.lastname = myLast;
        this.firstname = myFirst;
        this.age = age;
    }

    /**
     * Return the patient's full name in the form:
     * <br>
     * last name comma first name.
     *
     * @return the patient's full name.
     */
    public String getName() {
        return lastname + ", " + firstname;
    }

    /**
     * Return this patient's age.
     *
     * @return the patient's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Get the number of medications in this patient's history.
     *
     * @return the number of medications in this patient's history
     *
     */
    public int getNumberOfMeds() {
        return medications.size();
    }

    /**
     * Record a new medication for this patient.
     *
     * @param Name	the name of this medication
     * @param generic	boolean true = generic otherwise false
     *
     */
    public void recordNewMed(String name, boolean generic) {
        Medication medication = new Medication(name, generic);
        medications.add(medication);
    }

    /**
     * Remove this medication for this patient.
     *
     * @param med The medication to remove for this patient. If this medication
     * is not found on this patient's medication list, return false. Otherwise,
     * remove the first occurrence of this medication.
     *
     * @return true if this medication was removed, otherwise false
     *
     */
    public boolean removeMed(String med) {
        for (Iterator<Medication> iterator = medications.iterator(); iterator.hasNext();) {
            Medication medication = iterator.next();
            if (medication.getName().equals(med)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Print all medications in this patient's medication history in the order
     * the medication was prescribed.
     *
     */
    public void printMedicationHistory() {
        StringBuilder builder = new StringBuilder();
        if (medications.size() > 0) {
            for (Medication medication : medications) {
                builder.append(medication.toString()).append("\n");
            }
            builder.delete(builder.length() - 2, builder.length());
        }
        System.out.println(builder.toString());
    }

    /**
     * Produce a printable version of this Patient object. The format must be:
     * the first and last names as formatted in the getName() method followed by
     * a single space, then the age followed by a new line.
     *
     * Format each medication recorded for this patient. Format medications by
     * calling your toString() method in the Medication class and append a new
     * line after each one. Format medications in the order prescribed.
     *
     * @return a String version of this Patient.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName()).append(" ").append(getAge()).append("\n");
        for (Medication medication : medications) {
            builder.append(medication.toString()).append("\n");
        }
        builder.delete(builder.length() - 1, builder.length());
        return builder.toString();
    }

} // Patient
