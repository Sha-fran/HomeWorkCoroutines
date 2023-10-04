package com.example.homeworkcoroutines

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataClasses {
    data class Superheroes (

        @SerializedName("id"          ) var id          : Int?         = null,
        @SerializedName("name"        ) var name        : String?      = null,
        @SerializedName("slug"        ) var slug        : String?      = null,
        @SerializedName("powerstats"  ) var powerstats  : Powerstats?  = Powerstats(),
        @SerializedName("appearance"  ) var appearance  : Appearance?  = Appearance(),
        @SerializedName("biography"   ) var biography   : Biography?   = Biography(),
        @SerializedName("work"        ) var work        : Work?        = Work(),
        @SerializedName("connections" ) var connections : Connections? = Connections(),
        @SerializedName("images"      ) var images      : Images?      = Images()

    ):Serializable

    data class Powerstats (

        @SerializedName("intelligence" ) var intelligence : Int? = null,
        @SerializedName("strength"     ) var strength     : Int? = null,
        @SerializedName("speed"        ) var speed        : Int? = null,
        @SerializedName("durability"   ) var durability   : Int? = null,
        @SerializedName("power"        ) var power        : Int? = null,
        @SerializedName("combat"       ) var combat       : Int? = null

    )

    data class Appearance (

        @SerializedName("gender"    ) var gender    : String?           = null,
        @SerializedName("race"      ) var race      : String?           = null,
        @SerializedName("height"    ) var height    : ArrayList<String> = arrayListOf(),
        @SerializedName("weight"    ) var weight    : ArrayList<String> = arrayListOf(),
        @SerializedName("eyeColor"  ) var eyeColor  : String?           = null,
        @SerializedName("hairColor" ) var hairColor : String?           = null

    )

    data class Biography (

        @SerializedName("fullName"        ) var fullName        : String?           = null,
        @SerializedName("alterEgos"       ) var alterEgos       : String?           = null,
        @SerializedName("aliases"         ) var aliases         : ArrayList<String> = arrayListOf(),
        @SerializedName("placeOfBirth"    ) var placeOfBirth    : String?           = null,
        @SerializedName("firstAppearance" ) var firstAppearance : String?           = null,
        @SerializedName("publisher"       ) var publisher       : String?           = null,
        @SerializedName("alignment"       ) var alignment       : String?           = null

    )

    data class Work (

        @SerializedName("occupation" ) var occupation : String? = null,
        @SerializedName("base"       ) var base       : String? = null

    )

    data class Connections (

        @SerializedName("groupAffiliation" ) var groupAffiliation : String? = null,
        @SerializedName("relatives"        ) var relatives        : String? = null

    )

    data class Images (

        @SerializedName("xs" ) var xs : String? = null,
        @SerializedName("sm" ) var sm : String? = null,
        @SerializedName("md" ) var md : String? = null,
        @SerializedName("lg" ) var lg : String? = null

    )
}