package com.vismijatech.instantpost.utils

/**
 * Created by Vivek Sawant on 30-11-2023.
 */

// checks if the name input is valid.
fun isValidName(name: String): Boolean {
    // Check if the name is not empty and within the length range (2 to 50 characters)
    if (name.isNotEmpty() && name.length in 2..50) {
        // Check if each character is a letter or space
        for (char in name) {
            if (!char.isLetter() && char != ' ') {
                return false
            }
        }
        return true
    }
    return false
}

// check if the name is in range 2 to 50
fun isNameInRange(name: String): Boolean {
    return name.length in 2..50
}

//Check if each character is a letter or space
fun isContainsNumberOrSpace(input: String): Boolean {
    for (char in input) {
        if (!char.isLetter() && char != ' ') {
            return false
        }
    }
    return true
}
