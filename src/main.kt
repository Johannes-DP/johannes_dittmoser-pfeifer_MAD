import java.util.Scanner

fun Random4Digit(): IntArray{       //generates a random 4 Digit Number without Duplicates and returns it as Array
    var number = IntArray(4)
    for (i in 0..3){
        number[i] = (0..9).random()
        for(j in 0..i){
            while(number[i] == number[j] && i != j){
                number[i] = (0..9).random()
            }
        }
    }
    return number
}

fun Convertor(integer: Int) : IntArray{ //Convertes the number given by the user, in an array similar to the one in the function above
    var guessedNumber = IntArray(4)

    guessedNumber[3] = integer % 10
    guessedNumber[2] = (integer/10) % 10
    guessedNumber[1] = (integer/100) % 10
    guessedNumber[0] = integer / 1000

    return guessedNumber
}

fun CheckForRightDigits(guessedNumber: IntArray, number : IntArray): Int{   //checks how many digits are placed correctly by the user
    var counter = 0
    for (i in 0..3){
        if(guessedNumber[i] == number[i]){
            counter++;
        }
    }
    return counter
}

fun CheckForWrongPlace(guessedNumber: IntArray, number : IntArray): Int {   //checks how many right digits from the guess of the user are placed wrong.
    var counter = 0;
    var temp = arrayOf(10,10,10,10)
    var index = 0
    for(i in 0..3){
        for(j in 0..3){
            if(guessedNumber[j] == number[i] && !temp.contains(guessedNumber[j])){
                counter++;
                temp[index] = guessedNumber[j]
                index++
            }
        }
    }
    return counter
}


fun main() {
    val number = Random4Digit()         //Number that needs to be guessed
    var guessedNumber : IntArray
    var rightDigits = 0
    var wrongPlace: Int
    val input = Scanner(System.`in`)
    var integer: Int

    while(rightDigits != 4){
        println("Enter 4 Digits: ")
        integer = input.nextInt()           //Input for Guess
        guessedNumber = Convertor(integer)  //Converting the Int in an Array

        rightDigits = CheckForRightDigits(guessedNumber,number)
        wrongPlace = CheckForWrongPlace(guessedNumber,number)

        println ("$rightDigits : $wrongPlace")
    }
}
