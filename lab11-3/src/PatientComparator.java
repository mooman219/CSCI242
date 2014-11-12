import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Joseph Cumbo (mooman219)
 */
public class PatientComparator implements Comparator {

    /**
     * Driver for the test.
     *
     * @param args
     */
    public static void main(String[] args) {
        DoctorsOffice office = new DoctorsOffice("test");
        ArrayList<Patient> listOfPatients = new ArrayList<Patient>();
        office.addPatient("doe", "john", 39);
        office.addPatient("cumbo", "joe", 39);
        office.addPatient("bear", "polar", 39);
        office.addPatient("foo", "bar", 39);
        office.addPatient("doe", "deer", 39);
        office.addPatient("deer", "john", 39);
        office.listByName();
    }

    /**
     * Initializes a new PatientComparator object.
     */
    public PatientComparator() {
    }

    /**
     * Compare two Patient objects lexicographically by last name, then first
     * name. If either object is not a Patient object, A ClassCastException will
     * be thrown.
     *
     * @param o1 The first patient to be compared
     * @param o2 The second patient to be compared
     * @return Returns a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the second
     */
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 == null
                || o2 == null
                || !(o1 instanceof Patient)
                || !(o2 instanceof Patient)) {
            throw new ClassCastException();
        }
        Patient patientA = (Patient) o1;
        Patient patientB = (Patient) o2;
        return patientA.getName().compareTo(patientB.getName());
    }

}
