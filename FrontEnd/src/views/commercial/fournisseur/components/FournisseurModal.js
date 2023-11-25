import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react'
import { getDetailsService } from '../../besoinAchat/components/ApercuBesoinAchat';
import { CButton, CCol, CFormInput, CModal, CModalContent, CModalFooter, CModalHeader, CModalTitle, CRow } from '@coreui/react';
import { makeRequest } from 'src/Api';

export const ajouterFournisseur = (fournisseur) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `fournisseurs`,
            values: fournisseur,
            requestType: 'POST',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

export const updateFournisseur = (fournisseur) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `fournisseurs/update`,
            values: fournisseur,
            requestType: 'POST',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

export const getDetailsFournisseur = (idFournisseur) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `fournisseurs/${idFournisseur}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

const FournisseurModal = forwardRef((props, ref) => {
    // Visibility
    const [visibility, setVisibility] = useState(false)
    const [idFournisseur, setIdFournisseur] = useState(-1)

    useImperativeHandle((ref), () => ({
        showModal: (idFournisseur) => {
            setVisibility(true)
            if (idFournisseur)
                setIdFournisseur(idFournisseur)
            else setIdFournisseur(-1)
        },
        hideModal: () => {
            setVisibility(false)
        }
    }))

    const { isUpdate, onSubmit } = props;

    // Nom ref
    const [nom, setNom] = useState("");
    const [contact, setContact] = useState("");
    const [nomResponsable, setNomResponsable] = useState("");
    const [contactResponsable, setContactResponsable] = useState("");
    const [email, setEmail] = useState("");
    const [adresse, setAdresse] = useState("");
    const [localisation, setLocalisation] = useState("");
    const [descriptionFournisseur, setDescriptionFournisseur] = useState("");
    const [idTypeProduit, setIdTypeProduit] = useState(0);

    // Current service
    const [fournisseur, setFournisseur] = useState()
    useEffect(() => {
        if (isUpdate) {
            getDetailsFournisseur(idFournisseur).then((data) => {
                setFournisseur(data)
                setNom(data.nom);
                setContact(data.contact);
                setNomResponsable(data.nomResponsable);
                setContactResponsable(data.contactResponsable);
                setEmail(data.email);
                setAdresse(data.addresse);
                setLocalisation(data.localisation);
                setDescriptionFournisseur(data.descriptionFournisseur);
                setIdTypeProduit(data.idTypeProduit);
            })
        } else {
            setNom("");
            setContact("");
            setNomResponsable("");
            setContactResponsable("");
            setEmail("");
            setAdresse("");
            setLocalisation("");
            setDescriptionFournisseur("");
            setIdTypeProduit(0);
        }
    }, [idFournisseur, isUpdate])


    const handleModalSubmit = () => {
        if (isUpdate) {
            let newFournisseur = {
                idFournisseur: fournisseur.idFournisseur,
                nom: nom,
                contact: contact,
                nomResponsable: nomResponsable,
                contactResponsable: contactResponsable,
                email: email,
                addresse: adresse,
                localisation: localisation,
                descriptionFournisseur: descriptionFournisseur,
                idTypeProduit: idTypeProduit,
            };

            updateFournisseur(newFournisseur).then(() => {
                setVisibility(false)
                onSubmit()
            })
        } else {
            let newFournisseur = {
                nom: nom,
                contact: contact,
                nomResponsable: nomResponsable,
                contactResponsable: contactResponsable,
                email: email,
                addresse: adresse,
                localisation: localisation,
                descriptionFournisseur: descriptionFournisseur,
                idTypeProduit: idTypeProduit,
            };

            ajouterFournisseur(newFournisseur).then(() => {
                setVisibility(false)
                onSubmit();
            })
        }
    }

    return (
        <CModal
            visible={visibility}
            onClose={() => setVisibility(false)}
        >
            <CModalHeader>
                <CModalTitle>
                    {isUpdate ? "Modifier fournisseur" : "Ajouter fournisseur"}
                </CModalTitle>
            </CModalHeader>

            <CModalContent>
                <CRow>
                    <CCol className='d-flex flex-column gap-2' >
                        <CFormInput
                            type='text'
                            floatingLabel="Nom fournisseur"
                            value={nom}
                            onChange={(e) => { setNom(e.target.value) }}
                        />

                        <CFormInput
                            type='text'
                            floatingLabel="Contact"
                            value={contact}
                            onChange={(e) => { setContact(e.target.value) }}
                        />

                        <CFormInput
                            type='text'
                            floatingLabel="Nom Responsable"
                            value={nomResponsable}
                            onChange={(e) => { setNomResponsable(e.target.value) }}
                        />

                        <CFormInput
                            type='text'
                            floatingLabel="Contact Responsable"
                            value={contactResponsable}
                            onChange={(e) => { setContactResponsable(e.target.value) }}
                        />

                        <CFormInput
                            type='email'
                            floatingLabel="Email"
                            value={email}
                            onChange={(e) => { setEmail(e.target.value) }}
                        />

                        <CFormInput
                            type='text'
                            floatingLabel="Adresse"
                            value={adresse}
                            onChange={(e) => { setAdresse(e.target.value) }}
                        />

                        <CFormInput
                            type='text'
                            floatingLabel="Localisation"
                            value={localisation}
                            onChange={(e) => { setLocalisation(e.target.value) }}
                        />

                        <CFormInput
                            type='text'
                            floatingLabel="Description Fournisseur"
                            value={descriptionFournisseur}
                            onChange={(e) => { setDescriptionFournisseur(e.target.value) }}
                        />

                        <CFormInput
                        
                            type='number'
                            floatingLabel="ID Type Produit"
                            value={idTypeProduit}
                            onChange={(e) => { setIdTypeProduit(Number(e.target.value)) }}
                        />
                    </CCol>
                </CRow>
            </CModalContent>

            <CModalFooter>
                <CButton onClick={(e) => {
                    
                    handleModalSubmit()
                }} type='submit'>{isUpdate ? "Modifer" : "Ajouter"}</CButton>
            </CModalFooter>
        </CModal>
    )
});

FournisseurModal.displayName = "FournisseurModal"
export default FournisseurModal