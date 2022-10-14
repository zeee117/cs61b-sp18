/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    private static final int radix = 256;

    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxLength = 0;
        for(String s : asciis){
            maxLength = s.length() > maxLength ? s.length() : maxLength;
        }
        for(int index=maxLength - 1; index>=0; index--){
            sortHelperLSD(asciis, index);
        }
        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        if(index < 0){
            throw new IndexOutOfBoundsException();
        }
        int[] count = new int[radix + 1];
        for(String s : asciis){
            count[stringToIndex(s, index)]++;
        }
        int[] pos = new int[radix + 1];
        int now = 0;
        for(int i=0; i<radix + 1; i++){
            pos[i] = now;
            now += count[i];
        }
        String ans[] = new String[asciis.length];
        for(String s : asciis){
            int ind = stringToIndex(s, index);
            ans[pos[ind]] = s;
            pos[ind]++;
        }
        for(int i=0; i<ans.length; i++){
            asciis[i] = ans[i];
        }
    }

    private static int stringToIndex(String s, int index){
        if(index >= s.length()){
            return 0;
        }
        return (int)s.charAt(index) + 1;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = new String[] {"2", "100", "54", "64", "342", "2343"};
        String[] output = RadixSort.sort(asciis);
        for(String s : output){
            System.out.print(s + ", ");
        }
        System.out.println();

        String[] asciis2 = new String[] {"", "  ", "    ", "      "};
        String[] output2 = RadixSort.sort(asciis2);
        for(String s : output2){
            System.out.print(s + ", ");
        }
    }
}
