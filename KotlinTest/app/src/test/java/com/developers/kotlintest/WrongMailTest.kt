package com.developers.kotlintest

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
class WrongMailTest : FunSpec() {

    private val wrongMail = "amanjeetsingh150@"

    init {
        test("Wrong mail: ($wrongMail) should fail the test and return false.") {
            val emailValidator = EmailValidator()
            emailValidator.isValidEmail(wrongMail) shouldBe false
        }

    }
}