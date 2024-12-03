/**
 * Appel la fonction qui retourne toutes les critiques dans la BD
 */
export async function fetchAllCritiques(){
    const response = await fetch("http://localhost:8080/critiques")
    if (!response.ok){
        throw new Error ("Erreur du chargement de toutes les critiques. Pour: "+ await response.json())
    }
    return await response.json()
}

/**
 * Fetch toutes les critiques pour un oiseau
 * @param nomOiseau l'oiseau pour lequel on veux les critiques
 */
export async function fetchCritiqueParOiseau(nomOiseau){
    const response = await fetch("http://localhost:8080/critiques/" + nomOiseau)
    if (!response.ok){
        throw new Error ("Erreur du chargement de la critique de l'oiseau: " + nomOiseau + " pour: " + await response.json())
    }
    return await response.json()

}

/**
 * Fonction qui appel la méthode d'ajout de critique en BD
 * @param critique la critique passée en paramètre à ajouter
 * @returns {Promise<any>} la réponse json
 */
export async function ajouterCritique(critique){
    const response = await fetch("http://localhost:8080/ajouterCritique",
        {
            method: 'POST',
            body: JSON.stringify(critique),
            headers: {'Content-type': 'application/json'}
        })
        if (!response.ok) throw new Error("Erreur d'ajout de la nouvelle critique. Pour: " + await response.json())

    return await response.json()
}

/**
 * Méthode qui permet d'appeller la méthode de suppression de la critique en BD
 * @param idCritique l'id de la crtiique que l'on veut supprimer
 * @returns {Promise<any>} le message de la méthode de suppression json
 */
export async function supprimerCritique(idCritique){
    const response = await fetch("http://localhost:8080/supprimerCritique/" + idCritique,
        {
            method: 'DELETE',
        })
    if (!response.ok) throw new Error("La critique n'a pas pu être supprimée")
    return await response.json()
}

/**
 * Supprimer toutes les critiques associées à un oiseau selon son nom (race)
 * @param nomOiseau la race de l'oiseau pour lequel on doit supprimer toutes les critiques
 */
export async function supprimerToutesCritiqueParOiseau(nomOiseau){
    const response = await fetch("http://localhost:8080/supprimerToutesCritiquesParOiseau/" + nomOiseau,
        {
            method: 'DELETE',
        })

    if (!response.ok) throw new Error("Les critiques n'ont pas pu être supprimées")

    return await response.json()
}

export async function calculerNoteGlobale(idCritique){
    const response = await fetch("http://localhost:8080/getNoteGlobale/" +idCritique);

    if (!response.ok) throw new Error("Erreur de calcul de la note globale. Pour: " + await response.json())

    return await response.json()
}



