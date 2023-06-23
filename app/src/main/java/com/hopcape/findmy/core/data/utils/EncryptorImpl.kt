package com.feature.authentication.data.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import com.core.common.utils.Encryptor
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class EncryptionHelper(
    val alias: String
) : Encryptor {

    private val keyStore by lazy {
        KeyStore.getInstance("AndroidKeystore").apply {
            load(null)
        }
    }

    private fun decryptionCipher(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(
                Cipher.DECRYPT_MODE, getKey(),IvParameterSpec(iv)
            )
        }
    }

    private fun getKey(): SecretKey {
        val secretKey = keyStore.getEntry(alias, null) as? KeyStore.SecretKeyEntry
        return secretKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .build()
            )
        }

        return keyGenerator.generateKey()
    }

    override fun encrypt(textToEncrypt: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE,getKey())
        }

        val iv = cipher.iv
        val encryptedBytes = cipher.doFinal(textToEncrypt.toByteArray())

        val combined = ByteArray(iv.size + encryptedBytes.size)
        System.arraycopy(iv,0,combined,0,iv.size)
        System.arraycopy(encryptedBytes,0,combined,iv.size,encryptedBytes.size)
        return Base64.encodeToString(combined,Base64.DEFAULT)
    }

    override fun decrypt(encryptedData: String): String {
        val encryptedData = Base64.decode(encryptedData,Base64.DEFAULT)

        val iv = encryptedData.sliceArray(0 until 16)
        val encryptedBytes = encryptedData.sliceArray(16 until encryptedData.size)

        val decryptedBytes = decryptionCipher(iv).doFinal(encryptedBytes)
        return String(decryptedBytes)
    }

    companion object {
        const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }
}