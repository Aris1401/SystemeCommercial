import { CButton, CCard, CCardBody, CCardHeader, CCardTitle, CCol, CPlaceholder, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableRow } from '@coreui/react'
import React, { useEffect, useRef, useState } from 'react'
import { makeRequest } from 'src/Api';
import ReponseDemandeProformaModal from './components/ReponseDemandeProformaModal';

export var getDemandesProformaRecu = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'demandesrecuproformas',
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    });
}

const DemandesProformaRecu = () => {
    useEffect(() => {
        makeRequest({
            url: 'updateinbox',
            requestType: 'GET'
        })
    }, [])

    // Getting the demandes
    const [demandesRecu, setDemandesRecu] = useState([])
    useEffect(() => {
        getDemandesProformaRecu().then((data) => {
            setDemandesRecu(data)
        })
    }, [])

    // Modal ref
    const reponseRef = useRef()

    return (
        <CCard>
            <ReponseDemandeProformaModal ref={reponseRef} />

            <CCardHeader>
                <CCardTitle>
                    Liste des demandes recu
                </CCardTitle>
            </CCardHeader>

            <CCardBody>
                <CRow>
                    <CCol>
                        <CTable>
                            <CTableHead>
                                <CTableRow>
                                    <CTableDataCell scope='col'>Date reception</CTableDataCell>
                                    <CTableDataCell scope='col'>Fournisseur</CTableDataCell>
                                    <CTableDataCell scope='col'>Email fournisseur</CTableDataCell>
                                    <CTableDataCell scope='col'>Article</CTableDataCell>
                                    <CTableDataCell scope='col'>Quantite</CTableDataCell>
                                    <CTableDataCell scope='col'>Unite</CTableDataCell>
                                    <CTableDataCell scope='col'></CTableDataCell>
                                </CTableRow>
                            </CTableHead>

                            <CTableBody>
                                {demandesRecu.map((demandeRecu, index) => {
                                    return (
                                        <CTableRow key={index}>
                                            <CTableDataCell>{new Date(demandeRecu.dateDemande).toLocaleString()}</CTableDataCell>
                                            <CTableDataCell>
                                                <p className='text-medium-emphasis'>
                                                    {demandeRecu.fournisseur.nom}
                                                </p>
                                            </CTableDataCell>
                                            <CTableDataCell>
                                                <p>
                                                    {demandeRecu.fournisseur.email}
                                                </p>
                                            </CTableDataCell>
                                            <CTableDataCell>
                                                <p className='badge text-bg-primary text-wrap'>
                                                    {demandeRecu.article.nom}
                                                </p>
                                            </CTableDataCell>
                                            <CTableDataCell>
                                                <p className='fw-bold'>
                                                    {demandeRecu.quantite}
                                                </p>
                                            </CTableDataCell>
                                            <CTableDataCell>
                                                <p>
                                                    {demandeRecu.unite.nom}
                                                </p>
                                            </CTableDataCell>
                                            <CTableDataCell>
                                                <CButton
                                                    onClick={(e) => {
                                                        reponseRef.current.showModal(demandeRecu);
                                                    }}
                                                >Repondre</CButton>
                                            </CTableDataCell>
                                        </CTableRow>
                                    )
                                })}
                            </CTableBody>
                        </CTable>
                    </CCol>
                </CRow>
            </CCardBody>
        </CCard>
    )
}

export default DemandesProformaRecu