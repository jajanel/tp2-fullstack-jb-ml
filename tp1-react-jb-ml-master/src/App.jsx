import { useState, useEffect } from "react";
import './App.css';
import Navbar from "./components/Navbar.jsx";
import CatalogueOiseaux from "./components/Critiques-Oiseaux/CatalogueOiseaux.jsx";
import { dataOiseau, dataOiseau as donnesOiseauxDefaut } from "./assets/oiseaux.js";
import { filtrerEtMettreAJourOiseaux, supprimerOiseau } from "./classes/gestionCatalogueOiseaux.js";
import { DataoiseauContext } from "./contexts/DataOiseauContext.jsx";
import Footer from "./components/Footer.jsx";

const getDonneesLocalStorage = (key, donneesParDefaut) => {
    const donnees = localStorage.getItem(key);
    return donnees ? JSON.parse(donnees) : donneesParDefaut;
};

function App() {
    const [categorieSelectionne, setCategorieSelectionne] = useState("tous");
    const [boolOiseauTrie, setBoolOiseauTrie] = useState(false);
    const [theme, setTheme] = useState("lumen");

    //TODO MAEK
    const [dataOiseau,  setDataOiseau] = useState(() => getDonneesLocalStorage("dataOiseau", donnesOiseauxDefaut));


    const ouvertStatistiquesState = useState(false);




    // Resauvegarder les données dans le local storage à chauque changement
    useEffect(() => {
        localStorage.setItem("dataOiseau", JSON.stringify(dataOiseau));
    }, [dataOiseau]);


    // Changer la valeur de la catégorie montrée dans le catalogue en utilisant le setter setCategorieSelectionne
    const handleChangementCategorie = (categorieOiseau) => {
        setCategorieSelectionne(categorieOiseau);
    };

    // Filtrer les oiseaux selon la catégorie sélectionnée par l'utilisateur (valeur par défaut est tous les oiseaux)
    const oiseauxFiltre = categorieSelectionne === "tous" ? dataOiseau : dataOiseau.filter(oiseau => oiseau.categorie === categorieSelectionne);


    // Fonction pour supprimer un oiseau du catalogue et mettre à jour les données
    const handleTuerOiseau = (idOiseau) => {
        supprimerOiseau(idOiseau);
        filtrerEtMettreAJourOiseaux(idOiseau, setDataOiseau);

    };

    // Fermer le modal statistiques (utilisé pour fermer la section statiostique lorsqu'on change la catégorie utilisée) pour revenir au mode normal.
    const fermerStatistiquesToggle = () => {
        ouvertStatistiquesState[1](false);
        setBoolOiseauTrie(false);
    }

    return (
        <>
            {/*Oui c'est un peu sketch, c'était la maniere la plus simple avec bootswatch qui faisait déjà mon thème
            Au click du toggle dans navbar, l'import de bootswatch qui détermine le thème est changé pour le nouveau thème*/}
            <link rel="stylesheet" href={"https://bootswatch.com/5/"+theme+"/bootstrap.min.css"} />
            <DataoiseauContext.Provider value={[dataOiseau, setDataOiseau]}>
                <Navbar
                    surChangementCategorie={handleChangementCategorie}
                    dataOiseau={dataOiseau}
                    setDataOiseau={setDataOiseau}
                    oiseauxFiltre={oiseauxFiltre}
                    oiseauxTri={[boolOiseauTrie, setBoolOiseauTrie]}
                    ouvertStatistiquesState={ouvertStatistiquesState}
                    fermerStatistiquesToggle={fermerStatistiquesToggle}
                    setTheme={setTheme}
                    theme={theme}
                />
                <CatalogueOiseaux
                    oiseauxFiltre={oiseauxFiltre}
                    oiseauxTriBool={[boolOiseauTrie, setBoolOiseauTrie]}
                    dataOiseau={dataOiseau}
                    setDataOiseau={setDataOiseau}
                    tuerOiseau={handleTuerOiseau}
                    ouvertStatistiquesState={ouvertStatistiquesState}
                    fermerStatistiquesToggle={fermerStatistiquesToggle}
                    categorieSelectionne={categorieSelectionne}
                />
            </DataoiseauContext.Provider>
            <Footer />
        </>
    );
}

export default App;