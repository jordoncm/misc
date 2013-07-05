/* A computer science graduate's response. */

class FizzBuzzHundred {
    public void printNumbers() { // default number
       /*Call func     */
     this.printNumbers(0);
  }


    public void printNumbers(int s) {
System.out.println("");

                        if((((s+1))% 3)==  0)
                    System.out.print("Fizz");//Print Fizz

        if(  ( s +1)  % 5 == 0 /* Is divivible print "Buzz". */)       System.out.print("Buzz");

        if((s+1)%3!=0&&(s+1)%5!=0)
           System.out.print(Integer.toString( s+1));// Must use a recursive call
//here to continue until we get to the last one.
   if  ( s< 99) this.printNumbers (s + 1);

    }



}

/* A "Java guru's" response. */

/**
 * Class that prints the numbers from 1 to 100. But for multiples of three
 * print "Fizz" instead of the number and for the multiples of five print
 * "Buzz". For numbers which are multiples of both three and five print
 * "FizzBuzz".
 */
class FizzBuzzOneToOneHundred {

    /**
     * The value to start printing numbers at.
     */
    protected static int START = 0;

    /**
     * The value to stop printing numbers at.
     */
    protected static int END = 100;

    /**
     * Text to display when number is divisible by three.
     */
    protected static String MOD_THREE_TEXT = "Fizz";

    /**
     * Number to use when dividing by three.
     */
    protected static int THREE = 3;

    /**
     * Text to display when number is divisible by five.
     */
    protected static String MOD_FIVE_TEXT = "Buzz";

    /**
     * Number to use when dividing by five.
     */
    protected static int FIVE = 5;

    /**
     * Determines whether or not the given dividend is divisible by three.
     *
     * @param dividend The dividend to test divisibility with.
     * @return True if dividend is divisible by three, false otherwise.
     */
    protected boolean isDivisibleByThree(int dividend) {
        return this.isDivisibleBy(dividend, FizzBuzzOneToOneHundred.THREE);
    }

    /**
     * Determines whether or not the given dividend is divisible by five.
     *
     * @param dividend The dividend to test divisibility with.
     * @return True if dividend is divisible by five, false otherwise.
     */
    protected boolean isDivisibleByFive(int dividend) {
        return this.isDivisibleBy(dividend, FizzBuzzOneToOneHundred.FIVE);
    }

    /**
     * Determines whether or not the given dividend is divisible by the given
     * divisor.
     *
     * @param dividend The dividend to test divisibility with.
     * @param divisor The divisor to test divisibility with.
     * @return True if dividend is divisible by the divisor, false otherwise.
     */
    protected boolean isDivisibleBy(int dividend, int divisor) {
        return (dividend % divisor == 0);
    }

    /**
     * Builds the output string of numbers, "Fizz", "Buzz" and "FizzBuzz"
     * lines.
     *
     * @return The string of output.
     */
    protected String buildOutputString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = FizzBuzzOneToOneHundred.START;
        int dividend = 0;

        for(; i < FizzBuzzOneToOneHundred.END; i++) {
            dividend = i + 1;
            if(this.isDivisibleByThree(dividend)) {
                stringBuilder.append(FizzBuzzOneToOneHundred.MOD_THREE_TEXT);
            }
            if(this.isDivisibleByFive(dividend)) {
                stringBuilder.append(FizzBuzzOneToOneHundred.MOD_FIVE_TEXT);
            }
            if(!this.isDivisibleByThree(dividend) && !this.isDivisibleByFive(dividend)) {
                stringBuilder.append(dividend);
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    /**
     * Prints the output string of numbers, "Fizz", "Buzz" and "FizzBuzz"
     * lines.
     */
    protected void printOutputString() {
        System.out.print(this.buildOutputString());
    }
}

/* A real programmers response. */

class FizzBuzz {
    public static int START = 1;
    public static int END = 100;
    /**
     * Prints out numbers based on constants START and END above, highlighting
     * numbers divisible by 3 with the word "Fizz" and 5 with the word "Buzz".
     */
    public void print() {
        boolean divisible = false;
        for(int i = FizzBuzz.START; i <= FizzBuzz.END; i++) {
            divisible = false;
            if(i % 3 == 0) {
                System.out.print("Fizz");
                divisible = true;
            }
            if(i % 5 == 0) {
                System.out.print("Buzz");
                divisible = true;
            }
            if(!divisible) {
                System.out.println(i);
            } else {
                System.out.println("");
            }
        }
    }
}

class FizzBuzzApp {
    public static void main(String[] args) {
        FizzBuzzHundred fbh = new FizzBuzzHundred();
        fbh.printNumbers();
        FizzBuzzOneToOneHundred fboto = new FizzBuzzOneToOneHundred();
        fboto.printOutputString();
        FizzBuzz fb = new FizzBuzz();
        fb.print();
    }
}
