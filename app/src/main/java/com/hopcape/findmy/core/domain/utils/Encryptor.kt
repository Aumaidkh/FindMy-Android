package com.hopcape.findmy.core.domain.utils

/**
 * Exposes functions to encrypt a given string or decrypt a given string*/
interface Encryptor {

    /**
     * Encrypts
     * @param textToEncrypt
     * @return encryptedString*/
    fun encrypt(textToEncrypt: String): String

    /**
     * Decrypts
     * @param encryptedData
     * @return decryptedString
     * */
    fun decrypt(encryptedData: String): String
}