import AjouterOiseau from "./AjouterOiseau.jsx";
import CarteProduit from "./CarteProduit.jsx";
import {useEffect, useState} from "react";
import CatalogueStatistiques from "./CatalogueStatistiques.jsx";
import {calculerMoyenneParOiseau} from "../scripts/http-critiques.js";

export default function CatalogueOiseaux(props) {
    const [estOuvertAjouterOiseau, setEstOuvertAjouterOiseau] = useState(false);
    const [estOuvertStatistiques, setEstOuvertStatistiques] = props.ouvertStatistiquesState;
    const [oiseauxTries, setOiseauxTries] = useState([]);

    const toggleModalAjouterOiseau = () => {
        setEstOuvertAjouterOiseau(!estOuvertAjouterOiseau);
    };

    useEffect(() => {
        async function trierOiseauxParMoyenne() {
            if (estOuvertStatistiques) {
                const oiseauxAvecMoyenne = [];
                for (const oiseau of props.dataOiseau) {
                    const moyenne = await calculerMoyenneParOiseau(oiseau.race);
                    oiseauxAvecMoyenne.push({...oiseau, moyenne});
                }
                const oiseauxTries = oiseauxAvecMoyenne.sort((a, b) => b.moyenne - a.moyenne);
                setOiseauxTries(oiseauxTries);
            } else {
                //Si la section des stats n'est pas ouverte on garde le Filtre normal défini dans app
                setOiseauxTries(props.oiseauxFiltre);
            }
        }
        trierOiseauxParMoyenne();
    }, [estOuvertStatistiques, props.dataOiseau]);


    return (<>
        {estOuvertStatistiques && <CatalogueStatistiques
            dataCritiqueState={props.dataCritiqueState}
            estOuvertStatistiques={estOuvertStatistiques}
            fermerStatistiquesToggle={() => props.fermerStatistiquesToggle()}
        />}
        <div className="container-fluid ">
            <div className="row mb-4">
                <button className="btn btn-secondary btn-lg" onClick={toggleModalAjouterOiseau}>Ajouter un oiseau
                </button>
                <AjouterOiseau
                    estOuvert={estOuvertAjouterOiseau}
                    toggleModal={toggleModalAjouterOiseau}
                />
            </div>
            <div className="row px-5">
                {(oiseauxTries).map((oiseau, index) => (
                    <div className="col-xl-4 col-xxl-3 col-md-6 col-lg-6 align-content-center"
                         key={`${oiseau.idOiseau}-${index}`}>
                        <CarteProduit
                            tuerOiseau={props.tuerOiseau}
                            id={oiseau.idOiseau} categorie={oiseau.categorie}
                            race={oiseau.race} origine={oiseau.origine}
                            prix={oiseau.prix} srcImage={oiseau.srcImage}
                            datePublication={oiseau.datePublication}
                        />
                    </div>))}
            </div>
        </div>
    </>);
}