package com.example.demo.job.FileDataReadWrite.dto

data class PlayerYear(
    var ID: String,
    var lastName: String,
    var firstName: String,
    var position: String,
    var birthYear:Int,
    var debutYear:Int ,
    var yearsExperience:Int,
    ){
    companion object{
        fun playerToPlayerYear(player: Player):PlayerYear{
            return PlayerYear(
                player.ID,
                player.lastName,
                player.firstName,
                player.position,
                player.birthYear,
                player.debutYear,
                player.birthYear-player.debutYear)
        }
    }
}
