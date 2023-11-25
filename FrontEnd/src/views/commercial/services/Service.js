import React, { useEffect, useRef, useState } from 'react'
import { getAllServices } from '../besoinAchat/BesoinAchat'
import { CButton, CCard, CCardBody, CCardHeader, CCardTitle, CCol, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilPen, cilTrash } from '@coreui/icons'
import ServiceModal from './components/ServiceModal'

const Service = () => {
    const [update, setUpdate] = useState(false)

    // Getting all services
    const [services, setServices] = useState([])

    useEffect(() => {
        getAllServices().then((data) => {
            setServices(data)
        })
    }, [update])

    // Update ajout
    const [ isUpdate, setIsUpdate ] = useState(false)
    const serviceModalRef = useRef()

    const handleOnModalSubmit = () => {
        setUpdate(!update)
    }

    return (
        <CCard>
            <CCardHeader>
                <CCardTitle>
                    Services
                </CCardTitle>
            </CCardHeader>

            <CCardBody className='mt-2'>
                <CRow>
                    <CCol>
                        <CButton
                            onClick={(e) => {
                                setIsUpdate(false)
                                serviceModalRef.current.showModal();
                            }}
                        >Ajouter service</CButton>
                    </CCol>
                </CRow>

                <CTable>
                    <CTableHead>
                        <CTableRow>
                            <CTableHeaderCell scope='col'>Nom service</CTableHeaderCell>
                            <CTableHeaderCell scope='col'></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>

                    <CTableBody>
                        {services.map((article, index) => {
                            return (
                                <CTableRow key={article.nom}>
                                    <CTableDataCell>
                                        <p className='badge text-bg-info'>{article.nom}</p>
                                    </CTableDataCell>
                                    <CTableDataCell>
                                        <div className='d-flex gap-2 justify-content-end'>
                                            <CButton onClick={(e) => {
                                                setIsUpdate(true)
                                                serviceModalRef.current.showModal(article.idService)
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
            </CCardBody>

            <ServiceModal isUpdate={isUpdate} onSubmit={handleOnModalSubmit} ref={serviceModalRef} />
        </CCard>
    )
}

export default Service