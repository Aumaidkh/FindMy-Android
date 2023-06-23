package com.hopcape.findmy.core.domain.utils

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hopcape.findmy.core.data.utils.EncryptorImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class EncryptionTest {

    private lateinit var encryptor: Encryptor

    @Before
    fun setup(){
        encryptor = EncryptorImpl("Aumaid")
    }

    @Test fun testEncryptMethod_encryptsTheString_Correctly() {
        val string1 = encryptor.encrypt("HelloWorld")
        val string2 = encryptor.decrypt(string1)
        assert(string2.equals("HelloWorld",false))
    }

    @Test fun testEncryptMethod_returnsSameOutput_forSameString() {
        val string1 = encryptor.encrypt("HelloWorld")
        val string2 = encryptor.encrypt("HelloWorld")
        assert(string1.equals(string2,false))
    }
}