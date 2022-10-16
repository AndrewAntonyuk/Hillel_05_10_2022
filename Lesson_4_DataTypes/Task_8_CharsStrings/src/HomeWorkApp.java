import hw_8_Game.Plant;

public class HomeWorkApp {
    public static void main(String[] args) {
        HomeWorkApp hw = new HomeWorkApp();
        String source2 = "abcdaaabcdaa";
        char valSearch2 = 'c';
        String source3 = "Apollo";
        String target3 = "pollo";
        String source4 = "Hello";
        String source5 = "ERE";
        Plant plant = new Plant();

        System.out.println("Subtask 2:");
        System.out.println("Character \'" + valSearch2 + "\' repeats in the string \""
                + source2 + "\" " + hw.findSymbolOccurance(source2,valSearch2)
                + " times \n");

        System.out.println("Subtask 3:");
        System.out.println("Source: " + source3);
        System.out.println("Target: " + target3);
        System.out.println("Result: " + hw.findWordPosition(source3,target3) + "\n");

        System.out.println("Subtask 4:");
        System.out.println(source4 + " -> " + hw.stringReverse(source4) + "\n");

        System.out.println("Subtask 5:");
        System.out.println(source5 + " -> " + hw.isPalindrome(source5) + "\n");

        System.out.println("Subtask 6:");
        plant.actionsForGame();
    }

    public int findSymbolOccurance(String source, char valSearch){
        int sum = 0;
        char[] arr = source.toCharArray();

        for(char tmpChar : arr){
            if(tmpChar == valSearch){
                sum++;
            }
        }

        return sum;
    }

    public int findWordPosition(String source, String target){
        int ret;

        ret = source.indexOf(target);

        return ret;
    }

    public String stringReverse(String str){
        char[] srcArr = str.toCharArray();
        char[] trgArr = str.toCharArray();
        int max = str.length() - 1;
        String ret = "";

        for(int i = max; i >= 0; i--){
            trgArr[max - i] = srcArr[i];
        }
        ret = new String(trgArr);

        return ret;
    }

    public boolean isPalindrome(String str){
        boolean ret = false;
        String stringReversed = stringReverse(str);

        if(str.equals(stringReversed)){
            ret = true;
        }

        return ret;
    }
}

