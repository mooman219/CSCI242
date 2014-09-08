
public class Problems {

    public boolean arrayLike(int[] nums, int value) {
        /* An array "likes" a particular value if at least half of
         the elements in the array differ from the value by 2 or
         less.
         */
        int inRange = 0;
        for (int i = 0; i <= nums.length; i++) {
            if ((nums[i] >= value - 2) && (nums[i] <= value + 2)) {
                inRange++;
            }
        }
        return (inRange >= (nums.length) / 2);
    }

    public static void snooze(int day) {

        if (day <= 5) // 1..5 are weekdays
        {
            System.out.println("Hi ho, hi ho, it's off to school we go...");
        }
        if (day == 1) // Something extra for Mon
        {
            System.out.println("It's not the weekend any more!");
        } else // 6..7, it's the weekend
        {
            System.out.println("Hit the snooze button now!");
        }

    }

    public static void main(String[] args) {
        int day = 0;

        day = Integer.parseInt(args[0]);
        snooze(day);

        int[] myArray = {1, 6, 8, 7, 0, 0};
        if (arrayLike(myArray, day)) {
            System.out.println("I like " + args[0]);
        } else {
            System.out.println("I don't like " + args[0]);
        }

    }

}
