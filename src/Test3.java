import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {
        System.out.println("1: " + replaceVovels("apple") + " "
                + replaceVovels("Even if you did this task not by yourself, " +
                "you have to understand every single line of code."));
        System.out.println("2: " + stringTransform("hello") + " " + stringTransform("bookkeeper"));
        System.out.println("3: " + doesBlockFit(1, 3, 5, 4, 5) + " "
                + doesBlockFit(1, 8, 1, 1, 1) + " "
                + doesBlockFit(1, 2, 2, 1, 1));
        System.out.println("4: " + numCheck(243) + " " + numCheck(52));
        int[] numArray = {1, -3, 2};
        System.out.print("5: " + countRoots(numArray) + " ");
        numArray = new int[]{2, 5, 2};
        System.out.print(countRoots(numArray) + " ");
        numArray = new int[]{1, -6, 9};
        System.out.println(countRoots(numArray));
        String[][] stringArray = {{"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                                    {"Banana", "Shop2", "Shop3", "Shop4"},
                                    {"Orange", "Shop1", "Shop3", "Shop4"},
                                    {"Pear", "Shop2", "Shop4"}};
        System.out.println("6: " + Arrays.toString(salesData(stringArray)) + " ");
        stringArray = new String[][] {{"Fridge", "Shop2", "Shop3"},
                                        {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
                                        {"Laptop", "Shop3", "Shop4"},
                                        {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}};
        System.out.println(Arrays.toString(salesData(stringArray)));
        System.out.println("7: " + validSplit("apple eagle egg goat") + " "
                + validSplit("cat dog goose fish"));
        numArray = new int[]{3, 1, 4, 2, 7, 5};
        System.out.print("8: " + waveForm(numArray) + " ");
        numArray = new int[]{1, 2, 3, 4, 5};
        System.out.print(waveForm(numArray) + " ");
        numArray = new int[]{1, 2, -6, 10, 3};
        System.out.println(waveForm(numArray));
        System.out.println("9: " + commonVowel("Hello world") + " "
                + commonVowel("Actions speak louder than words."));
        int[][] doubleArray = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {5, 5, 5, 5, 5}, {7, 4, 3, 14, 2}, {1, 0, 11, 10, 1}};
        System.out.println("10: " + Arrays.deepToString((dataScience(doubleArray))) + " ");
        doubleArray = new int[][] {{6, 4, 19, 0, 0}, {81, 25, 3, 1, 17}, {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217}, {5, 73, 0, 4, 21}};
        System.out.println(Arrays.deepToString((dataScience(doubleArray))));


    }

    //1
    public static String replaceVovels(String string) {
        string = string.toLowerCase();
        char[] newString = string.toCharArray();
        char[] vovels = {'e', 'y', 'u', 'i', 'o', 'a'};
        for (int i = 0; i < newString.length; i++) {
            for (int j = 0; j < vovels.length; j++) {
                if (newString[i] == vovels[j]) {
                    newString[i] = '*';
                    break;
                }
            }
        }
        return String.valueOf(newString);
    }

    //2
    public static String stringTransform(String string) {
        char[] newString = string.toCharArray();
        String[] finalString = new String[newString.length];
        for (int i = 0; i < newString.length - 1; i++) {
            if (newString[i] == newString[i + 1]) {
                finalString[i] = "Double" + String.valueOf(newString[i]).toUpperCase();
                finalString[i + 1] = "";
                i++;
            } else {
                finalString[i] = String.valueOf(newString[i]);
            }
        }
        finalString[finalString.length - 1] = String.valueOf(newString[newString.length - 1]);
        return String.join("", finalString);
    }

    //3
    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        if ((a <= w && b <= h) || (a <= h && b <= w) || (a <= w && c <= h) ||
                (a <= h && c <= w) || (b <= w && c <= h) || (b <= h && c <= w)) {
            return true;
        }
        return false;
    }

    //4
    public static boolean numCheck(int num) {
        int squareSumm = 0;
        for (int i = 0; i < String.valueOf(num).length(); i++) {
            squareSumm += (int) Math.pow(((int) (num / Math.pow(10, i))) % 10, 2);
        }
        return (squareSumm % 2 == num % 2);
    }

    //5
    public static int countRoots(int[] numArray) {
        int a = numArray[0];
        int b = numArray[1];
        int c = numArray[2];
        int rootCounter = 0;
        int D = (int) Math.pow(b, 2) - 4 * a * c;
        if (D >= 0) {
            if (D == 0) {
                if ((int) (((double) (-b / 2 * a)) * 10) % 10 == 0) rootCounter += 1;
            } else {
                if ((int) ((((-b + Math.sqrt(D)) / (2 * a))) * 10) % 10 == 0) rootCounter += 1;
                if ((int) ((((-b - Math.sqrt(D)) / (2 * a))) * 10) % 10 == 0) rootCounter += 1;
            }
        }
        return rootCounter;
    }

    //6
    public static String[] salesData(String[][] sales) {
        int maxLen = 0;
        String productList = "";
        for (int i = 0; i < sales.length; i++) {
            if (sales[i].length > maxLen) maxLen = sales[i].length; //Ищем максимальную длину среди массивов
        }
        for (int i = 0; i < sales.length; i++) {
            if (sales[i].length == maxLen)  productList += sales[i][0] + " ";
        }
        String[] productListArray = productList.split(" ");
        return productListArray;
    }

    //7
    public static boolean validSplit(String sentence) {
        String[] wordArray = sentence.split(" ");
        for (int i = 0; i < wordArray.length - 1; i++) {
            if (wordArray[i].toCharArray()[wordArray[i].toCharArray().length - 1] !=
                    wordArray[i + 1].toCharArray()[0]) {
                return false;
            }
        }
        return true;
    }

    //8
    public static boolean waveForm(int[] numArray) {
        for (int i = 0; i < numArray.length - 2; i++) {
            if ((numArray[i] <= numArray[i + 1] && numArray[i + 1] <= numArray[i + 2]) ||
                    (numArray[i] >= numArray[i + 1] && numArray[i + 1] >= numArray[i + 2])) {
                return false;
            }
        }
        return true;
    }

    //9
    public static char commonVowel(String string) {
        int[] vowelsCounts = new int[6];
        int[] vowels = {'e', 'y', 'u', 'i', 'o', 'a'};
        for (char element : string.toCharArray()) {
            switch (element) {
                case 'e':
                    vowelsCounts[0]++;
                case 'y':
                    vowelsCounts[1]++;
                case 'u':
                    vowelsCounts[2]++;
                case 'i':
                    vowelsCounts[3]++;
                case 'o':
                    vowelsCounts[4]++;
                case 'a':
                    vowelsCounts[5]++;
            }
        }
        int max = vowelsCounts[0];
        for (int i = 1; i < vowelsCounts.length; i++) {
            if (vowelsCounts[i] > max) {
                max = vowelsCounts[i];
            }
        }
        return (char) vowels[indexOf(vowelsCounts, max)];
    }

    public static int indexOf(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    //10
    public static int[][] dataScience(int[][] dataArray) {
        for (int i = 0; i < dataArray.length; i++) { //Перебор элементов вложенного массива
            int sum = 0;
            for (int j = 0; j < dataArray.length; j++) { //Перебор массивов в массиве
                if (j != i) {
                    sum += dataArray[j][i];
                }
            }
            dataArray[i][i] = (int) (Math.round((double) sum / (dataArray.length - 1)));
        }
        return dataArray;
    }
}
