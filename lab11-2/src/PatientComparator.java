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
        ArrayList<Patient> listOfPatients = new ArrayList<Patient>();
        listOfPatients.add(new Patient("doe", "john", 39));
        listOfPatients.add(new Patient("cumbo", "joe", 39));
        listOfPatients.add(new Patient("bear", "polar", 39));
        listOfPatients.add(new Patient("foo", "bar", 39));
        listOfPatients.add(new Patient("doe", "deer", 39));
        listOfPatients.add(new Patient("deer", "john", 39));
        listOfPatients.sort(new PatientComparator());

        for (Patient patient : listOfPatients) {
            System.out.println(patient.getName());
        }
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
        Patient a = (Patient) o1;
        Patient b = (Patient) o2;
        return a.getName().compareTo(b.getName());
    }

}
