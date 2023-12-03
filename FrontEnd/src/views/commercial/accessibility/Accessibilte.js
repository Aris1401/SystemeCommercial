import { cilPlus } from '@coreui/icons';
import CIcon from '@coreui/icons-react';
import { CButton, CCard, CCardBody, CCardHeader, CCardTitle, CCol, CFormSelect, CModal, CModalBody, CModalFooter, CModalHeader, CModalTitle, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react'
import React, { useEffect, useRef, useState } from 'react'
import { makeRequest } from 'src/Api';
import routes, { _routes } from 'src/routes';

const getProfilsRestrictedForPage = (page) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `/accessibilite/page?page=${page}`,
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

export const getAllProfils = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `profils`,
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

export const addProfilRestrictionToPage = (profil, page) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `accessibilite/restrict/profil/${ profil }?page=${ page }`,
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

const Accessibilte = () => {
    // Update
    const [ update, setUpdate ] = useState(false)

    // Getting all of the restrict  ed profiles for pages
    const [ pageRestrictedProfiles, setPageRestrictedProfiles ] = useState({})
    useEffect(() => {
        _routes.map((route, index) => {
            getProfilsRestrictedForPage(route.path).then((data) => {
                setPageRestrictedProfiles(prev => ({...prev, [route.path]: data.data}))
                console.log(pageRestrictedProfiles)
            })
        })
    }, [update])

    
    // Profils
    const [ profils, setProfils ] = useState([])
    useEffect(() => {
        setProfils([])
        getAllProfils().then((data) => {
            data.map((profil, index) => {
                setProfils(prev => [...prev, {label: profil.nom, value: profil.idProfil}])
            })
        })
    }, [])

    // Profils selection modal
    const [ visibilityProfilSelectionModal, setVisibilityProfilSelectionModal ] = useState(false)
    const [ selectedRoute, setSelectedRoute ] = useState("")
    const profilSelectionRef = useRef()
    const showProfilSelectionModal = (route) => {
        setVisibilityProfilSelectionModal(true)
        setSelectedRoute(route);
    }

    const handleAddRestrictionButton = (e) => {
        addProfilRestrictionToPage(profilSelectionRef.current.value, selectedRoute).then(() => {
            setVisibilityProfilSelectionModal(false)
            setUpdate(!update)
        })
    }    

  return (
    <CCard>
        <CCardHeader>
            <CCardTitle>Accessibilite des pages</CCardTitle>
        </CCardHeader>

        <CCardBody>
            <CRow>
                <CCol>
                    <CTable>
                        <CTableHead>
                            <CTableRow>
                                <CTableHeaderCell scope='col'>Page name</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Page</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Profils non autoriser</CTableHeaderCell>
                            </CTableRow>
                        </CTableHead>

                        <CTableBody>
                            { _routes.map((route, index) => {
                                return (
                                    <CTableRow key={index}>
                                        <CTableDataCell>
                                            <p className='fw-bolder'>
                                                { route.name }
                                            </p>
                                        </CTableDataCell>

                                        <CTableDataCell>
                                            <p className='text-medium-emphasis'>
                                                { route.path }
                                            </p>
                                        </CTableDataCell>

                                        <CTableDataCell>
                                            <div className='d-flex gap-2 flex-wrap align-items-center' style={{ width: '400px' }} >
                                                { pageRestrictedProfiles[route.path] && pageRestrictedProfiles[route.path].map((pageRestricted, index) => {
                                                    console.log(pageRestricted)
                                                    return (
                                                        <p className='badge text-bg-primary text-wrap m-0' key={pageRestricted.nom} >
                                                            { pageRestricted.nom }
                                                        </p>
                                                    )
                                                })}
                                                <CButton
                                                    onClick={(e) => {
                                                        showProfilSelectionModal(route.path)
                                                    }}
                                                >
                                                    <CIcon icon={cilPlus} />
                                                </CButton>
                                            </div>
                                        </CTableDataCell>
                                    </CTableRow>
                                )
                            }) }
                        </CTableBody>
                    </CTable>
                </CCol>
            </CRow>
        </CCardBody>

        <CModal
            visible = {visibilityProfilSelectionModal}
            onClose={() => {
                setVisibilityProfilSelectionModal(false)
            }}
        >
            <CModalHeader>
                <CModalTitle>
                    Profiles
                </CModalTitle>
            </CModalHeader>

            <CModalBody>
                <CRow>
                    <CCol>
                        <CFormSelect ref={ profilSelectionRef } floatingLabel="Profils" options={profils} />
                    </CCol>
                </CRow>
            </CModalBody>

            <CModalFooter>
                <CButton onClick={handleAddRestrictionButton}>
                    Ajouter restriction
                </CButton>
            </CModalFooter>
        </CModal>
    </CCard>
  )
}

export default Accessibilte