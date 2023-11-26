import { CButton, CCol, CFormInput, CInputGroup, CInputGroupText, CModal, CModalBody, CModalFooter, CModalHeader, CModalTitle, CNavLink, CRow, CTableFoot } from '@coreui/react';
import React, { forwardRef, useEffect, useImperativeHandle, useState } from 'react'
import { NavLink } from 'react-router-dom';
import { makeRequest } from 'src/Api';
import { getEtatDeStock } from 'src/views/commercial/stock/EtatDeStock/EtatDeStock';

export const repondreDemande = (idDemande, values) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `/proformas/demandes/reponse/${idDemande}`,
            values: values,
            requestType: 'POST',
            successCallback: (response) => {
                resolve(response)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    });
}

const ReponseDemandeProformaModal = forwardRef((props, ref) => {
    const [visibility, setVisibility] = useState(false)

    const [demandeRecu, setDemandeRecu] = useState();

    useImperativeHandle(ref, () => ({
        showModal: (demande) => {
            setDemandeRecu(demande)
            setVisibility(true)
        }
    }))

    // Stock
    const [ quantite, setQuantite ] = useState(0)
    const [ prixUnitaire, setPrixUnitaire ] = useState(0)

    const [ etatDeStock, setEtatDeStock ] = useState()
    useEffect(() => {
        if (demandeRecu) {
            getEtatDeStock(demandeRecu.article.idArticle, demandeRecu.unite.idUnite).then((data) => {
                let stock = data.data[0]
                stock.ligneStocks.map((ligne) => {
                    setQuantite(ligne.quantite)
                    setPrixUnitaire(ligne.prixUnitaireMoyen)
                })
            })
        }
    }, [demandeRecu])

    // On reponse
    const handleReponseSubmit = (e) => {
        let reponseValues = {
            quantite: quantite,
            prixUnitaire: prixUnitaire
        }

        repondreDemande(demandeRecu.idDemandeRecuProforma, reponseValues).then((data) => {
            setVisibility(false)
        })
    }

    return (
        <CModal
            visible={visibility}
            onClose={() => {
                setVisibility(false)
            }}
            size='lg'
        >
            <CModalHeader>
                <CModalTitle>
                    Reponse a demande
                </CModalTitle>
            </CModalHeader>

            <CModalBody>
                <CRow>
                    <CCol>
                        <p className='m-0' >From: <p className='fw-bolder m-0 d-inline'>{ demandeRecu && demandeRecu.fournisseur.nom }</p></p>
                        <p className='m-0 text-medium-emphasis' style={{ fontSize: '.9rem' }}>Email: { demandeRecu && demandeRecu.fournisseur.email }</p>
                    </CCol>
                </CRow>

                <CRow className='mt-5'>
                    <CCol>
                        <p className='m-0'>Article: <p className='m-0 d-inline badge text-bg-primary text-wrap'>{ demandeRecu && demandeRecu.article.nom }</p></p>
                        <p className='m-0'>Pour: <p className='d-inline fw-bolder'>{ demandeRecu && demandeRecu.quantite }</p> { demandeRecu && demandeRecu.unite.nom }</p>
                    </CCol>
                </CRow>

                <CRow className='mt-3'>
                    <CCol className='d-flex gap-2'>
                        <CInputGroup>
                            <CFormInput floatingLabel="Quantite" type='number' step={0.01} onChange={(e) => { setQuantite(e.target.value) }} value={quantite} />
                            <CInputGroupText>{ demandeRecu && demandeRecu.unite.nom }</CInputGroupText>
                        </CInputGroup>

                        <CInputGroup>
                            <CFormInput floatingLabel="Prix unitaire" type='number' step={0.01} onChange={(e) => { setPrixUnitaire(e.target.value) }} value={prixUnitaire} />
                            <CInputGroupText>Ar</CInputGroupText>
                        </CInputGroup>
                    </CCol>
                </CRow>
            </CModalBody>

            <CModalFooter  className='d-flex justify-content-between'>
                
                        <CNavLink component={NavLink} to='/etatstock'>
                            <CButton>Afficher etat de stock</CButton>
                        </CNavLink>
                        <CButton
                            onClick={handleReponseSubmit}
                        >Envoyer</CButton>
                    
            </CModalFooter>
        </CModal>
    )
});

ReponseDemandeProformaModal.displayName = "ReponseDemandeProformaModal"
export default ReponseDemandeProformaModal