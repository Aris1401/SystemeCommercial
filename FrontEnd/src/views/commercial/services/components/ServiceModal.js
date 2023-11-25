import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react'
import { getDetailsService } from '../../besoinAchat/components/ApercuBesoinAchat';
import { CButton, CCol, CFormInput, CModal, CModalContent, CModalFooter, CModalHeader, CModalTitle, CRow } from '@coreui/react';
import { makeRequest } from 'src/Api';

export const ajouterService = (service) => {
    return new Promise((resolve, reject) => {
		makeRequest({
			url: `services`,
            values: service,
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

export const updateService = (service) => {
    return new Promise((resolve, reject) => {
		makeRequest({
			url: `services/update`,
            values: service,
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

const ServiceModal = forwardRef((props, ref) => {
    // Visibility
    const [visibility, setVisibility] = useState(false)
    const [idService, setIdService] = useState(-1)

    useImperativeHandle((ref), () => ({
        showModal: (idService) => {
            setVisibility(true)
            if (idService) 
                setIdService(idService)
            else setIdService(-1)
        },
        hideModal: () => {
            setVisibility(false)
        }
    }))

    const { isUpdate, onSubmit } = props;
    
    // Nom ref
    const [ nom, setNom ] = useState("")

    // Current service
    const [service, setService] = useState()
    useEffect(() => {
        if (isUpdate) {
            getDetailsService(idService).then((data) => {
                setService(data)
                setNom(data.nom)
            })
        } else setNom("")
    }, [idService, isUpdate])


    const handleModalSubmit = () => {
        if (isUpdate) {
            let newService = service
            newService.nom = nom
            updateService(newService).then(() => {
                setVisibility(false)
                onSubmit()
            })
        } else {
            let newService = {nom: nom}
            ajouterService(newService).then(() => {
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
                    {isUpdate ? "Modifier service" : "Ajouter service"}
                </CModalTitle>
            </CModalHeader>

            <CModalContent>
                <CRow>
                    <CCol>
                        <CFormInput type='text' floatingLabel="Nom service" value={ nom } onChange={(e) => { setNom(e.target.value)}} />
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

ServiceModal.displayName = "ServiceModal"
export default ServiceModal