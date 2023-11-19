import { cilArrowRight } from '@coreui/icons';
import CIcon from '@coreui/icons-react';
import { CButton, CCard, CCardBody, CCardHeader, CCardSubtitle, CCardTitle, CCol, CNav, CNavLink, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react';
import React, { useEffect, useRef, useState } from 'react'
import { NavLink, useParams } from 'react-router-dom'
import { makeRequest } from 'src/Api';
import DemandeProformaBesoin from '../demamdeProforma/DemandeProforma';

export const getDetailsBesoinAchat = (idBesoinAchat) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `besoinsachat/${idBesoinAchat}`,
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

const DetailsBesoinAchat = () => {
    // Getting the id param
    const { idBesoinAchat } = useParams();

    // Details besoin achat
    const [besoinAchat, setBesoinAchat] = useState()
    useEffect(() => {
        getDetailsBesoinAchat(idBesoinAchat).then((data) => {
            setBesoinAchat(data)
        })
    }, [])

    // Selected article
    const [selectedArticleBesoin, setSelectedArticleBesoin] = useState(-1)

    // Demande achat modal
    const demandeAchatModalRef = useRef()

    return (
        <CCard className='p-2'>
            <CCardBody>
                <CCardTitle>
                    <h3>
                        Details demande
                    </h3>
                </CCardTitle>

                <CCardSubtitle className='mb-2 text-medium-emphasis d-flex w-100 justify-content-between'>
                    <div>
                        <p className='badge bg-primary text-wrap m-0 mt-2'>Service: {besoinAchat && besoinAchat.service.nom}</p>
                        <p className='text-secondary m-0' style={{ fontSize: '.75rem' }}>
                            {besoinAchat && new Date(besoinAchat.dateBesoin).toLocaleDateString()}
                            <CIcon icon={cilArrowRight}></CIcon>
                            {besoinAchat && new Date(besoinAchat.dateCloture).toLocaleDateString()}
                        </p>
                        <p className='text-medium-emphasis mt-2' style={{ fontSize: '.8rem' }}>
                            {besoinAchat && besoinAchat.description}
                        </p>
                    </div>

                    <div>
                        <p className='badge bg-secondary text-wrap'>{besoinAchat && besoinAchat.statusString}</p>
                    </div>
                </CCardSubtitle>

                <CRow>
                    <CCol>
                        <h5 className='text-medium-emphasis'>Details articles</h5>

                        <CTable>
                            <CTableHead>
                                <CTableRow>
                                    <CTableHeaderCell scope='col'>Designation</CTableHeaderCell>
                                    <CTableHeaderCell scope='col'>Quantite</CTableHeaderCell>
                                    <CTableHeaderCell scope='col'>Estimation prix</CTableHeaderCell>
                                    <CTableHeaderCell scope='col'>Description</CTableHeaderCell>
                                    <CTableHeaderCell scope='col'></CTableHeaderCell>
                                </CTableRow>
                            </CTableHead>

                            <CTableBody>
                                {besoinAchat && besoinAchat.articlesBesoinAchat.map((articleBesoin, index) => {
                                    return <CTableRow key={index}>
                                        <CTableDataCell><p className='badge text-bg-info text-wrap'>{articleBesoin.article.nom}</p></CTableDataCell>
                                        <CTableDataCell>{`${articleBesoin.quantite} ${articleBesoin.unite.nom}`}</CTableDataCell>
                                        <CTableDataCell className='fw-semibold'>0 Ar</CTableDataCell>
                                        <CTableDataCell className='text-medium-emphasis' style={{ fontSize: '.9rem' }}>{articleBesoin.descriptionArticleBesoin}</CTableDataCell>
                                        <CTableDataCell style={{ verticalAlign: 'middle' }}>
                                            <div className='d-flex justify-content-end'>
                                                <CButton style={{ fontSize: '.8rem' }} onClick={(e) => {
                                                    setSelectedArticleBesoin(articleBesoin.idArticleBesoinAchat)
                                                    demandeAchatModalRef.current.showModal(articleBesoin.idArticleBesoinAchat)
                                                }}>Faire une demande de proforma</CButton>
                                            </div>
                                        </CTableDataCell>
                                    </CTableRow>;
                                })}
                            </CTableBody>
                        </CTable>
                    </CCol>
                </CRow>

                <CRow className='text-end mt-3'>
                    <CCol className='d-flex flex-column justify-content-end align-items-end gap-2'>
                        <p className='text-medium-emphasis fw-bold mb-0' style={{ fontSize: '.9rem' }}>Estimation montant total: 0 Ar</p>
                        <CButton style={{ fontSize: '.9rem' }} disabled>Valider besoin</CButton>
                        <CButton style={{ fontSize: '.9rem' }} className='btn-success text-white' disabled>Generer bons de commandes</CButton>
                    </CCol>
                </CRow>
            </CCardBody>

            <DemandeProformaBesoin ref={demandeAchatModalRef} />
        </CCard>
    )
}

export default DetailsBesoinAchat