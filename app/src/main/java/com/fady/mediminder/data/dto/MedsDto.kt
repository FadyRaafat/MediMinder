package com.fady.mediminder.data.dto


import com.fady.mediminder.domain.models.MedsItem
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MedsDto(
    @SerializedName("problems") val problems: List<Problem>?
) {
    fun mapToDomainModel(): List<MedsItem> {
        val medsItems = mutableListOf<MedsItem>()
        problems?.forEach { problem ->
            problem.diabetes?.forEach { diabetes ->
                diabetes.extractMedsItems(medsItems)
            }
        }
        return medsItems
    }

    private fun Diabete.extractMedsItems(medsItems: MutableList<MedsItem>) {
        medications?.forEach { medication ->
            medication.medicationsClasses?.forEach { medicationsClass ->
                medicationsClass.className?.processAssociatedDrugs(medsItems)
                medicationsClass.className2?.processAssociatedDrugs(medsItems)
            }
        }
    }

    private fun List<ClassName>?.processAssociatedDrugs(medsItems: MutableList<MedsItem>) {
        this?.forEach { className ->
            className.associatedDrug?.addMedsItems(medsItems)
            className.associatedDrug2?.addMedsItems(medsItems)
        }
    }

    private fun List<AssociatedDrug>?.addMedsItems(medsItems: MutableList<MedsItem>) {
        this?.forEach { drug ->
            medsItems.add(
                MedsItem(
                    name = drug.name ?: "", dose = drug.dose ?: "", strength = drug.strength ?: ""
                )
            )
        }
    }
}

@Serializable
data class Problem(
    @SerializedName("Diabetes") val diabetes: List<Diabete>?
)

@Serializable
data class Diabete(
    @SerializedName("medications") val medications: List<Medication>?
)

@Serializable
data class Lab(
    @SerializedName("missing_field") val missingField: String?
)

@Serializable
data class Medication(
    @SerializedName("medicationsClasses") val medicationsClasses: List<MedicationsClasse>?
)

@Serializable
data class MedicationsClasse(
    @SerializedName("className") val className: List<ClassName>?,
    @SerializedName("className2") val className2: List<ClassName>?
)

@Serializable
data class ClassName(
    @SerializedName("associatedDrug") val associatedDrug: List<AssociatedDrug>?,
    @SerializedName("associatedDrug#2") val associatedDrug2: List<AssociatedDrug>?
)

@Serializable
data class AssociatedDrug(
    @SerializedName("dose") val dose: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("strength") val strength: String?
)




