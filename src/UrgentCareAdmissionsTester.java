// Title:    Test cases for the UrgentCareAdmissions class.


import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

// Javadoc style class header comes here

/**
 * This class contains various methods which have various test cases which test the various methods
 * used in the UrgentCareAdmissions class.
 */
public class UrgentCareAdmissionsTester {


    public static boolean testGetIndex() {

        // The non-empty, non-full oversize arrays to use in this test.
        // Note that we're using the UrgentCareAdmissions named constants to create these test records,
        // rather than their corresponding literal int values.
        // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid.
        int[][] patientsListAllLevels = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                {31501, 1, UrgentCareAdmissions.GREEN},
                null, null, null
        };
        int allLevelsSize = 5;

        int[][] patientsListOnlyYellow = new int[][]{
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                null, null, null, null, null
        };
        int onlyYellowSize = 3;

        // scenario 1: add a patient with a higher priority than any existing patient
        {
            int expected = 0;
            int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
                    patientsListOnlyYellow, onlyYellowSize);
            if (expected != actual) return false;
        }

        // scenario 2: add a patient with a lower priority than any existing patient
        {
            int expected = onlyYellowSize;
            int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
                    patientsListOnlyYellow, onlyYellowSize);
            if (expected != actual) return false;
        }

        // scenario 3: verify that a patient with the same priority as existing patients gets
        // added after all of those patients
        {
            int expected = 1;
            int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
                    patientsListAllLevels, allLevelsSize);
            if (expected != actual) return false;

            expected = 4;
            actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW,
                    patientsListAllLevels, allLevelsSize);
            if (expected != actual) return false;

            expected = allLevelsSize;
            actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
                    patientsListAllLevels, allLevelsSize);
            if (expected != actual) return false;
        }

        // and finally, verify that the arrays were not changed at all
        {
            int[][] allLevelsCopy = new int[][]{
                    {32702, 3, UrgentCareAdmissions.RED},
                    {21801, 2, UrgentCareAdmissions.YELLOW},
                    {22002, 4, UrgentCareAdmissions.YELLOW},
                    {11901, 5, UrgentCareAdmissions.YELLOW},
                    {31501, 1, UrgentCareAdmissions.GREEN},
                    null, null, null
            };
            if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy)) return false;

            int[][] onlyYellowCopy = new int[][]{
                    {21801, 2, UrgentCareAdmissions.YELLOW},
                    {22002, 4, UrgentCareAdmissions.YELLOW},
                    {11901, 5, UrgentCareAdmissions.YELLOW},
                    null, null, null, null, null
            };
            if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy)) return false;
        }

        return true;
    }

    // Tests the behavior of the addPatient method using a non-empty, non-full array. Each test
    // should verify that the returned size is as expected and that the array has been updated
    // (or not) appropriately

    /**
     * This method tests three scenarios.This test tests the method addPatient.
     * 1. In the first test, we add a patient to the end of the patientsList array of size 5. The number of patients
     * after the patient is added, is returned.We expect the integer returned to be 6.If we get the same integer,
     * the next test starts. If we get some other integer, false is returned.
     * 2. In the second test, we add a patient to the middle of the patientsList array. The number of patients
     * after the patient is added, is returned.We expect the integer returned to be 6.If we get the same integer,
     * the next test starts. If we get some other integer, false is returned.
     * 3. In the third test, we add a patient using an invalid index.We expect the integer to be returned as -1.
     * If we get the same integer, the test passes and hence true is returned.
     *
     * @return true is returned only if all tests pass otherwise false is returned.
     */
    public static boolean testAddPatient() {
        // (1) add a patient to the END of the patientsList
        {
            int[][] patientsListMoreThanOne = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}, null, null
            };
            int[] patientRecord = {21768, 0, UrgentCareAdmissions.RED};
            int size = 5;
            int index = size - 1;
            int expected = 6;
            int actual = UrgentCareAdmissions.addPatient(patientRecord, index, patientsListMoreThanOne, size);
            if (expected != actual) {
                return false;
            }
        }
        // (2) add a patient to the MIDDLE of the patientsList
        {
            int[][] patientsListMoreThanOne = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}, null, null
            };
            int[] patientRecord = {21768, 0, UrgentCareAdmissions.RED};
            int size = 5;
            int index = size / 2;
            int expected = 6;
            int actual = UrgentCareAdmissions.addPatient(patientRecord, index, patientsListMoreThanOne, size);
            if (expected != actual) {
                return false;
            }
        }


        // (3) add a patient using an invalid (out-of-bounds) index
        {
            int[][] patientsListMoreThanOne = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}, null, null
            };
            int[] patientRecord = {21768, 0, UrgentCareAdmissions.RED};
            int size = 5;
            int index = 8;
            int expected = 5;
            int actual = UrgentCareAdmissions.addPatient(patientRecord, index, patientsListMoreThanOne, size);
            if (expected != actual) {
                return false;
            }
        }
        return true;
    }

    // Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each
    // test should verify that the returned size is as expected and that the array has been updated
    // (or not) appropriately

    /**
     * This method tests 2 cases. It tests the method removeNextPatient.
     * 1. In the first test, a patient record is removed from the patientsList array of size 5.
     * The size of the array is returned after the process is done which is expected to be 4.
     * If any other integer is returned, false is returned. Otherwise the next test starts.
     * 2.In the second test, a patient record, removed from the patientsList array of size 1.
     * The size of the array is expected to be 0. If any other integer is returned, false is returned.
     * If the test passes, true is returned.
     *
     * @return true is returned only if all tests pass otherwise false is returned.
     */
    public static boolean testRemovePatient() {
        // (1) remove a patient from a patientsList containing more than one record
        {
            int[][] patientsListMoreThanOne = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}, null, null
            };
            int patientsListMoreThanOneSize = 5;
            int expected = 4;
            int actual = UrgentCareAdmissions.removeNextPatient(patientsListMoreThanOne, patientsListMoreThanOneSize);
            if (expected != actual) {
                return false;
            }
        }


        // (2) remove a patient from a patientsList containing only one record
        {
            int[][] patientsListOne = new int[][]{
                    {46875, 1, UrgentCareAdmissions.RED}};
            int patientsListOneSize = 1;
            int expectedOne = 0;
            int actualOne = UrgentCareAdmissions.removeNextPatient(patientsListOne, patientsListOneSize);
            if (expectedOne != actualOne) {
                return false;
            }

        }
        return true;
    }
    // Tests the behavior of the getPatientIndex method using a non-empty, non-full array.

    /**
     * This test tests the getPatientIndex method. It has 3 cases.
     * 1. This case looks for the patient at the end of the list using caseID. The integer expected to be returned is 4
     * because the size of the list is 5.  If any other integer is returned, false is returned. Otherwise
     * the next test starts.
     * 2.  This case looks for the patient at the middle of the list using caseID. The integer expected to be returned is 2
     * because the size of the list is 5.  If any other integer is returned, false is returned. Otherwise
     * the next test starts.
     * 3. This case looks for a patient with an invalid caseID. The integer expected to be returned is -1
     * because the method returns -1 if the patient doesn't exist.  If any other integer is returned, false is returned. Otherwise
     * the next test starts.
     *
     * @return true is returned only if all tests pass otherwise false is returned.
     */


    public static boolean testGetPatientIndex() {
        // (1) look for a patient at the end of the list
        {
            int[][] patientsList = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}, null, null
            };
            int patientsNumber = 5;
            int expected = 4;
            int caseID = 31451;
            int actualNumber = UrgentCareAdmissions.getPatientIndex(caseID, patientsList, patientsNumber);
            if (expected != actualNumber) {
                return false;
            }
        }
        // (2) look for a patient in the middle of the list
        {
            int[][] patientsList = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}, null, null
            };
            int patientsNumber = 5;
            int expected = 2;
            int caseID = 28002;
            int actualNumber = UrgentCareAdmissions.getPatientIndex(caseID, patientsList, patientsNumber);
            if (expected != actualNumber) {
                return false;
            }
        }
        {
            // (3) look for a patient not present in the list
            int[][] patientsList = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}, null, null
            };

            int patientsNumber = 5;
            int expected = -1;
            int caseID = 28004;
            int actualNumber = UrgentCareAdmissions.getPatientIndex(caseID, patientsList, patientsNumber);
            if (expected != actualNumber) {
                return false;
            }
        }
        return true;
    }

    // Tests the getLongestWaitingPatientIndex method using a non-empty, non-full array. When
    // designing these tests, recall that arrivalOrder values will all be unique!

    /**
     * This test tests the getLongestWaitingPatientIndex method.It has 2 cases.
     * 1. In this test, the patientsList array has only one patientRecord.
     * Since there is only one record, the index expected to be returned is 0.
     * If any other integer is returned, false is returned.
     * 2. In this test, the patientsList array has 5 patient records.
     * The patient with the least integer in its priority is placed at index 0.
     * The integer to be returned is expected to be 0. If any other integer is returned,
     * false is returned.
     *
     * @return true is returned only if all tests pass otherwise false is returned.
     */
    public static boolean testLongestWaitingPatient() {
        // (1) call the method on a patientsList with only one patient
        {
            int[][] patientsListOne = new int[][]{
                    {46875, 1, UrgentCareAdmissions.RED}};

            int size = 1;
            int actualNumber = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListOne, size);
            int expected = 0;
            if (actualNumber != expected) {
                return false;
            }


        }
        // (2) call the method on a patientsList with at least three patients
        {
            int[][] patientsList = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}, null, null
            };

            int size = 5;
            int actualNumber = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsList, size);
            int expected = 0;
            if (actualNumber != expected) {
                return false;
            }
        }

        return true;
    }

    // Tests the edge case behavior of the UrgentCareAdmissions methods using empty and full arrays

    /**
     * This method tests edge case behaviour of the UrgentCareAdmissions methods using empty and full arrays.
     * It has 6 cases.
     * 1.This case tests the getAdmissionIndex method. The array used is empty i.e. the patientsList array
     * is empty. The size of the array is 0. The triage level used is 0.The integer expected to be
     * returned is -1. If any other integer is returned, false is returned.
     * 2. This case tests the getAdmissionIndex method. The array used is full i.e. the patientsList array
     * is full. The size of the array is 5. The triage level used is 0. The integer expected to be returned is 1
     * because the Red patient is at index 0 and if the patients of RED level needs to be added, the index will be 1.
     * If any other integer is returned, false is returned.
     * 3. This case tests the addPatient method.The array used is full i.e. the patientsList array
     * is full. The size is 5. The size after adding the patient is added. A new patient record is added.
     * The integer to be expected is 5. If any other integer is returned, false is returned.
     * 4. This case tests the removeNextPatient method.An empty array is used. The size is 0.
     * Since the method returns 0 is the array is empty,the integer to be expected is 0.If any other integer
     * is returned, false is returned.
     * 5. This case tests the getPatientIndex method using an empty array. The patientsList array is empty.
     * A patient record with a caseID is used. Since the method returns -1 for empty array, the integer
     * to be expected is -1.If any other integer is returned, false is returned.
     * 6. This case tests the getLongestWaitingPatientIndex using an empty patientsList array.
     * Since the method returns -1 if the array is empty, -1 is expected to be returned.
     *
     * @return true is returned only if all tests pass otherwise false is returned.
     */


    public static boolean testEmptyAndFullArrays() {
        // (1) test getAdmissionIndex using an empty patientsList array and any triage level
        {
            int[][] patientsList = new int[0][];
            int size = 0;
            int expected = -1;
            int triage = 0;
            int actualNumber = UrgentCareAdmissions.getAdmissionIndex(triage, patientsList, size);
            if (actualNumber != expected) {
                return false;
            }

        }

        // (2) test getAdmissionIndex using a full patientsList array and any triage level
        {
            int[][] patientsList = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}
            };

            int size = 5;
            int expected = 1;
            int triage = 0;
            int actualNumber = UrgentCareAdmissions.getAdmissionIndex(triage, patientsList, size);
            if (actualNumber != expected) {
                return false;
            }

        }
        // (3) test addPatient using a full patientsList array
        {
            int[][] patientsList = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}
            };
            int[] patientRecord = {21768, 0, UrgentCareAdmissions.RED};
            int index = 2;
            int size = 5;
            int expected = 5;
            int actualNumber = UrgentCareAdmissions.addPatient(patientRecord, index, patientsList, size);
            if (actualNumber != expected) {
                return false;
            }
        }


        // (4) test removeNextPatient using an empty patientsList array
        {
            int[][] patientsList = new int[0][];
            int size = 0;
            int expected = 0;
            int actualNumber = UrgentCareAdmissions.removeNextPatient(patientsList, size);
            if (actualNumber != expected) {
                return false;
            }
        }
        // (5) test getPatientIndex using an empty patientsList array
        {
            {
                int[][] patientsList = new int[0][];
                int caseID = 76474;
                int size = 0;
                int expected = -1;
                int actualNumber = UrgentCareAdmissions.getPatientIndex(caseID, patientsList, size);
                if (actualNumber != expected) {
                    return false;
                }
            }
        }        // (6) test getLongestWaitingPatientIndex using an empty patientsList array
        {
            int[][] patientsList = new int[0][];
            int size = 0;
            int caseID = 45678;
            int expected = -1;
            int actualNumber = UrgentCareAdmissions.getPatientIndex(caseID, patientsList, size);
            if (actualNumber != expected) {
                return false;
            }

            return true;
        }
    }

    // Tests the getSummary method using arrays in various states

    /**
     * This method tests the getSummary method using arrays in various states.
     * 1. This case tests the getSummary method using an empty array. The patientsList is empty and the size is 0.
     * If the string returned is not equals to the expected string in which the red counter, yellow counter
     * and green counter have values as 0, false is returned.
     * 2. This case tests the getSummary method with multiple patients at a single triage level.
     * The array used has 5 reds, 0 yellows and 0 greens. If the string returned is not the same as the expected string,
     * in which the red counter is 5, and the  yellow counter and green counter have values as 0, false is returned.
     * 3. This case tests the getSummary method with multiple patients at multiple triage levels. In this array, 1 red,
     * 3 yellow and 1 green is used. If the string returned is not the same as the expected string,
     * in which the red counter is 1, the yellow counter is 3 and green counter have values as 1, false is returned.
     *
     * @return true is returned only if all tests pass otherwise false is returned.
     */


    public static boolean testGetSummary() {
        // (1) test getSummary using an empty patientsList
        {
            int[][] patientsList = new int[0][];
            int size = 0;
            int redCounter = 0;
            int yellowCounter = 0;
            int greenCounter = 0;
            String summary = "Total number of patients: " + size + "\n" + "RED: " + redCounter + "\n" + "YELLOW: " + yellowCounter + "\n" + "GREEN: " + greenCounter + "\n";
            String actual = UrgentCareAdmissions.getSummary(patientsList, size);
            if (!actual.equals(summary)) {
                //System.out.println("A"); //968
                return false;
            }
        }
        // (2) test getSummary using a patientsList with multiple patients at a single triage level
        {
            int[][] patientsList = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.RED},
                    {28002, 5, UrgentCareAdmissions.RED},
                    {11956, 3, UrgentCareAdmissions.RED},
                    {31451, 4, UrgentCareAdmissions.RED}
            };
            int size = 5;
            int redCounter = 5;
            int yellowCounter = 0;
            int greenCounter = 0;
            String expected = "Total number of patients: " + size + "\n" + "RED: " + redCounter + "\n" + "YELLOW: " + yellowCounter + "\n" + "GREEN: " + greenCounter + "\n";
            String actual = UrgentCareAdmissions.getSummary(patientsList, size);
            if (!actual.equals(expected)) {
                // System.out.println("B"); //968
                return false;
            }

        }
        // (3) test getSummary using a patientsList with patients at all triage levels
        {
            int[][] patientsList = new int[][]{
                    {45675, 1, UrgentCareAdmissions.RED},
                    {42001, 2, UrgentCareAdmissions.YELLOW},
                    {28002, 5, UrgentCareAdmissions.YELLOW},
                    {11956, 3, UrgentCareAdmissions.YELLOW},
                    {31451, 4, UrgentCareAdmissions.GREEN}
            };
            int size = 5;
            int redCounter = 1;
            int yellowCounter = 3;
            int greenCounter = 1;
            String expected = "Total number of patients: " + size + "\n" + "RED: " + redCounter + "\n" + "YELLOW: " + yellowCounter + "\n" + "GREEN: " + greenCounter + "\n";
            String actual = UrgentCareAdmissions.getSummary(patientsList, size);
            if (!actual.equals(expected)) {
                System.out.println("C"); //968
                return false;
            }
        }
        return true;
    }

    /**
     * Runs each of the tester methods and displays their result
     *
     * @param args unused
     */
    public static void main(String[] args) {
        System.out.println("get index: " + testGetIndex());
        System.out.println("add patient: " + testAddPatient());
        System.out.println("remove patient: " + testRemovePatient());
        System.out.println("get patient: " + testGetPatientIndex());
        System.out.println("longest wait: " + testLongestWaitingPatient());
        System.out.println("empty/full: " + testEmptyAndFullArrays());
        System.out.println("get summary: " + testGetSummary());
    }

}  
