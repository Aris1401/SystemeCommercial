import React, { forwardRef, useEffect, useRef, useState } from 'react'
import { CButton, CCard, CCardBody, CCardHeader, CCardTitle, CCol, CFormInput, CModal, CModalContent, CModalFooter, CModalHeader, CModalTitle, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react';
import { getFournisseurs } from '../demandeProforma/DemandeProforma';
import CIcon from '@coreui/icons-react';
import { cilPen, cilTrash } from '@coreui/icons';
import FournisseurModal from './components/FournisseurModal';

const Fournisseurs = forwardRef((props, ref) => {
    const [update, setUpdate] = useState(false)

    // Getting all services
    const [fournisseurs, setFournisseurs] = useState([])

    useEffect(() => {
        getFournisseurs().then((data) => {
            setFournisseurs(data)
        })
    }, [update])

    // Update ajout
    const [isUpdate, setIsUpdate] = useState(false)
    const fournisseurModalRef = useRef()

    const handleOnModalSubmit = () => {
        setUpdate(!update)
    }

    return (
        <CCard>
            <FournisseurModal isUpdate={isUpdate} ref={fournisseurModalRef} onSubmit={handleOnModalSubmit} />

            <CCardHeader>
                <CCardTitle>
                    Fournisseurs
                </CCardTitle>
            </CCardHeader>

            <CCardBody className='mt-2'>
                <CRow>
                    <CCol>
                        <CButton
                            onClick={(e) => {
                                setIsUpdate(false)
                                fournisseurModalRef.current.showModal();
                            }}
                        >Ajouter fournisseurs</CButton>
                    </CCol>
                </CRow>

                <div style={{ overflowX: 'scroll' }}>
                    <CTable className='mt-2'>
                        <CTableHead>
                            <CTableRow>
                                <CTableHeaderCell scope='col'>Nom fournisseur</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Type de produit</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Nom repsonsable</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Contact fournisseur</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Email fournisseur</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Contact responsable</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Addresse</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Localisation</CTableHeaderCell>
                                <CTableHeaderCell scope='col'></CTableHeaderCell>
                            </CTableRow>
                        </CTableHead>

                        <CTableBody>
                            {fournisseurs.map((fournisseur, index) => {
                                return (
                                    <CTableRow key={fournisseur.nom}>
                                        <CTableDataCell className='text-medium-emphasis'>
                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.nom}</p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p className='badge text-bg-info text-wrap'>{fournisseur.typeProduit.nom}</p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.nomResponsable}</p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p style={{ fontSize: '.9rem' }}><b>(+261) </b>{fournisseur.contact}</p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.email}</p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.contactResponsable}</p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.addresse}</p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.localisation}</p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <div className='d-flex gap-2 justify-content-end'>
                                                <CButton onClick={(e) => {
                                                    setIsUpdate(true)
                                                    fournisseurModalRef.current.showModal(fournisseur.idFournisseur)
                                                }}>
                                                    <CIcon icon={cilPen}></CIcon>
                                                </CButton>

                                                <CButton color='danger'>
                                                    <CIcon icon={cilTrash}></CIcon>
                                                </CButton>
                                            </div>
                                        </CTableDataCell>
                                    </CTableRow>
                                )
                            })}
                        </CTableBody>
                    </CTable>
                </div>
            </CCardBody>
        </CCard>
    )
});

Fournisseurs.displayName = "Fournisseurs"
export default Fournisseurs