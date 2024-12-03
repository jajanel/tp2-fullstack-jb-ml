
import {calculerNoteGlobale, supprimerCritique} from "../scripts/http-critiques.js";
import {useEffect, useState} from "react";
export default function CritiquePrecedente(props) {

    /**
     * Fonction pour supprimer une critique de la liste des critiques, affiche message d'alerte
    const handleSupprimerCritique = (idCritique) => {
        supprimerCritique(idCritique);
        props.setDataCritiques(prevCritiques => prevCritiques.filter(critique => critique.idCritique !== idCritique));
        alert("Critique #" + idCritique + " supprimée");
    const [noteGlobale, setNoteGlobale] = useState(0);


    }

    //todo arrondir la note globale
    useEffect(() => {
        async function fetchNoteGlobale() {
            try {
                const note = await calculerNoteGlobale(props.idCritique);
                setNoteGlobale(note);
            } catch (e) {
                console.log("La note globale de la critique avec l'id " + props.idCritique + " n'a pas pu être calculée");
            }
        }
        fetchNoteGlobale();
    }, [props.idCritique]);


    return (
        <div className="rounded-3 p-2 my-5">
            <div className="pb-4">
                <h4 className="form-label">ID de la critique</h4>
                <input type="text" className="form-control form-select-lg" id="note" disabled value={props.idCritique} />
            </div>
            <div className="pb-4">
                <h4 className="form-label">La note globale calculée:</h4>
                <input type="number" className="form-control form-select-lg" id="note" disabled value={noteGlobale} />
            </div>
            <div className="pb-4">
                <h4 className="form-label">Tempérament:</h4>
                <input type="number" className="form-control form-select-lg" id="temperament" value={props.temperament} disabled />
            </div>
            <div className="pb-4">
                <h4 className="form-label">Beauté:</h4>
                <input type="number" className="form-control form-select-lg" id="beaute" disabled value={props.beaute} />
            </div>
            <div className="pb-4">
                <h4 className="form-label">Utilisation:</h4>
                <input type="number" className="form-control form-select-lg" id="utilisation" disabled value={props.utilisation} />
            </div>
            <hr />
            <div className="btn-wrapper text-center d-flex justify-content-between">
                <p className="modal-title text-muted">Supprimer la critique</p>
                <button type="button" className="btn btn-dark text-white btn-sm" onClick={() => handleSupprimerCritique(props.idCritique)}>Supprimer</button>
            </div>
        </div>
    );
}