import { CButton, CCol, CModal, CModalBody, CModalHeader, CModalTitle, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow, CToast } from '@coreui/react';
import React, { forwardRef, useEffect, useImperativeHandle, useState } from 'react'
import { makeRequest } from 'src/Api';

export const getFournisseurs = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url:'fournisseurs',
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

export const faireDemandeProfrma = (demande) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'proformas/demande',
            requestType: 'POST',
            values: demande,
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

const DemandeProformaBesoin = forwardRef((props, ref) => {
    useImperativeHandle(ref, () => ({
        showModal: (idArticleBesoinAchat) => {
            setVisibilityDemandeModal(true)
            setSelectedArticleBesoinAchat(idArticleBesoinAchat)
        },
        hideModal: () => {
            setVisibilityDemandeModal(false)
        },
    }))

    // Modal visibility
    const [ visibilityDemandeModal, setVisibilityDemandeModal ] = useState(false)

    // Obtenir les fournisseurs
    const [ fournisseurs, setFournisseurs ] = useState([])
    useEffect(() => {
        getFournisseurs().then((data) => [
            setFournisseurs(data)
        ])
    }, [])

    // Selected article besoin achat
    const [ selectedArticleBesoinAchat, setSelectedArticleBesoinAchat ] = useState(-1)

    const handleFaireDemande = (e, idFournisseur) => {
        if (selectedArticleBesoinAchat === -1) return;

        let demande = {
            idFournisseur: idFournisseur,
            idArticleBesoinAchat: selectedArticleBesoinAchat,
        }

        faireDemandeProfrma(demande).then((data) => {
            setVisibilityDemandeModal(false)
        })
    }
    
    return (
        <CModal
            visible={visibilityDemandeModal}
            onClose={() => {
                setVisibilityDemandeModal(false)
            }}
            size='xl'
        >
            <CModalHeader>
                <CModalTitle>
                    Demande de proforma
                </CModalTitle>
            </CModalHeader>

            <CModalBody>
                <CRow>
                    <CCol>
                        <h6>Fournisseurs</h6>

                        <div className='table-responsive p-2' style={{ height: '300px' }}>
                            <CTable>
                                <CTableHead>
                                    <CTableRow>
                                        <CTableHeaderCell scope='col'>Nom fournisseur</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'>Type de produit</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'>Nom repsonsable</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'>Contact fournisseur</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'>Email fournisseur</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'></CTableHeaderCell>
                                    </CTableRow>
                                </CTableHead>

                                <CTableBody>
                                    { fournisseurs.map((fournisseur, index) => {
                                        return (
                                            <CTableRow key={index}>
                                                <CTableDataCell className='text-medium-emphasis'>
                                                    <p style={{ fontSize: '.9rem' }}>{ fournisseur.nom }</p>
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <p className='badge text-bg-info text-wrap'>{ fournisseur.typeProduit.nom }</p>
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <p style={{ fontSize: '.9rem' }}>{ fournisseur.nomResponsable }</p>
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <p style={{ fontSize: '.9rem' }}><b>(+261) </b>{ fournisseur.contact }</p>
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <p style={{ fontSize: '.9rem' }}>{ fournisseur.email }</p>
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <CButton 
                                                        style={{ fontSize: '.8rem' }}
                                                        onClick={
                                                            (e) => {
                                                                handleFaireDemande(e, fournisseur.idFournisseur)
                                                            }
                                                        }
                                                    >Faire une demande</CButton>
                                                </CTableDataCell>
                                            </CTableRow>
                                        )
                                    }) }
                                </CTableBody>
                            </CTable>
                        </div>
                    </CCol>
                </CRow>
            </CModalBody>
        </CModal>
    );
});

DemandeProformaBesoin.displayName = "DemandeProformaBesoin"
export default DemandeProformaBesoin