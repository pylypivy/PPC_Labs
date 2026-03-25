package week10_labs;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

/** The goal of this practice is not to "get the answer" through AI or some other means, but for you to think through the questions and
* come up with a strategy. You can decide not to do it at your own cost.
*/

/**
* In the following, write code to achieve what's asked. You don't need to but if you want to very the accuracy of your code,
* include statements to print the result.
*/

/** Submit to the TA, and he will assign you a grade based on a few selected responses.  */

public class Week10_labs
{
    public static void main(String[] args)
	{
        List<String> fruit = Arrays.asList("cherry","banana","berry","apple","cherry","kiwi","fig","date","lemon","honeydew","cherry","elderberry","apple","banana","grape");

		Set<String> fruitSet=fruit.stream().collect(Collectors.toSet());// Collect elements into a Set
		System.out.println("Collect elements into a Set: "+fruitSet);
		System.out.println("---------------------");
		
        Function<String,Character> getFirst = s -> s.charAt(0);//Returns the first character of each word 
        Map<Character,List<String>> groupedByFirstChar=fruitSet.stream().collect(Collectors.groupingBy(getFirst));// Collect the fruit into groups based on their first character
        System.out.println("Collect the fruit into groups based on their first character: "+groupedByFirstChar);
        System.out.println("---------------------");
		
        Function<String,Integer> wordLength=s -> s.length();//Returns the length of a word
        Map<Integer,List<String>> groupedByLength = fruitSet.stream().collect(Collectors.groupingBy(wordLength));// Group fruit by the length of the name
        System.out.println("Group fruit by the length of the name: "+groupedByLength);
        System.out.println("---------------------");
		
        Predicate<String> containsPart = s -> s.contains("erry");//Returns true if the word contains "erry"
        List<String> containsErry = fruitSet.stream().filter(containsPart).collect(Collectors.toList());//Collect the fruit that has erry in it 
        System.out.println("Collect the fruit that has erry in it: "+containsErry);
        System.out.println("---------------------");
        
		Map<Boolean, List<String>> fruitWithErry=fruitSet.stream().collect(Collectors.partitioningBy(containsPart));
		System.out.println("Create a partition of fruit based on if it contains erry: "+fruitWithErry); //Create a partition of fruit based on if it contains erry
		System.out.println("---------------------");

		Predicate<String> lessThanFive = s -> s.length() <= 5;//Returns true if the length on the word is 5 or less
		List<String> fruitsLessThanFive = fruitSet.stream().filter(lessThanFive).collect(Collectors.toList());
		System.out.println("Collect the fruit that has 5 or less symbols: "+fruitsLessThanFive);//collect the fruit that has 5 or less symbols
		System.out.println("---------------------");

		int symbolSum=fruitSet.stream().map(wordLength).mapToInt(Integer::intValue).sum();
		System.out.println("Total # of symbols in a list: "+ symbolSum);//find the total number of symbols in all the fruit stored
		System.out.println("---------------------");


		List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23, 56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

        Predicate<Integer> greaterThanFifty= x -> x >=50;//Returns true if the number is 50 or greater
        Map<Boolean,List<Integer>> partitionedData = data.stream().collect(Collectors.partitioningBy(greaterThanFifty));
        System.out.println("Partition data based on if >=50: "+partitionedData);// Partition data based on if >=50
        System.out.println("---------------------");
        
		Function<Integer,Integer> remainder = x -> x % 7;//Returns the remainder after deleting by 7
		Map<Integer,List<Integer>> groupedByRem=data.stream().collect(Collectors.groupingBy(remainder));
		System.out.println("Divide data into groups based on the remainder when divided by 7: "+groupedByRem);//divide data into groups based on the remainder when divided by 7
		System.out.println("---------------------");
		
		int sum = data.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Total of the data: "+ sum);//find the sum of the data
		System.out.println("---------------------");
		
		Set<Integer> uniqueData=data.stream().collect(Collectors.toSet());
		System.out.println("Unique values in a Data list: "+uniqueData);//collect the unique values
		System.out.println("---------------------");

        Function<Integer,Integer> cube = x -> x*x*x;//Returns the cube of the number
        List<Integer> cubedData = uniqueData.stream().map(cube).collect(Collectors.toList());
        System.out.println("Original set: "+ uniqueData);
        System.out.println("Computed cube of each values"+cubedData);//compute the cube of each values
        System.out.println("---------------------");

		int cubesSum = uniqueData.stream().map(cube).mapToInt(Integer::intValue).sum();//find the sum of the cubes of each value
		System.out.println("Total of the cubed values: "+cubesSum);
		System.out.println("---------------------");
		
		Function<Integer,Integer> plusFive = x -> x+5;//Adds 5 to the number and returns it
		List<Integer> increasedDataByFive = uniqueData.stream().map(plusFive).collect(Collectors.toList());
		System.out.println("Original set: "+ uniqueData);
		System.out.println("Increase the value of each element by 5: "+increasedDataByFive);//increase the value of each element by 5
		System.out.println("---------------------");

		Predicate<Integer> evenNum = x -> x%2 == 0;//Returns even values
		List<Integer> evenValues=uniqueData.stream().filter(evenNum).collect(Collectors.toList());
		List<Integer> cubesOfEvenValues = uniqueData.stream().filter(evenNum).map(cube).collect(Collectors.toList());
		System.out.println("Even values: "+ evenValues);
		System.out.println("Computed cube of even values"+cubesOfEvenValues);//compute the cube of the even values
		System.out.println("---------------------");

   }
}
