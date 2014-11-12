/**
 * A class to simulate a patient in a doctor's office. Objects of this type know
 * the first and last name and age of the patient, and will contain a medication
 * history. The medication history will contain Medication objects and must be
 * maintained in the same order the medication is prescribed.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class Patient {

    /**
     * Constructor for this object.
     *
     * @param myLast
     * @param myFirst
     * @param age
     */
    public Patient(String myLast, String myFirst, int age) {

    }

    /**
     * Return this patient's age.
     *
     * @return the patient's age
     */
    public int getAge() {

    }

    /**
     * Return the patient's full name in the form: last-name COMMA first-name.
     *
     * @return the patient's full name.
     */
    public String getName() {

    }

    /**
     * Get the number of medications in this patient's history.
     *
     * @return the number of medications in this patient's history
     */
    public int getNumberOfMeds() {

    }

    /**
     * Print all medications in this patient's medication history in the order
     * the medication was prescribed.
     */
    public void printMedicationHistory() {

    }

    /**
     * Record a new medication for this patient.
     *
     * @param name the name of this medication
     * @param generic true = generic otherwise false
     */
    public void recordNewMed(String name, boolean generic) {

    }

    /**
     * Remove this medication for this patient
     *
     * @param med The medication to remove for this patient. If the medication
     * is not found on this patient's medication list, return false. Otherwise,
     * remove the first occurrence of this medication.
     * @return true if this medication was removed, otherwise false
     */
    public boolean removeMed(String med) {

    }

    /**
     * Produce a printable version of this Patient object. The format must be:
     * the first and last names as formatted in the getName() method followed by
     * a single space, then the age followed by a new line. Format each
     * medication recorded for this patient. Format medications by calling your
     * toString() method in the Medication class. Format medications in the
     * order prescribed - be sure to insert a new line after each medication.
     *
     * @return a String version of this Patient.
     */
    public String toString() {

    }
}
