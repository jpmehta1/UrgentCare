// Title:    UrgentCareAdmissions

/**
 * In this class, methods are used to edit or use the contents inside the array to give the specific output.
 * The methods used are getAdmissionIndex,addPatient,removeNextPatient,getPatientIndex, getLongestWaitingPatientIndex and getSummary
 */
public class UrgentCareAdmissions {
    public static final int GREEN = 2;
    public static final int YELLOW = 1;
    public static final int RED = 0;

    /**
     *In this method, the index at which the new patient of the given triage level, is identified and returned.
     * The for loops help to identify the index by searching through the array and by telling us where to add the new patient in the array
     * This  method doesn't modify anything in the list.
     * @param triage it is the urgency level of a patient,which are red,yellow and green
     * @param patientsList the current list of patient records
     * @param size the current number of patients in the list
     * @return returns the index at which the patient should be inserted into, and it returns -1 if the patients' list is full
     */
    public static int getAdmissionIndex(int triage,
                                         int[][] patientsList,
                                         int size) {

        if (triage == 0) {
            for (int i = 0; i < size; i++) {
                if (patientsList[i][2] == RED) {
                    continue;
                } else {
                    return i;
                }
            }


        }
       else if (triage == 1) {
            for (int i = 0; i < size; i++) {
                if (patientsList[i][2] == RED||patientsList[i][2]==YELLOW) {

                    if((i==size-1)&& patientsList[i][2]==YELLOW){
                        return i;
                    }
                    continue;
                } else {
                    return i;
                }
            }
        }

        else if (triage == 2) {
           return size;
        }

        return -1;
    }

    /**
     * In this method, a new patient record needs to be added to the active patient record.
     * A new array is made and various for loops are used to add the elements of the previous array including the new patient record that needs to be added.
     * The method checks whether the array is full, the index is useful or if the index is out of bounds.
     * @param patientRecord it is a three element perfect size array of ints, it contains the five digit case ID ,admission order number and their triage level
     * @param index it is the index at which the patientRecord should be added to the patientsList
     * @param patientsList the active list of patients
     * @param size number of patients in the current list
     * @return the number of patients after this method has finished running
     */
    public static int addPatient(int[] patientRecord,
                                 int index,
                                 int[][] patientsList,
                                 int size) {

        if (size == patientsList.length) {
            return size;
        } else if (index<0||index>size) {
            return size;
        }else if (index >= patientsList.length) {
                return size;
            }
int [][]temporaryArray = new int [patientsList.length][];
            for(int i = 0; i<index;++i){
                temporaryArray[i] = patientsList[i];
            }

            temporaryArray[index]=patientRecord;

            for(int i = index;i<size;++i){
                temporaryArray[i+1]=patientsList[i];

            }
            for(int i = 0; i<patientsList.length;++i){

                patientsList[i]=temporaryArray[i];
            }


size+=1;
        return size;
    }

    /**
     * It removes the patient record at index 0(if there's any), and updates the list by shifting the remaining patient
     * records to the left. It returns 0 if there is no patient record at order 0.
     * @param patientsList the active list of patient records
     * @param size the number of patients in the list.
     * @return the number of patients in the list after the process is done.
     */
    public static int removeNextPatient(int[][] patientsList,
                                        int size) {
        if (size == 0) {
            return 0;
        } else {
            for (int i = 0; i < patientsList.length - 1; ++i) {
                patientsList[i] = patientsList[i + 1];
            }
            return size-1;
        }
    }

    /**
     *This method gives the patient index by the specified caseID. Whichever record matches the caseID, the index of that recrod is returned.
     * @param caseID the five digit patient case ID
     * @param patientsList the active list of patient records.
     * @param size the number of patients in the list
     * @return the index of the patient record matching the ID number is returned, and -1 is returned if the list is empty or the caseID is not found
     */
    public static int getPatientIndex(int caseID,
                                      int[][] patientsList,
                                      int size) {

        for (int i = 0; i < size; ++i) {
            if (patientsList[i][0] == caseID) {
                return i;
            }
        }
        return -1;

    }

    /**
     * This method returns the index of the longest waiting patient index. The patient with the least number is given top priority
     * and the index is returned.
     * @param patientsList the current list of active patients
     * @param size the number of patients in the list
     * @return -1 is returned if the patientsList is empty, or the index of the longest waiting patient is returned.
     */
    public static int getLongestWaitingPatientIndex(int[][] patientsList,
                                                    int size) {

        if (patientsList[0] == null) {
            return -1;
        }

       else {
            int indexToReturn = 0;
            int minimum = patientsList[0][1];
            for (int i = 0; i < size; ++i) {
                if (patientsList[i][1] < minimum) {
                    minimum = patientsList[i][1];
                    indexToReturn = i;

                }

            }
            return indexToReturn;

        }
    }

    /**
     *This method gives a summary of the patientsList. It uses the information about the various triage levels and gives the summary.
     * @param patientsList the current list of patient records
     * @param size the number of patients currently in the list
     * @return returns the string corresponding to each condition.
     */
    public static String getSummary(int[][] patientsList,
                                    int size) {

        if(size==0){
            int redCounterZero =0;
            int yellowCounterZero =0;
            int greenCounterZero = 0;
            return "Total number of patients: " + size + "\n" + "RED: " + redCounterZero + "\n" + "YELLOW: " + yellowCounterZero + "\n" + "GREEN: " + greenCounterZero + "\n";
   } else {
            int redCounter = 0;
            for (int i = 0; i < size; ++i) {
                if (patientsList[i][2] == 0) {
                    redCounter++;
                }
                continue;
            }
            int yellowCounter = 0;
            for (int i = 0; i < size; ++i) {
                if (patientsList[i][2] == 1) {
                    yellowCounter++;
                }
                continue;
            }
            int greenCounter = 0;
            for (int i = 0; i < size; ++i) {
                if (patientsList[i][2] == 2) {
                    greenCounter++;
                }
                continue;
            }

            return ("Total number of patients: " + size + "\n" + "RED: " + redCounter + "\n" + "YELLOW: " + yellowCounter + "\n" + "GREEN: " + greenCounter + "\n");
        }
    }

    /**
     * main method
     * @param args unused
     */
    public static void main(String[] args){

    }

}










