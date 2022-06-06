package com.example.HikingHelperPI.model

import java.io.Serializable

/**
 * Created by Azhar Rivaldi on 02-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class ModelGunung : Serializable {
    var strNamaGunung: String? = null
    var strImageGunung: String? = null
    var strLokasiGunung: String? = null
    var strDeskripsi: String? = null
    var strJalurPendakian: String? = null
    var strInfoGunung: String? = null
    var strLat = 0.0
    var strLong = 0.0
}